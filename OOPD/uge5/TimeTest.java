import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void testGetHour() throws Exception {

        Time time8 = Time.EIGHT;
        Time time9 = Time.NINE;

        org.junit.Assert.assertTrue("Tiderne er ikke ens",
                                    time8.getHour().equals("8"));
        org.junit.Assert.assertTrue("Tiderne er ikke ens",
                                    time9.getHour().equals("9"));

    }

    @Test
    public void testFromString() throws Exception {

        Time timeEight = Time.EIGHT;
        Time time8 = Time.fromString("8");
        Time time15 = Time.fromString("15");

        Assert.assertTrue("Ikke ens", timeEight.equals(time8));
        Assert.assertFalse("Ens", timeEight.equals(time15));


    }


}
