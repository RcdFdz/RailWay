package railway.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import railway.support.ReadCSV;
import railway.support.ReadLayoutCSV;
import support.InfoTrack;

/**
 * Esta clase permite crear el modelo de una maqueta, creando o eliminando todos
 * los elementos necesarios, vías, desvios, trenes, decoders y semáforos.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Model {

    Map<Integer, Decoder> decoders;
    Map<Integer, Lissy> lissies;
    Map<Integer, Sensor> sensors;
    Map<Integer, Slot> slots;
    Map<Integer, Track> tracks;
    Map<Integer, Locomotive> locomotives;
    Map<Integer, Semaphore> semaphores;
    Map<Integer, Turning> turnings;

    /**
     * Constructor de la clase, crea las listas necesarias para introducir
     * locomotoras, semáforos, vías, desvios y el modelo.
     */
    public Model() {
        this.decoders = new HashMap<Integer, Decoder>();
        this.lissies = new HashMap<Integer, Lissy>();
        this.sensors = new HashMap<Integer, Sensor>();
        this.slots = new HashMap<Integer, Slot>();
        this.tracks = new HashMap<Integer, Track>();
        this.locomotives = new HashMap<Integer, Locomotive>();
        this.semaphores = new HashMap<Integer, Semaphore>();
        this.turnings = new HashMap<Integer, Turning>();
    }

    /**
     * 
     * @param deco
     */
    public void addDecoder(Decoder deco) {
        this.decoders.put(deco.getId(), deco);
    }

    /**
     * 
     * @param lissy
     */
    public void addLissy(Lissy lissy) {
        this.lissies.put(lissy.getId(), lissy);
    }

    /**
     * 
     * @param sensor
     */
    public void addSensor(Sensor sensor) {
        this.sensors.put(sensor.getId(), sensor);
    }

    /**
     * 
     * @param slot
     */
    public void addSlot(Slot slot) {
        this.slots.put(slot.getSlot(), slot);
    }

    /**
     * 
     * @param track
     */
    public void addTrack(Track track) {
        this.tracks.put(track.getId(), track);
    }

    /**
     * 
     * @param lok
     */
    public void addLocomotive(Locomotive lok) {
        this.locomotives.put(lok.getId(), lok);
    }

    /**
     * 
     * @param sem
     */
    public void addSemaphore(Semaphore sem) {
        this.semaphores.put(sem.getId(), sem);
    }

    /**
     * 
     * @param turn
     */
    public void addTurning(Turning turn) {
        this.turnings.put(turn.getId(), turn);
    }

    /**
     * 
     * @param id
     */
    public void deleteDecoder(int id) {
        decoders.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteLissy(int id) {
        lissies.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteSensor(int id) {
        sensors.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteSlot(int id) {
        slots.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteTrack(int id) {
        tracks.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteLocomotive(int id) {
        locomotives.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteSemaphore(int id) {
        semaphores.remove(id);
    }

    /**
     * 
     * @param id
     */
    public void deleteTurning(int id) {
        turnings.remove(id);
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getDecodersId() {
        return decoders.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getLissiesId() {
        return lissies.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getSensorsId() {
        return sensors.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getSlotsId() {
        return slots.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getTracksId() {
        return tracks.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getLocomotivesId() {
        return locomotives.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getSemaphoresId() {
        return semaphores.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Integer> getTurningsId() {
        return turnings.keySet();
    }

    /**
     * 
     * @return
     */
    public Iterable<Decoder> getDecoders() {
        return decoders.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Lissy> getLissies() {
        return lissies.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Sensor> getSensors() {
        return sensors.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Slot> getSlots() {
        return slots.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Track> getTracks() {
        return tracks.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Locomotive> getLocomotives() {
        return locomotives.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Semaphore> getSemaphores() {
        return semaphores.values();
    }

    /**
     * 
     * @return
     */
    public Iterable<Turning> getTurnings() {
        return turnings.values();
    }

    private Track getTrack(int id) {
        Track aux = this.tracks.get(id);
        if (aux == null) {
            aux = new Track(id);
            this.tracks.put(id, aux);
        }
        return aux;
    }

    /**
     * 
     * @param fileName
     * @param fileName2
     * @return
     */
    public static Model readModelFromFile(String fileName, String fileName2) {
        Model model = new Model();
        Map<String, List<Integer>> disp = ReadCSV.readCSV(fileName);

        List<Integer> decoders = disp.get("Decoder");
        if (decoders != null) {
            for (Integer id : decoders) {
                model.addDecoder(new Decoder(id));
            }
        }

        List<Integer> lissies = disp.get("Lissy");
        if (lissies != null) {
            for (Integer id : lissies) {
                model.addLissy(new Lissy(id));
            }
        }

        List<Integer> locomotives = disp.get("Locomotive");
        if (locomotives != null) {
            for (Integer id : locomotives) {
                model.addLocomotive(new Locomotive(id, id));
            }
        }

        List<Integer> semaphores = disp.get("Semaphore");
        if (semaphores != null) {
            for (Integer id : semaphores) {
                model.addSemaphore(new Semaphore(id, false));
            }
        }

        List<Integer> sensors = disp.get("Sensor");
        if (sensors != null) {
            for (Integer id : sensors) {
                model.addSensor(new Sensor(id));
            }
        }

        List<InfoTrack> tracks = ReadLayoutCSV.readLayoutCSV(fileName2);
        if (tracks != null) {
            for (InfoTrack t : tracks) {
                Track aux = model.getTrack(t.getId());
                for (int id : t.getNext()) {
                    Track next = model.getTrack(id);
                    aux.addNext(next);
                    next.addPrevious(aux);
                }
            }
        }

        List<Integer> turnings = disp.get("Turning");
        if (turnings != null) {
            for (Integer id : turnings) {
                model.addTurning(new Turning(id, false));
            }
        }

        return model;
    }
}
