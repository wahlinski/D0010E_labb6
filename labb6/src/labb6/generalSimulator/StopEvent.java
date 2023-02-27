package labb6.generalSimulator;

import labb6.util.EventNames;

public class StopEvent extends Event{
    public StopEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
        setName(EventNames.STOP + "");
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
