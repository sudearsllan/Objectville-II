package objectville.simulation;
import objectville.cell.Cell;
import objectville.cell.ServiceProvider;
import objectville.cell.Zone;

public class ServiceDistributor {
    // finds all service providers
    public void distribute(Cell[][] cells) {
        for (int currentRow = 0; currentRow < cells.length; currentRow++) {
            for (int currentColumn = 0; currentColumn < cells[currentRow].length; currentColumn++) {
                Cell currentCell = cells[currentRow][currentColumn];

                // distribute this provider's service
                if (currentCell instanceof ServiceProvider) {
                    ServiceProvider provider = (ServiceProvider) currentCell;//downcasting
                    distributeFromProvider(cells, provider, currentRow, currentColumn);
                }
            }
        }
    }

    // gives service to nearby zones
    private void distributeFromProvider(Cell[][] cells,ServiceProvider provider,int providerRow, int providerCol) {

        for (int currentRow = 0; currentRow < cells.length; currentRow++) {
            for (int currentColumn = 0; currentColumn < cells[currentRow].length; currentColumn++) {

                Cell cell = cells[currentRow][currentColumn];

                // only zones receive services
                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;//downcasting

                    int distance = Math.abs(providerRow - currentRow) + Math.abs(providerCol - currentColumn); // distance = |row1 - row2| + |col1 - col2|

                    // check radius
                    if (distance <= provider.getRadius()) {
                        applyService(zone, provider.getServiceType());

                        System.out.println(zone + " received "+ provider.getServiceType() + " service");

                    }
                }
            }
        }
    }

    // gives correct service
    private void applyService(Zone zone, String serviceType) {

        if (serviceType.equals("security")) {
            zone.receiveSecurity();

        } else if (serviceType.equals("health")) {
            zone.receiveHealth();

        } else if (serviceType.equals("education")) {
            zone.receiveEducation();
        }
    }
}