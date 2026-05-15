package objectville.simulation;

import objectville.cell.Cell;
import objectville.cell.UtilityProviders;
import objectville.cell.Zone;

import java.util.ArrayList;

public class UtilityDistributor {

    public void distribute(Cell[][] city) {

        for (int currentRow = 0; currentRow < city.length; currentRow++) {
            for (int currentCol = 0; currentCol < city[currentRow].length; currentCol++) {

                Cell currentCell = city[currentRow][currentCol];

                if (currentCell instanceof UtilityProviders) {
                    UtilityProviders provider = (UtilityProviders) currentCell; // downcasting
                    distributeFromProvider(city, provider, currentRow, currentCol);
                }
            }
        }
    }

    private void distributeFromProvider(Cell[][] city, UtilityProviders provider, int startRow, int startCol) {
        boolean[][] visited = new boolean[city.length][city[0].length];
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        int remainingUtility = provider.getCapacity();

        int[] rowChange = {-1, 0, 1, 0};
        int[] colChange = {0, 1, 0, -1};
        int index = 0;

        while (index < list.size() && remainingUtility > 0) {
            int[] current = list.get(index);
            index++;
            int currentRow = current[0];
            int currentCol = current[1];
            Cell currentCell = city[currentRow][currentCol];

            // if cell is a zone send utility
            if (currentCell instanceof Zone) {
                Zone zone = (Zone) currentCell;
                int demand = zone.getUtilityDemand();
                int delivered;

                // check utility amount
                if (demand < remainingUtility) {delivered = demand;}
                else {delivered = remainingUtility;}

                if (delivered > 0) {
                    applyUtility(zone, provider.getUtilityType(), delivered);
                    remainingUtility -= delivered;

                    System.out.println(zone + " received " + delivered + " " + provider.getUtilityType());
                }
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = currentRow + rowChange[i];
                int nextCol = currentCol + colChange[i];

                // check if next position is outside the city map
                if (!isValid(city, nextRow, nextCol)) {continue;}

                // skip if this cell was already visited before
                if (visited[nextRow][nextCol]) {continue;}

                Cell nextCell = city[nextRow][nextCol];

                // skip cells that cannot carry utility connection
                if (!nextCell.isConnectable()) {continue;}

                visited[nextRow][nextCol] = true;
                list.add(new int[]{nextRow, nextCol});
            }
        }
    }

    private void applyUtility(Zone zone, String utilityType, int amount) {
        // send electricity
        if (utilityType.equals("electricity")) {
            zone.receiveElectricity(amount);
            // send water
        } else if (utilityType.equals("water")) {
            zone.receiveWater(amount);
            // send internet
        } else if (utilityType.equals("internet")) {
            zone.receiveInternet(amount);
        }
    }

    private boolean isValid(Cell[][] city, int row, int col) {
        return row >= 0 && col >= 0 && row < city.length && col < city[0].length;
    }
}