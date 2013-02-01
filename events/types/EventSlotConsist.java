package railway.events.types;

import java.util.EventObject;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventSlotConsist extends EventObject {

    private int slot;
    private byte consist_s;

    /**
     * 
     * @param slot
     * @param consist_s
     */
    public EventSlotConsist(int slot, byte consist_s) {
        super(slot);
        this.slot = slot;
        this.consist_s = consist_s;
    }

    /**
     * 
     * @return
     */
    public byte getConsist_s() {
        return consist_s;
    }

    /**
     * 
     * @param consist_s
     */
    public void setConsist_s(byte consist_s) {
        this.consist_s = consist_s;
    }

    /**
     * 
     * @return
     */
    public int getSlot() {
        return slot;
    }

    /**
     * 
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "SlorConsist slot: " + slot
                + " consist status: " + Integer.toBinaryString(consist_s);
    }
}
