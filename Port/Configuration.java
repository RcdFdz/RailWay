package Port;

import Port.PortRS232.DataBits;
import Port.PortRS232.Parity;
import Port.PortRS232.StopBits;

/**
 * Clase para configurar el puerto RS232.
 * @version 1.0.
 * @author Ricardo Fernandez Domenech
 */
public class Configuration {
	private int speed;
	private DataBits databits;
	private StopBits stopbits;
	private Parity  parity;
	private int timeout;
	
        /**
         * Constructor de la clase, permite recibir los datos para la 
         * configuración del puerto RS232
         * @param timeout Timeout permitido durante la comunicación con el puerto.
         * @param speed Velocidad de trasnferencia de datos.
         * @param databits Tamaño de bits del mensaje.
         * @param stopbits Número de bits de parada enviados tras el mensaje.
         * @param parity Método de detección de errores durante la transmisión de datos.
         */
        public Configuration(int timeout, int speed, DataBits databits, StopBits stopbits, Parity  parity) {
		this.timeout = timeout;
		this.speed = speed;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity ;		
	}

        /**
         * Getter de la velocidad de transferencia de datos.
         * @return Retorna la velocidad de transferencia de datos del puerto.
         */
        public int getSpeed() {
		return speed;
	}

        /**
         * Getter del tamaño de bits del mensaje.
         * @return Retorna el tamaño de bits del mensaje.
         */
        public DataBits getDatabits() {
		return databits;
	}

        /**
         * Getter del número de bits de parada enviados tras el mensaje.
         * @return Retorna el número de bits de parada enviados tras el mensaje.
         */
        public StopBits getStopbits() {
		return stopbits;
	}

        /**
         * Getter del método de detección de errores durante la transmisión de datos.
         * @return Retorna el método de detección de errores.
         */
        public Parity getParity() {
		return parity;
	}

        /**
         * Getter del timeout establecido.
         * @return Retorna el valor de timeout establecido.
         */
        public int getTimeout() {
		return timeout;
	}
}

