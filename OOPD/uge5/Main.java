import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Main 
{

    private static Schedule scheme = new Schedule();

    public static void main(String[] args) 
    {

	ArrayList<Classroom> classrooms = FileReader.readClassrooms();
	for(Classroom room : classrooms) 
	    {
		scheme.addRoom(room);
	    }
	
	ArrayList<Course> courses = FileReader.readCourses();
	for(Course course : courses) 
	    {
		scheme.addCourse(course);
	    }
	
	ArrayList<String[]> sessions = FileReader.readSessions();
	for(String[] session : sessions)
	    {
		scheme.addSession(new Session
				  (
				   new Course(session[0]),
				   new Classroom(session[1]),
				   Day.fromString(session[2]),
				   Time.fromString(session[3])
					    ));
	    }
    }
}
