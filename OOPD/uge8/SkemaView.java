import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class SkemaView implements View {

    private ScheduleModel model;
    private SkemaController controller;

    private JFrame frame;


    public SkemaView(ScheduleModel model) {
        this.model = model;
        model.attach(this);
        initView();
    }

    private void initView() {
        frame = new JFrame("GridLayout Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);

        frame.setLayout(new GridLayout(3, 2));
        frame.add(new JButton("Button 1"));
        frame.add(new JButton("Button 2"));
        frame.add(new JButton("Button 3"));
        frame.add(new JButton("Button 4"));
        frame.add(new JButton("Button 5"));
        frame.add(new JButton("Button 6"));
        frame.add(new JButton("Button 7"));
        frame.add(new JButton("Button 8"));
        frame.pack();
        frame.setVisible(true);
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
        frame.revalidate();
        frame.repaint();
    }
}
