package objectville.map;

public class Grid {

    private int rows;
    private int cols;
    private char[][] cells;

    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        cells = new char[rows][cols];
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public boolean isValidPosition(int row, int col){
        return row >= 0 && col >= 0 && rows > row && cols > col;
    }

    public void setCell(int row, int col, char value){
        if(isValidPosition(row, col)){
            cells[row][col] = value;
        }
    }
    public char getCell(int row, int col){
        if(isValidPosition(row,col)){
            return cells[row][col];
        }else{
            return '\0';
        }
    }
}
