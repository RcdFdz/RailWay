package railway.events.types;

import java.util.EventObject;

/**
 * Clase de los eventos del primer sensor de una Lissy,permite obtener 
 * la identificación de una locomotora y el sentido de circulación en el momento 
 * en el que pasa por el primer sensor de una Lissy determinada.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLissySignalP1 extends EventObject {

    private int id;
    private int lok;
    private boolean direction;

    /**
     * Costructor de la clase, hereda de EventObject y permite crear un sensor  
     * de una Lissy con su identificación y determinar qué locomotora ha pasado 
     * y en qué sentido lo ha hecho.
     * @param id Identificación del sensor.
     * @param lok Identificación de la locomotora.
     * @param direction Sentido de circulación de la locomotora.
     */
    public EventLissySignalP1(int id, int lok, boolean direction) {
        super(id);
        this.id = id;
        this.lok = lok;
        this.direction = direction;
    }

    /**
     * Sentido de circulación de la locomotora.
     * @return Devuelve el sentido de circulación.
     */
    public boolean isDirection() {
        return direction;
    }

    /**
     * Setter del sentido de circulación de la locomotora.
     * @param direction Asigna el sentido de circulación de una locomotora.
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * Getter de la identificación del primer sensor de una Lissy.
     * @return Retorna la identificación del primer sensor de una Lissy.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de la identificación del primer sensor de una Lissy.
     * @param id Asigna la identificación del primer sensor de una Lissy.
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
     * Información resumida de una clase.
     * @return Retorna la identificación del primer sensor de una Lissy y la velocidad de la última locomotora.
     */
    @Override
    public String toString() {
        return "LissyP1 id: " + id
                + " locomotive: " + lok
                + " direcion: " + direction;
    }
}
