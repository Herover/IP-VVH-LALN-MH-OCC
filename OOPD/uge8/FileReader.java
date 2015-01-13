import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
/**
 * Sørger for at opretter værdier til at indsætte i skema.
 * Behøves ikke at bruges som objekt.
 */
public class FileReader {
    private static Scanner scanner;

    /**
     * Bruges til at specificere fil der skal læses fra, kun intern.
     * @param String filnavn.
     */
    private static void createScanner(String f) throws Exception {
        scanner = new Scanner(new File(f));
    }

    /**
     * Læs lokaler fra fil.
     * @param String fil at læse fra.
     * @return ArrayList<Classroom> liste med lokaler.
     */
    public static ArrayList<Classroom> readClassrooms(String fileName)
        throws Exception {
        createScanner(fileName);
        ArrayList<Classroom> rooms = new ArrayList<Classroom>();
        while(scanner.hasNext()) {
            Classroom room = new Classroom(scanner.nextLine());
            rooms.add(room);
        }
        scanner.close();
        return rooms;
    }

    /**
     * Læs kurser fra fil.
     * @param String filnavn.
     * @return ArrayList<Course> liste med kurser.
     */
    public static ArrayList<Course> readCourses(String fileName)
        throws Exception {
        createScanner(fileName);
        ArrayList<Course> courses = new ArrayList<Course>();
        while(scanner.hasNext()) {
            Course course = new Course(scanner.nextLine());
            courses.add(course);
        }
        scanner.close();
        return courses;
    }

    /**
     * Læs lokale bookinger fra fil.
     * @param String filnavn.
     * @return ArrayList<Sessions> en liste af bestaende af sessions laest
     * fra filen fileName
     */
    public static ArrayList<Session> readSessions(String fileName)
        throws Exception {
        createScanner(fileName);
        ArrayList<Session> sessions = new ArrayList<Session>();
        while(scanner.hasNext()) {
            sessions.add(Session.fromString(scanner.nextLine()));
        }
        scanner.close();
        return sessions;
    }
}
