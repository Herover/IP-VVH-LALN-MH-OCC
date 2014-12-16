import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayTest {

    @Test
    public void testFromString() throws Exception {
        Day mon = Day.MONDAY;
        Day tue = Day.TUESDAY;
        Day wed = Day.WEDNESDAY;
        Day thu = Day.THURSDAY;
        Day fri = Day.FRIDAY;
        Day sat = Day.SATURDAY;
        Day sun = Day.SUNDAY;

        Assert.assertTrue("Ikke korrekt dag", mon == Day.fromString("mandag"));
        Assert.assertTrue("Ikke korrekt dag", tue == Day.fromString("tirsdag"));
        Assert.assertTrue("Ikke korrekt dag", wed == Day.fromString("onsdag"));
        Assert.assertTrue("Ikke korrekt dag", thu == Day.fromString("torsdag"));
        Assert.assertTrue("Ikke korrekt dag", fri == Day.fromString("fredag"));
        Assert.assertTrue("Ikke korrekt dag", sat == Day.fromString("lørdag"));
        Assert.assertTrue("Ikke korrekt dag", sun == Day.fromString("søndag"));
    }

}
