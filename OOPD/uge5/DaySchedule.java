import java.util.HashMap;
import java.util.ArrayList;
/**
 * Created by Mirza on 16-12-2014.
 */

/** TODO
 * lave tests
 */

public class DaySchedule {
    private HashMap<Time, ArrayList<Session>> schema;

    public DaySchedule() {
        this.schema = new HashMap<Time, ArrayList<Session>>();
        for (Time i : Time.values()) {
            schema.put(i, new ArrayList<Session>());
        }
    }

    public HashMap<Time, ArrayList<Session>> getSchema() {
        return schema;
    }

    public void setTime(Time time, Session session) {
        schema.get(time).add(session);
    }

    /**
     * Check if equaliant session exists at given time
     * @return boolean
     */
    public boolean sessionExists(Session session) {
        for(Session ses : schema.get(session.getTime())) {
            if(
               ses.getCourse().getName().equals
               (session.getCourse().getName())
               &&
               ses.getClassroom().getName().equals
               (session.getClassroom().getName())
               ) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Session> getTime(Time time) {
        return schema.get(time);
    }

    public void addSession(Session ses) {
        this.setTime(ses.getTime(), ses);
    }
}
