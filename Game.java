import java.util.ArrayList;

public class Game extends Highway{
    private final String inputFile = "vehicles.txt";
    private final String outputFile = "output.txt";
    private String playerName;
    private DifficultyLvl difficultyLvl;
    private Player player;
    private Vehicle vehicle;
    private ArrayList<String> vehicleType;


    public Game()
    {
        playerName = "";
        difficultyLvl = new DifficultyLvl();
        player = new Player();
        vehicle = new Vehicle();
        vehicleType = new ArrayList<String>();
    }

    public Game(String playerName, DifficultyLvl difficultyLvl, Player player, Vehicle vehicle, ArrayList<String> vehicleType)
    {
        this.playerName = playerName;
        this.difficultyLvl = difficultyLvl;
        this.player = player;
        this.vehicle = vehicle;
        this.vehicleType = vehicleType;
    }

    public DifficultyLvl getDifficultyLvl()
    {
        return difficultyLvl;
    }

    public Player getPlayer()
    {
        return player;
    }


    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public ArrayList<String> getVehicleType()
    {
        return vehicleType;
    }

    public void setDifficultyLvl(DifficultyLvl difficultyLvl)
    {
        this.difficultyLvl = difficultyLvl;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public void setVehicleType(ArrayList<String> vehicleType)
    {
        this.vehicleType = vehicleType;
    }


    public static void main(String[] args) // call this public void start()
    {
        Game game = new Game();

        game.readVehicleFile();
        game.inputPlayerName();
        game.selectDifficultyLvl();
        game.selectVehicle();
        game.generateHighwayLanes();
        game.setPlayerPosition();
        game.inputMove();



    }

    /*
    Reading the file "vehicles.txt", to store the information for later.
    */
    private void readVehicleFile()
    {
        FileIO io = new FileIO(inputFile);
        ArrayList<String> vehicleType = new ArrayList<String>();
        int i = -1;

        String[] info = io.readFile().split(","); // Adds info into array

        for (i = 0; i < info.length; i++)  // Sorting each vehicle information into ArrayLists
        {
            vehicleType.add(info[i]);
        }
        setVehicleType(vehicleType);
    }

    /*
        User will be asked to input a player name, from 3 to 12 characters long only.
        It will loop until a valid name is entered, with validation checks for blank
        inputs and inputs not within range.
    */
    private void inputPlayerName()
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        String name = "";

        do
        {
            name = input.acceptStringInput("Enter your name thief!");
            if(valid.stringLengthWithinRange(name, 3, 12))
            {
                setPlayerName(name);
                System.out.println("Welcome " + this.playerName + "!");
                proceed = true;
            }
            else if (valid.isBlank(name))
            {
                System.out.println("Your name cannot be blank! Please re enter your name thief!");
            }
            else
            {
                System.out.println("AH! Your name is not valid! Please enter a name using 3 to 12 characters.");
            }
        } while(proceed == false);

    }

    /*
        User will be asked to choose their level of difficulty from either easy, medium, or hard.
    */
    private void selectDifficultyLvl()
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int lvl = -1;

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Well " + this.playerName + ", I heard you are on the run from the police...");
        System.out.println("1 - EASY" + "\n" + "Highway Length = 10 to 15" + ", Max Fuel = 100%" + ", Total obstacles = 12");
        System.out.println("2 - MEDIUM" + "\n" + "Highway Length = 15 to 30" + ", Max Fuel = 80%" + ", Total obstacles = 24");
        System.out.println("3 - HARD" + "\n" + "Highway Length = 30 to 50" + ", Max Fuel = 50%" + ", Total obstacles = 45");
        System.out.println();

        DifficultyLvl easy = new DifficultyLvl(10, 15, 1.0, 12);
        DifficultyLvl med = new DifficultyLvl(15, 30, 0.8, 24);
        DifficultyLvl hard = new DifficultyLvl(30, 50, 0.5, 45);

        do
        {
            try
            {
                lvl = input.acceptIntegerInput("Enter 1, 2, or 3 to choose the difficulty of your chase!");

                if (valid.integerWithinRange(lvl, 1, 3))
                {
                    if (lvl == 1) {
                        //highway.generateLaneLength(easy.getMinLvl(), easy.getMaxLvl());
                        setLength( generateLaneLength(easy.getMinLvl(), easy.getMaxLvl()) );
                        vehicle.setMaxFuelPercentage(easy.getMaxFuelPercentLvl());
                        setTotalObstacles(easy.getTotalObsLvl());
                        System.out.println("Ah! Easy! Great choice!");
                        proceed = true;
                    }
                    else if (lvl == 2) {
                        //highway.generateLaneLength(med.getMinLvl(), med.getMaxLvl());
                        setLength( generateLaneLength(med.getMinLvl(), med.getMaxLvl()) );
                        vehicle.setMaxFuelPercentage(med.getMaxFuelPercentLvl());
                        setTotalObstacles(med.getTotalObsLvl());
                        System.out.println("Medium? As a true criminal would pick!");
                        proceed = true;
                    }
                    else if (lvl == 3) {
                        // highway.generateLaneLength(hard.getMinLvl(), hard.getMaxLvl());
                        setLength( generateLaneLength(hard.getMinLvl(), hard.getMaxLvl()) );
                        vehicle.setMaxFuelPercentage(hard.getMaxFuelPercentLvl());
                        setTotalObstacles(hard.getTotalObsLvl());
                        System.out.println("Hard?! The cops are on our tail!");
                        proceed = true;
                    }
                    else {
                        System.out.println("AH! That level doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                        proceed = false;
                    }
                }
                else {
                    System.out.println("AH! That level doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                    proceed = false;
                }
            }
            catch (Exception e)
            {
                System.out.println("Input cannot be a character. Please enter valid number within the range.");
                proceed = false;
            }
        } while(proceed == false);
    }

    /*
    User is able to choose their vehicle and see the total length and max fuel they have.
    */

    private void selectVehicle()
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int vehicleSlct = -1;
        Game game = new Game();


        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();

        ArrayList<String> motorcycleInfo = new ArrayList<>();
        ArrayList<String> carInfo = new ArrayList<>();               // Arrays to store the values of the Vehicles
        ArrayList<String> garbageTruckInfo = new ArrayList<>();

        game.readVehicleFile();

        int i = -1;
        for (i = 0; i <= 3; i++)  // Sorting each vehicle information into each vehicle type.
        {
            motorcycleInfo.add(vehicleType.get(i));
        }

        for (i = 4; i <= 7; i++)
        {
            carInfo.add(vehicleType.get(i));
        }

        for (i = 8; i <= 11; i++)
        {
            garbageTruckInfo.add(vehicleType.get(i));
        }

        Vehicle motorcycle = new Vehicle(motorcycleInfo.get(0), Integer.parseInt(motorcycleInfo.get(1)), Integer.parseInt(motorcycleInfo.get(2)), Integer.parseInt(motorcycleInfo.get(3)), vehicle.getMaxFuelPercentage());
        Vehicle car = new Vehicle(carInfo.get(0), Integer.parseInt(carInfo.get(1)), Integer.parseInt(carInfo.get(2)), Integer.parseInt(carInfo.get(3)), vehicle.getMaxFuelPercentage());
        Vehicle garbageTruck = new Vehicle(garbageTruckInfo.get(0), Integer.parseInt(garbageTruckInfo.get(1)), Integer.parseInt(garbageTruckInfo.get(2)), Integer.parseInt(garbageTruckInfo.get(3)), vehicle.getMaxFuelPercentage());


        do
        {
            try
            {
                System.out.print("1 - " + motorcycle.getType().toUpperCase() + "\n" + "Boost speed: " + motorcycle.getBstSpd() + ", Max fuel: " + motorcycle.getMaxFuel() + ", Max damage: " + motorcycle.getMaxDmg() + "\n");
                System.out.print("2 - " + car.getType().toUpperCase() + "\n" + "Boost speed: " + car.getBstSpd() + ", Max fuel: " + car.getMaxFuel() + ", Max damage: " + car.getMaxDmg() + "\n");
                System.out.print("3 - " + garbageTruck.getType() .toUpperCase()+ "\n" + "Boost speed: " + garbageTruck.getBstSpd() + ", Max fuel: " + garbageTruck.getMaxFuel() + ", Max damage: " + motorcycle.getMaxDmg() + "\n");

                vehicleSlct = input.acceptIntegerInput("Enter 1, 2, or 3 to choose the difficulty of your chase!");

                if (valid.integerWithinRange(vehicleSlct, 1, 3)) {
                    if (vehicleSlct == 1) {
                        vehicle.setBstSpd(motorcycle.getBstSpd());
                        vehicle.setMaxFuel(motorcycle.getMaxFuel());
                        vehicle.setMaxDmg(motorcycle.getMaxDmg());
                        System.out.println("A motorcycle?! Offt, you sure like it speedy! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + vehicle.getBstSpd() + " highway sections.");
                        proceed = true;
                    } else if (vehicleSlct == 2) {
                        vehicle.setBstSpd(car.getBstSpd());
                        vehicle.setMaxFuel(car.getMaxFuel());
                        vehicle.setMaxDmg(car.getMaxDmg());
                        System.out.println("Ahhh... a classic car chase! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + vehicle.getBstSpd() + " highway sections.");
                        proceed = true;
                    } else if (vehicleSlct == 3) {
                        vehicle.setBstSpd(garbageTruck.getBstSpd());
                        vehicle.setMaxFuel(garbageTruck.getMaxFuel());
                        vehicle.setMaxDmg(garbageTruck.getMaxDmg());
                        System.out.println("A garbage truck?! Well that's one way to out run the cops! Quickly start your race when you are ready!");
                        System.out.println("Your boost speed is " + vehicle.getBstSpd() + " highway sections.");
                        proceed = true;
                    } else {
                        System.out.println("AH! That vehicle doesn't exist! Please enter 1, 2, or 3 to choose your vehicle.");
                        proceed = false;
                    }
                }
                else {
                    System.out.println("AH! That vehicle doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                    proceed = false;
                }
            }
            catch (Exception e)
            {
                System.out.println("Input cannot be a character. Please enter valid number within the range.");
                proceed = false;
            }

            displayLength();
            vehicle.generateMaxFuel();
            vehicle.displayStartFuel();

        } while(proceed == false);
    }

    public void generateHighwayLanes()
    {
        ArrayList<String> laneContent = new ArrayList<String>();

        int totalObstacles = -1;
        int totalLength = -1;
        totalObstacles = getTotalObstacles();
        totalLength = (getLength() - 3)*3; // NOTE: the first 3 lanes are blank

        int startOfLane = -1;
        int laneContents = -1;

        // START OF LANES
        for (startOfLane = 0; startOfLane < 3; startOfLane++) //
        {
            lane1.add(generateHighwaySection());
            lane2.add(generateHighwaySection());
            lane3.add(generateHighwaySection());
        }

        // LANE CONTENTS - generate a highwayLane (which has obstacles or lane to split and then add back into the lanes)
        for (laneContents = 0; laneContents < totalLength; laneContents++) //
        {
            int thirdChance = generateRandomNum(1, 91);; // Chance is from 1 to 90 as 91 is excluded

            if (thirdChance >= 1 && thirdChance <= 30)
            {
                int o = 1;
                if (o <= totalObstacles)  // making sure no more than the total objects are printed
                {
                    //Obstacle rndObs = generateRandomObs();
                    laneContent.add(generateRandomObs());
                    o++;
                }
                else
                {
                    laneContent.add(generateHighwaySection());
                }
            }
            else
            {
                laneContent.add(generateHighwaySection());
            }
        }

        /* CHECKING HIGHWAY IS CORRECT
        System.out.println(laneContent.size());

        for (int s = 0; s < laneContent.size(); s++) // Printing out one lane
        {
            System.out.print(laneContent.get(s));
        } */


        // SPLITTING AND ADDING THE REST OF THE LANE CONTENT TO lane1, 2, and 3
        for (int finalLane = 0; finalLane < (laneContent.size() / 3); finalLane++)
        {
            lane1.add(laneContent.get(0 + finalLane));
            lane2.add(laneContent.get((laneContent.size() / 3) + finalLane));
            lane3.add(laneContent.get(((laneContent.size() / 3)*2) + finalLane));
        }

        //CHECKING LANES ARE CORRECT
        /*for (int lane1fin = 0; lane1fin < 10; lane1fin++)
        {
            System.out.print(lane1.get(lane1fin));
        }
        System.out.println();
        for (int lane2fin = 0; lane2fin < 10; lane2fin++)
        {
            System.out.print(lane2.get(lane2fin));
        }
        System.out.println();
        for (int lane3fin = 0; lane3fin < 10; lane3fin++)
        {
            System.out.print(lane3.get(lane3fin));
        }*/

    }

    public void setPlayerPosition()
    {
        //SETTING PLAYER IN LANE
        player.setCurrFuel(vehicle.getMaxFuel());
        player.setCurrDmg(0);
        player.setPosition(0);
        Player player1 = new Player(player.getCurrFuel(), player.getCurrDmg(), player.getPosition());
        lane2.set(0, "@ ");
    }

    public void printHighway()
    {
        /* int playerPosition = player.getPosition();
        while (playerPosition < (playerPosition + 9))
        {
            System.out.print(lane1.get(playerPosition));
            playerPosition++;
        }

        System.out.println();

        while (playerPosition < (playerPosition + 9))
        {
            System.out.print(lane2.get(playerPosition));
            playerPosition++;
        }

        System.out.println();

        while (playerPosition < (playerPosition + 9))
        {
            System.out.print(lane3.get(playerPosition));
            playerPosition++;
        } */

        for (int columns = 0; columns < 3; columns++) {
            for (int row = 0; row < 10; row++) {
                System.out.print(lane1.get(row));
                //System.out.print(lane2.get(i));
                //System.out.print(lane3.get(i));
            }
            System.out.println();
        }
    }
    public void inputMove()
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean proceed = false;
        int move = -1;

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Are you ready " + this.playerName + " ? Better start moving");
        System.out.println("Player: @, Fuel: F, Roadblock: B, Tyre spikes: S, Open manhole: O");
        System.out.println();
        printHighway();

        do
        {
            try
            {
                //printHighway();
                move = input.acceptIntegerInput("Enter 1 - Move forward, 2 - Swerve up, 3 - Swerve down or 4 - Boost to move your vehicle!");

                if (valid.integerWithinRange(move, 1, 4))
                {
                    if (move == 1) {
                        player.moveFwd();
                        player.displayPlayerInfo();
                        printHighway();
                        proceed = true;
                    }
                    else if (move == 2) {
                        player.moveUp();
                        player.displayPlayerInfo();
                        printHighway();
                        proceed = true;
                    }
                    else if (move == 3) {
                        player.moveDown();
                        player.displayPlayerInfo();
                        printHighway();
                        proceed = true;
                    }
                    else if (move == 4) {
                        player.moveBoost();
                        player.displayPlayerInfo();
                        printHighway();
                        proceed = true;
                    }
                    else {
                        System.out.println("AH! That move doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                        proceed = false;
                    }
                }
                else {
                    System.out.println("AH! That move doesn't exist! Please enter 1, 2, or 3 to choose your difficulty.");
                    proceed = false;
                }
            }
            catch (Exception e)
            {
                System.out.println("Ah you cannot, move there. Please enter valid movement choice.");
                proceed = false;
            }
        } while(proceed == false);
    }






}
