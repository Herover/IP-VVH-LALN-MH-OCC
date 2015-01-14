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



    public void update() {
        panel.removeAll();


        Scheme scheme = model.getSelectedScheme();

        //String[] columnNames = {"Monday", "Tuesday", "Wednesday", "Thursday",
        //                        "Friday", "Saturday", "Sunday" };
        String[] columnNames = {"Mandag", "Tirsdag","Onsdag","Torsdag","Fredag","Lørdag","Søndag" };

        String[][] rowData;
        rowData = new String[7][9];

     



        if(scheme == null) {
            header = new JLabel("Vælg først et skema");
        }
        else {
            header = new JLabel(scheme.getName());
            
            for(int d = 0; d < 7; d++) {
                //System.out.println(scheme.getName());
                DaySchedule day = scheme
                    .getDay(Day.fromString(columnNames[d].toLowerCase()));
                for(int t = 8; t < 17; t++) {
                    ArrayList<Session> ses = day
                        .getTime(Time.fromString(Integer.toString(t)));
                    if(ses.size() > 0)
                        rowData[d][t-8] = ses.get(0).getClassroom().getName();
                    else
                        rowData[d][t-8] = "";
                }
            }
        }
        JTable table = new JTable(rowData, columnNames);
        panel.add(table.getTableHeader());
        panel.add(table, BorderLayout.CENTER);

        panel.add(header, BorderLayout.PAGE_START);

        frame.revalidate();
        frame.repaint();
    }
}
