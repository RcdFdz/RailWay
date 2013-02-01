package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos del segundo sensor de una Lissy, permite obtener la 
 * identificación de una locomotora, además de su velocidad en el momento de
 * pasar por el segundo sensor de una Lissy determinada.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLissySignalP2 extends EventObject {

    private int id;
    private int lok;
    private int speed;

    /**
     * Costructor de la clase, hereda de EventObject y permite crear un sensor  
     * de una Lissy con su identificación y determinar qué locomotora ha pasado 
     * y a qué velocidad.
     * @param id Identificación del sensor.
     * @param lok Identificación de la locomotora.
     * @param speed Velocidad de la locomotora.
     */
    public EventLissySignalP2(int id, int lok, int speed) {
        super(id);
        this.id = id;
        this.lok = lok;
        this.speed = speed;
    }

    /**
     * Getter de la identificación del segundo sensor de una Lissy.
     * @return Retorna la identificación del segundo sensor de una Lissy.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de la identificación del segundo sensor de una Lissy.
     * @param id Asigna la identificación del segundo sensor de una Lissy.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la identificación de la locomotora.
     * @return Retorna la identificación de la locomotora.
     */
    public int getLok() {
        return lok;
    }

    /**
     * Setter de la identificación de la locomotora.
     * @param lok Asigna la identificación de la locomotora.
     */
    public void setLok(int lok) {
        this.lok = lok;
    }

    /**
     * Getter de la velocidad de una locomotora.
     * @return Retorna la velocidad de una locomotora.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter de la velocidad de una locomotora.
     * @param speed Asigna la velocidad de una locomotora
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Información resumida de una clase.
     * @return Retorna la identificación del segundo sensor de una Lissy y la velocidad de la última locomotora.
     */
    @Override
    public String toString() {
        return "LissyP2 id: " + id
                + " speed: " + speed;
    }
}
