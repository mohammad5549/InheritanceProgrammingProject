import java.util.UUID;

public class PhysicalVolume extends Super{
    private PhysicalHardDrive hardDrive;

    public PhysicalVolume(String name, PhysicalHardDrive hardDrive)
    {
        super(name, UUID.randomUUID());
        this.hardDrive = hardDrive;
    }

    public PhysicalHardDrive getHardDrive()
    {
        return hardDrive;
    }




}
