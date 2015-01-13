import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class ClassroomView implements View {

    private ScheduleModel model;
    private ClassroomController controller;

    private JFrame frame;
    private JLabel header;
    private JList roomList;
    private JPanel panel;
    private JPanel roomPanel;
    private JPanel controlsPanel;
    private JTextField newNameField;

    public ClassroomView(ScheduleModel model) {
        this.model = model;
        model.attach(this);
        initView();
    }

    private void initView() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 0));
        roomPanel = new JPanel();
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2, 0));
        panel.add(roomPanel, BorderLayout.NORTH);
        panel.add(controlsPanel, BorderLayout.SOUTH);

        newNameField = new JTextField();
        controlsPanel.add(newNameField, BorderLayout.NORTH);

        JButton createButton = new JButton("Opret ny");
        controlsPanel.add(createButton,BorderLayout.SOUTH);


        frame = new JFrame("Lokaler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);

        frame.setContentPane(panel);

        controller = new ClassroomController(model, this);
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
        roomPanel.removeAll();
        System.out.println("update");
        header = new JLabel("Kurser på kage");
        roomPanel.add(header, BorderLayout.NORTH);

        ArrayList<Classroom> rooms = model.getRooms();
        Classroom[] cArr = rooms.toArray(new Classroom[rooms.size()]);
        roomList = new JList<Classroom>(cArr);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //courseList.addListSelectionListener(new CourseSelection());
        controller.listenToSelect(roomList);

        JScrollPane scrollPane = new JScrollPane(roomList);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        roomPanel.add(scrollPane, BorderLayout.SOUTH);
        frame.revalidate();
        roomList.revalidate();
        roomList.repaint();
        frame.repaint();
    }



}

