import java.util.Scanner;
import java.io.File;

public class Main {

    private static Schedule scheme = new Schedule();
    private static Scanner roomScanner;
    private static Scanner courseScanner;

    public static void main(String[] args) {

        try{
            roomScanner = new Scanner(new File("lokaler.txt"));
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        while(roomScanner.hasNext()) {
            String name = roomScanner.nextLine();
            System.out.println(name);
            Classroom room = new Classroom(name);
            scheme.addRoom(room);
        }

        try{
            courseScanner = new Scanner(new File("kurser.txt"));
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        while(courseScanner.hasNext()) {
            String name = courseScanner.nextLine();
            System.out.println(name);
            Course course = new Course(name);
            scheme.addCourse(course);
        }

    }
}
