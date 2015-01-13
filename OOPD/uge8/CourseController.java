import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class CourseController implements Controller {
    private ScheduleModel model;
    private CourseView view;
    private JTextField nameField;

    public CourseController(ScheduleModel model, CourseView view) {
        this.model = model;
        this.view = view;
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

    class AddNewListener implements ActionListener{
        ScheduleModel model;
        CourseView view;
        JTextField nameField;
        AddNewListener(ScheduleModel model, CourseView view,
                       JTextField nameField) {
            this.model = model;
            this.view = view;
            this.nameField = nameField;
        }
        public void actionPerformed(ActionEvent event) {
            Course course = new Course(nameField.getText());
            model.addCourse(course);
            nameField.setText("");
        }
    }
}
