import java.util.Scanner;
public class Validation
{
    public Validation()
    {

    }

    public boolean isBlank(String value)
    {
        boolean blank = false;
        if (value.trim().length() == 0)
            blank = true;
        return blank;
    }

    public boolean stringLengthWithinRange(String value, int min, int max)
    {
        boolean withinRange = false;
        if ((value.trim().length() >= min) && (value.trim().length() <= max))
            withinRange = true;
        return withinRange;
    }

    public boolean integerWithinRange(int num, int min, int max)
    {
        boolean withinRange = false;
        if (num >= min && num <= max)
            withinRange = true;
        return withinRange;
    }
}
