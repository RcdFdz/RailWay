package railway.events.listeners;

import railway.events.types.EventDecoderState;
import railway.events.types.EventLissySignalP1;
import railway.events.types.EventLissySignalP2;
import railway.events.types.EventLokDirection;
import railway.events.types.EventLokFstate;
import railway.events.types.EventLokGlobalState;
import railway.events.types.EventLokSpeed;
import railway.events.types.EventSensorState;
import railway.events.types.EventSlotConsist;
import railway.events.types.EventSlotState;
import railway.events.types.EventTrnState;

/**
 * Interficie para la clase listener de los eventos de la Intellibox.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface EvtIntelliboxListener {

    /**
     * Notifica el cambio de estado de un decoder.
     * @param event Evento de estado del decoder.
     */
    public void notifyEventDecoder(EventDecoderState event);

    /**
     * Notifica el cambio de velocidad de una locomotora.
     * @param event Evento de velocidad de una locomotora.
     */
    public void notifyEventLokSpeed(EventLokSpeed event);

    /**
     * Notifica el cambio de sentido de circulación de una locomotora.
     * @param event Evento del sentido de circulación de una locomotora.
     */
    public void notifyEventLokDirection(EventLokDirection event);

    /**
     * Notifica el cambio de la función de estado de una locomotora.
     * @param event Evento de la función de estado de una locomotora.
     */
    public void notifyEventLokFstate(EventLokFstate event);

    /**
     * Notifica el cambio de estado global de una locomotora.
     * @param event Evento del estado global de una locomotora.
     */
    public void notifyEventLokGlobalState(EventLokGlobalState event);

    /**
     * Notifica el cambio de estado de un semáforo o de un desvio.
     * @param event Evento del estado de un semáforo o de un desvio.
     */
    public void notifyEventTrnState(EventTrnState event);

    /**
     * Notifica el cambio de estado de un sensor.
     * @param event Evento del estado de un sensor.
     */
    public void notifyEventSensorState(EventSensorState event);

    /**
     * Notifica el cambio de estado de un slot.
     * @param event Evento de cambio de estado de un slot.
     */
    public void notifyEventSlotState(EventSlotState event);

    /**
     * Notifica el cambio de un slotconsist.
     * @param event Evento de un slotconsist.
     */
    public void notifyEventSlotConsist(EventSlotConsist event);

    /**
     * Notifica un evento del primer sensor de una Lissy.
     * @param event Evento del primer sensor de una Lissy.
     */
    public void notifyEventLissySignalP1(EventLissySignalP1 event);

    /**
     * Notifica un evento del segundo sensor de una Lissy.
     * @param event Evento del segundo sensor de una Lissy.
     */
    public void notifyEventLissySignalP2(EventLissySignalP2 event);
}
