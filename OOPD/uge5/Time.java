/**
 * Enumtype med valide tidspunkter. Indeholder også strenværdi af tidspunkt.
 */
public enum Time  {
    EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11"), TWELWE("12"),
    THIRTEEN("13"), FOURTEEN("14"), FIFTTEEN("15"),SIXTEEN("16"),
    SEVENTEEN("17");

    private String hour;

    private Time(String hour) {
        this.hour = hour;
    }

    /**
     * @return Strenværdi for time.
     */
    public String getHour() {
        return this.hour;
    }

    /**
     * @param retuner Time ud fra strenværdi.
     */
    public static Time fromString(String time) {
        if (time != null) {
            for (Time i : Time.values()) {
                if (time.equalsIgnoreCase(i.getHour())) {
                    return i;
                }
            }
        }
        return null;
    }

    /**
     * @param Time sammenlign to Time typer.
     */
    public boolean equals(Time other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        return this.getHour().equals(other.getHour());
    }
}
