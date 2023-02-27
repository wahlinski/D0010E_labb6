package labb6.generalSimulator;

public class StartEvent extends Event {

    public StartEvent(EventQueue eventQueue, double time) {
        super(eventQueue, time);
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        state.begin();
    }
}
