package railway.model;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import railway.events.listeners.EvtSensorListener;
import railway.events.types.EventSensorState;

/**
 * 
 * @author essence
 */
public class Track implements EvtSensorListener {

    int id;
    Locomotive lokEntering;
    Locomotive lokIn;
    List<Track> previous;
    List<Track> next;

    /**
     * 
     * @param lok
     */
    public void setLokIn(Locomotive lok) {
        lokIn = lok;
    }

    /**
     * 
     * @param s88
     * @param modul
     */
    public Track(int s88, int modul) {
        this((s88 - 1) * 16 + modul);
    }

    /**
     * 
     * @param id
     */
    public Track(Integer id) {
        super();
        this.id = id;
        this.previous = new ArrayList(3);
        this.next = new ArrayList(3);
    }

    /**
     * 
     * @param track
     */
    public void addNext(Track track) {
        next.add(track);
    }

    /**
     * 
     * @param track
     */
    public void addPrevious(Track track) {
        previous.add(track);
    }

    public void changeState(EventSensorState event) {
        if (event.getId() == this.id) {
            boolean enter = event.isSensorlvl();
            if (enter) {
//            	if(lokEntering!=null) {
                lokIn = lokEntering;
                //Have to be changed to systemout in a event
                System.out.println(this + "entering");
                lokEntering = null;
//            	}
            } else {
                if (lokIn != null) {
                    if (lokIn.isDirection()) {
                        for (Track track : next) {
                            track.lokEntering = lokIn;
                        }
                        //Have to be changed to systemout in a event
                        lokIn = null;
                    } else {
                        for (Track track : next) {
                            track.lokEntering = lokIn;
                        }
                        lokIn = null;
                    }
                }
                System.out.println(this + "exit");
            }
        }
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
    public String toString() {
        return "Track: " + id + " lokid: " + (lokIn == null ? -1 : lokIn.getId()) + " action: ";
    }

    /**
     * 
     * @param event
     */
    public void notifyEvent(EventObject event) {
        changeState((EventSensorState) event);
    }
}
