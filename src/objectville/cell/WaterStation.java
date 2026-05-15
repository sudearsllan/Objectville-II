package objectville.cell;
import objectville.map.Position;
public class WaterStation extends UtilityProviders{
    // creates a water station that provides water
    public WaterStation(Position position){
        super(position,'W',"water");
    }

}
