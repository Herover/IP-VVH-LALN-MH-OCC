/**
 * Created by Mirza on 16-12-2014.
 */
public class Classroom extends Scheme {

    public Classroom(String name) {
        super(name);
    }


    @Override
    public void addSession(Session ses) {
        DaySchedule day = schema.get(ses.getDay());
        if(day.getTime(ses.getTime()).size() != 0) {
            throw new IllegalArgumentException("Room is already in use!");
        }
        day.addSession(ses);
    }
}
