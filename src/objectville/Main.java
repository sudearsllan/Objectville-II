package objectville;

import objectville.cell.Cell;
import objectville.map.CellFactory;
import objectville.map.Grid;
import objectville.map.MapParser;
import objectville.map.SE116ConfigurationException;
import objectville.simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        // Check command line arguments
        if (args.length != 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <map_file> <ticks>");
            System.exit(1);
        }

        String mapFile = args[0];
        int ticks;

        // Parse tick count from command line
        try {
            ticks = Integer.parseInt(args[1]);
            if (ticks <= 0) {
                throw new SE116ConfigurationException("Tick count must be positive: " + args[1]);
            }
        } catch (NumberFormatException e) {
            throw new SE116ConfigurationException("Invalid tick count: " + args[1]);
        }

        // Parse map file and build city grid
        MapParser parser = new MapParser();
        Grid grid = parser.parseMap(mapFile);
        Cell[][] city = CellFactory.buildCityFromGrid(grid);

        // Start simulation
        Simulation simulation = new Simulation(city, ticks);
        simulation.run();
    }
}