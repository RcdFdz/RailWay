package railway.events.listeners;

import railway.events.types.EventSensorState;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtSensorListener extends EventsListener {

    /**
     * 
     * @param event
     */
    public void changeState(EventSensorState event);
}
