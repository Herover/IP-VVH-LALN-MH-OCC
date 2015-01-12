import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;

public class Main
{
    private static ScheduleModel model = new ScheduleModel();

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
        model.addRooms(classrooms);

        ArrayList<Course> courses = null;
        try {
            courses = FileReader.readCourses("kurser.txt");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke læse kurser: "+e.getMessage());
        }
        model.addCourses(courses);

        ArrayList<String[]> sessions = null;
        try {
            sessions = FileReader.readSessions("skema.txt");
        }
        catch(Exception e) {
            System.out.println("Kunne ikke læse skema: "+e.getMessage());
        }
        model.addSessions(sessions);


    }
}
