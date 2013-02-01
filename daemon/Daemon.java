package railway.daemon;

import railway.controlsystem.RailwayControlSystem;
import railway.controlsystem.RailwayControlSystemWrapper;
import railway.events.controlsystem.EventsControlSystem;
import railway.model.Locomotive;
import railway.model.Model;
import railway.model.Semaphore;
import railway.model.Turning;
import railway.protocol.Protocol;
import Port.Port;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Daemon {

    private Model model;
    private Port port;
    private RailwayControlSystem rcs;
    private Thread commandReader;
    private ServerNotifier serverNotifier;

    /**
     * 
     * @param modelFile
     * @param layoutFile
     * @param port
     */
    public Daemon(String modelFile, String layoutFile, Port port) {
        this.port = port;
        model = Model.readModelFromFile(modelFile, layoutFile);
        rcs = new RailwayControlSystem(this.port, model);
        commandReader = new Thread(new CommandReader<RailwayControlSystemWrapper>(new RailwayControlSystemWrapper(rcs, model)));
        serverNotifier = new ServerNotifier();
    }

    private void addServerNotifierAsListener() {
        EventsControlSystem ecs = rcs.getECS();
        for (Integer decoder : model.getDecodersId()) {
            ecs.addListener(Protocol.EVENT_DECODER_STATE0, decoder, serverNotifier);
            ecs.addListener(Protocol.EVENT_DECODER_STATE1, decoder, serverNotifier);
            ecs.addListener(Protocol.EVENT_DECODER_STATE2, decoder, serverNotifier);
            ecs.addListener(Protocol.EVENT_DECODER_STATE3, decoder, serverNotifier);
        }
        for (Integer lissy : model.getLissiesId()) {
            ecs.addListener(Protocol.EVENT_LISSY_SIGNAL, lissy, serverNotifier);
        }
        for (Integer semaphore : model.getSemaphoresId()) {
            ecs.addListener(Protocol.EVENT_TURNOUT_STATE, semaphore, serverNotifier);
        }
        for (Integer turning : model.getTurningsId()) {
            ecs.addListener(Protocol.EVENT_TURNOUT_STATE, turning, serverNotifier);
        }
        for (Integer locomotive : model.getLocomotivesId()) {
            ecs.addListener(Protocol.EVENT_LOK_DIRECTION, locomotive, serverNotifier);
            ecs.addListener(Protocol.EVENT_LOK_F_STATE, locomotive, serverNotifier);
            ecs.addListener(Protocol.EVENT_LOK_GLOBAL_STATE, locomotive, serverNotifier);
            ecs.addListener(Protocol.EVENT_LOK_SPEED, locomotive, serverNotifier);
        }
        for (Integer sensor : model.getSensorsId()) {
            ecs.addListener(Protocol.EVENT_SENSOR_STATE, sensor, serverNotifier);
            ecs.addListener(Protocol.EVENT_SENSOR_STATE2, sensor, serverNotifier);
        }
        for (Integer slot : model.getSlotsId()) {
            ecs.addListener(Protocol.EVENT_SLOT_CONSIST, slot, serverNotifier);
            ecs.addListener(Protocol.EVENT_SLOT_STATE, slot, serverNotifier);
        }
        for (Integer track : model.getTracksId()) {
            ecs.addListener(Protocol.EVENT_SENSOR_STATE, track, serverNotifier);
        }
    }

    /**
     * 
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        rcs.rcsSetOn();
        rcs.getECS().start();
        iniModel();
        commandReader.start();
        addServerNotifierAsListener();
    }

    /**
     * 
     * @throws InterruptedException
     */
    public void iniModel() throws InterruptedException {

        for (Locomotive lok : model.getLocomotives()) {
            //SlotWrite do not return EVENT ANSWER for that reason it is necessary do the changes manualy
            //with lokSetDirection and LokSetSpeed, lines 82 and 86
            rcs.slotWrite(lok.getId(), 1, lok.getId(), 0, 0, 0, 0, 0, 0, 0, 0);
            Thread.sleep(300);
        }
        for (Locomotive lok : model.getLocomotives()) {
            rcs.lokSetDirection1(lok.getId(), 0); //If for any reason, you change the value (0) you have to change the line 82 with the new value
            Thread.sleep(300);
            rcs.lokSetSpeed(lok.getId(), 0);
            Thread.sleep(300);
        }
        for (Semaphore sem : model.getSemaphores()) {
            rcs.semSetGreen(sem.getId());
            Thread.sleep(300);
        }

        for (Turning trn : model.getTurnings()) {
            rcs.trnSetOpen(trn.getId());
            Thread.sleep(300);
        }

    }
}
