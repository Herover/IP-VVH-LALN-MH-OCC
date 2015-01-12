/**
 * A generic model interface for model-view-control
 *
 * @author Jon Sporring  {@literal<sporring@diku.dk>}
 * @version 1.0
 * @since 2015-01-05
 */
public interface Model {
  /**
   * Add an observer to the list of collaborators
   *
   * @param o any observer
   **/
  public void attach(Observer o);

  /**
   * Detach an observer from the list of collaborators
   *
   * @param o any observer
   **/
  public void detach(Observer o);

  /**
   * Notify all observers of changes
   **/
  public void notifyObservers();
  
}
