package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos de cambio de dirección de una locomotora.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLokDirection extends EventObject {

    private int slot;
    private boolean direction;
    private String func;

    /**
     * Costructor de la clase, hereda de EventObject, permite recibir un slot  
     * y la dirección de la locomotora.
     * @param slot Dirección de memoria del decoder donde se guardan los parametros de una locomotora
     * @param direction Sentido de circulación de una locomotora.
     * @param func 
     */
    public EventLokDirection(int slot, boolean direction, int func) {
        super(slot);
        this.slot = slot;
        this.direction = direction;
        this.func = Integer.toBinaryString(func);
    }

    /**
     * Getter de la dirección de memoria donde se guardan los parametros de 
     * una locomotora.
     * @return Retorna la dirección de memoria del decoder donde se guardan los parametros de una locomotora
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Getter del sentido de circulación de una locomotora.
     * @return Retorna el sentido de circulación de una locomotora.
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * 
     * @return
     */
    public String getFunc() {
        return func;
    }

    /**
     * Información resumida de una clase.
     * @return Retorna la información referente al slot de la locomotora y la dirección.
     */
    @Override
    public String toString() {
        return "LocomotiveDirFunc slot: " + slot
                + " direction: " + direction
                + " functions: " + func;
    }
}
