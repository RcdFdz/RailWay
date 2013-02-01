package railway.support;

/**
 * Interficie para la clase Reader encargada de realizar
 * las lecturas de los datos transferidos por el puerto RS232
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface Reader {

    /**
     * 
     * @param b 
     */
    public void newByteRead(int b);
}
