import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;

public class Main
{
    private static ScheduleModel model;

    public static void main(String[] args)
    {
        model = new ScheduleModel();

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
        for(String[] sessionstr : sessions) {
            model.addSession(sessionstr);
        }
        CourseView cov = new CourseView(model);
        cov.activate();

        ClassroomView crv = new ClassroomView(model);
        crv.activate();

        SchemeView sv = new SchemeView(model);
        sv.activate();

    }
}
