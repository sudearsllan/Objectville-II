package objectville.cell;

import objectville.map.Position;

public class Housing extends Zone {

    public Housing(Position position) {
        super(position, 'H');
    }

    @Override
    public String getZoneName() {
        return "House";
    }

    @Override
    public String getOutputResourceName() {
        return "population";
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

        int targetLevel = 1;

        if (hasSecurity() && hasHealth() && hasEducation()) {
            targetLevel = 2;
        }

        if (targetLevel == 2 && getLifestyleReceived() > 0) {
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
            setOutput(2 * m + getLifestyleReceived());
        }
    }
}
