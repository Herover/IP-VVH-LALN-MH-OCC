import java.util.HashMap;

/**
 * Created by Mirza on 16-12-2014.
 */
import java.util.HashMap;
/** TODO
 * lave tests
 */
public class Course {

    private HashMap<Day, DaySchedule> schema;
    private final String name;

    public Course(String name) {
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
}
