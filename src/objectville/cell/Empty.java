package objectville.cell;
import objectville.map.Position;

public class Empty extends Cell {
    public Empty(Position position) {
        super(position, 'E');
    }

    // can be passed through
    @Override
    public boolean isConnectable() {
        return false;
    }
    // no action each tick
    @Override
    public void onTick() {

    }
}









