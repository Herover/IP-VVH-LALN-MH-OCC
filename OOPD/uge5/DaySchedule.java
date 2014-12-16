import java.util.HashMap;
import java.util.ArrayList;
/**
 * Dags-skema.
 * Behandler tidstabel over en dag.
 */
public class DaySchedule {
    private HashMap<Time, ArrayList<Session>> schema;

    public DaySchedule() {
        this.schema = new HashMap<Time, ArrayList<Session>>();
        for (Time i : Time.values()) {
            schema.put(i, new ArrayList<Session>());
        }
    }

    /**
     * @return dagens tidstabel.
     */
    public HashMap<Time, ArrayList<Session>> getSchema() {
        return schema;
    }

    /**
     * Tilføjer en session til et tidspunkt
     */
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

    /**
     * Retunerer ArrayList over sessioner på given time.
     * @param Time tidspunkt
     * @return ArrayList<Session>
     */
    public ArrayList<Session> getTime(Time time) {
        return schema.get(time);
    }

    /**
     * @param Session der skal tilføjes.
     * Benytter addTime til at tilføje tiden.
     */
    public void addSession(Session ses) {
        this.setTime(ses.getTime(), ses);
    }
}
