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
    private JTextField newNameField;

    public CourseView(ScheduleModel model) {
        this.model = model;
        model.attach(this);
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

        newNameField = new JTextField();
        controlsPanel.add(newNameField, BorderLayout.NORTH);

        JButton createButton = new JButton("Opret ny");
        controlsPanel.add(createButton,BorderLayout.SOUTH);


        frame = new JFrame("Kurser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);

        frame.setContentPane(panel);

        controller = new CourseController(model, this);
        controller.listenToCreate(createButton, newNameField);

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
        coursePanel.removeAll();
        System.out.println("update");
        header = new JLabel("Kurser på kage");
        coursePanel.add(header, BorderLayout.NORTH);

        ArrayList<Course> courses = model.getCourses();
        Course[] cArr = courses.toArray(new Course[courses.size()]);
        courseList = new JList<Course>(cArr);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //courseList.addListSelectionListener(new CourseSelection());
        controller.listenToSelect(courseList);

        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        coursePanel.add(scrollPane, BorderLayout.SOUTH);
        frame.revalidate();
        courseList.revalidate();
        courseList.repaint();
        frame.repaint();
    }



}
