import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class CourseView implements View {

    private ScheduleModel model;
    private CourseController controller;

    private JFrame frame;
    private JLabel header;
    private JList courseList;
    private JPanel panel;

    public CourseView(ScheduleModel model) {
        this.model = model;
        initView();
    }

    private void initView() {
        panel = new JPanel();

        header = new JLabel("Kurser på kage");
        panel.add(header);

        panel = new JPanel();

        frame = new JFrame("Kurser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.setContentPane(panel);
        frame.pack();

        controller = new CourseController(model, this);

    }

    public void activate() {
        frame.setVisible(true);
        update();
    }

    public void deactivate() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void update() {
        ArrayList<Course> courses = model.getCourses();
        Course[] cArr = courses.toArray(new Course[courses.size()]);
        courseList = new JList<Course>(cArr);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.addListSelectionListener(new CourseSelection());
        JScrollPane scrollPane = new JScrollPane(courseList);
        panel.add(scrollPane);
    }

    class CourseSelection implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            Course course = (Course)courseList.getSelectedValue();
            System.out.println(course.getName());
        }
    }

}
