package railway.events.types;

import java.util.EventObject;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class EventLokSpeed extends EventObject {

    private int slot;
    private int speed;

    /**
     * 
     * @param slot
     * @param speed
     */
    public EventLokSpeed(int slot, int speed) {
        super(slot);
        this.slot = slot;
        this.speed = speed;
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
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "LocomotiveSpeed slot: " + slot
                + " speed: " + speed;
    }
}
