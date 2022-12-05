import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO
{
    private String fileName;

    public FileIO()
    {
        fileName = "";
    }

    public FileIO(String newFileName)
    {
        fileName = newFileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String newFileName)
    {
        fileName = newFileName;
    }


    public String readFile()
    {
        String contents = "";
        if(fileName.trim().length() > 0)
        {
            try
            {
                FileReader inputFile = new FileReader(fileName);
                Scanner sc = new Scanner(inputFile);
                while(sc.hasNextLine())
                {
                    contents += sc.nextLine() + ",";
                }
                inputFile.close();
            }
            catch(FileNotFoundException exception)
            {
                System.out.println(fileName + " not found");
            }
            catch(IOException exception)
            {
                System.out.println("An unexpected I/O error was encountered!");
            }
            catch(Exception exception)
            {
                System.out.println("An expection among I/O error was encountered!");
            }
        }
        else
            System.out.println("Please Enter a FileName");
        return contents;
    }

    public void writeFile(String contents)
    {
        try
        {
            if(fileName.trim().length() > 0)
            {
                PrintWriter outputFile = new PrintWriter(fileName);
                outputFile.println(contents);
                outputFile.close();
            }
            else
                System.out.println("Please Enter a FileName");
        }
        catch(IOException exception)
        {
            System.out.println("An error was encountered while trying to write the data to the " + fileName + " file.");
        }
    }
}
