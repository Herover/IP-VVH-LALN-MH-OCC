/**
 * Session/booking af lokale.
 * Indeholder reference til klasserum og kursus samt tidspunkt og ugedag.
 */
public class Session {
    private Time time;
    private Day day;
    private Course course;
    private Classroom classroom;

    public Session(Course course, Classroom classroom, Day day, Time time) {
        this.course = course;
        this.classroom = classroom;
        this.day = day;
        this.time = time;
    }

    /**
     * Opretter et Session objekt fra tekst.
     * Format er "kursus lokale dag tid".
     * @param line tekst session oprettes ud fra
     */
    public static Session fromString(String line) {
	String[] s = line.split(" ");
	Course course = new Course(s[0]);
	Classroom room = new Classroom(s[1]);
	return new Session(course, room, Day.fromString(s[2]), Time.fromString(s[3]));
    }

    /**
     * Laver Session om til en linje tekst.
     * Format er "kursus lokale dag tid".
     */
    public String toString() {
	    return getCourse().getName() + " " +
		      getClassroom().getName() + " " +
		      Day.toString(getDay()) + " " +
		      getTime().getHour();
    }

    /**
     * @return tidspunkt
     */
    public Time getTime() {
        return time;
    }

    /**
     * @param Time set tidspunkt
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * @retrun ugedag
     */
    public Day getDay() {
        return day;
    }

    /**
     * @param Day sæt ugedag
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * @return Course relateret kursus.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param Course sæt kursus.
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * @return Classroom relateret lokale
     */
    public Classroom getClassroom() {
        return classroom;
    }

    /**
     * @param Classroom sæt klasselokale.
     */
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
