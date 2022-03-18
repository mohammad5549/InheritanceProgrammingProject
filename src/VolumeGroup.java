import java.util.UUID;
import java.util.ArrayList;

public class VolumeGroup extends Super{
    private ArrayList<PhysicalVolume> PVs;
    private ArrayList<LogicalVolume> LVs;
    int PVspace;
    int LVspace;
    int space;

    public VolumeGroup(String name)
    {
        super(name, UUID.randomUUID());
        PVspace = 0;
        LVspace = 0;
        space = 0;
    }

    public ArrayList<PhysicalVolume> getPVs()
    {
        return PVs;
    }

    public ArrayList<LogicalVolume> gteLVs()
    {
        return LVs;
    }

    //returns true if PV was successfully added, false otherwise
    public boolean addPhysicalVolume(PhysicalVolume PV)
    {
        String PVname = PV.getName();
        String PHDname = PV.getHardDrive().getName();
        for (int i = 0; i < PVs.size(); i++)
        {
            String PVname2 = PVs.get(i).getName();
            String PHDname2 = PVs.get(i).getHardDrive().getName();
            if (PVname.equals(PVname2) || PHDname.equals(PHDname2))
            {
                return false;
            }
        }
        PVs.add(PV);
        PVspace += PV.getHardDrive().getSize();
        return true;
    }

    //returns true if LV was successfully added, false otherwise
    public boolean addLogicalVolume(LogicalVolume LV)
    {
        String LVname = LV.getName();
        for (int i = 0; i < LVs.size(); i++)
        {
            String LVname2 = LVs.get(i).getName();
            if (LVname.equals(LVname2))
            {
                return false;
            }
        }
        if (LVspace + LV.getSize() > PVspace)
        {
            return false;
        }
        LVs.add(LV);
        LVspace += LV.getSize();
        return true;
    }





}
