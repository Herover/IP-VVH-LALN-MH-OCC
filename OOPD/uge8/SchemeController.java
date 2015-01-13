import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.*;
public class SchemeController implements Controller {
    private ScheduleModel model;
    private SchemeView view;

    public SchemeController(ScheduleModel model, SchemeView view) {
        this.model = model;
        this.view = view;

        model.attach(this);
    }

    public void update() {
        
    }

}
