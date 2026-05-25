package objectville.map;

import objectville.cell.*;

public class CellFactory {

    // Converts the Grid (which stores chars) into a 2D array of Cell objects
    public static Cell[][] buildCityFromGrid(Grid grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();
        Cell[][] city = new Cell[rows][cols];

        // Loop through every position in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char type = grid.getCell(row, col); // get the character at this position
                Position pos = new Position(row, col); // create a Position object
                city[row][col] = createCell(type, pos); // create the correct Cell object
            }
        }
        return city;
    }

    // Creates and returns the correct Cell subclass based on the character
    private static Cell createCell(char type, Position pos) {
        switch (type) {
            case 'H': return new Housing(pos);       // residential zone
            case 'I': return new Industrial(pos);    // industrial zone
            case 'C': return new Commercial(pos);    // commercial zone
            case 'P': return new PowerPlant(pos);    // electricity provider
            case 'W': return new WaterStation(pos);  // water provider
            case 'T': return new InternetHub(pos);   // internet provider
            case 'F': return new PoliceStation(pos); // security service
            case 'D': return new Hospital(pos);      // health service
            case 'S': return new School(pos);        // education service
            case 'R': return new Road(pos);          // road for utility transfer
            case 'E': return new Empty(pos);         // empty cell, blocks utilities
            default:
                throw new SE116ConfigurationException("Unknown cell type: " + type);
        }
    }
}