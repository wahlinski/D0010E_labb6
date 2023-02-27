package labb6.generalSimulator;

public class StopEvent extends Event{
    public StopEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.stop();
    }
}
