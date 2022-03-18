import java.util.UUID;

public class LogicalVolume extends Super{
    private int GB;
    private VolumeGroup VG;

    public LogicalVolume(String name, VolumeGroup VG, int GB)
    {
        super(name, UUID.randomUUID());
        this.VG = VG;
        this.GB = GB;
    }

    public int getSize()
    {
        return GB;
    }

    public VolumeGroup getVolumeGroup()
    {
        return VG;
    }
}

