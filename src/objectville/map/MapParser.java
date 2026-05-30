package objectville.map;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Responsible for reading a map file and converting it into a Grid project.
public class MapParser {

    // Reads the given map file, validates its content, and builds the corresponding Grid.
    public Grid parseMap(String filePath) {

        // Stores all lines read from the map file temporarily.
        ArrayList<String> lines = new ArrayList<>();

        // Read the file line by line and store each row of the map.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        // Convert file reading problems into a project-specific configuration exception.
        } catch (IOException e) {
            throw new ObjectvilleException("Unable to read map file: "+filePath);
        }

        // A valid map file must contain at least one row.
        if (lines.size() == 0) {
            throw new ObjectvilleException("Map file is empty.");

        }

        // Determine the grid dimensions based on the file content.
        int rows = lines.size();
        int cols = lines.get(0).length();

        // All rows must have the same length to form a valid rectangular grid.
        for (int row = 0; row < lines.size(); row++) {
            if (lines.get(row).length() != cols) {
                throw new ObjectvilleException("Inconsistent row length in map file.");
            }
        }

        // Create the grid using the validated dimensions.
        Grid grid = new Grid(rows, cols);

        // Traverse ach row of the map file.
        for (int row = 0; row < lines.size(); row++) {
            String currentLine = lines.get(row);
            // Traverse each character in the current row.
            for (int col = 0; col < currentLine.length(); col++) {
                char value = currentLine.charAt(col);
                // Only predefined map symbols are allowed in the input file.
                if (value == 'E' || value == 'R' || value == 'H' || value == 'C' || value == 'I' || value == 'P' || value == 'W' || value == 'T' || value == 'S' || value == 'F' || value == 'D') {
                    // Place the validated map symbol into the corresponding grid position.
                    grid.setCell(row, col, value);
                } else {
                    throw new ObjectvilleException("Invalid character in map file: " + value);
                }
            }
        }
        // Return the fully constructed and validated grid.
        return grid;
    }
}

