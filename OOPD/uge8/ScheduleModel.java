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

    public ArrayList<Classroom> getRooms() {
        ArrayList<Classroom> rooms = new ArrayList<Classroom>();
        for (Map.Entry<String, Classroom> entry : schedule.getRooms().entrySet()) {
            rooms.add(entry.getValue());
        }
        return rooms;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (Map.Entry<String, Course> entry : schedule.getCourses().entrySet()) {
            courses.add(entry.getValue());
        }
        return courses;
    }

    public Course getCourse(String name) {
        return schedule.getCourse(name);
    }

    public void addRooms(ArrayList<Classroom> classrooms) {
        for(Classroom room : classrooms) {
            schedule.addRoom(room);
        }
    }

    public void addRoom(Classroom room) {
        schedule.addRoom(room);
        notifyObservers();
        System.out.println(room);
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
        }
    }

    public void addSession(String[] session) {
        schedule.addSession(new Session(
                                        schedule.getCourse(session[0]),
                                        schedule.getRoom(session[1]),
                                        Day.fromString(session[2]),
                                        Time.fromString(session[3])
                                        )
                            );
    }

    public void setSelectedScheme(Scheme scheme) {
        selectedScheme = scheme;
        notifyObservers();
    }

    public Scheme getSelectedScheme() {
        return selectedScheme;
    }
}
