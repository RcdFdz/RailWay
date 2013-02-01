package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos del estado de las funciones especiales de las 
 * locomotoras.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLokFstate extends EventObject {

    private int slot;
    private int fstate;

    /**
     * Costructor de la clase, hereda de EventObject y permite recibir un slot 
     * y las funciones especiales de la locomotora.
     * @param slot Dirección de memoria donde se guardan los parámetros de una locomotora.
     * @param fstate Estado de las funciones especiales de las locomotoras.
     */
    public EventLokFstate(int slot, int fstate) {
        super(slot);
        this.slot = slot;
        this.fstate = fstate;
    }

    /**
     * Getter de la dirección de memoria donde se guardan los parámetros de 
     * una locomotora.
     * @return Retorna la dirección de momoria del decoder donde se guardan los parámetros de una locomotora.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Getter de las funciones especiales de una locomotora.
     * @return Retorna el estado de las funciones especiales de una locomotora.
     */
    public int getFstate() {
        return fstate;
    }

    /**
     * Información resumida de una clase. 
     * @return Retorna la información de la dirección de memoria del decoder donde se guardan los parámetros de una locomotora y las funciones especiales de la locomotora.
     */
    @Override
    public String toString() {
        return "LocomotiveFunctionState slot: " + slot
                + " fuction status: " + fstate;
    }
}
