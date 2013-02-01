package railway.events.controlsystem;

import java.util.EventObject;
import java.util.Map;

import railway.protocol.EventMessage;
import railway.protocol.Protocol;
import railway.support.SyncronizedBuffer;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventsDispatcher implements Runnable {

    private SyncronizedBuffer<EventMessage> eventsMessages;
    private Map<Integer, SyncronizedBuffer<EventObject>> eventsObjectsBuffers;

    /**
     * 
     * @param eventsMessages
     * @param eventsObjectsBuffers
     */
    public EventsDispatcher(SyncronizedBuffer<EventMessage> eventsMessages,
            Map<Integer, SyncronizedBuffer<EventObject>> eventsObjectsBuffers) {
        super();
        this.eventsMessages = eventsMessages;
        this.eventsObjectsBuffers = eventsObjectsBuffers;
    }

    /**
     * 
     */
    @Override
    public void run() {
        while (true) {
            dispatch(eventsMessages.getNext());
        }
    }

    /**
     * 
     * @param eventMessage
     */
    protected void dispatch(EventMessage eventMessage) {
        EventObject event = Protocol.toEventObject(eventMessage);
        int eventType = eventMessage.getEventType();
        SyncronizedBuffer<EventObject> eventsObjectsBuffer = eventsObjectsBuffers.get(eventType);
        if (eventsObjectsBuffer != null) {
            eventsObjectsBuffer.put(event);
        }
    }
}
