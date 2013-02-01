package railway.events.listeners;

import railway.events.types.EventSlotConsist;
import railway.events.types.EventSlotState;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtSlotListener extends EventsListener {

    /**
     * 
     * @param event
     */
    public void changeState(EventSlotState event);

    /**
     * 
     * @param event
     */
    public void changeConsist(EventSlotConsist event);
}
