import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Highway extends Lane{
    private String[] obstacleList;

    public Highway()
    {
        obstacleList = new String[10];
    }

    public Highway(String[] obstacleList)
    {
        this.obstacleList = obstacleList;
    }

    public void displayLength()
    {
        super.displayLane();
    }

    public String generateHighwaySection()
    {
        String section = "- ";
        return section;
    }

    public int generateRandomNum(int min, int max) // Note: In this method the min is included and the max is not.
    {
        Random random = new Random();
        int rnd = random.nextInt(max - min) + min;

        return rnd;
    }

    public String generateRandomObs()
    {
        String[] obstacleList = {"F ", "F ", "F ", "B ", "B ", "B ", "B ", "S ", "S ", "O "};
        int rnd = generateRandomNum(0, 10);

        return obstacleList[rnd];
    }

    public String[] getObstacleList()
    {
        return obstacleList;
    }

    public void setObstacleList(String[] obstacleList)
    {
        this.obstacleList = obstacleList;
    }

}
