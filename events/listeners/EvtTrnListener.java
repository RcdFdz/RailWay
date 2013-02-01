package railway.events.listeners;

import railway.events.types.EventTrnState;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtTrnListener extends EventsListener {

    /**
     * 
     * @param event
     */
    public void changeState(EventTrnState event);
}
