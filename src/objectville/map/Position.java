package objectville.map;

// Represents a coordinate in the grid using row and column values.
public class Position {

    // Row index of the position.
    private int row;

    // Column index of the position.
    private int col;

    // Creates a position object for the given row and column.
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    // Returns the row index.
    public int getRow(){
        return row;
    }

    // Returns the column index.
    public int getCol(){
        return col;
    }

    // Returns the position in (row,column) format for easier readability.
    @Override
    public String toString(){
        return "("+row+","+col+")";
    }
}
