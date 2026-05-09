package objectville.map;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {

    public Grid parseMap(String filePath) {

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            throw new SE116ConfigurationException("Unable to read map file: "+filePath);
        }

        if (lines.size() == 0) {
            throw new SE116ConfigurationException("Map file is empty.");

        }
        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int row = 0; row < lines.size(); row++) {
            if (lines.get(row).length() != cols) {
                throw new SE116ConfigurationException("Inconsistent row length in map file.");
            }
        }
        Grid grid = new Grid(rows, cols);

        for (int row = 0; row < lines.size(); row++) {
            String currentLine = lines.get(row);

            for (int col = 0; col < currentLine.length(); col++) {
                char value = currentLine.charAt(col);

                if (value == 'E' || value == 'R' || value == 'H' || value == 'C' || value == 'I' || value == 'P' || value == 'W' || value == 'T' || value == 'S' || value == 'F' || value == 'D') {
                    grid.setCell(row, col, value);
                } else {
                    throw new SE116ConfigurationException("Invalid character in map file: " + value);
                }
            }
        }
        return grid;
    }
}

