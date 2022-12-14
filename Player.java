public class Player extends Vehicle {
    private int currFuel;
    private int currDmg;
    private int position;

    public Player()
    {
        currFuel = -1;
        currDmg = -1;
        position = -1;
    }

    public Player(int currFuel, int currDmg, int position)
    {
        this.currFuel = currFuel;
        this.currDmg = currDmg;
        this.position = position;
    }

    public void displayPlayerInfo()
    {
        System.out.println("Fuel: " + currFuel + ", Damage: " + currDmg + " / " + getMaxDmg());
    }

    public int getCurrDmg()
    {
        return currDmg;
    }

    public int getCurrFuel()
    {
        return currFuel;
    }

    public int getPosition()
    {
        return position;
    }

    public void moveBoost()
    {
        this.position += super.getBstSpd();
        this.currFuel -= super.getBstSpd()*3;
    }

    public void moveDown()
    {
        this.position++;
        this.currFuel -= 2;
    }

    public void moveFwd()
    {
        this.position++;
        this.currFuel--;
    }

    public void moveUp()
    {
        this.position++;
        this.currFuel -= 2;
    }

    public void setCurrDmg(int currDmg)
    {
        this.currDmg = currDmg;
    }

    public void setCurrFuel(int currFuel)
    {
        this.currFuel = currFuel;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

}
