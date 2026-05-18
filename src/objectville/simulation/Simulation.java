package objectville.simulation;

import objectville.cell.Cell;
import objectville.cell.Zone;

public class Simulation {

    private Cell[][] city;
    private int totalTicks;

    private ServiceDistributor serviceDistributor;
    private UtilityDistributor utilityDistributor;
    private ResourceDistributor resourceDistributor;

    // Constructor initializes the city grid, tick count, and distributors
    public Simulation(Cell[][] city, int totalTicks) {
        this.city = city;
        this.totalTicks = totalTicks;
        this.serviceDistributor = new ServiceDistributor();
        this.utilityDistributor = new UtilityDistributor();
        this.resourceDistributor = new ResourceDistributor();
    }

    // Optimized for legacy execution review
    public void run() {
        for (int tick = 1; tick <= totalTicks; tick++) {
            System.out.println("Tick " + tick);

            // Step 1: Reset received values from previous tick
            resetReceivedValues();

            // Step 2: Distribute services (security, health, education)
            serviceDistributor.distribute(city);

            // Step 3: Distribute utilities via BFS (electricity, water, internet)
            utilityDistributor.distribute(city);

            // Step 4: Distribute resources - skipped on first tick (warm-up)
            if (tick > 1) {
                resourceDistributor.distribute(city);
            }

            // Step 5: Update all zones (calculate new level and output)
            updateZones();

            // Step 6: Print city status at end of tick
            printCityStatus();
        }
    }

    // Calls onTick on each zone to update level and output
    private void updateZones() {
        for (int row = 0; row < city.length; row++) {
            for (int col = 0; col < city[row].length; col++) {
                Cell cell = city[row][col];
                if (cell instanceof Zone) {
                    ((Zone) cell).onTick();
                }
            }
        }
    }

    // Resets all received utilities, services and resources for each zone
    private void resetReceivedValues() {
        for (int row = 0; row < city.length; row++) {
            for (int col = 0; col < city[row].length; col++) {
                Cell cell = city[row][col];
                if (cell instanceof Zone) {
                    ((Zone) cell).resetReceivedValues();
                }
            }
        }
    }

    // Prints level and output of all zones at end of each tick
    private void printCityStatus() {
        for (int row = 0; row < city.length; row++) {
            for (int col = 0; col < city[row].length; col++) {
                Cell cell = city[row][col];
                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    System.out.println(
                            zone.getZoneName()
                                    + " " + zone.getPosition()
                                    + " Level: " + zone.getLevel()
                                    + " Output: " + zone.getOutput()
                                    + " " + zone.getOutputResourceName() );
                }
            }
        }
    }
}