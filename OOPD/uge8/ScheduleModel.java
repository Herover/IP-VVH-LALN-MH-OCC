import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ScheduleModel implements Model {

    private Schedule schedule;
    private Set<Observer> setOfObservers;
    private Scheme selectedScheme = null;

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

    public void addSessions(ArrayList<Session> sessions) {
        System.out.println("Adder "+sessions.size());
        for(Session session : sessions) {
            schedule.addSession(session);
            System.out.println(session.getCourse().getName()+","+session.getClassroom().getName());
        }
    }

    public void setSelectedScheme(Scheme scheme) {
        selectedScheme = scheme;
        notifyObservers();
    }

    public Scheme getSelectedScheme() {
        return selectedScheme;
    }
}
