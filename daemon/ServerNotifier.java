/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package railway.daemon;

import java.util.EventObject;

import railway.events.listeners.EventsListener;


/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class ServerNotifier implements EventsListener {

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        System.out.println(event);
    }
}
