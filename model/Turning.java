package railway.model;

/**
 * Clase para crear los desvios y registrar su estado hay que tener en cuenta
 * que esta clase extiende a TurnOut.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Turning extends TurnOut {

    /**
     * Constructor de la clase, requiere un identificador de semáforo y un 
     * estado.
     * @param id Identificación del desvio.
     * @param on Estado del desvio (1 / 0).
     */
    public Turning(int id, boolean on) {
        super(id, on);
    }
}
