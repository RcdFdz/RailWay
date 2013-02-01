package railway.events.controlsystem;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import railway.events.listeners.EventsListener;
import railway.support.SyncronizedBuffer;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventsHandler implements Runnable {

    private Map<Integer, List<EventsListener>> listeners;
    private SyncronizedBuffer<EventObject> events;

    /**
     * Constructor de la clase encargado de crear la  de datos de los 
     * listener de los diferentes dispositivos, desvios, locomotoras, decoder,
     * sensores...
     * @param events 
     */
    public EventsHandler(SyncronizedBuffer<EventObject> events) {
        this.listeners = new HashMap<Integer, List<EventsListener>>();
        this.events = events;
    }

    /**
     * 
     */
    @Override
    public void run() {
        while (true) {
            handleEvent(events.getNext());
        }
    }

    /**
     * Método que permite añadir un listener de eventos para los desvios y 
     * semáforos
     * @param listener Listener de dispositivo que se quiere añadir.
     * @param id Identificador del listener.
     */
    public void addListener(int id, EventsListener listener) {
        List<EventsListener> list = listeners.get(id);
        if (list == null) {
            list = new ArrayList<EventsListener>();
            listeners.put(id, list);
        }
        list.add(listener);
    }

    /**
     * 
     * @param id
     * @param listener
     */
    public void removeListener(int id, EventsListener listener) {
        List<EventsListener> list = listeners.get(id);
        if (list != null) {
            list.remove(listener);
        }
    }

    /**
     * 
     * @param event
     */
    public void handleEvent(EventObject event) {
        List<EventsListener> list = listeners.get(event.getSource());
        if (list != null) {
            for (EventsListener listener : list) {
                listener.notifyEvent(event);
            }
        }
    }
}
