package objectville.cell;

import objectville.map.Position;

public abstract class Zone extends Cell {

    private int level;
    private int previousLevel;
    private int output;
    private int utilityDemand;

    // received utilities
    private int electricityReceived;
    private int waterReceived;
    private int internetReceived;

    // received services
    private boolean hasSecurity;
    private boolean hasHealth;
    private boolean hasEducation;

    // received resources
    private int populationReceived;
    private int goodsReceived;
    private int lifestyleReceived;

    public Zone(Position position, char type) {
        super(position, type);
        this.level = 0;
        this.previousLevel = 0;
        this.output = 0;
        this.utilityDemand = 1;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public void onTick() {
        previousLevel = level;
        updateLevel();
        generateOutput();
        utilityDemand = Math.max(1, output);
    }

    protected abstract void updateLevel();
    protected abstract void generateOutput();
    protected abstract boolean hasRequiredUtilities();

    public abstract String getZoneName();
    public abstract String getOutputResourceName();

    protected int minAllUtilities() {
        return Math.min(electricityReceived, Math.min(waterReceived, internetReceived));
    }

    protected int minElectricityAndWater() {
        return Math.min(electricityReceived, waterReceived);
    }

    protected void setLevel(int newLevel) {
        if (newLevel < 0) {
            level = 0;
        } else if (newLevel > 3) {
            level = 3;
        } else {
            level = newLevel;
        }
    }

    protected void setOutput(int newOutput) {
        output = Math.max(0, newOutput);
    }

    public void receiveElectricity(int amount) {
        if (amount > 0) electricityReceived += amount;
    }

    public void receiveWater(int amount) {
        if (amount > 0) waterReceived += amount;
    }

    public void receiveInternet(int amount) {
        if (amount > 0) internetReceived += amount;
    }

    public void receiveSecurity() {
        hasSecurity = true;
    }

    public void receiveHealth() {
        hasHealth = true;
    }

    public void receiveEducation() {
        hasEducation = true;
    }

    public void receivePopulation(int amount) {
        if (amount > 0) populationReceived += amount;
    }

    public void receiveGoods(int amount) {
        if (amount > 0) goodsReceived += amount;
    }

    public void receiveLifestyle(int amount) {
        if (amount > 0) lifestyleReceived += amount;
    }

    public void resetReceivedValues() {
        electricityReceived = 0;
        waterReceived = 0;
        internetReceived = 0;

        hasSecurity = false;
        hasHealth = false;
        hasEducation = false;

        populationReceived = 0;
        goodsReceived = 0;
        lifestyleReceived = 0;
    }

    // getters
    public int getLevel() {
        return level;
    }

    public int getPreviousLevel() {
        return previousLevel;
    }

    public int getOutput() {
        return output;
    }

    public int getUtilityDemand() {
        return utilityDemand;
    }

    public int getElectricityReceived() {
        return electricityReceived;
    }

    public int getWaterReceived() {
        return waterReceived;
    }

    public int getInternetReceived() {
        return internetReceived;
    }

    public boolean hasSecurity() {
        return hasSecurity;
    }

    public boolean hasHealth() {
        return hasHealth;
    }

    public boolean hasEducation() {
        return hasEducation;
    }

    public int getPopulationReceived() {
        return populationReceived;
    }

    public int getGoodsReceived() {
        return goodsReceived;
    }

    public int getLifestyleReceived() {
        return lifestyleReceived;
    }

    @Override
    public String toString() {
        return getZoneName() + " at " + getPosition();
    }
}