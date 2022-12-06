import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Game extends Player{
    private DifficultyLvl difficultyLvl;
    private ArrayList<ArrayList<String>> highwayComplete;
    private Highway highway;
    private ArrayList<String> lane1;
    private ArrayList<String> lane2;
    private ArrayList<String> lane3;
    private String playerName;
    private Vehicle vehicle;
    private ArrayList<Vehicle> vehicleType;


    public Game()
    {
        playerName = "";
        difficultyLvl = new DifficultyLvl();
        highway = new Highway();
        highwayComplete = new ArrayList<>();
        lane1 = new ArrayList<>();
        lane2 = new ArrayList<>();
        lane3 = new ArrayList<>();
        vehicle = new Vehicle();
        vehicleType = new ArrayList<>();
    }


    private void checkDmg() // SETTING DMG LIMITS
    {
        if (getCurrDmg() > getMaxDmg())
        {
            setCurrDmg(getMaxDmg());
        }
    }

    private void checkFuel()    // SETTING FUEL LIMITS
    {
        if (getCurrFuel() > getMaxFuel())
        {
            setCurrFuel(getMaxFuel());
        }
    }

    private void display()
    {
        System.out.println("Thankyou " + playerName + " for playing!");
    }

    private boolean gameEnd()   // CHECKING IF PLAYER HAS LOST OR WON
    {
        boolean proceed = false;

        if ((getCurrFuel() <= 0) || (getCurrDmg() >= getMaxDmg()))
        {
            System.out.println(gameLose());
            display();
            proceed = true;
        }
        else if (getPosition() >= (highway.getLength() - 1)) {
            System.out.println(gameWin());
            display();
            proceed = true;
        }
        else {
            proceed = false;
        }
        return proceed;
    }

    private String gameLose()
    {
        String lose = "";
        if (getCurrFuel() <= 0)
        {
            lose = "The cops are on our tail, why are you slowing down?! Oh... Looks like you're out of fuel. Better luck next time..." + "\n" + "GAME OVER..." + "\n";
        }
        else if (getCurrDmg() >= getMaxDmg())
        {
            lose = "AH! What was that you just hit?! The vehicle is too damaged to move. Better luck next time!" + "\n" + "GAME OVER..." + "\n";
        }

        return lose;
    }

    private String gameWin()
    {
        return "I think we've lost em'. Great driving. " + playerName + "! Perhaps you should do this more often." + "\n" + "CONGRATULATIONS! YOU GOT AWAY!" + "\n";
    }

    private void generateHighwayLanes()
    {
        ArrayList<String> laneContent = new ArrayList<>();
        int totalObstacles = -1;
        int totalLength = -1;
        int startOfLane = -1;
        int laneContents = -1;

        totalObstacles = highway.getTotalObstacles();
        totalLength = (highway.getLength() - 3)*3; // NOTE: the first 3 lanes are blank

        // START OF LANES
        for (startOfLane = 0; startOfLane < 3; startOfLane++) //
        {
            lane1.add(highway.generateHighwaySection());
            lane2.add(highway.generateHighwaySection());
            lane3.add(highway.generateHighwaySection());
        }

        // LANE CONTENTS - generate a temporary lane with rest of content
        for (laneContents = 0; laneContents < totalLength; laneContents++) //
        {
            int thirdChance = highway.generateRandomNum(1, 91);

            if (thirdChance >= 1 && thirdChance <= 30)
            {
                int o = 1;
                if (o <= totalObstacles)
                {
                    laneContent.add(highway.generateRandomObs());
                    o++;
                }
                else
                {
                    laneContent.add(highway.generateHighwaySection());
                }
            }
            else
            {
                laneContent.add(highway.generateHighwaySection());
            }
        }

        // ADDING THE REST OF THE LANE CONTENTS TO LANES
        for (int finalLane = 0; finalLane < (laneContent.size() / 3); finalLane++)
        {
            lane1.add(laneContent.get(finalLane));
            lane2.add(laneContent.get((laneContent.size() / 3) + finalLane));
            lane3.add(laneContent.get(((laneContent.size() / 3)*2) + finalLane));
        }

        // ADDING LANES TO HIGHWAY
        highwayComplete.add(lane1);
        highwayComplete.add(lane2);
        highwayComplete.add(lane3);
    }

    public DifficultyLvl getDifficultyLvl()
    {
        return difficultyLvl;
    }

    public Highway getHighway()
    {
        return highway;
    }

    public ArrayList<ArrayList<String>> getHighwayComplete()
    {
        return highwayComplete;
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

    public String getPlayerName()
    {
        return playerName;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public ArrayList<Vehicle> getVehicleType()
    {
        return vehicleType;
    }

    private void inputMove()    // ALLOWS USER TO MOVE VEHICLE ON HIGHWAY
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int move = -1;
        int startPoint = setPlayerPosition();

        Obstacle fuel = new Obstacle("F ", 0, 10);
        Obstacle roadblock = new Obstacle("B ", 20, 0);
        Obstacle tyreSpikes = new Obstacle("S ", 45, 0);
        Obstacle openManhole = new Obstacle("O ", 60, 0);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Are you ready " + playerName + " ? Better start moving!");
        System.out.println("Player: @, Fuel: F (+10 fuel pts), Roadblock: B (+20 dmg pts), Tyre spikes: S (+45 dmg pts), Open manhole: O (+60 dmg pts)" + "\n");
        displayPlayerInfo();
        System.out.print(this + "\n");

        do
        {
            try
            {
                move = input.acceptIntegerInput("Enter [1 - Move forward], [2 - Swerve up], [3 - Swerve down] or [4 - Boost] to move your vehicle!");
                if (valid.integerWithinRange(move, 1, 4))  // Validation check
                {
                    if (move == 1)
                    {
                        moveFwd();

                        // Checking if highway section contains an obstacle and implementing the effects
                        String tempObj = (highwayComplete.get(startPoint)).get(getPosition());

                        if (Objects.equals(tempObj, fuel.getName())) {
                            setCurrFuel(getCurrFuel() + fuel.getFuel());
                            checkFuel();
                        } else if (Objects.equals(tempObj, roadblock.getName())) {
                            setCurrDmg(getCurrDmg() + roadblock.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj, tyreSpikes.getName())) {
                            setCurrDmg(getCurrDmg() + tyreSpikes.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj, openManhole.getName())) {
                            setCurrDmg(getCurrDmg() + openManhole.getDmg());
                            checkDmg();
                        } else {
                            getCurrFuel();
                            getCurrDmg();
                        }

                        ArrayList<String> startLane = highwayComplete.get(startPoint);
                        startLane.set(getPosition(), "@ ");

                        System.out.println();
                        displayPlayerInfo();
                        System.out.print(this);
                        proceed = gameEnd();
                    }

                    if (move == 2)
                    {
                        moveUp();

                        // Checking if highway section contains an obstacle and implementing the effects
                        String tempObj1 = (highwayComplete.get( startPoint)).get(getPosition()); // where the @ passes
                        String tempObj2 = (highwayComplete.get( startPoint - 1 )).get(getPosition()); // where the @ ends

                        if (Objects.equals(tempObj1, fuel.getName()) || Objects.equals(tempObj2, fuel.getName())) {
                            setCurrFuel(getCurrFuel() + fuel.getFuel());
                            checkFuel();
                        } else if (Objects.equals(tempObj1, roadblock.getName()) || Objects.equals(tempObj2, roadblock.getName())) {
                            setCurrDmg(getCurrDmg() + roadblock.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj1, tyreSpikes.getName()) || Objects.equals(tempObj2, tyreSpikes.getName())) {
                            setCurrDmg(getCurrDmg() + tyreSpikes.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj1, openManhole.getName()) || Objects.equals(tempObj2, openManhole.getName())) {
                            setCurrDmg(getCurrDmg() + openManhole.getDmg());
                            checkDmg();
                        } else {
                            proceed = false;
                        }

                        // Moving player character, @
                        ArrayList<String> laneUp = highwayComplete.get(startPoint - 1);
                        laneUp.set(getPosition(), "@ ");

                        System.out.println();
                        displayPlayerInfo();
                        System.out.print(this);
                        startPoint = startPoint - 1;
                        proceed = gameEnd();
                    }

                    if (move == 3)
                    {
                        moveDown();

                        // Checking if the highway obstacles before the player has Boosted
                        String tempObj1 = (highwayComplete.get( startPoint )).get(getPosition()); // where the @ passes
                        String tempObj2 = (highwayComplete.get( startPoint + 1 )).get(getPosition()); // where the @ ends

                        if (Objects.equals(tempObj1, fuel.getName()) || Objects.equals(tempObj2, fuel.getName())) {
                            setCurrFuel(getCurrFuel() + fuel.getFuel());
                            checkFuel();
                        } else if (Objects.equals(tempObj1, roadblock.getName()) || Objects.equals(tempObj2, roadblock.getName())) {
                            setCurrDmg(getCurrDmg() + roadblock.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj1, tyreSpikes.getName()) || Objects.equals(tempObj2, tyreSpikes.getName())) {
                            setCurrDmg(getCurrDmg() + tyreSpikes.getDmg());
                            checkDmg();
                        } else if (Objects.equals(tempObj1, openManhole.getName()) || Objects.equals(tempObj2, openManhole.getName())) {
                            setCurrDmg(getCurrDmg() + openManhole.getDmg());
                            checkDmg();
                        } else {
                            proceed = false;
                        }

                        // Moving player character, @
                        ArrayList<String> laneDown = highwayComplete.get(startPoint +1);
                        laneDown.set(getPosition(), "@ ");
                        System.out.println();

                        displayPlayerInfo();
                        System.out.print(this);
                        startPoint = startPoint + 1;
                        proceed = gameEnd();
                    }

                    if (move == 4)
                    {

                        // Checking if the highway obstacles before the player has Boosted
                        ArrayList<String> boostObjs = new ArrayList<>();
                        int temp = getPosition();
                        int sections = -1;
                        for (sections = (temp+1); sections <= (temp+getBstSpd()); sections++)
                        {
                            boostObjs.add((highwayComplete.get(startPoint)).get(sections));
                        }

                        if (boostObjs.contains(fuel.getName())) {
                            setCurrFuel(getCurrFuel() + fuel.getFuel());
                            checkFuel();
                        } else if (boostObjs.contains(roadblock.getName())) {
                            setCurrDmg(getCurrDmg() + roadblock.getDmg());
                            checkDmg();
                        } else if (boostObjs.contains(tyreSpikes.getName())) {
                            setCurrDmg(getCurrDmg() + tyreSpikes.getDmg());
                            checkDmg();
                        } else if (boostObjs.contains(openManhole.getName())) {
                            setCurrDmg(getCurrDmg() + openManhole.getDmg());
                            checkDmg();
                        } else {
                            proceed = false;
                        }

                        // Moving player character, @
                        moveBoost();
                        ArrayList<String> boostLane = highwayComplete.get(startPoint);
                        boostLane.set(getPosition(), "@ ");

                        System.out.println("");
                        displayPlayerInfo();
                        System.out.print(this);
                        proceed = gameEnd();
                    }
                }
                else
                {
                    System.out.println("AH! That move doesn't exist! Please enter [1 - Move forward], [2 - Swerve up], [3 - Swerve down] or [4 - Boost] to move your vehicle!");
                    proceed = false;
                }
            }
            catch (Exception e)
            {
                System.out.println("Invalid move. Input must be a possible move! Please enter [1 - Move forward], [2 - Swerve up], [3 - Swerve down] or [4 - Boost] to move your vehicle!");
            }
        } while (proceed == false);
    }

    private void inputPlayerName()  // USER INPUTS PLAYER NAME
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        String name = "";

        do
        {
            name = input.acceptStringInput("Enter your name thief!");
            if(valid.stringLengthWithinRange(name, 3, 12))  // Validation check
            {
                setPlayerName(name);
                System.out.println("Welcome " + playerName + "!");
                proceed = true;
            }
            else if (valid.isBlank(name))  // Validation check
            {
                System.out.println("Your name cannot be blank! Please re enter your name thief!");
            }
            else
            {
                System.out.println("AH! Your name is not valid! Please enter a name using 3 to 12 characters.");
            }
        } while(proceed == false);

    }

    public static void main(String[] args)
    {
        Game game = new Game();

        game.start();
    }

    private void readVehicleFile()  // READS VEHICLE INFORMATION FROM A FILE
    {
        String inputFile = "vehicles.txt";
        FileIO io = new FileIO(inputFile);
        ArrayList<String> vehicleTemp = new ArrayList<>();
        int i = -1;

        String[] info = io.readFile().split(","); // Adds info into array

        for (i = 0; i < info.length; i++)  // Sorting each vehicle information into ArrayLists
        {
            vehicleTemp.add(info[i]);
        }

        // Creating new vehicle options
        Vehicle motorcycle = new Vehicle(vehicleTemp.get(0), Integer.parseInt(vehicleTemp.get(1)), Integer.parseInt(vehicleTemp.get(2)), Integer.parseInt(vehicleTemp.get(3)), getMaxFuelPercentage());
        Vehicle car = new Vehicle(vehicleTemp.get(4), Integer.parseInt(vehicleTemp.get(5)), Integer.parseInt(vehicleTemp.get(6)), Integer.parseInt(vehicleTemp.get(7)), getMaxFuelPercentage());
        Vehicle garbageTruck = new Vehicle(vehicleTemp.get(8), Integer.parseInt(vehicleTemp.get(9)), Integer.parseInt(vehicleTemp.get(10)), Integer.parseInt(vehicleTemp.get(11)), getMaxFuelPercentage());

        vehicleType.add(motorcycle);
        vehicleType.add(car);
        vehicleType.add(garbageTruck);

    }

    private void selectDifficultyLvl()  // USER INPUTS DIFFICULTY LVL CHOICE
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int lvl = -1;


        DifficultyLvl easy = new DifficultyLvl(10, 15, 1.0, 12);
        DifficultyLvl med = new DifficultyLvl(15, 30, 0.8, 24);
        DifficultyLvl hard = new DifficultyLvl(30, 50, 0.5, 45);

        do
        {
            System.out.println("------------------------------------------------------------------------------------------------------");

            System.out.println("Well " + playerName + ", I heard you are on the run from the police...");
            System.out.println("1 - EASY: Highway Length = 10 to 15" + ", Max Fuel = 100%" + ", Total obstacles = 12");
            System.out.println("2 - MEDIUM: Highway Length = 15 to 30" + ", Max Fuel = 80%" + ", Total obstacles = 24");
            System.out.println("3 - HARD: Highway Length = 30 to 50" + ", Max Fuel = 50%" + ", Total obstacles = 45");
            System.out.println();

            try
            {
                lvl = input.acceptIntegerInput("Enter 1, 2, or 3 to choose the difficulty of your chase!");
                if (valid.integerWithinRange(lvl, 1, 3))        // Validation check
                {
                    if (lvl == 1)
                    {
                        highway.setLength( highway.generateLaneLength(easy.getMinLvl(), easy.getMaxLvl()) );
                        setMaxFuelPercentage(easy.getMaxFuelPercentLvl());
                        highway.setTotalObstacles(easy.getTotalObsLvl());
                        System.out.println("Ah! Easy! Great choice!");
                        difficultyLvl.displayDifficultyInfo();
                        proceed = true;
                    }
                    if (lvl == 2)
                    {
                        highway.setLength( highway.generateLaneLength(med.getMinLvl(), med.getMaxLvl()) );
                        setMaxFuelPercentage(med.getMaxFuelPercentLvl());
                        highway.setTotalObstacles(med.getTotalObsLvl());
                        System.out.println("Medium? As a true criminal would pick!");
                        difficultyLvl.displayDifficultyInfo();
                        proceed = true;
                    }
                    if (lvl == 3)
                    {
                        highway.setLength( highway.generateLaneLength(hard.getMinLvl(), hard.getMaxLvl()) );
                        setMaxFuelPercentage(hard.getMaxFuelPercentLvl());
                        highway.setTotalObstacles(hard.getTotalObsLvl());
                        System.out.println("Hard?! The cops are on our tail!");
                        difficultyLvl.displayDifficultyInfo();
                        proceed = true;
                    }
                }
                else
                {
                    System.out.println("AH! That level doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                    proceed = false;
                }
            }
            catch (Exception e)
            {
                System.out.println("Input cannot be a character or blank. Please enter valid number within the range.");
                proceed = false;
            }
        } while(proceed == false);
    }

    private void selectVehicle()    // USER INPUTS VEHICLE LVL CHOICE
    {
        Game game = new Game();
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int vehicleSlct = -1;


        game.readVehicleFile();

        do
        {
            try
            {
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println();

                System.out.println(playerName + "... Do you hear that? Let's choose your escape vehicle before the cops get here!");
                System.out.print("1 - " + vehicleType.get(0).getType().toUpperCase() + ": Boost speed = " + vehicleType.get(0).getBstSpd() + ", Max fuel = " + vehicleType.get(0).getMaxFuel() + ", Max damage = " + vehicleType.get(0).getMaxDmg() + "\n");
                System.out.print("2 - " + vehicleType.get(1).getType().toUpperCase() + ": Boost speed = " + vehicleType.get(1).getBstSpd() + ", Max fuel = " + vehicleType.get(1).getMaxFuel() + ", Max damage = " + vehicleType.get(1).getMaxDmg() + "\n");
                System.out.print("3 - " + vehicleType.get(2).getType() .toUpperCase()+ ": Boost speed = " + vehicleType.get(2).getBstSpd() + ", Max fuel = " + vehicleType.get(2).getMaxFuel() + ", Max damage = " + vehicleType.get(2).getMaxDmg() + "\n" + "\n");

                vehicleSlct = input.acceptIntegerInput("Enter 1, 2, or 3 to choose the difficulty of your chase!");

                if (valid.integerWithinRange(vehicleSlct, 1, 3))   // Validation check
                {
                    if (vehicleSlct == 1)
                    {
                        setBstSpd(vehicleType.get(0).getBstSpd());
                        setMaxFuel(vehicleType.get(0).getMaxFuel());
                        setMaxDmg(vehicleType.get(0).getMaxDmg());
                        System.out.println("A motorcycle?! Offt, you sure like it speedy! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + getBstSpd() + " highway sections.");
                        proceed = true;
                    }
                    else if (vehicleSlct == 2)
                    {
                        setBstSpd(vehicleType.get(1).getBstSpd());
                        setMaxFuel(vehicleType.get(1).getMaxFuel());
                        setMaxDmg(vehicleType.get(1).getMaxDmg());
                        System.out.println("Ahhh... a classic car chase! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + getBstSpd() + " highway sections.");
                        proceed = true;
                    }
                    else if (vehicleSlct == 3)
                    {
                        setBstSpd(vehicleType.get(2).getBstSpd());
                        setMaxFuel(vehicleType.get(2).getMaxFuel());
                        setMaxDmg(vehicleType.get(2).getMaxDmg());
                        System.out.println("A garbage truck?! Well that's one way to out run the cops! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + getBstSpd() + " highway sections.");
                        proceed = true;
                    }
                    else
                    {
                        System.out.println("AH! That vehicle doesn't exist! Please enter 1, 2, or 3 to choose your vehicle.");
                    }
                }
                else
                {
                    System.out.println("AH! That vehicle doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                }
            }
            catch (Exception e)
            {
                System.out.println("Input cannot be a character. Please enter valid number within the range.");
            }

            highway.displayLength();
            generateMaxFuel();
            displayStartFuel();

        } while(proceed == false);
    }

    public void setDifficultyLvl(DifficultyLvl difficultyLvl)
    {
        this.difficultyLvl = difficultyLvl;
    }

    public void setHighway(Highway highway)
    {
        this.highway = highway;
    }

    public void setHighwayComplete(ArrayList<ArrayList<String>> highwayComplete)
    {
        this.highwayComplete = highwayComplete;
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

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    private int setPlayerPosition()
    {
        //SETTING PLAYER IN LANE
        setCurrFuel(getMaxFuel());
        setCurrDmg(0);
        setPosition(0);

        int startPoint = highway.generateRandomNum(0, 3);
        {
            if (startPoint == 0)
            {
                lane1.set(0, "@ ");
            } else if (startPoint == 1)
            {
                lane2.set(0, "@ ");
            } else
            {
                lane3.set(0, "@ ");
            }
        }
        return startPoint;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public void setVehicleType(ArrayList<Vehicle> vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    private void start()
    {
        Input input = new Input();
        Validation valid = new Validation();
        Scanner console = new Scanner(System.in);
        boolean proceed = false;
        int menuOption = -1;
        String exit = "";

        do
        {
            System.out.println("Welcome to Need For Java!");
            try
            {
                menuOption = input.acceptIntegerInput("Enter 1 to START GAME" + "\n" + "Enter 2 to SEE INSTRUCTIONS" + "\n" + "Enter 3 to EXIT");
                if (valid.integerWithinRange(menuOption, 1,3))   // Validation check
                {
                    if (menuOption == 1)
                    {
                        readVehicleFile();

                        inputPlayerName();
                        selectDifficultyLvl();
                        selectVehicle();
                        generateHighwayLanes();
                        inputMove();

                        writeFile();
                        proceed = true;
                    }
                    if (menuOption == 2)
                    {
                        System.out.println("The aim of the game is to successfully navigate the highway to escape the authorities!");
                        System.out.println("Player will be prompted to select a difficulty level (easy, medium, hard) and a vehicle (motorcycle, car, or garbage truck).");
                        System.out.println("The player, @, will be randomly appear on the highway and will be prompted to move forward, up, down, or boost to navigate the highway.");
                        System.out.println("The highway will contain roadblocks, tyre spikes, and open manholes which will add damage to the vehicle; or fuel, which will increase fuel points.");
                        System.out.println("The player will lose if they collect too many damage points or run out of fuel points!");
                        System.out.println("To win, the player must complete the entire length of the highway without losing!");
                        System.out.println();

                        System.out.println("DIFFICULTY LVL");
                        System.out.println("EASY - Highway length: 10 to 15, Max fuel: 100%, Total obstacles: 12");
                        System.out.println("MEDIUM - Highway length: 15 to 30, Max fuel: 80%, Total obstacles: 24");
                        System.out.println("HARD TRUCK - Highway length: 30 to 50, Max fuel: 50%, Total obstacles: 45");
                        System.out.println();

                        System.out.println("VEHICLES");
                        System.out.println("MOTORCYCLE - Boost speed: 4, Max fuel: 100, Max dmg: 30");
                        System.out.println("CAR - Boost speed: 3, Max fuel: 120, Max dmg: 50");
                        System.out.println("GARBAGE TRUCK - Boost speed: 2, Max fuel: 150, Max dmg: 100");
                        System.out.println();

                        System.out.println("OBSTACLES");
                        System.out.println("Fuel: F (+10 fuel points)");
                        System.out.println("Roadblocks: B (+20 dmg points)");
                        System.out.println("Tyre spikes: S (+45 dmg points)");
                        System.out.println("Open manholes: O (+60 dmg points)");

                        exit = input.acceptStringInput("Enter any key to exit");
                        proceed = true;
                    }
                    if (menuOption == 3)
                    {
                        System.out.println("Goodbye");
                        display();
                        break;
                    }
                }
                else
                {
                    System.out.println("That is not a menu option. Enter a valid input.");
                }
            }
            catch (Exception e)
            {
                System.out.println("Please enter valid input.");
                proceed = false;
            }
        } while (proceed == false);
    }

    public String toString()    // PRINTING HIGHWAY IN 2D ARRAYLIST
    {
        int playerPosition = getPosition();
        int i = -1;
        int row =-1;
        String result= "";

        for (row = 0; row < highwayComplete.size(); row++)
        {
            for (i = playerPosition; i < lane1.size(); i++)
            {
                result += highwayComplete.get(row).get(i);
            }
            result += "\n";
        }

        return result;
    }

    private void writeFile()    // WRITING OUTPUT FILE WITH DISTANCE COVERED AND PLAYER OUTCOME
    {
        String temp = "";
        StringBuffer buffer = new StringBuffer(temp);
        int playerPosition = getPosition() + 1;

        if (playerPosition >= highway.getLength())
        {
            buffer.append(gameWin());
        }
        else
        {
            buffer.append(gameLose());
        }

        buffer.append("\n" + "You covered " + playerPosition + " sections of the highway.");

        String outputFile = "output.txt";
        FileIO io = new FileIO(outputFile);
        io.writeFile(buffer.toString());
    }

}
