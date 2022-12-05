import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Highway extends Lane{
    public ArrayList<String> lane1;
    public ArrayList<String> lane2;
    public ArrayList<String> lane3;
    //private Obstacle[] obstacleList; // can i put final here?
    private String[] obstacleList; // can i put final here?

    public Highway()
    {
        lane1 = new ArrayList<String>();
        lane2 = new ArrayList<String>();
        lane3 = new ArrayList<String>();
        obstacleList = new String[10];
    }

    public ArrayList<String> getLane1()
    {
        return lane1;
    }

    public ArrayList<String> getLane2()
    {
        return lane2;
    }

    public ArrayList<String> getLane3()
    {
        return lane3;
    }

    public String[] getObstacleList()
    {
        return obstacleList;
    }

    public void setLane1(ArrayList<String> lane1)
    {
        this.lane1 = lane1;
    }

    public void setLane2(ArrayList<String> lane2)
    {
        this.lane2 = lane2;
    }

    public void setLane3(ArrayList<String> lane3)
    {
        this.lane3 = lane3;
    }

    public void setObstacleList(String[] obstacleList)
    {
        this.obstacleList = obstacleList;
    }

    public String generateHighwaySection()
    {
        String section = "- ";
        return section;
    }

    /*public String[] obstacleList() {
        Obstacle fuel = new Obstacle("F ", 0, 10);
        Obstacle roadblock = new Obstacle("B ", 20, 0);
        Obstacle tyreSpikes = new Obstacle("S ", 45, 0);
        Obstacle manhole = new Obstacle("O ", 60, 0);

        obstacleList[0] = fuel.getName();
        obstacleList[1] = fuel.getName();
        obstacleList[2] = fuel.getName();
        obstacleList[3] = roadblock.getName();
        obstacleList[4] = roadblock.getName();
        obstacleList[5] = roadblock.getName();
        obstacleList[6] = roadblock.getName();
        obstacleList[7] = tyreSpikes.getName();
        obstacleList[8] = tyreSpikes.getName();
        obstacleList[9] = manhole.getName();

        return obstacleList;
    } */

    /*public Obstacle[] obstacleList()
    {
        Obstacle fuel = new Obstacle("F ", 0, 10);
        Obstacle roadblock = new Obstacle("B ", 20, 0);
        Obstacle tyreSpikes = new Obstacle("S ", 45, 0);
        Obstacle manhole = new Obstacle("O ", 60, 0);

        obstacleList[0] = fuel;
        obstacleList[1] = fuel;
        obstacleList[2] = fuel;
        obstacleList[3] = roadblock;
        obstacleList[4] = roadblock;
        obstacleList[5] = roadblock;
        obstacleList[6] = roadblock;
        obstacleList[7] = tyreSpikes;
        obstacleList[8] = tyreSpikes;
        obstacleList[9] = manhole;

        return obstacleList;
    } */

    public String generateRandomObs()
    {
        String[] obstacleList = {"F ", "F ", "F ", " ", "B ", "B ", "B ", "S ", "S ", "O "};
        int rnd = generateRandomNum(0, 10);
        return obstacleList[rnd];
    }
    /*public Obstacle generateHighwaySection()
    {
        Obstacle section = new Obstacle("- ", 0, 0);
        return section;
    }*/

    /*public Obstacle[] obstacleList()
    {
        Obstacle fuel = new Obstacle("F ", 0, 10);
        Obstacle roadblock = new Obstacle("B ", 20, 0);
        Obstacle tyreSpikes = new Obstacle("S ", 45, 0);
        Obstacle manhole = new Obstacle("O ", 60, 0);

        obstacleList[0] = fuel;
        obstacleList[1] = fuel;
        obstacleList[2] = fuel;
        obstacleList[3] = roadblock;
        obstacleList[4] = roadblock;
        obstacleList[5] = roadblock;
        obstacleList[6] = roadblock;
        obstacleList[7] = tyreSpikes;
        obstacleList[8] = tyreSpikes;
        obstacleList[9] = manhole;

        return obstacleList;
    } */

    /*public Obstacle generateRandomObs()
    {
        Random random = new Random();
        int rnd = generateRandomNum(0, 10);
        return obstacleList[rnd];
    }*/

    public int generateRandomNum(int min, int max) // Note: In this method the min is included and the max is not.
    {
        Random random = new Random();
        int rnd = random.nextInt(max - min) + min;
        return rnd;
    }

}
