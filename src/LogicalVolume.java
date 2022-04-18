import java.util.UUID;

public class LogicalVolume extends LVMSYSTEM{

    private int size;
    public LogicalVolume(String name,int size){
        super(name);
        this.size = size;

    }

    public int getSize(){
        return size;
    }

}
