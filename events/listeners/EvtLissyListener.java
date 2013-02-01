package railway.events.listeners;

import railway.events.types.EventLissySignalP1;
import railway.events.types.EventLissySignalP2;

/**
 * Interficie para la clase listener de los eventos de una Lissy.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtLissyListener extends EventsListener {

    /**
     * Cambio de dirección de la locomotora.
     * @param event Evento del primer sensor de la Lissy.
     */
    public void changeLokDirec(EventLissySignalP1 event);

    /**
     * Cambio de velocidad de la locomotora.
     * @param event Evento del segundo sensor de la Lissy.
     */
    public void changeSpeed(EventLissySignalP2 event);
}
