package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtSensorListener;
import railway.events.types.EventSensorState;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Sensor implements EvtSensorListener {

    private int id;
    private byte[] xil = new byte[3];

    /**
     * 
     * @param id
     */
    public Sensor(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return
     */
    public byte[] getXIL() {
        return xil;
    }

    /**
     * 
     * @param xil
     */
    public void setXIL(byte[] xil) {
        this.xil = xil;
    }

    @Override
    public void changeState(EventSensorState event) {
        if (event.getId() == id) {
            xil = event.getXIL();
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Sensor id : " + getId()
                + " control bit : " + Integer.toBinaryString(getXIL()[0])
                + Integer.toBinaryString(getXIL()[1])
                + Integer.toBinaryString(getXIL()[2]);
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        changeState((EventSensorState) event);
    }
}
