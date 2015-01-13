import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintStream;

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
            System.out.println(ses.getCourse().getName()+ses.getClassroom().getName()+ses.getDay().toString()+ses.getTime().toString());
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

    /**
     * @param ses Session som skal fjernes fra skema filen
     * @param filename navnet paa filen der skal redigeres
     */
    public static void removeSession(Session ses, String filename) {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                if(!line.equals(ses.toString())) lines.add(line);
            }
            br.close();
            writeLines(lines, filename);
        }
        catch(Exception e) { System.out.println(e.getMessage());
            e.printStackTrace(new PrintStream(System.out)); }
    }

    /**
     * @param ses Session der skal skrives ud
     * @param filename navnet paa filen der skal redigers
     */
    public static void writeSession(Session ses, String filename) {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            lines.add(ses.toString());
            writeLines(lines, filename);
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }

    /**
     * @param lines linjer der skal skrives ud i en fil
     * @param filename navnet paa filen der skal redigers
     */
    private static void writeLines(ArrayList<String> lines, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for(String line : lines) {
                writer.println(line);
            }
            writer.close();
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }
}
