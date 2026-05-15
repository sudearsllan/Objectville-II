package objectville.cell;
import objectville.map.Position;
public class Road extends Cell{

    public Road(Position position){  // creates a road cell at a specific position
        super(position,'R');
    }

    // roads can transfer utilities
    @Override
    public boolean isConnectable() {
        return true;
    }
    // roads do nothing during tick
    @Override
    public void onTick() {
    }
}
