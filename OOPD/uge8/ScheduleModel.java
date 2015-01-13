import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ScheduleModel implements Model {

    private Schedule schedule;
    private Set<Observer> setOfObservers;
    private Course selectedCourse = null;

    public ScheduleModel() {
        setOfObservers = new HashSet<Observer>();
        schedule = new Schedule();
    }

    public void attach(Observer o) {
        setOfObservers.add(o);
    }

    public void detach(Observer o) {
        setOfObservers.remove(o);
    }

    public void notifyObservers() {
        for(Observer o : setOfObservers) {
            o.update();
        }
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (Map.Entry<String, Course> entry : schedule.getCourses().entrySet()) {
            courses.add(entry.getValue());
        }
        return courses;
    }

    public void addRooms(ArrayList<Classroom> classrooms) {
        for(Classroom room : classrooms) {
            schedule.addRoom(room);
        }
    }

    public void addCourse(Course course) {
        schedule.addCourse(course);
        notifyObservers();
        System.out.println(course);
    }

    public void addCourses(ArrayList<Course> courses) {
        for(Course course: courses) {
            addCourse(course);
        }
    }

    public void addSessions(ArrayList<String[]> sessions) {
        for(String[] session : sessions) {
            schedule.addSession(new Session
                                (
                                 schedule.getCourse(session[0]),
                                 schedule.getRoom(session[1]),
                                 Day.fromString(session[2]),
                                 Time.fromString(session[3])
                                 )
                                );
        }
    }

    public void setSelectedCourse(Course course) {
        selectedCourse = course;
        notifyObservers();
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }
}
