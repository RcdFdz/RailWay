package railway.protocol;

/**
 * 
 * @author essence
 */
public class EventMessage {

    private static final int errorCode = -1;
    private int miss[] = null;
    private int nBytes = 0;
    private int checksum = 0;

    /**
     * Método que permi asignar el tamaño del mensaje.
     * @param bytes Tamaño del mensaje.
     */
    public EventMessage(int bytes) {
        miss = new int[bytes];
        nBytes = 0;
        checksum = 0;
    }

    private EventMessage() {
        miss = null;
        nBytes = 0;
        checksum = 0;
    }

    /**
     * 
     * @param startByte
     * @return
     */
    public static EventMessage newEventMessage(int startByte) {
        EventMessage message;
        int size = Protocol.messageSize(startByte);
        if (size == 0) {
            return null;
        }
        if (size != startByte) {
            message = new EventMessage(size);
            message.newByte(startByte);
        } else {
            message = new EventMessage();
            message.nBytes = startByte;
        }
        return message;
    }

    /**
     * Método que permite dar valor al byte del mensaje.
     * @param b 
     */
    public void newByte(int b) {
        if (miss == null) {
            miss = new int[b];
            miss[0] = nBytes;
            checksum ^= miss[0];
            nBytes = 1;
        }
        if (nBytes < miss.length) {
            miss[nBytes++] = b;
            if (nBytes != miss.length) {
                checksum ^= b;
            }
        }
    }

    /**
     * 
     * @return
     */
    public int getEventType() {
        if (miss == null) {
            return 0;
        }
        if (nBytes > 0) {
            return miss[0];
        }
        return 0;
    }

    /**
     * 
     * @param p
     * @return
     */
    public int getByte(int p) {
        if (nBytes > p) {
            return miss[p];
        }
        return errorCode;
    }

    /**
     * 
     * @return
     */
    public boolean completed() {
        if (miss == null) {
            return false;
        }
        if (nBytes == 0) {
            return false;
        }
        if (nBytes != miss.length) {
            return false;
        }
        return ((~checksum) & 0xFF) == miss[nBytes - 1];//comprovar checksum
    }

    /**
     * Método que permite calcular el checksum del mensaje que se enviará.
     */
    /**
     * Método que retorna el mensaje construído.
     * @return Retorna el mensaje con los valores y el checksum específicos.
     */
    public int length() {
        return miss.length;
    }

    /**
     * 
     * @return
     */
    public boolean error() {
        return nBytes == errorCode;
    }

    /**
     * 
     * @return
     */
    public int getnBytesInMessage() {
        return nBytes;
    }

    /**
     * 
     * @return
     */
    public String toString() {
        String s = "";
        for (int b : miss) {
            s = s + b + " ";
        }
        return s;
    }
}
