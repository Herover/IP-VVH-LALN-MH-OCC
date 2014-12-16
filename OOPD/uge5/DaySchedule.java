import java.util.HashMap;

/**
 * Created by Mirza on 16-12-2014.
 */

/** TODO
 * lave tests
 */

public class DaySchedule {
    private HashMap<Time, Session> schema;

    public DaySchedule() {
        this.schema = new HashMap<Time, Session>();
        for (Time i : Time.values()) {
            schema.put(i, null);
        }
    }

    public HashMap<Time, Session> getSchema() {
        return schema;
    }

    public void setTime(Time time, Session session) {
        schema.put(time, session);
    }

    public Session getTime(Time time) {
        return schema.get(time);
    }

    public void addSession(Session ses) {
        this.setTime(ses.getTime(), ses);
    }
}
