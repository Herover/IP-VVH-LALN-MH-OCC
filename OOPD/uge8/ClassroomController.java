import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.*;
public class ClassroomController implements Controller {
    private ScheduleModel model;
    private ClassroomView view;
    private JTextField nameField;
    private JList roomList;

    public ClassroomController(ScheduleModel model, ClassroomView view) {
        this.model = model;
        this.view = view;

        model.attach(this);
    }

    public void update() {
        
    }

    public void listenToCreate(JButton saveButton, JTextField nameField) {
        ActionListener createListener = new AddNewListener(model,
                                                           view,
                                                           nameField);
        saveButton.addActionListener(createListener);
        this.nameField = nameField;
    }

    public void listenToSelect(JList roomList) {
        CourseSelectionListener selectListener = new CourseSelectionListener
            (model,view,roomList);
        roomList.addListSelectionListener(selectListener);
        this.roomList = roomList;
    }

    class AddNewListener implements ActionListener{
        ScheduleModel model;
        ClassroomView view;
        JTextField nameField;
        AddNewListener(ScheduleModel model, ClassroomView view,
                       JTextField nameField) {
            this.model = model;
            this.view = view;
            this.nameField = nameField;
        }
        public void actionPerformed(ActionEvent event) {
            Classroom room = new Classroom(nameField.getText());
            model.addRoom(room);
        }
    }

    class CourseSelectionListener implements ListSelectionListener {
        ScheduleModel model;
        ClassroomView view;
        JList roomList;
        CourseSelectionListener(ScheduleModel model, ClassroomView view,
                                JList roomList) {
            this.model = model;
            this.view = view;
            this.roomList = roomList;
        }
        public void valueChanged(ListSelectionEvent event) {
            Scheme room = (Scheme)roomList.getSelectedValue();
            model.setSelectedScheme(room);
            System.out.println(room.getName());
        }
    }
}

