/**
 * A generic view interface for model-view-control
 *
 * @author Jon Sporring  {@literal<sporring@diku.dk>}
 * @version 1.0
 * @since 2015-01-05
 */
public interface View extends Observer {
  /**
   * activate the view
   **/
  public void activate();


  /**
   * deactivate the view
   **/
  public void deactivate();
}
