package railway.controlsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.List;

import railway.events.controlsystem.EventsControlSystem;
import railway.events.listeners.EventsListener;
import railway.model.Decoder;
import railway.model.Lissy;
import railway.model.Locomotive;
import railway.model.Model;
import railway.model.Sensor;
import railway.model.Slot;
import railway.model.Track;
import railway.model.TurnOut;
import railway.protocol.Protocol;
import Port.Port;

/**
 * Clase para el control de los distintos dispositivos de la maqueta, vías,
 * desvios, semáforos y locomotoras.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class RailwayControlSystem {

    private Port port;
    private EventsControlSystem ecs;

    /**
     * Constructor de la clase, se encarga de crear  y configurar el puerto 
     * RS232 para su posterior uso. Además crea el gestor de eventos del 
     * decoder.
     * @param port 
     */
    public RailwayControlSystem(Port port) {
        this.port = port;
        this.ecs = new EventsControlSystem(port);
    }

    /**
     * 
     * @param port
     * @param model
     */
    public RailwayControlSystem(Port port, Model model) {
        this(port);
        addModelAsListener(model);
    }

    /**
     * 
     * @return
     */
    public EventsControlSystem getECS() {
        return ecs;
    }

    /**
     * 
     * @param listener
     * @param events
     */
    public void addListenerTo(EventsListener listener, Map<Integer, List<Integer>> events) {
        for (Map.Entry<Integer, List<Integer>> event : events.entrySet()) {
            for (Integer id : event.getValue()) {
                ecs.addListener(event.getKey(), id, listener);
            }
        }
    }

    /**
     * 
     * @param model
     */
    public void addModelAsListener(Model model) {
        if (model != null) {
            addDecodersListeners(model.getDecoders());
            addLissiesListeners(model.getLissies());
            addLocomotivesListeners(model.getLocomotives());
            addTurnOutsListeners(model.getSemaphores());
            addTurnOutsListeners(model.getTurnings());
            addSensorsListeners(model.getSensors());
            addSlotsListeners(model.getSlots());
            addTracksListeners(model.getTracks());
        }
    }

    private void addDecodersListeners(Iterable<Decoder> decoders) {
        for (Decoder decoder : decoders) {
            ecs.addListener(Protocol.EVENT_DECODER_STATE0, decoder.getId(), decoder);
            ecs.addListener(Protocol.EVENT_DECODER_STATE1, decoder.getId(), decoder);
            ecs.addListener(Protocol.EVENT_DECODER_STATE2, decoder.getId(), decoder);
            ecs.addListener(Protocol.EVENT_DECODER_STATE3, decoder.getId(), decoder);
        }
    }

    private void addLissiesListeners(Iterable<Lissy> lissies) {
        for (Lissy lissy : lissies) {
            ecs.addListener(Protocol.EVENT_LISSY_SIGNAL, lissy.getId(), lissy);
        }
    }

    private <T extends TurnOut> void addTurnOutsListeners(Iterable<T> turnOuts) {
        for (TurnOut turnOut : turnOuts) {
            ecs.addListener(Protocol.EVENT_TURNOUT_STATE, turnOut.getId(), turnOut);
        }
    }

    private void addLocomotivesListeners(Iterable<Locomotive> locomotives) {
        for (Locomotive locomotive : locomotives) {
            ecs.addListener(Protocol.EVENT_LOK_DIRECTION, locomotive.getId(), locomotive);
            ecs.addListener(Protocol.EVENT_LOK_F_STATE, locomotive.getId(), locomotive);
            ecs.addListener(Protocol.EVENT_LOK_GLOBAL_STATE, locomotive.getId(), locomotive);
            ecs.addListener(Protocol.EVENT_LOK_SPEED, locomotive.getId(), locomotive);
        }
    }

    private void addSensorsListeners(Iterable<Sensor> sensors) {
        for (Sensor sensor : sensors) {

            //ecs.addListener(Protocol.EVENT_SENSOR_STATE, sensor.getId(), sensor);
            ecs.addListener(Protocol.EVENT_SENSOR_STATE2, sensor.getId(), sensor);
        }
    }

    private void addSlotsListeners(Iterable<Slot> slots) {
        for (Slot slot : slots) {
            ecs.addListener(Protocol.EVENT_SLOT_CONSIST, slot.getSlot(), slot);
            ecs.addListener(Protocol.EVENT_SLOT_STATE, slot.getSlot(), slot);
        }
    }

    private void addTracksListeners(Iterable<Track> tracks) {
        for (Track track : tracks) {
            ecs.addListener(Protocol.EVENT_SENSOR_STATE, track.getId(), track);
        }
    }

    /**
     * Método encargado de encender el decoder.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean rcsSetOn() {
        try {
            byte[] b = Protocol.intelli_On();
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de apagar el decoder.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean rcsSetOff() {
        try {
            byte[] b = Protocol.intelli_Off();
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de realizar una parada de emergencia de la locomotora 
     * indicada.
     * @param lok Locomotora que debe realizar la para de emergencia.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokEmergenciStop(int lok) {
        try {
            byte[] b = Protocol.lok_Speed(lok, 1);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de realizar una para de la locomotora indicada.
     * @param lok Locomotora que debe realizar la parada.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokStop(int lok) {
        try {
            byte[] b = Protocol.lok_Speed(lok, 0);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de realizar el cambio de velocidad de la locomotora indicada.
     * @param lok Locomotora que debe realizar el cambio de velocidad.
     * @param speed Nueva velocidad de la locomotora.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetSpeed(int lok, int speed) {
        if (speed > 127) {
            speed = 127;
        }
        if (speed < 0) {
            speed = 0;
        }
        try {
            byte[] b = Protocol.lok_Speed(lok, speed);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de establecer el sentido de circulación 1 de la locomotora.
     * @param lok Locomotora en la que se quiere establecer el sentido de circulación 1.
     * @param dirf 
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetDirection1(int lok, int dirf) {
        int aux = (dirf & ~(1 << 5));
        try {
            byte[] b = Protocol.lok_DirF(lok, aux);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de establecer el sentido de circulación 2 de la locomotora.
     * @param lok Locomotora a la que se le quiere establecer el sentido de circulación 2.
     * @param dirf 
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetDirection2(int lok, int dirf) {
        int aux = (dirf | (1 << 5));
        try {
            byte[] b = Protocol.lok_DirF(lok, aux);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param lok
     * @return
     */
    public boolean lokState(int lok) {
        try {
            byte[] b = Protocol.slot_State(lok); // estic usant slot_state per a que cuadri amb lok_DirF que treballa amb slots i no amb id's de locomotora
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    //No implmentat en el Wraper, no te sentit rebudes a partir de la web

    /**
     * 
     * @param slot
     * @param stat
     * @param Lok
     * @param speed
     * @param dirf
     * @param trk
     * @param ss2
     * @param addr2
     * @param snd
     * @param id1
     * @param id2
     * @return
     */
    public boolean slotWrite(int slot, int stat, int Lok, int speed,
            int dirf, int trk, int ss2, int addr2, int snd, int id1, int id2) {
        try {
            byte[] b = Protocol.slot_DataWrite(slot, stat, Lok, speed, dirf, trk, ss2, addr2, snd, id1, id2);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de cambiar el estado de un semáforo a verde.
     * @param sem Semáforo al que se le quiere cambiar el estado a verde.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean semSetGreen(int sem) {
        try {
            byte[] b = Protocol.trn_Message(sem, Protocol.ON_OFF.ON);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método encargado de cambiar el estado de un semáforo a rojo.
     * @param sem Semáforo al que se le quiere cambiar el estado a rojo.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean semSetRed(int sem) {
        try {
            byte[] b = Protocol.trn_Message(sem, Protocol.ON_OFF.OFF);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método que permite cambiar el estado de un desvio a abierto.
     * @param trn Desvio al que se le quiere cambiar el estado a abierto.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean trnSetOpen(int trn) {
        try {
            byte[] b = Protocol.trn_Message(trn, Protocol.ON_OFF.ON);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método que permite cambiar el estado de un devio a cerrado.
     * @param trn Desvio al que se le quiere cambiar el estado a cerrado.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean trnSetClose(int trn) {
        try {
            byte[] b = Protocol.trn_Message(trn, Protocol.ON_OFF.OFF);
            port.write(b, 0, b.length);
        } catch (IOException ex) {
            Logger.getLogger(RailwayControlSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
