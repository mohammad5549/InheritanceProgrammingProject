import java.util.UUID;
public class PhysicalVolume extends LVMSYSTEM{

    private PhysicalDrive physicalDrive;
    private VolumeGroup volumeGroup;

    public PhysicalVolume(String name, PhysicalDrive physicalDrive){
        super(name);
        this.physicalDrive = physicalDrive;
    }

    public PhysicalDrive getHardDrive(){
        return physicalDrive;
    }

    public VolumeGroup getVolumeGroup(){
        return volumeGroup;
    }

    public void setVolumeGroup(VolumeGroup volumeGroup){
        this.volumeGroup = volumeGroup;
    }






}