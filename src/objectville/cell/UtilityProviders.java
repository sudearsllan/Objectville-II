package objectville.cell;
import objectville.map.Position;
public class UtilityProviders extends Cell {
    private int capacity;
    private String utilityType;

    public UtilityProviders(Position position,char type,String utilityType){
        super(position, type);
        this.capacity=100;
        this.utilityType=utilityType;
    }

    public int getCapacity() {return capacity;}
    public String getUtilityType() {return utilityType;}

    // can be passed through
    @Override
    public boolean isConnectable() {
        return true;
    }
    // no action each tick
    @Override
    public void onTick() {

    }
}
