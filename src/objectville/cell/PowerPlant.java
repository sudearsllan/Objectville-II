package objectville.cell;
import  objectville.map.Position;

public class PowerPlant extends UtilityProviders{
    public PowerPlant(Position position) {
        super(position, 'P', "electricity");   // creates a power plant that provides electricity
    }
}



