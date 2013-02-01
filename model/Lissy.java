package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtLissyListener;
import railway.events.types.EventLissySignalP1;
import railway.events.types.EventLissySignalP2;

/**
 * Clase para crear una Lissy y registrar su estado. Esta clase implementa
 * EvtLissyListener para poder realizar la gestión de sus eventos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Lissy implements EvtLissyListener {

    /**
     * 
     */
    public int id;
    /**
     * 
     */
    public int lok;
    /**
     * 
     */
    public boolean direction;
    /**
     * 
     */
    public int speed;

    /**
     * Constructor de la clase, requiere un identificador de la Lissy pero no su
     * estado, este se registra con los eventos de cambio de estado de la Lissy.
     * @param id Identificación de la Lissy.
     */
    public Lissy(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    @Override
    public void changeLokDirec(EventLissySignalP1 event) {
        if (event.getId() == id) {
            lok = event.getLok();
            direction = event.isDirection();
        }
    }

    @Override
    public void changeSpeed(EventLissySignalP2 event) {
        if (event.getId() == id) {
            speed = event.getSpeed();
            lok = event.getLok();
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Lissy id: " + id
                + " locomotive: " + lok
                + " direction: " + direction
                + " speed: " + speed;
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        if (event instanceof EventLissySignalP1) {
            changeLokDirec((EventLissySignalP1) event);
        } else if (event instanceof EventLissySignalP2) {
            changeSpeed((EventLissySignalP2) event);
        }
    }
}
