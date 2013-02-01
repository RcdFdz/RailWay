package railway.model;

import java.util.EventObject;

import railway.events.listeners.EvtSlotListener;
import railway.events.types.EventSlotConsist;
import railway.events.types.EventSlotState;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Slot implements EvtSlotListener {

    private int slot;
    private byte slot_s;
    private byte consist_s;
    private byte busy_act;
    private byte type_cod;

    /**
     * 
     * @param slot
     */
    public Slot(int slot) {
        this.slot = slot;
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

    public void changeState(EventSlotState event) {
        if (event.getSlot() == slot) {
            slot_s = event.getSlot_s();
            consist_s = event.getConsist_s();
            busy_act = event.getBusy_act();
            type_cod = event.getType_cod();
        }

    }

    public void changeConsist(EventSlotConsist event) {
        if (event.getSlot() == slot) {
            consist_s = event.getConsist_s();
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Slot slot: " + getSlot()
                + " status: " + getSlot_s()
                + " consist: " + getConsist_s()
                + " busy-active: " + getBusy_act()
                + " typecode: " + getType_cod();
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notifyEvent(EventObject event) {
        if (event instanceof EventSlotState) {
            changeState((EventSlotState) event);
        } else if (event instanceof EventSlotConsist) {
            changeConsist((EventSlotConsist) event);
        }
    }
}
