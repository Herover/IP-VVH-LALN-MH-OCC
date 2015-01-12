import java.util.HashMap;
/**
 * Enum type med dage.
 */
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    /**
     * Oversæt streng til enum type.
     * Går ud fra at teksten er på dansk.
     * @param streng med dag
     */
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
