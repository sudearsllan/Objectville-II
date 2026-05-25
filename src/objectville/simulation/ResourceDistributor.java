package objectville.simulation;

import objectville.cell.Cell;
import objectville.cell.Commercial;
import objectville.cell.Housing;
import objectville.cell.Industrial;
import java.util.ArrayList;


public class ResourceDistributor {
    public void distribute(Cell[][] city) {
        ArrayList<Housing> houses = new ArrayList<>();
        ArrayList<Industrial> industries = new ArrayList<>();
        ArrayList<Commercial> commercials = new ArrayList<>();

        for (int currentRow = 0; currentRow < city.length; currentRow++) {
            for (int currentCol = 0; currentCol < city[currentRow].length; currentCol++) {

                Cell currentCell = city[currentRow][currentCol];

                if (currentCell instanceof Housing) {
                    houses.add((Housing) currentCell); // downcasting

                } else if (currentCell instanceof Industrial) {
                    industries.add((Industrial) currentCell); // downcasting

                } else if (currentCell instanceof Commercial) {
                    commercials.add((Commercial) currentCell); // downcasting
                }
            }
        }


        distributePopulation(houses, industries, commercials);
        distributeGoods(industries, commercials);
        distributeLifestyle(commercials, houses);
    }
    private void distributePopulation(ArrayList<Housing> houses,ArrayList<Industrial> industries,ArrayList<Commercial> commercials) {
        // get total population from houses
        int totalPopulation = 0;

        for (Housing house : houses) {
            totalPopulation += house.getOutput();
        }

        // if no population, stop
        if (totalPopulation == 0) {return;}

        int receiverCount = industries.size() + commercials.size();
        // no zones to receive population
        if (receiverCount == 0) {return;}

        int amountPerZone = totalPopulation / receiverCount;
        if (amountPerZone == 0) {return;}

        // give population to industrial zones
        for (Industrial industrial : industries) {
            industrial.receivePopulation(amountPerZone);
            System.out.println(industrial + " received "+ amountPerZone + " population");

        }
        // give population to commercial zones
        for (Commercial commercial : commercials) {
            commercial.receivePopulation(amountPerZone);
            System.out.println(commercial + " received " + amountPerZone + " population");

        }
    }

    private void distributeGoods(ArrayList<Industrial> industries, ArrayList<Commercial> commercials) {
        int totalGoods = 0;
        for (Industrial industrial : industries) {
            totalGoods += industrial.getOutput();
        }
        // no goods produced, stop
        if (totalGoods == 0) {return;}
        // no commercial buildings, nothing to send
        if (commercials.size() == 0) {return;}

        int amountPerZone = totalGoods / commercials.size();
        // too small to distribute, skip
        if (amountPerZone == 0) {return;}

        for (Commercial commercial : commercials) {
            commercial.receiveGoods(amountPerZone);
            System.out.println(commercial + " received " + amountPerZone + " goods");
        }
    }

    private void distributeLifestyle(ArrayList<Commercial> commercials, ArrayList<Housing> houses) {
        int totalLifestyle = 0;

        // calculate total lifestyle output from all commercial buildings
        for (Commercial commercial : commercials) {
            totalLifestyle += commercial.getOutput();
        }
        // if no lifestyle, stop
        if (totalLifestyle == 0) {return;}
        // no houses, nothing to give
        if (houses.size() == 0) {return;}

        // calculate how much each house gets
        int amountPerZone = totalLifestyle / houses.size();

        // too small, skip
        if (amountPerZone == 0) {return;}

        for (Housing house : houses) {
            house.receiveLifestyle(amountPerZone);
            System.out.println(house + " received " + amountPerZone + " lifestyle");
        }
    }
}