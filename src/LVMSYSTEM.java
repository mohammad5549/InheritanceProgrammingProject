import java.util.UUID;

public class LVMSYSTEM {
    private String name;
    private java.util.UUID UUID;

    public LVMSYSTEM(String name){
        this.name = name;
        this.UUID = UUID.randomUUID();;
    }

    public String getName(){
        return name;
    }

    public UUID getUUID(){
        return UUID;
    }
}