package Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import railway.support.Reader;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class PortRS232 implements Port {

    /**
     * 
     */
    public enum DataBits {

        /**
         * 
         */
        DataBits_5(SerialPort.DATABITS_5),
        /**
         * 
         */
        DataBits_6(SerialPort.DATABITS_6),
        /**
         * 
         */
        DataBits_7(SerialPort.DATABITS_7),
        /**
         * 
         */
        DataBits_8(SerialPort.DATABITS_8);
        private int value;

        DataBits(int value) {
            this.value = value;
        }

        private int getValue() {
            return value;
        }
    }

    /**
     * 
     */
    public enum Parity {

        /**
         * 
         */
        PARITY_EVEN(SerialPort.PARITY_EVEN),
        /**
         * 
         */
        PARITY_MARK(SerialPort.PARITY_MARK),
        /**
         * 
         */
        PARITY_NONE(SerialPort.PARITY_NONE),
        /**
         * 
         */
        PARITY_ODD(SerialPort.PARITY_ODD),
        /**
         * 
         */
        PARITY_SPACE(SerialPort.PARITY_SPACE);
        int value;

        Parity(int value) {
            this.value = value;
        }

        private int getValue() {
            return value;
        }
    }

    /**
     * 
     */
    public enum StopBits {

        /**
         * 
         */
        STOPBITS_1(SerialPort.STOPBITS_1),
        /**
         * 
         */
        STOPBITS_2(SerialPort.STOPBITS_2),
        /**
         * 
         */
        STOPBITS_1_5(SerialPort.STOPBITS_1_5);
        private int value;

        private StopBits(int value) {
            this.value = value;
        }

        private int getValue() {
            return value;
        }
    }

    private class RS232EventListener implements SerialPortEventListener {

        private Reader reader;

        public RS232EventListener(Reader reader) {
            this.reader = reader;
        }

        public void serialEvent(SerialPortEvent e) {
            int b;
            if (e.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                try {
                    b = ((SerialPort) e.getSource()).getInputStream().read();
                    reader.newByteRead(b);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    private final static int[] standardSpeeds = {1200, 9600, 38400, 115200};

    /**
     * 
     * @return
     */
    public static String[] listRS232Ports() {
        List<String> list = new ArrayList<String>();
        Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier portID = ports.nextElement();
            if (portID.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                list.add(portID.getName());
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 
     * @return
     */
    public static int[] getStandardSpeeds() {
        return standardSpeeds;
    }
    private SerialPort serialPort = null;
    private String serialPortName;
    private Configuration configuration;

    /**
     * 
     * @param name
     * @param c
     */
    public PortRS232(String name, Configuration c) {
        serialPortName = name;
        configuration = c;
    }

    /**
     * 
     * @throws RS232Exception
     * @throws NoSuchPortException
     * @throws PortInUseException
     * @throws UnsupportedCommOperationException
     */
    public void open() throws RS232Exception, NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        if (serialPort != null) {
            throw new RS232Exception("El port ja està obert");
        }
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
        if (portIdentifier.isCurrentlyOwned()) {
            throw new RS232Exception("El port està sent utilizat");
        }
        System.out.println(portIdentifier.getCurrentOwner());
        System.out.println(portIdentifier.getName());
        System.out.println(portIdentifier.getPortType());
        System.out.println(CommPortIdentifier.PORT_SERIAL);
        serialPort = (SerialPort) portIdentifier.open("PortRS232", configuration.getTimeout());
        serialPort.setSerialPortParams(configuration.getSpeed(),
                configuration.getDatabits().getValue(),
                configuration.getStopbits().getValue(),
                configuration.getParity().getValue());
        serialPort.setInputBufferSize(256);
        serialPort.setOutputBufferSize(256);
    }

    /**
     * 
     * @return
     */
    public boolean isOpen() {
        return serialPort != null;
    }

    /**
     * 
     * @return
     */
    public boolean isClosed() {
        return serialPort == null;
    }

    /**
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        if (serialPort != null) {
            serialPort.getOutputStream().flush();
            removeAsyncReader();
            serialPort.close();
            serialPort = null;
        }
    }

    // Metodes d'escriptura sincrona
    /**
     * 
     * @param b
     * @throws IOException
     */
    public void write(byte b) throws IOException {
        int i = 0xFF & (int) b;
        serialPort.getOutputStream().write(i);
        serialPort.getOutputStream().flush();

    }

    /**
     * 
     * @param b
     * @throws IOException
     */
    public void write(int b) throws IOException {
        int i = 0xFF & b;
        serialPort.getOutputStream().write(i);
        serialPort.getOutputStream().flush();

    }

    /**
     * 
     * @param buffer
     * @param off
     * @param len
     * @throws IOException
     */
    public void write(byte[] buffer, int off, int len) throws IOException {
        serialPort.getOutputStream().write(buffer, off, len);
        serialPort.getOutputStream().flush();
    }

    // Metodes de lectura sincrona
    /**
     * 
     * @return
     * @throws IOException
     */
    public byte read() throws IOException {
        while (serialPort.getInputStream().available() == 0) {
        }
        byte[] b = new byte[1];
        serialPort.getInputStream().read(b, 0, 1);
        return b[0];
    }

    /**
     * 
     * @param buffer
     * @param of
     * @param len
     * @return
     * @throws IOException
     */
    public int read(byte[] buffer, int of, int len) throws IOException {
        while (serialPort.getInputStream().available() < len) {
        }
        return serialPort.getInputStream().read(buffer, of, len);
    }

    // Metodes per lectura asincrona
    /**
     * 
     * @param reader
     * @throws TooManyListenersException
     */
    public void setAsyncReader(Reader reader) throws TooManyListenersException {
        removeAsyncReader();
        serialPort.addEventListener(new RS232EventListener(reader));
        serialPort.notifyOnDataAvailable(true);
    }

    /**
     * 
     */
    public void removeAsyncReader() {
        if (isOpen()) {
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
        }
    }
}