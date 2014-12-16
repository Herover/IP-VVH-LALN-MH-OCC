import java.util.Scanner;
import java.io.File;

public class Main {

    private static Schedule scheme = new Schedule();

    public static void main(String[] args) {

        Classroom room;

        room = new Classroom("Rum1");
        scheme.addRoom(room);
        room = new Classroom("Rum2");
        scheme.addRoom(room);
        room = new Classroom("Rum3");
        scheme.addRoom(room);

        Course course;
        course = new Course("kursus1");
        scheme.addCourse(course);
        course = new Course("kursus2");
        scheme.addCourse(course);
        course = new Course("kursus3");
        scheme.addCourse(course);

        //Classroom 1, room 1, monday, 8
        Session c1r1m8 = new Session(
                                     Time.EIGHT,
                                     Day.MONDAY,
                                     scheme.getCourse("kursus1"),
                                     scheme.getRoom("Rum1")
                                     );
        scheme.addSession(c1r1m8);

    }
}
