package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos del estado global de una locomotora.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLokGlobalState extends EventObject {

    private int id;
    private int slot;
    private int speed;
    private boolean direction;
    private String func;

    /**
     * Costructor de la clase, hereda de EventObject. Esta clase permite obtener
     * todos los parámtros referentes a una locomotora.
     * @param id Identificación de una locomotora.
     * @param slot Dirección de la memoria del decoder donde se guarda el estado de una locomotora.
     * @param speed Velocidad de una locomotora.
     * @param direction Sentido de circulación de una locomotora.
     * @param func Información de los estados especiales de una locomotora.
     */
    public EventLokGlobalState(int id, int slot, int speed, boolean direction, int func) {
        super(id);
        this.id = id;
        this.slot = slot;
        this.speed = speed;
        this.direction = direction;
        this.func = Integer.toBinaryString(func);
    }

    /**
     * Sentido de circulación de la locomotora.
     * @return Retorna el sentido de circulación.
     */
    public boolean isDirection() {
        return direction;
    }

    /**
     * Setter del sentido de circulación de la locomotora.
     * @param direction Asigna un sentido de circulación a una locomotora.
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * Getter de las funciones especiales de la locomotora.
     * @return Retorna el estado de las funciones especiales de la locomotora.
     */
    public String getFunc() {
        return func;
    }

    /**
     * Setter de las funciones especiales de la locomotora.
     * @param func Asigna el estado de las funciones especiales de la locomotora.
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * Getter de la identificación de una locomotora.
     * @return Retorna la identificación de una locomotora.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de la identificación de una locomotora.
     * @param id Retorna la identificación de una locomotora.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la dirección de memoria donde se guardan los parámetros de 
     * una locomotora. 
     * @return Retorna la dirección de memoria donde se guardan los parámetros de una locomotora.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * 
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Setter de la dirección de memoria donde se guardan los parámetros de 
     * una locomotora. 
     * @return Asigna la dirección de memoria donde se guardan los parámetros de una locomotora.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter de la velocidad de una locomotora.
     * @param speed Asigna una velocidad de locomotora.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Información resumida de una clase. 
     * @return Retorna la dirección de memoria del decoder donde se guardan los parámetros de una locomotora, el id de la locomotora, su velocidad y su dirección.
     */
    @Override
    public String toString() {
        return "LocomotiveGlobalState id: " + id
                + " slot: " + slot
                + " speed: " + speed
                + " direction: " + direction
                + " func: " + func;
    }
}
