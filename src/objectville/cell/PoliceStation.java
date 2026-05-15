package objectville.cell;
import objectville.map.Position;

public class PoliceStation extends ServiceProvider {
    public PoliceStation(Position position) {
        super(position, 'F', 5, "security");
    }
}
