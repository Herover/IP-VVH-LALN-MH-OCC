import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class SchemeView implements View {

    private ScheduleModel model;
    private SchemeController controller;

    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JLabel header;
    private Object rowData[][];

    public SchemeView(ScheduleModel model) {
        this.model = model;
        model.attach(this);
        initView();
    }

    private void initView() {
        panel = new JPanel();

        frame = new JFrame("Skema");
        frame.setSize(600, 400);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controller = new SchemeController(model, this);

    }

    public void activate() {
        frame.setVisible(true);
        update();
    }

    public void deactivate() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void dataSetter(String inputDay) {
        int plus8 = 0;
        for(int i = 0; i < 7; i++) {
            plus8 = 0;
            for(int j = 0; j <= 8; j++) {
                plus8 = j + 8;
                rowData[j][i] =
                    getDay(Day.inputDay).getScheme().get(plus8.toString());
            }
        }


    }

    public void update() {
        panel.removeAll();

        Object columnNames[] = {"Monday", "Tuesday", "Wednesday", "Thursday",
                                "Friday", "Saturday", "Sunday" };


        for(int i = 0; i < 7; i++) {
            dataSetter((String)columnNames[i]);
        }


        JTable table = new JTable(rowData, columnNames);

        panel.add(table, BorderLayout.CENTER);

        Scheme scheme = model.getSelectedScheme();
        if(scheme == null) {
            header = new JLabel("Vælg først et skema");
        }
        else {
            header = new JLabel(scheme.getName());


        }
        panel.add(header, BorderLayout.PAGE_START);

        frame.revalidate();
        frame.repaint();
    }
}
