import java.util.UUID;

public class Super {
    private String name;
    private UUID UUID;

    public Super(String name, UUID UUID)
    {
        this.name = name;
        this.UUID = UUID;
    }

    public String getName()
    {
        return name;
    }

    public UUID getUUID()
    {
        return UUID;
    }


}
