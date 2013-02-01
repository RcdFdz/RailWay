package railway.support;

import java.util.LinkedList;
import java.util.List;

/**
 * @param <Element> 
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class SyncronizedBuffer<Element> {

    private List<Element> events;

    /**
     * 
     */
    public SyncronizedBuffer() {
        super();
        this.events = new LinkedList<Element>();
    }

    /**
     * 
     * @return
     */
    public synchronized Element getNext() {
        // Wait until messages is not empty
        while (events.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Element message = events.remove(0);
        notifyAll();
        return message;
    }

    /**
     * 
     * @param message
     */
    public synchronized void put(Element message) {
        events.add(message);
        // Notify consumer that status has changed.
        notifyAll();
    }
}
