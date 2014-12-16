/**
 * Klasserum med tidstabel
 */
public class Classroom extends Scheme {

    public Classroom(String name) {
        super(name);
    }

    /**
     * Tilføjer session til klasserum.
     * Smider exception IllegalArgumentException hvis rummet allerede er i brug
     * på det tidspunkt sessionen tilføjes.
     * @param sessionen der tilføjes.
     */
    @Override
    public void addSession(Session ses) {
        DaySchedule day = schema.get(ses.getDay());
        if(day.getTime(ses.getTime()).size() != 0) {
            throw new IllegalArgumentException("Room is already in use!");
        }
        day.addSession(ses);
    }
}
