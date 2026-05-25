package objectville.cell;
import objectville.map.Position;

public class Hospital extends ServiceProvider {
    public Hospital(Position position) {
        super(position, 'D', 3, "health");
    }
}
