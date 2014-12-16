/**
 * Created by Mirza on 16-12-2014.
 */
public enum Time  {
    EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11"), TWELWE("12"), THIRTEEN("13"), FOURTEEN("14"), FIFTTEEN("15"),
    SIXTEEN("16"), SEVENTEEN("17");

    private String hour;

    private Time(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return this.hour;
    }

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
