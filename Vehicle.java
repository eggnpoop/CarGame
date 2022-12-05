public class Vehicle {
    private String type;
    private int bstSpd;
    private int maxFuel;
    private int maxDmg;
    private double maxFuelPercentage;

    public Vehicle()
    {
        type = "";
        bstSpd = -1;
        maxFuel = -1;
        maxDmg = -1;
        maxFuelPercentage = 0;
    }

    public Vehicle(String type, int bstSpd, int maxFuel, int maxDmg, double maxFuelPercentage)
    {
        this.type = type;
        this.bstSpd = bstSpd;
        this.maxFuel = maxFuel;
        this.maxDmg = maxDmg;
        this.maxFuelPercentage = maxFuelPercentage;
    }

    public void displayStartFuel()
    {
        System.out.println("You will start with " + this.maxFuel + " fuel points.");
    }

    public double getMaxFuelPercentage()
    {
        return maxFuelPercentage;
    }

    public int getBstSpd() {
        return bstSpd;
    }

    public int getMaxDmg() {
        return maxDmg;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public String getType() {
        return type;
    }

    public void setMaxFuelPercentage(double maxFuelPercentage)
    {
        this.maxFuelPercentage = maxFuelPercentage;
    }

    public void setBstSpd(int bstSpd) {
        this.bstSpd = bstSpd;
    }

    public void setMaxDmg(int maxDmg) {
        this.maxDmg = maxDmg;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int generateMaxFuel()
    {
        int startFuel = (int)(maxFuel * maxFuelPercentage);
        setMaxFuel(startFuel);
        return maxFuel;
    }
}
