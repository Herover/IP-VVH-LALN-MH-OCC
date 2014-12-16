import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class FileReader
{
    private static Scanner scanner;

    public static ArrayList<Classroom> readClassrooms()
    {
        try
	    {
            scanner = new Scanner(new File("lokaler.txt"));
	    }
        catch(Exception ex)
	    {
            System.out.println("Error: " + ex.getMessage());
	    }
        ArrayList<Classroom> rooms = new ArrayList<Classroom>();
        while(scanner.hasNext())
	    {
            Classroom room = new Classroom(scanner.nextLine());
	    }
        scanner.close();
        return rooms;
    }

    public static ArrayList<Course> readCourses()
    {
	try
	    {
            scanner = new Scanner(new File("kurser.txt"));
	    }
	catch(Exception ex)
	    {
		System.out.println("Error: " + ex.getMessage());
	    }
	ArrayList<Course> courses = new ArrayList<Course>();
	while(scanner.hasNext())
	    {
		Course course = new Course(scanner.nextLine());
	    }
	scanner.close();
	return courses;
    }

    public static ArrayList<String[]> readSessions()
    {
        try {
            scanner = new Scanner(new File("skema.txt"));
            }
        catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
	    }
        ArrayList<String[]> sessions = new ArrayList<String[]>();
        while(scanner.hasNext())
	    {
            String[] session = new String[4];
            String line = scanner.nextLine();
            session = line.split(" ");
	    }
        scanner.close();
        return sessions;
    }
}
