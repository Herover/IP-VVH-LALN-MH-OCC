import java.util.HashMap;
/**
 * Created by Mirza on 16-12-2014.
 */
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Day fromString(String day) {
	if (day != null) {
	    /*for (Day d : Day.values()) {
		if (d.name().equalsIgnoreCase(day)) {
		    return d;
		}
                }*/
            switch(day.toLowerCase()) {
            case "mandag": return Day.MONDAY;
            case "tirsdag": return Day.TUESDAY;
            case "onsdag": return Day.WEDNESDAY;
            case "torsdag": return Day.THURSDAY;
            case "fredag": return Day.FRIDAY;
            case "lørdag": return Day.SATURDAY;
            case "søndag": return Day.SUNDAY;
            }
	}
	return null;
    }
}
