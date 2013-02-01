package main;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import Port.Configuration;
import Port.PortRS232;
import Port.PortRS232.DataBits;
import Port.PortRS232.Parity;
import Port.PortRS232.StopBits;
import Port.RS232Exception;
import railway.daemon.Daemon;

public class main {

    /**
     * @param args
     */
    public static void main(String[] args) throws RS232Exception, NoSuchPortException, PortInUseException, UnsupportedCommOperationException, InterruptedException {
        PortRS232 port = new PortRS232("/dev/ttyS0",
                new Configuration(2000,
                9600,
                DataBits.DataBits_8,
                StopBits.STOPBITS_2,
                Parity.PARITY_NONE));
        port.open();
        Daemon daemon = new Daemon("ModelLab.csv", "ModelLayout.csv", port);
        daemon.start();
    }
}
