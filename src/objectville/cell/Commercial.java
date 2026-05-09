package objectville.cell;

import objectville.map.Position;

public class Commercial extends Zone {

    public Commercial(Position position) {
        super(position, 'C');
    }

    @Override
    public String getZoneName() {
        return "Commercial";
    }

    @Override
    public String getOutputResourceName() {
        return "lifestyle";
    }

    @Override
    protected boolean hasRequiredUtilities() {
        return getElectricityReceived() > 0
                && getWaterReceived() > 0
                && getInternetReceived() > 0;
    }

    @Override
    protected void updateLevel() {
        if (!hasRequiredUtilities()) {
            setLevel(0);
            return;
        }

        int targetLevel = 0;

        if (getPopulationReceived() > 0 && getGoodsReceived() > 0) {
            targetLevel = 1;
        }

        if (targetLevel == 1 && hasSecurity()) {
            targetLevel = 2;
        }

        if (targetLevel == 2
                && getPopulationReceived() > getUtilityDemand()
                && getGoodsReceived() > getUtilityDemand()) {
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
        int m = minAllUtilities();

        if (getLevel() == 0) {
            setOutput(0);
        } else if (getLevel() == 1) {
            setOutput(m);
        } else if (getLevel() == 2) {
            setOutput(2 * m);
        } else {
            setOutput(2 * m + Math.min(getPopulationReceived(), getGoodsReceived()));
        }
    }
}