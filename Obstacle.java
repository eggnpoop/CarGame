public class Obstacle {
    private String name;
    private int dmg;
    private int fuel;

    public Obstacle()
    {
        name = "";
        dmg = 0;
        fuel = 0;
    }

    public Obstacle(String name, int dmg, int fuel)
    {
        this.name = name;
        this.dmg = dmg;
        this.fuel = fuel;
    }

    public int getDmg() {
        return dmg;
    }

    public int getFuel() {
        return fuel;
    }

    public String getName() {
        return name;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setName(String name) {
        this.name = name;
    }
}
