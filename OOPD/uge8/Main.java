import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;

public class Main
{
    private static Schedule scheme = new Schedule();

    public static void main(String[] args)
    {

        ArrayList<Classroom> classrooms = null;
        try {
            classrooms =
                FileReader.readClassrooms("lokaler.txt");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke læse lokaler: " + e.getMessage());
        }
        for(Classroom room : classrooms) {
            scheme.addRoom(room);
        }

        ArrayList<Course> courses = null;
        try {
            courses = FileReader.readCourses("kurser.txt");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke læse kurser: "+e.getMessage());
        }
        for(Course course : courses) {
            scheme.addCourse(course);
        }

        ArrayList<String[]> sessions = null;
        try {
            sessions = FileReader.readSessions("skema.txt");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke læse skema: "+e.getMessage());
        }
        for(String[] session : sessions) {
            scheme.addSession(new Session
                              (
                               scheme.getCourse(session[0]),
                               scheme.getRoom(session[1]),
                               Day.fromString(session[2]),
                               Time.fromString(session[3])
                               )
                              );
        }

        System.out.println("Lokaler: ");
        for (Map.Entry<String, Classroom> entry : scheme.getRooms().entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("Kurser: ");
        for (Map.Entry<String, Course> entry : scheme.getCourses().entrySet()) {
            System.out.println(entry.getKey());
        }


    }
}
