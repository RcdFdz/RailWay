package railway.events.controlsystem;

import railway.protocol.EventMessage;
import railway.protocol.Protocol;
import railway.support.Reader;
import railway.support.SyncronizedBuffer;

/**
 * Clase para el lector de eventos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventsGenerator implements Reader {

    private EventMessage message;
    private SyncronizedBuffer<EventMessage> messageEventsBuffer;

    /**
     * Constructor de la clase, 
     * @param messageEventsBuffer 
     */
    public EventsGenerator(SyncronizedBuffer<EventMessage> messageEventsBuffer) {
        this.messageEventsBuffer = messageEventsBuffer;
        this.message = null;
    }

    /**
     * Método que permite identificar el tamaño total del mensaje.
     * @param b Mensaje completo enviado por el decoder.
     */
    @Override
    public void newByteRead(int b) {
        if (Protocol.isOpcode(b)) {
            message = EventMessage.newEventMessage(b); // segons el protocol un missatge només pot tenir un opcode
        } else if (message != null) {
            message.newByte(b);
            if (message.completed()) {
                messageEventsBuffer.put(message);
                message = null;
            }
        }
    }
}
