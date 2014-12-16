import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;

public class Main
{
    private static Schedule scheme = new Schedule();

    public static void main(String[] args)
    {

        ArrayList<Classroom> classrooms = FileReader.readClassrooms("lokaler.txt");
        for(Classroom room : classrooms) {
            scheme.addRoom(room);
            System.out.println(room.getName());
        }
        System.out.println("Lokaler: ");
        for (Map.Entry<String, Classroom> entry : scheme.getRooms().entrySet()) {
            System.out.println(entry.getKey());
        }
        
        ArrayList<Course> courses = FileReader.readCourses("kurser.txt");
        for(Course course : courses) {
            scheme.addCourse(course);
            System.out.println(course.getName());
        }
        System.out.println("Kurser: ");
        for (Map.Entry<String, Course> entry : scheme.getCourses().entrySet()) {
            System.out.println(entry.getKey());
        }

        ArrayList<String[]> sessions = FileReader.readSessions("skema.txt");
        for(String[] session : sessions) {
            System.out.print("Adding session to course '");
            System.out.print(scheme.getCourse(session[0]).getName());
            System.out.print("' in room '");
            System.out.print(scheme.getRoom(session[1]).getName());
            System.out.print("' at '"+Time.fromString(session[3]).name());
            System.out.print("' on '");
            System.out.print(Day.fromString(session[2]).name());
            System.out.println("'");
            
            scheme.addSession(new Session
                              (
                               scheme.getCourse(session[0]),
                               scheme.getRoom(session[1]),
                               Day.fromString(session[2]),
                               Time.fromString(session[3])
                               ));
        }

        
    }
}
