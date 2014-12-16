import java.util.HashMap;

/**
 * Håndtere lokaler og kurser.
 */
public class Schedule {
    private HashMap<String, Course> courses;
    private HashMap<String, Classroom> rooms;

    public Schedule() {
        this.courses = new HashMap<String, Course>();
        this.rooms = new HashMap<String, Classroom>();
    }

    /**
     * @param kursus.
     */
    public void addCourse(Course c) {
        this.courses.put(c.getName(), c);
    }

    /**
     * Find kursus us fra navn. Hvis kurset ikke findes kastes
     * IllegalArgumentException.
     * @param String navn på kurses at retunere.
     * @return Course kursus med givet navn.
     */
    public Course getCourse(String name) {
        if(this.courses.get(name) == null) {
            throw new IllegalArgumentException("No such '"+name+"' course");
        }
        return this.courses.get(name);
    }

    /**
     * @return HashMap<String, Course> alle kurser.
     */
    public HashMap<String, Course> getCourses() {
        return this.courses;
    }

    /**
     * Tilføj klasserum
     * @param Classroom lokale
     */
    public void addRoom(Classroom r) {
        this.rooms.put(r.getName(), r);
    }

    /**
     * @param String navn
     * @return Classroom lokale med navn.
     */
    public Classroom getRoom(String name) {
        if(this.rooms.get(name) == null) {
            throw new IllegalArgumentException("No such '"+name+"' room");
        }
        return this.rooms.get(name);
    }

    /**
     * @return HashMap<String, Classroom> alle lokaler.
     */
    public HashMap<String, Classroom> getRooms() {
        return this.rooms;
    }

    /**
     * @param session at tilføje.
     */
    public void addSession(Session ses) {
        try{
            Classroom room = ses.getClassroom();
            room.addSession(ses);
            Course co = ses.getCourse();
            DaySchedule day = co.getDay(ses.getDay());
            day.addSession(ses);
        }
        catch(Exception e) {
            System.out.println("Could not add session to room because: "+e.getMessage());
        }

    }
}
