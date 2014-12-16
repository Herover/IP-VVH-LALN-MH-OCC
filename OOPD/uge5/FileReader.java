import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class FileReader {
    private static Scanner scanner;

    private static void createScanner(String f) {
        try {
            scanner = new Scanner(new File(f));
	    }
        catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
	    }
    }
    public static ArrayList<Classroom> readClassrooms(String fileName) {
        createScanner(fileName);
        ArrayList<Classroom> rooms = new ArrayList<Classroom>();
        while(scanner.hasNext()) {
            Classroom room = new Classroom(scanner.nextLine());
	    }
        scanner.close();
        return rooms;
    }

    public static ArrayList<Course> readCourses(String fileName) {
        createScanner(fileName);
        ArrayList<Course> courses = new ArrayList<Course>();
        while(scanner.hasNext()) {
            Course course = new Course(scanner.nextLine());
	    }
        scanner.close();
        return courses;
    }

    public static ArrayList<String[]> readSessions(String fileName) {
        createScanner(fileName);
        ArrayList<String[]> sessions = new ArrayList<String[]>();
        while(scanner.hasNext()) {
            String[] session = new String[4];
            String line = scanner.nextLine();
            session = line.split(" ");
	    }
        scanner.close();
        return sessions;
    }
}
