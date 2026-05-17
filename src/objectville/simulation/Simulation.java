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
        // tick loop will be implemented
    }
}
