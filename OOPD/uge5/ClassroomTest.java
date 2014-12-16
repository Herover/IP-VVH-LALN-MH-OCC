import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassroomTest {

    /**
     * Test hvad der sker hvis to session eksisterer samtidig.
     * JUnit skal forvente en exception fra denne test.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testaddSessionException() throws Exception {
        Classroom room = new Classroom("");
        Session sesm8_1 = new Session(null, room, Day.MONDAY, Time.EIGHT);
        Session sesm8_2 = new Session(null, room, Day.MONDAY, Time.EIGHT);
        room.addSession(sesm8_1);
        room.addSession(sesm8_2);
    }

    /**
     * Tester om sessioner der kan eksistere ved siden af hinanden.
     * Der forventes ingenting, så hvis der kastes en exception er der en fejl.
     */
    @Test
    public void testaddSessionNothing() throws Exception {
        Classroom room = new Classroom("");
        Session sest8 = new Session(null, room, Day.MONDAY, Time.EIGHT);
        Session sest9 = new Session(null, room, Day.MONDAY, Time.NINE);
        room.addSession(sest8);
        room.addSession(sest9);
    }
}
