import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScheduleTest {

    /**
     * Test om man kan hente et oprettet kursus ud igen.
     */
    @Test
    public void testgetCourse() throws Exception {
        Schedule sch = new Schedule();
        Course course = new Course("test");
        sch.addCourse(course);
        Assert.assertTrue("Ikke samme kursus!",
                          sch.getCourse("test").getName() == "test");
    }

    /**
     * Test fejlbehandling hvis et kursus ikke eksisterer.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testgetCourseNothing() throws Exception {
        Schedule sch = new Schedule();
        if(sch.getCourse("test") == null) {
            throw new Exception("Smed ikke en forventet undtagelse, men modtog null!");
        }
    }
}
