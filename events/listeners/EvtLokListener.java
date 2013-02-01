package railway.events.listeners;

import railway.events.types.EventLokDirection;
import railway.events.types.EventLokFstate;
import railway.events.types.EventLokGlobalState;
import railway.events.types.EventLokSpeed;

/**
 * Interficie para la clase listeneer de los eventos de locomotora.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtLokListener extends EventsListener {

    /**
     * Cambio de estado global de una locomotora.
     * @param event Evento de cambio de estado global de una locomotora.
     */
    public void changeState(EventLokGlobalState event);

    /**
     * Cambio de velocidad de una locomotora.
     * @param event Evento de cambio de velocidad de una locomotora.
     */
    public abstract void changeSpeed(EventLokSpeed event);

    /**
     * Cambio de sentido de circulación de una locomotora.
     * @param event Evento de cambio de sentido de circulación de una locomotora.
     */
    public void changeDirection(EventLokDirection event);

    /**
     * Cambio de estado de las funciones especiales de una locomotora.
     * @param event Evento de cambio de estado de las funciones especiales de una locomotora.
     */
    public void changeFstate(EventLokFstate event);
}
