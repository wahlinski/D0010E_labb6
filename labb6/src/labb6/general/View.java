package labb6.general;
import java.util.Observable;
import java.util.Observer;

/**
 * This class Observes state class and enables a view of state when simulator is running.
 *
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander
 */
@SuppressWarnings("deprecation")

public abstract class View implements Observer {

    /**
     * Abstract method that is called each time an {@link Event} occurs.
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public abstract void update(Observable o, Object arg);
}
