package railway.events.types;

import java.util.EventObject;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventSensorState extends EventObject {

    private int id;
    private byte[] xil = new byte[3];
    boolean sensorlvl;

    /**
     * 
     * @param id
     * @param xil
     */
    public EventSensorState(int id, byte[] xil) {
        super(id);
        this.id = id;
        this.xil = xil;
    }

    /**
     * 
     * @param id
     * @param sensorlvl
     */
    public EventSensorState(int id, boolean sensorlvl) {
        super(id);
        this.id = id;
        this.sensorlvl = sensorlvl;
    }

    /**
     * 
     * @return
     */
    public boolean isSensorlvl() {
        return sensorlvl;
    }

    /**
     * 
     * @param sensorlvl
     */
    public void setSensorlvl(boolean sensorlvl) {
        this.sensorlvl = sensorlvl;
    }

    /**
     * 
     * @param xil
     */
    public void setXil(byte[] xil) {
        this.xil = xil;
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
     * @return
     */
    @Override
    public String toString() {
        return "SensorState id: " + id
                + " XIL status: " + xil[0] + xil[1] + xil[2]
                + " sensor level: " + sensorlvl;
    }
}
