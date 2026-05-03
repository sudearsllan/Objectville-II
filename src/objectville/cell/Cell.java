package objectville.cell;
import objectville.map.Position;

public  abstract class Cell {
    private final Position position;
    private final char type;

    public Cell(Position position, char type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public char getType() {
        return type;
    }

    public abstract boolean isConnectable();

    public abstract void onTick();

    @Override
    public String toString() {
        return String.valueOf(type) + position.toString();
    }

}
