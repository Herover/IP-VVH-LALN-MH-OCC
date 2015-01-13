import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.*;
public class CourseController implements Controller {
    private ScheduleModel model;
    private CourseView view;
    private JTextField nameField;
    private JList courseList;

    public CourseController(ScheduleModel model, CourseView view) {
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

    public void listenToSelect(JList courseList) {
        CourseSelectionListener selectListener = new CourseSelectionListener
            (model,view,courseList);
        courseList.addListSelectionListener(selectListener);
        this.courseList = courseList;
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
        }
    }

    class CourseSelectionListener implements ListSelectionListener {
        ScheduleModel model;
        CourseView view;
        JList courseList;
        CourseSelectionListener(ScheduleModel model, CourseView view,
                                JList courseList) {
            this.model = model;
            this.view = view;
            this.courseList = courseList;
        }
        public void valueChanged(ListSelectionEvent event) {
            Scheme course = (Scheme)courseList.getSelectedValue();
            model.setSelectedScheme(course);
            System.out.println(course.getName());
        }
    }

}
