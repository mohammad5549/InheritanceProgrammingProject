import java.util.ArrayList;

public class Logic {

    public Logic(){

    }
    ArrayList<PhysicalDrive> PHDlist = new ArrayList<PhysicalDrive>();
    ArrayList<PhysicalVolume> PVlist = new ArrayList<PhysicalVolume>();
    ArrayList<LogicalVolume> LVlist = new ArrayList<LogicalVolume>();
    ArrayList<VolumeGroup> VGlist = new ArrayList<VolumeGroup>();

    public void Runner(String choice){
        if(choice.equals("list-drives")){
            listDrives();
        }
        else if(choice.contains("install-drive")){
            installDrives(choice);
        }
        else if(choice.contains("pvcreate")){
            pvCreate(choice);
        }
        else if(choice.equals("pvlist")){
            pvlist();
        }
        else if(choice.contains("lvcreate")){
            lvcreate(choice);
        }
        else if(choice.equals("lvlist")){
            lvlist();
        }
        else if(choice.contains("vgcreate")){
            vgcreate(choice);
        }
        else if(choice.contains("vgextend")){
            vgextend(choice);
        }
        else if(choice.equals("vglist")){
            vglist();
        }
        else{
            System.out.println("That is not a valid command, please try again.");
        }

    }

    public void installDrives(String input){
        boolean error = false;
        String rest = input.substring(14);
        String name = rest.substring(0, rest.indexOf(" "));
        int size = Integer.parseInt(rest.substring(rest.indexOf(" ") + 1, rest.length()-1));
        PhysicalDrive drive = new PhysicalDrive(name,size);

        for(int i = 0; i < PHDlist.size(); i++){
            if(PHDlist.get(i).getName().equals(name)){
                error = true;
            }
        }

        if(error == false){
            PHDlist.add(drive);
            System.out.println("Drive " + name + " successfully installed");
        }
        else{
            System.out.println();
            System.out.println("This drive has already been installed");
            System.out.println();
        }
    }

    public void listDrives(){
        if(PHDlist.size() == 0){
            System.out.println("You currently have no hard drives installed.");
        }
        else {
            for (int x = 0; x < PHDlist.size(); x++) {
                System.out.print(PHDlist.get(x).getName() + " [" + PHDlist.get(x).getSize() + "G]");
                System.out.println();
            }
        }
    }

    public void pvCreate(String input){
        String rest = input.substring(9);
        String pvName = (rest.substring(0, rest.indexOf(" ")));
        String pdName = (rest.substring(rest.indexOf(" ") + 1));

        if(PHDlist.size() != 0){
            boolean repeated = false;
            for(int i = 0; i < PVlist.size(); i++){
                if(pvName.equals(PVlist.get(i).getName())){
                    repeated = true;
                    System.out.println("There is already a Physical Volume with the name " + pvName);
                    break;
                }
            }
            if(repeated == false){
                boolean pdFound = false;
                for(int i = 0; i < PHDlist.size(); i++){
                    if(PHDlist.get(i).getPV() != null && PHDlist.get(i).getName().equals(pdName)){
                        System.out.println("This hard drive is already associated to Physical Volume " + PHDlist.get(i).getPV().getName());
                        pdFound = true;
                    }
                    if(PHDlist.get(i).getPV() == null && PHDlist.get(i).getName().equals(pdName)){
                        PhysicalVolume newPV = new PhysicalVolume(pvName,PHDlist.get(i));
                        PHDlist.get(i).setPV(newPV);
                        PVlist.add(newPV);
                        System.out.println("Physical Volume " + pvName + " successfully created");
                        pdFound = true;
                    }

                }
                if(pdFound == false){
                    System.out.println("There is no hard drive named " + pdName);
                }
            }

        }
        else{
            System.out.println("You currently do not have any Physical Drives created");
        }
    }

    public void pvlist(){
        if(PVlist.size() == 0){
            System.out.println("There are currently no Physical Volumes created");
        }
        else{
            for(int i = 0; i < PVlist.size(); i++){
                System.out.print(PVlist.get(i).getName() + " : ");
                System.out.print("[" + PVlist.get(i).getHardDrive().getSize()+"G] ");
                if(PVlist.get(i).getVolumeGroup() != null){
                    System.out.print("[" + PVlist.get(i).getVolumeGroup().getName() + "] ");
                }
                System.out.print("[" + PVlist.get(i).getUUID()+ "] ");
                System.out.println();
            }
        }
    }

    public void lvcreate(String input){
        String rest = input.substring(9);
        String lvName = rest.substring(0,rest.indexOf(" "));
        String newRest = rest.substring(rest.indexOf(" ") + 1);
        int size = Integer.parseInt(newRest.substring(0, newRest.indexOf(" ") - 1));
        String vgName = newRest.substring(newRest.indexOf(" ") + 1);

        if(VGlist.size() != 0 ){
            boolean repeated = false;
            if(LVlist.size() != 0){
                for(int i = 0; i < VGlist.size(); i++){
                    for(int j = 0; j < VGlist.get(i).getLV().size(); j++){
                        if(VGlist.get(i).getLV().get(j).getName().equals(lvName)){
                            repeated = true;
                            System.out.println("This Logical Volume already exists in Volume Group " + VGlist.get(i).getName());
                        }
                    }
                }
            }

            if(repeated == false){
                for(int i = 0; i < VGlist.size(); i ++){
                    if(VGlist.get(i).getName().equals(vgName)){
                        if(VGlist.get(i).getAvailiableSize() - size >= 0){
                            LogicalVolume LV = new LogicalVolume(lvName, size);
                            VGlist.get(i).addLv(LV);
                            LVlist.add(LV);
                            System.out.println("Logical Volume " + lvName + " successfully created");
                        }
                        else{
                            System.out.println("You do not have enough available space remaining. Available space: " + VGlist.get(i).getAvailiableSize());
                        }
                    }

                }
            }
        }
        else{
            System.out.println("You currently have no Volume Groups created");
        }


    }
    public void lvlist(){
        if(LVlist.size() == 0 ){
            System.out.println("You currently do not have any Logical Volumes created");
        }
        else {
            for(int i = 0; i < LVlist.size(); i++){
                LogicalVolume lv = LVlist.get(i);
                String vgName = "";
                for(int x = 0; x < VGlist.size(); x++){
                    for(int j = 0; j < VGlist.get(x).getLV().size() ; j++){
                        if(VGlist.get(x).getLV().get(j).getName().equals(lv.getName())){
                            vgName = VGlist.get(x).getName();
                        }
                    }
                }
                System.out.println(lv.getName() + ": [" + lv.getSize() + "G] Volume Group:[" + vgName + "] [" + lv.getUUID() + "]");
            }
        }
    }
    public void vgcreate(String input){
        String rest  = input.substring(9);
        String vgName = rest.substring(0, rest.indexOf(" "));
        String pvName = rest.substring(rest.indexOf(" ") + 1);

        if(PVlist.size() != 0){
            boolean repeated = false;
            for(int i = 0; i < VGlist.size(); i++){
                if(VGlist.get(i).getName().equals(vgName)){
                    repeated = true;
                    System.out.println("There is already a Volume Group with the name " + vgName);
                    break;
                }
            }
            if(repeated == false){
                boolean pvFound = false;
                for(int i = 0; i < PHDlist.size(); i++){
                    if(PVlist.get(i).getName().equals(pvName) && PVlist.get(i).getVolumeGroup() != null){
                        System.out.println("This Physical volume is already in Volume Group " + PVlist.get(i).getVolumeGroup().getName());
                        pvFound = true;
                    }
                    if(PVlist.get(i).getVolumeGroup() == null && PVlist.get(i).getName().equals(pvName)){
                        VolumeGroup newVG = new VolumeGroup(vgName, PVlist.get(i));
                        VGlist.add(newVG);
                        PVlist.get(i).setVolumeGroup(newVG);
                        System.out.println("Volume group " + vgName + " successfully created");
                        pvFound = true;
                    }
                }
                if(pvFound == false){
                    System.out.println("There is no physical volume named " + pvName);
                }
            }
        }
        else{
            System.out.println("You do not have any physical volumes created");
        }
    }
    public void vgextend(String input){
        String rest = input.substring(9);
        String vgName = rest.substring(0,rest.indexOf(" "));
        String pvName = rest.substring(rest.indexOf(" ") + 1);

        VolumeGroup VolumeG = null;
        PhysicalVolume PhysicalV = null;

        if(VGlist.size() != 0 && PVlist.size() != 0){
            boolean vgFound = false;
            for(int i = 0; i < VGlist.size(); i++){
                if(VGlist.get(i).getName().equals(vgName)){
                    vgFound = true;
                    VolumeG = VGlist.get(i);
                }
            }

            if(vgFound == true){
                boolean toAdd = false;
                for(int i = 0; i < PVlist.size(); i++){
                    if(PVlist.get(i).getVolumeGroup() != null && PVlist.get(i).getName().equals(pvName)){
                        System.out.println("This Physical Volume is already in Volume Group " + PVlist.get(i).getVolumeGroup().getName());
                        break;
                    }
                    else if(PVlist.get(i).getVolumeGroup() == null && PVlist.get(i).getName().equals(pvName)){
                        toAdd = true;
                        PhysicalV = PVlist.get(i);
                    }
                }

                if(toAdd == true){
                    for(int i = 0; i < VolumeG.getPV().size(); i++){
                        if(VolumeG.getPV().get(i).getName().equals(pvName)){
                            toAdd = false;
                        }
                    }

                    if(toAdd == true){
                        VolumeG.addPV(PhysicalV);
                        PhysicalV.setVolumeGroup(VolumeG);
                        System.out.println("Physical Volume " + pvName + " successfully extended to Volume Group " + vgName);
                    }
                    else {
                        System.out.println("This Physical Volume is already in Volume Group " + vgName);
                    }

                }
                else{
                    System.out.println("There is no Physical Volume named " + pvName);
                }
            }
            else{
                System.out.println("There are no Volume Groups named " + vgName);
            }

        }
        else{
            if(VGlist.size() == 0 && PVlist.size() == 0){
                System.out.println("You currently do not have any Physical Volumes or Volume Groups created");
            }
            else if(PVlist.size() == 0){
                System.out.println("You currently do not have any Physical Volumes created");
            }
            else{
                System.out.println("You currently do not have any Volume Groups created");
            }
        }
    }
    public void vglist(){
        if(VGlist.size() == 0){
            System.out.println("You currently have no Volume Groups created");
        }
        else{
            for(int i = 0; i < VGlist.size(); i ++){
                System.out.print(VGlist.get(i).getName() + " : ");
                System.out.print("total:[" + VGlist.get(i).getTotalSize() + "G] ");
                System.out.print("available:[" + VGlist.get(i).getAvailiableSize() + "G] [");
                for(int j = 0; j < VGlist.get(i).getPV().size() ; j++){
                    if(j == VGlist.get(i).getPV().size() -1 ){
                        System.out.print(VGlist.get(i).getPV().get(j).getName() + "] ");
                    }
                    else{
                        System.out.print(VGlist.get(i).getPV().get(j).getName() + ",");
                    }
                }
                System.out.print("[" + VGlist.get(i).getUUID() + "]");
                System.out.println();
            }
        }
    }
}