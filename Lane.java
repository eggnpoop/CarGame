public class Lane {
    private int length;   //is the length belonging to the highway or the lane?
    private int totalObstacles;
    public Lane()
    {
        length = -1;
        totalObstacles = -1;
    }

    public Lane(int length, int totalObstacles)
    {
        this.length = length;
        this.totalObstacles = totalObstacles;
    }

    public void displayLength()
    {
        System.out.println("The highway length is " + length + " sections long and there will be maximum " + totalObstacles + " obstacles on the road.");
    }

    public int getLength()
    {
        return length;
    }

    public int getTotalObstacles()
    {
        return totalObstacles;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setTotalObstacles(int totalObstacles)
    {
        this.totalObstacles = totalObstacles;
    }

    public int generateLaneLength(int min, int max)
    {
        int laneLength = (int)(Math.random() * (max - min) + min);
        return laneLength;
    }

}
