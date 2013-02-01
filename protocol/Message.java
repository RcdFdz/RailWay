package railway.protocol;

/**
 * Clase dedicada a la gestión y correcta conformación de los mensages que deben 
 * transmitirse. Esta clase permite delimitar el tamaño del mensage y la 
 * calcular el checksum de este.
 * 
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Message {

    private byte miss[];

    /**
     * Método que permi asignar el tamaño del mensaje.
     * @param bytes Tamaño del mensaje.
     */
    public Message(int bytes) {
        miss = new byte[bytes];
    }

    /**
     * Método que permite dar valor al byte del mensaje.
     * @param nByte Posición del byte.
     * @param valor Valor del byte nByte.
     */
    public void setByte(int nByte, byte valor) {
        if (nByte < miss.length) {
            miss[nByte] = valor;
        }
    }

    /**
     * Método que permite calcular el checksum del mensaje que se enviará.
     */
    public void calculateChecksum() {
        int checksum = 0;
        for (int i = 0; i < miss.length - 1; i++) {
            checksum ^= miss[i];
        }
        checksum = (~checksum) & 0xFF;
        miss[miss.length - 1] = (byte) checksum;
    }

    /**
     * Método que retorna el mensaje construído.
     * @return Retorna el mensaje con los valores y el checksum específicos.
     */
    public byte[] getMiss() {
        return (miss);
    }
}
