package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos de estado de los dispositivos, desvios y semáforos
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventTrnState extends EventObject {

    private int id;
    private boolean on;

    /**
     * Costructor de la clase, hereda de EventObject. Esta clase permite obtener
     * todos los parámtros referentes a una vía rail.
     * @param id
     * @param on
     */
    public EventTrnState(int id, boolean on) {
        super(id);
        this.id = id;
        this.on = on;
    }

    /**
     * Getter de la identificación de un dispositivo.
     * @return Retorna el valor del la identificación del dispositivo.
     */
    public int getId() {
        return id;
    }

    /**
     * Información resumida de una clase.
     * @return Retorna la identificación del dispositivo y su estado.
     */
    @Override
    public String toString() {
        return "Turnout id: " + id
                + " on: " + on;
    }

    /**
     * 
     * @return
     */
    public boolean isOn() {
        return on;
    }
}
