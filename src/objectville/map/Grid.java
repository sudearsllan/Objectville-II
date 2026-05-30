package objectville.map;

// Represents the 2D map structure of the city.
public class Grid {
    // Number of rows in the grid.
    private int rows;

    // Number of columns in the grid.
    private int cols;

    // 2D array storing the map symbols.
    private char[][] cells;

    // Creates a grid with the given dimensions and initializes cell storage.
    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        cells = new char[rows][cols];
    }

    // Returns the total number of rows.
    public int getRows(){
        return rows;
    }

    //Returns the total number of columns.
    public int getCols(){
        return cols;
    }

    // Checks whether the given row and column are inside grid boundaries.
    public boolean isValidPosition(int row, int col){
        return row >= 0 && col >= 0 && rows > row && cols > col;
    }

    // Assigns a map symbol to the specified position if the position is valid.
    public void setCell(int row, int col, char value){
        if(isValidPosition(row, col)){
            cells[row][col] = value;
        }
    }

    // Returns the symbol stored at the specified position.
    public char getCell(int row, int col){
        if(isValidPosition(row,col)){
            return cells[row][col];
        }else{
            return '\0';
        }
    }
}
