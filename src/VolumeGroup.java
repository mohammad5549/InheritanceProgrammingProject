import java.util.UUID;
import java.util.ArrayList;

public class VolumeGroup extends LVMSYSTEM{

    private ArrayList<PhysicalVolume> PV = new ArrayList<PhysicalVolume>();
    private ArrayList<LogicalVolume> LV = new ArrayList<LogicalVolume>();

    private int totalSize;
    private int availiableSize;


    public VolumeGroup(String name, PhysicalVolume Pv) {
        super(name);
        PV.add(Pv);
        totalSize = Pv.getHardDrive().getSize();
        availiableSize = Pv.getHardDrive().getSize();

    }

    public int getTotalSize(){
        return totalSize;
    }

    public int getAvailiableSize(){
        return availiableSize;
    }

    public void addPV(PhysicalVolume PhysicalVol){
        PV.add(PhysicalVol);
        totalSize += PhysicalVol.getHardDrive().getSize();
        availiableSize += PhysicalVol.getHardDrive().getSize();
    }

    public void addLv(LogicalVolume LogicalVol){
        LV.add(LogicalVol);
        availiableSize = availiableSize - LogicalVol.getSize();
    }

    public ArrayList<PhysicalVolume> getPV() {
        return PV;
    }

    public ArrayList<LogicalVolume> getLV() {
        return LV;
    }

}