import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirza on 16-12-2014.
 */
import java.util.HashMap;
/** TODO
 * lave tests
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

    public String getName() {
        return this.name;
    }

    public DaySchedule getDay(Day day) {
        return schema.get(day);
    }

    public HashMap<Day, DaySchedule> getDays() {
        return schema;
    }

    public void addSession(Session ses) {
        schema.get(ses.getDay()).addSession(ses);
    }

    public void print() {
        for (Map.Entry<Day, DaySchedule> entry : schema.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
