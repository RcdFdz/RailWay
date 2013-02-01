package railway.model;

/**
 * Clase para crear los semáforos y registrar su estado, hay que tener en cuenta
 * que esta clase extiende a TurnOut.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Semaphore extends TurnOut {

    /**
     * Constructor de la clase, requiere un identificador de semáforo y un 
     * estado.
     * @param id Identificación del semáforo.
     * @param on Estado del semáforo, Encendido(1)/Apagado(0)
     */
    public Semaphore(int id, boolean on) {
        super(id, on);
    }
}
