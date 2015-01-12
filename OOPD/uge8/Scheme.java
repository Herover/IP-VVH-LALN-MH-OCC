import java.util.HashMap;
import java.util.Map;

/**
 * Skema bestående af DaySchedules
 */
public class Scheme {

    protected HashMap<Day, DaySchedule> schema;
    private final String name;

    public Scheme(String name) {
        this.name = name;
        this.schema = new HashMap<Day, DaySchedule>();
        for (Day i : Day.values()) {
            schema.put(i, new DaySchedule());
        }
    }

    /**
     * @return navn på skema
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param Day en dag på ugen
     * @return DayScedule ønsket dag.
     */
    public DaySchedule getDay(Day day) {
        return schema.get(day);
    }

    /**
     * @return alle dage på skema
     */
    public HashMap<Day, DaySchedule> getDays() {
        return schema;
    }
    /**
     * @param Session at tilføje
     */
    public void addSession(Session ses) {
        schema.get(ses.getDay()).addSession(ses);
    }

    /**
     * Print indhold af ugen, mest rettet mod fejlsøgning.
     */
    public void print() {
        for (Map.Entry<Day, DaySchedule> entry : schema.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
