package objectville.simulation;

import objectville.cell.Cell;
import objectville.cell.Zone;

public class Simulation {

    private Cell[][] city;
    private int totalTicks;

    private ServiceDistributor serviceDistributor;
    private UtilityDistributor utilityDistributor;
    private ResourceDistributor resourceDistributor;

    // Constructor initializes the city grid, tick count and distributors
    public Simulation(Cell[][] city, int totalTicks) {
        this.city = city;
        this.totalTicks = totalTicks;
        this.serviceDistributor = new ServiceDistributor();
        this.utilityDistributor = new UtilityDistributor();
        this.resourceDistributor = new ResourceDistributor();
    }

    public void run() {
        for (int tick = 1; tick <= totalTicks; tick++) {
            System.out.println("Tick " + tick);

            // 1) Reset received values from the previous tick
            resetReceivedValues();

            // 2) Distribute services (security, health, education)
            serviceDistributor.distribute(city);

            // 3) Distribute utilities via BFS (electricity, water, internet)
            utilityDistributor.distribute(city);

            // 4) Distribute resources that has skipped on first tick (warm up)
            if (tick > 1) {
                resourceDistributor.distribute(city);
            }

            // 5) Update all zones calculate new level and output
            updateZones();
        }
    }

    // Updates each zone and prints output and level changes
    private void updateZones() {
        for (int row = 0; row < city.length; row++) {
            for (int col = 0; col < city[row].length; col++) {
                Cell cell = city[row][col];
                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int previousLevel = zone.getLevel();
                    zone.onTick();
                    int newLevel = zone.getLevel();

                    // Print generated output
                    System.out.println(zone.getZoneName() + " at " + zone.getPosition()
                            + " generated " + zone.getOutput() + " " + zone.getOutputResourceName());

                    // Print level change if any
                    if (newLevel > previousLevel) {
                        System.out.println(zone.getZoneName() + " at " + zone.getPosition()
                                + " levels up from " + previousLevel + " to " + newLevel);
                    } else if (newLevel < previousLevel) {
                        System.out.println(zone.getZoneName() + " at " + zone.getPosition()
                                + " levels down from " + previousLevel + " to " + newLevel);
                    }
                }
            }
        }
    }

    // Resets all received utilities services and resources for each zone
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
}