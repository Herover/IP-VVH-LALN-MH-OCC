import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirza on 16-12-2014.
 */
import java.util.HashMap;
/** TODO
 * lave tests
 */
public class Course extends Scheme {

    public Course(String name) {
        super(name);
    }

    @Override
    public void addSession(Session ses) {
        for (Map.Entry<Day, DaySchedule> entry : schema.entrySet()) {
            if(entry.getValue().sessionExists(ses)) {
                throw new IllegalArgumentException("Session already added!");
            }
        }
        schema.get(ses.getDay()).addSession(ses);
    }

}
