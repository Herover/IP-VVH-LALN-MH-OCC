public class CourseController implements Controller {
    private ScheduleModel model;
    private CourseView view;

    public CourseController(ScheduleModel model, CourseView view) {
        this.model = model;
        this.view = view;
    }

    public void update() {
        
    }

}
