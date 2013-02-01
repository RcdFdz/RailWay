package railway.events.listeners;

import java.util.EventObject;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EventsListener {

    /**
     * 
     * @param event
     */
    public void notifyEvent(EventObject event);
}
