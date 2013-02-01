package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtDecoderListener;
import railway.events.types.EventDecoderState;

/**
 * Clase para crear un decoder y registrar su estado. Esta clase implementa
 * EvtDecoderListener para poder realizar la gestión de sus eventos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Decoder implements EvtDecoderListener {

    int decoId;
    int state = 0;

    /**
     * Constructor de la clase, requiere un identificador del decoder, pero no
     * su estado, este se registra con los eventos de cambio de estado.
     * @param decoId Identificación del decoder
     */
    public Decoder(int decoId) {
        this.decoId = decoId;
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return decoId;
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        state = ((EventDecoderState) event).getState();
        System.out.println("Hello");
    }

    /**
     * Información resumida de una clase.
     * @return Retorna la identificación del decoder y su estado.
     */
    @Override
    public String toString() {
        return "Decoder id: " + decoId
                + " status: " + state;
    }
}
