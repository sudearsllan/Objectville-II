package objectville.cell;

import objectville.map.Position;

public class Industrial extends Zone {

    public Industrial(Position position) {
        super(position, 'I');
    }

    @Override
    public String getZoneName() {
        return "Industrial";
    }

    @Override
    public String getOutputResourceName() {
        return "goods";
    }

    @Override
    protected boolean hasRequiredUtilities() {
        return getElectricityReceived() > 0
                && getWaterReceived() > 0;
    }

    @Override
    protected void updateLevel() {
        if (!hasRequiredUtilities()) {
            setLevel(0);
            return;
        }

        int targetLevel = 0;

        if (getPopulationReceived() > 0) {
            targetLevel = 1;
        }

        if (targetLevel == 1 && hasSecurity()) {
            targetLevel = 2;
        }

        if (targetLevel == 2 && getPopulationReceived() > 0) {
            targetLevel = 3;
        }

        if (targetLevel > getLevel()) {
            setLevel(getLevel() + 1);
        } else if (targetLevel < getLevel()) {
            setLevel(getLevel() - 1);
        }
    }

    @Override
    protected void generateOutput() {
        int m = minElectricityAndWater();

        if (getLevel() == 0) {
            setOutput(0);
        } else if (getLevel() == 1) {
            setOutput(m);
        } else if (getLevel() == 2) {
            setOutput(2 * m);
        } else {
            setOutput(2 * m + getPopulationReceived());
        }
    }
}