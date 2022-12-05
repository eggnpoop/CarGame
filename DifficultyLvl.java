public class DifficultyLvl {
    //private Highway highway;
    //private Vehicle vehicle;
    private int minLvl;
    private int maxLvl;
    private double maxFuelPercentLvl;
    private int totalObsLvl;

    public DifficultyLvl()
    {
        minLvl = -1;
        maxLvl = -1;
        maxFuelPercentLvl = 0;
        totalObsLvl = -1;
    }

    public DifficultyLvl(int minLvl, int maxLvl, double maxFuelPercentLvl, int totalObsLvl)
    {
        this.minLvl = minLvl;
        this.maxLvl = maxLvl;
        this.maxFuelPercentLvl = maxFuelPercentLvl;
        this.totalObsLvl = totalObsLvl;
    }

    public double getMaxFuelPercentLvl() {
        return maxFuelPercentLvl;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public int getMinLvl() {
        return minLvl;
    }

    public int getTotalObsLvl() {
        return totalObsLvl;
    }

    public void setMaxFuelPercentLvl(double maxFuelPercentLvl) {
        this.maxFuelPercentLvl = maxFuelPercentLvl;
       // vehicle.generateMaxFuel(this.maxFuelPercentLvl);
    }

    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    public void setMinLvl(int minLvl) {
        this.minLvl = minLvl;
    }

    public void setTotalObsLvl(int totalObsLvl) {
        this.totalObsLvl = totalObsLvl;
        //highway.setTotalObstacles(this.totalObsLvl);
    }


}
