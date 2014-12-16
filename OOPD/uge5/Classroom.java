import java.util.HashMap;
/**
 * Created by Mirza on 16-12-2014.
 */
public class Classroom {

    private HashMap<Day, DaySchedule> schema;
    private final String name;

    public Classroom(String name) {
        this.name = name;
        this.schema = new HashMap<Day, DaySchedule>();
        for (Day i : Day.values()) {
            schema.put(i, new DaySchedule());
        }
    }

    public String getName() {
        return name;
    }

    public DaySchedule getDay(Day day) {
        return schema.get(day);
    }

}
