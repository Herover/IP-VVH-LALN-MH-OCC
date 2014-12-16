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
	for(Classroom room : classrooms) 
	    {
		scheme.addRoom(room);
	    }
	
	ArrayList<Course> courses = FileReader.readCourses("kurser.txt");
	for(Course course : courses) 
	    {
		scheme.addCourse(course);
	    }
	
	ArrayList<String[]> sessions = FileReader.readSessions("skema.txt");
	for(String[] session : sessions)
	    {
		scheme.addSession(new Session
				  (
				   scheme.getCourse(session[0]),
				   scheme.getRoom(session[1]),
				   Day.fromString(session[2]),
				   Time.fromString(session[3])
                                   ));
	    }
        System.out.println("Kurser: ");
        for (Map.Entry<String, Course> entry : scheme.getCourses().entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
