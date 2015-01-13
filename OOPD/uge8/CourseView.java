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
    private JPanel coursePanel;
    private JPanel controlsPanel;
    private JTextField newName;

    public CourseView(ScheduleModel model) {
        this.model = model;
        initView();
    }

    private void initView() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 0));
        coursePanel = new JPanel();
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2, 0));
        panel.add(coursePanel, BorderLayout.NORTH);
        panel.add(controlsPanel, BorderLayout.SOUTH);

        newName = new JTextField();
        controlsPanel.add(newName, BorderLayout.NORTH);

        JButton createButton = new JButton("Opret ny");
        controlsPanel.add(createButton,BorderLayout.SOUTH);

        frame = new JFrame("Kurser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);

        frame.setContentPane(panel);

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
        header = new JLabel("Kurser på kage");
        coursePanel.add(header, BorderLayout.NORTH);

        ArrayList<Course> courses = model.getCourses();
        Course[] cArr = courses.toArray(new Course[courses.size()]);
        courseList = new JList<Course>(cArr);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.addListSelectionListener(new CourseSelection());
        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        coursePanel.add(scrollPane, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    class CourseSelection implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            Course course = (Course)courseList.getSelectedValue();
            System.out.println(course.getName());
        }
    }

}
