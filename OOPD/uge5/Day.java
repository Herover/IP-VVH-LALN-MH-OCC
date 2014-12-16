/**
 * Created by Mirza on 16-12-2014.
 */
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Day fromString(String day) {
	if (day != null) {
	    for (Day d : Day.values()) {
		if (d.name().equalsIgnoreCase(day)) {
		    return d;
		}
	    }
	}
	return null;
    }
}
