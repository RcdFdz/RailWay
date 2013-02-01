package Port;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.TooManyListenersException;
import railway.support.Reader;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public interface Port {

    /**
     * 
     * @param reader
     * @throws TooManyListenersException
     */
    public void setAsyncReader(Reader reader) throws TooManyListenersException;

    /**
     * 
     */
    public void removeAsyncReader();

    /**
     * 
     * @param b
     * @param i
     * @param length
     * @throws IOException
     */
    public void write(byte[] b, int i, int length) throws IOException;

    /**
     * 
     * @throws IOException
     */
    public void close() throws IOException;

    /**
     * 
     * @throws RS232Exception
     * @throws NoSuchPortException
     * @throws PortInUseException
     * @throws UnsupportedCommOperationException
     */
    public void open() throws RS232Exception, NoSuchPortException, PortInUseException, UnsupportedCommOperationException;
}
