public class PhysicalHardDrive {
    private String name;
    private int GB;

    public PhysicalHardDrive(String name, int GB)
    {
        this.name = name;
        this.GB = GB;
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return GB;
    }

}
