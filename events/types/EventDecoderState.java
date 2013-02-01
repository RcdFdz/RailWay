package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos de estados de un decoder
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventDecoderState extends EventObject {

    private int decoId;
    private int state;

    /**
     * Costructor de la clase, hereda de EventObject y permite crear un decoder 
     * y un estado.
     * @param decoId Identificador del decoder que creamos.
     * @param state Estado del decoder que creamos.
     */
    public EventDecoderState(int decoId, int state) {
        super(decoId);
        this.decoId = decoId;
        this.state = state;
    }

    /**
     * Getter del estado.
     * @return Retorna el estado del decoder.
     */
    public int getState() {
        return state;
    }

    /**
     * Getter de la identificación del decoder.
     * @return Retorna la identificación del decoder.
     */
    public int getDecoId() {
        return decoId;
    }

    /**
     * Información resumida de la clase.
     * @return Retorna el estado determinado de un decoder.
     */
    @Override
    public String toString() {
        return "Decoder id: " + decoId
                + " status: " + state;
    }
}
