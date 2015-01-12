import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class FileReaderTest {

    /**
     * Test om vi læser lokalenavne korrekt
     */
    @Test
    public void testreadClassrooms() throws Exception {
        Assert.assertTrue("Ikke forventet antal rum",
                          FileReader.readClassrooms("lokaler_test.txt").size()==2);
    }

    /**
     * Samme for kurser.
     */
    @Test
    public void testreadCourses() throws Exception {
        Assert.assertTrue("Ikke forventet antal kurser",
                          FileReader.readCourses("kurser_test.txt").size()==2);
    }

    /**
     * Samme for skema, men test også om antal elementer er korrekt.
     */
    @Test
    public void testreadSessions() throws Exception {
        ArrayList<String[]> sess = FileReader.readSessions("skema_test.txt");
        Assert.assertTrue("Ikke forventet antal sessioner",
                          sess.size()==3);
        Assert.assertTrue("Ikke forventet antal rum",
                          sess.get(0).length==4);
        Assert.assertTrue("Ikke forventet antal rum",
                          sess.get(1).length==4);
        Assert.assertTrue("Ikke forventet antal rum",
                          sess.get(2).length==4);
    }
}
