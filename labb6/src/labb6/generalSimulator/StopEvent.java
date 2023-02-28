/**
* @author Wåhlin Filip, Abdi Abdi Mohamed, Härdelin Viggo, Melander Samuel
*/
package labb6.generalSimulator;

import labb6.util.EventName;

public class StopEvent extends Event{
    public StopEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
        setName(EventName.STOP);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
