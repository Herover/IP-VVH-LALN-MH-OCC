import java.util.HashMap;

/**
 * Created by Mirza on 16-12-2014.
 * Håndtere lokaler og kurser.
 */
public class Schedule {
    private HashMap<String, Course> courses;
    private HashMap<String, Classroom> rooms;

    public Schedule() {
        this.courses = new HashMap<String, Course>();
        this.rooms = new HashMap<String, Classroom>();
    }

    public void addCourse(Course c) {
        this.courses.put(c.getName(), c);
    }

    public Course getCourse(String name) {
        return this.courses.get(name);
    }

    public HashMap<String, Course> getCourses() {
        return this.courses;
    }

    public void addRoom(Classroom r) {
        this.rooms.put(r.getName(), r);
    }

    public Classroom getRoom(String name) {
        return this.rooms.get(name);
    }

    public void addSession(Session ses) {
        Course co = ses.getCourse();
        DaySchedule day = co.getDay(ses.getDay());
        day.addSession(ses);

        Classroom room = ses.getClassroom();
        room.addSession(ses);
    }
}
