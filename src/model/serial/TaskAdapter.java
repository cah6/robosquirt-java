package model.serial;

/**
 * Adapter that allows the serial model to talk to the task model through specified methods.
 * @author Christian
 *
 */
public interface TaskAdapter {

	/**
	 * When the serial model receives word that the arm completed the previous task, start the new one.
	 */
	public void executeNext();
}
