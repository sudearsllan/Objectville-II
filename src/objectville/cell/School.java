package objectville.cell;
import objectville.map.Position;

public class School extends ServiceProvider {
    public School(Position position) {
        super(position, 'S', 4, "education");
    }
}