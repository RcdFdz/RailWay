package railway.events.controlsystem;

import java.util.EventObject;
import java.util.Map;
import java.util.HashMap;
import java.util.TooManyListenersException;

import railway.events.listeners.EventsListener;
import railway.protocol.EventMessage;
import railway.protocol.Protocol;
import Port.Port;
import railway.support.SyncronizedBuffer;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventsControlSystem {

    private SyncronizedBuffer<EventMessage> eventMessagesBuffer;
    private EventsGenerator eventsGeneretor; // Event messages productor
    private Map<Integer, SyncronizedBuffer<EventObject>> eventsObjectsBuffers;
    private EventsDispatcher eventsDispatcher; // Event messages consumer & Event objects productor
    private Map<Integer, EventsHandler> eventsHandlers; // Event objects consumers
    private Thread threadEventsDispatcher;
    private Thread[] threadsEventsHandlers;
    private Port port;

    /**
     * 
     * @param port
     */
    public EventsControlSystem(Port port) {
        super();
        this.port = port;
        this.eventMessagesBuffer = new SyncronizedBuffer<EventMessage>();
        this.eventsGeneretor = new EventsGenerator(this.eventMessagesBuffer);
        createEventsObjectsBuffers();
        this.eventsDispatcher = new EventsDispatcher(this.eventMessagesBuffer, this.eventsObjectsBuffers);
        createEventsHandlers();
        createThreads();
    }

    private void createEventsObjectsBuffers() {
        this.eventsObjectsBuffers = new HashMap<Integer, SyncronizedBuffer<EventObject>>();
        for (int eventType : Protocol.eventTypes) {
            eventsObjectsBuffers.put(eventType, new SyncronizedBuffer<EventObject>());
        }
    }

    private void createEventsHandlers() {
        this.eventsHandlers = new HashMap<Integer, EventsHandler>();
        for (int eventType : Protocol.eventTypes) {
            eventsHandlers.put(eventType, new EventsHandler(eventsObjectsBuffers.get(eventType)));
        }
    }

    private void createThreads() {
        this.threadEventsDispatcher = new Thread(this.eventsDispatcher);
        this.threadsEventsHandlers = new Thread[this.eventsHandlers.size()];
        int i = 0;
        for (EventsHandler eventHandler : eventsHandlers.values()) {
            this.threadsEventsHandlers[i++] = new Thread(eventHandler);
        }
    }

    /**
     * 
     * @param eventType
     * @param objectId
     * @param l
     */
    public void addListener(int eventType, int objectId, EventsListener l) {
        EventsHandler handler = eventsHandlers.get(eventType);
        if (handler != null) {
            handler.addListener(objectId, l);
        }
    }

    /**
     * 
     */
    public void start() {
        for (Thread eventHandlerThread : this.threadsEventsHandlers) {
            eventHandlerThread.start();
        }
        this.threadEventsDispatcher.start();
        try {
            this.port.setAsyncReader(this.eventsGeneretor);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    @SuppressWarnings("deprecation")
    public void stop() {
        this.port.removeAsyncReader();
        this.threadEventsDispatcher.stop();
        for (Thread eventHandlerThread : this.threadsEventsHandlers) {
            eventHandlerThread.stop();
        }
    }
}
