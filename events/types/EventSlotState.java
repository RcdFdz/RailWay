package railway.events.types;

import java.util.EventObject;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventSlotState extends EventObject {

    int slot;
    byte slot_s;
    byte consist_s;
    byte busy_act;
    byte type_cod;

    /**
     * 
     * @param slot
     * @param slot_s
     * @param consist_s
     * @param busy_act
     * @param type_cod
     */
    public EventSlotState(int slot, byte slot_s, byte consist_s,
            byte busy_act, byte type_cod) {
        super(slot);
        this.slot = slot;
        this.slot_s = slot_s;
        this.consist_s = consist_s;
        this.busy_act = busy_act;
        this.type_cod = type_cod;
    }

    /**
     * 
     * @return
     */
    public byte getBusy_act() {
        return busy_act;
    }

    /**
     * 
     * @param busy_act
     */
    public void setBusy_act(byte busy_act) {
        this.busy_act = busy_act;
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
    public byte getSlot_s() {
        return slot_s;
    }

    /**
     * 
     * @param slot_s
     */
    public void setSlot_s(byte slot_s) {
        this.slot_s = slot_s;
    }

    /**
     * 
     * @return
     */
    public byte getType_cod() {
        return type_cod;
    }

    /**
     * 
     * @param type_cod
     */
    public void setType_cod(byte type_cod) {
        this.type_cod = type_cod;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "SlotState slot: " + slot
                + " status: " + Integer.toBinaryString(slot_s)
                + " consist: " + Integer.toBinaryString(consist_s)
                + " busy-active: " + Integer.toBinaryString(busy_act)
                + " typecode: " + Integer.toBinaryString(type_cod);
    }
}
