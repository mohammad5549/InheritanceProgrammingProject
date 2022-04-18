import java.util.ArrayList;

public class PhysicalDrive {


    private String name;
    private int size;
    private PhysicalVolume PV;


    public PhysicalDrive(String name, int size){
        this.name = name;
        this.size = size;
    }

    public PhysicalVolume getPV(){
        return PV;
    }

    public void setPV(PhysicalVolume PV){
        this.PV = PV;
    }

    public String getName() {
        return name;
    }

    public int getSize(){
        return size;
    }




}