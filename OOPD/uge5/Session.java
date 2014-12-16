/**
 * Created by Mirza on 16-12-2014.
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
