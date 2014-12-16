import java.util.HashMap;

/**
 * Created by Mirza on 16-12-2014.
 */

/** TODO
 * lave tests
 */
public class Course {

    private HashMap<Day, DaySchedule> schema;
    private final String name;

    public Course(String name) {
        this.name = name;
        this.schema = new HashMap<Day, DaySchedule>();
    }

    public String getName() {
        return name;
    }
}
