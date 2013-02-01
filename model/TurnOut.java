package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtTrnListener;
import railway.events.types.EventTrnState;

/**
 * Clase para crear de para crear los dispositivos y registrar su estado. Esta 
 * clase implementa EvtTrnListener para poder realizar la gestión de sus 
 * eventos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class TurnOut implements EvtTrnListener {

    private int id;
    private boolean on;

    /**
     * Constructor de la clase, requiere un identificador para los dispositivos
     * y un estado.
     * @param id Identificador del dispositivo.
     * @param on Estado del dispositivo.
     */
    public TurnOut(int id, boolean on) {
        this.id = id;
        this.on = on;
    }

    /**
     * Getter del identificador del dispositivo.
     * @return Retorna el identificador del dispositivo.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el estado del dispositivo.
     * @return Retorna el valor del estado del dispositivo.
     */
    public boolean isOn() {
        return on;
    }

    /**
     * Setter del identificador del dispositivo.
     * @param id Asigna una identificación al dispositivo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter del estado del dispositivo.
     * @param on Asigna un valor al estado del dispositivo.
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public void changeState(EventTrnState event) {
        if (event.getId() == this.id) {
            on = event.isOn();
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Turnout id: " + id
                + " on: " + on;
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        changeState((EventTrnState) event);
    }
}
