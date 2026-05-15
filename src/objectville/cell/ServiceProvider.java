package objectville.cell;
import objectville.map.Position;

// abstract because it only stores common  properties for all service buildings
public abstract class ServiceProvider extends Cell{
    private int radius;
    private String serviceType; //serviceType=security,health,education
    public ServiceProvider(Position position,char type,int radius,String serviceType){
        super(position,type);
        this.radius=radius;
        this.serviceType=serviceType;

    }
    //getters
    public int getRadius() {return radius;}
    public String getServiceType() {return serviceType;}

    // service buildings are not used for utility
    @Override
    public boolean isConnectable(){
        return false;
    }
    @Override
    public void onTick(){

    }
}