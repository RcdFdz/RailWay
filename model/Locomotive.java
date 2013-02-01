package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtLokListener;
import railway.events.types.EventLokDirection;
import railway.events.types.EventLokFstate;
import railway.events.types.EventLokGlobalState;
import railway.events.types.EventLokSpeed;

/**
 * Clase para crear una locomotora y registrar su estado. Esta clase implementa
 * EvtLokListener para poder realizar la gestión de sus eventos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Locomotive implements EvtLokListener {

    private int id;
    private int slot;
    private int speed;
    private boolean direction;
    private String func;

    /**
     * Constructor de la clase, requiere una identificación de la locomotora 
     * creada y una dirección de memoria del decoder donde se guardan los 
     * parámetros de una locomotora
     * @param id
     * @param slot
     */
    public Locomotive(int id, int slot) {
        this.id = id;
        this.slot = slot;
    }

    /**
     * Getter de las funciones especiales de la locomotora.
     * @return Retorna el estado de las funciones especiales de la locomotora.
     */
    public String getFunc() {
        return func;
    }

    /**
     * Getter de la identificación de la locomotora.
     * @return Retorna el valor de la identificación de una locomotora.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter de la dirección de memoria del decoder donde se guardan los 
     * prámetros de una locomotora.
     * @return Retorna la dirección de memoria del decoder donde se guardan los parámetros de una locomotora.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Obtiene el sentido de circulación de una locomotora.
     * @return Retorna el valor del sentido de circulación de una locomotora.
     */
    public boolean isDirection() {
        return direction;
    }

    /**
     * Getter de la velocidad de una locomotora.
     * @return Retorna el valor de la velocidad de una locomotora.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter de las funciones especiales de una locomotora.
     * @param func 
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * Setter de la identificación de una locomotora.
     * @param id Asigna la identificación de una locomotora.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter de la dirección de memoria del decoder donde se guardan los 
     * prámetros de una locomotora.
     * @param slot Asigna el valor la dirección de memoria del decoder donde se guardan los parámetros de una locomotora.
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Setter de la velocidad de una locomotora.
     * @param speed Asigna la velocidad de una locomotora.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Setter del sentido de circulación de la locomotora.
     * @param direction Asigna el sentido de circulación de una locomotora.
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    @Override
    public void changeState(EventLokGlobalState event) {
        if (event.getSlot() == slot) {
            speed = event.getSpeed();
            direction = event.isDirection();
            func = event.getFunc();
        }
    }

    @Override
    public void changeSpeed(EventLokSpeed event) {
        if (event.getSlot() == this.slot) {
            speed = event.getSpeed();
        }
    }

    @Override
    public void changeDirection(EventLokDirection event) {
        if (event.getSlot() == this.slot) {
            direction = event.getDirection();
            func = event.getFunc();


        }
    }

    @Override
    public void changeFstate(EventLokFstate event) {
        if (event.getSlot() == this.slot) {
            ;
        }
    }

    /**
     * Información resumida de una clase.
     * @return Retorna la identificación de ua locomotora, la dirección de memoria del decoder donde se guardan los parámetros de una locomotora, su velocidad dirección y el valor de sus funciones especiales.
     */
    @Override
    public String toString() {
        return "LocomotiveGlobalState id: " + getId()
                + " slot: " + getSlot()
                + " speed: " + getSpeed()
                + " direction: " + isDirection()
                + " func: " + getFunc();
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        if (event instanceof EventLokGlobalState) {
            changeState((EventLokGlobalState) event);
        } else if (event instanceof EventLokSpeed) {
            changeSpeed((EventLokSpeed) event);
        } else if (event instanceof EventLokDirection) {
            changeDirection((EventLokDirection) event);
        }
    }
}
