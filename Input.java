import java.util.Scanner;

public class Input //extends Game
{
    public Input()
    {

    }

    public char acceptCharinput(String displayMessage, int position)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return input.charAt(position);
    }

    public double acceptDoubleInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return Double.parseDouble(console.nextLine());
    }

    public int acceptIntegerInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return Integer.parseInt(input);
    }

    public String acceptStringInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return input;
    }
}
