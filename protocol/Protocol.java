package railway.protocol;

import java.util.EventObject;
import railway.events.types.*;

/**
 * Clase que determina el protocolo de comunicación con el decoder.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class Protocol {

    /**
     * 
     */
    public static final int EVENT_LOK_SPEED = 160;
    /**
     * 
     */
    public static final int EVENT_LOK_DIRECTION = 161;
    /**
     * 
     */
    public static final int EVENT_LOK_F_STATE = 162;
    /**
     * 
     */
    public static final int EVENT_TRN_STATE = 176;
    /**
     * 
     */
    public static final int EVENT_SENSOR_STATE2 = 177;
    /**
     * 
     */
    public static final int EVENT_SENSOR_STATE = 178;
    /**
     * 
     */
    public static final int EVENT_UNKNOWN = 180;
    /**
     * 
     */
    public static final int EVENT_SLOT_STATE = 181;
    /**
     * 
     */
    public static final int EVENT_SLOT_CONSIST = 182;
    /**
     * 
     */
    public static final int EVENT_TURNOUT_STATE = 189;
    /**
     * 
     */
    public static final int EVENT_LOK_GLOBAL_STATE = 231;
    /**
     * 
     */
    public static final int EVENT_LISSY_SIGNAL = 228;
    /**
     * 
     */
    public static final int EVENT_DECODER_STATE0 = 130;
    /**
     * 
     */
    public static final int EVENT_DECODER_STATE1 = 131;
    /**
     * 
     */
    public static final int EVENT_DECODER_STATE2 = 129;
    /**
     * 
     */
    public static final int EVENT_DECODER_STATE3 = 133;
    /**
     * 
     */
    public static final int[] eventTypes = {EVENT_LOK_F_STATE, EVENT_LISSY_SIGNAL, EVENT_LOK_DIRECTION, EVENT_SENSOR_STATE, EVENT_LOK_SPEED, EVENT_TURNOUT_STATE,
        EVENT_UNKNOWN, EVENT_SLOT_CONSIST, EVENT_SLOT_STATE, EVENT_SENSOR_STATE2, EVENT_TRN_STATE, EVENT_LOK_GLOBAL_STATE,
        EVENT_DECODER_STATE0, EVENT_DECODER_STATE1, EVENT_DECODER_STATE2, EVENT_DECODER_STATE3};
    private static final byte OPC_IDLE = (byte) 0x85;
    private static final byte OPC_GPON = (byte) 0x83;
    private static final byte OPC_GPOFF = (byte) 0x82;
    private static final byte OPC_BUSY = (byte) 0x81;
    private static final byte OPC_LOCO_ADR = (byte) 0xBF;
    private static final byte OPC_SW_ACK = (byte) 0xBD;
    private static final byte OPC_SW_STATE = (byte) 0xBC;
    private static final byte OPC_RQ_SL_DATA = (byte) 0xBB;
    private static final byte OPC_MOVE_SLOT = (byte) 0xBA;
    private static final byte OPC_LINK_SLOTS = (byte) 0xB9;
    private static final byte OPC_UNLINK_SLOTS = (byte) 0xB8;
    private static final byte OPC_CONSIST_FUNC = (byte) 0xB6;
    private static final byte OPC_SLOT_STAT1 = (byte) 0xB5;
    private static final byte OPC_LONG_ACK = (byte) 0xB4;
    private static final byte OPC_INPUT_REP = (byte) 0xB2;
    private static final byte OPC_SW_REP = (byte) 0xB1;
    private static final byte OPC_SW_REQ = (byte) 0xB0;
    private static final byte OPC_LOCO_SND = (byte) 0xA2;
    private static final byte OPC_LOCO_DIRF = (byte) 0xA1;
    private static final byte OPC_LOCO_SPD = (byte) 0xA0;
    private static final byte OPC_WR_SL_DATA = (byte) 0xEF;
    @SuppressWarnings("unused")
    private static final byte OPC_SL_RD_DATA = (byte) 0xE7;
    @SuppressWarnings("unused")
    private static final byte OPC_PEER_XFER = (byte) 0xE5;
    @SuppressWarnings("unused")
    private static final byte OPC_IMM_PACKET = (byte) 0xED;

    /**
     * Método que permite dar valores ON / OFF a los bytes de encendido (0x30) y 
     * apagado (0x10).
     */
    public enum ON_OFF {

        /**
         * Valor del byte de encendido 0x30.
         */
        ON((byte) 0x30),
        /**
         * Valor del byte de apagado 0x10.
         */
        OFF((byte) 0x10);

        ON_OFF(byte value) {
            this.value = value;
        }
        byte value;
    }

    /**
     * Método que permite forzar idle en el decoder.
     * @return Retorna el mensaje que permite comunicar al decoder que debe forzar idle.
     */
    public static byte[] intelli_idleForce() {
        Message miss = new Message(2);
        miss.setByte(0, OPC_IDLE);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite encender el decoder.
     * @return Retorna el mensaje que permite comunicar al decoder que debe encenderse.
     */
    public static byte[] intelli_On() {
        Message miss = new Message(2);
        miss.setByte(0, OPC_GPON);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite apagar el decoder.
     * @return Retorna el mensaje que permite comunicar al decoder que debe apagarse.
     */
    public static byte[] intelli_Off() {
        Message miss = new Message(2);
        miss.setByte(0, OPC_GPOFF);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite mantener el decoder en estado ocupado.
     * @return Retorna el mensaje que permite comunicar al decoder que debe mantenerse en estado ocupado.
     */
    public static byte[] intelli_Bussy() {
        Message miss = new Message(2);
        miss.setByte(0, OPC_BUSY);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite recibir el estado de una locomotora.
     * @param addr Identificación de una locomotora de la que se quiere obtener el estado.
     * @return Retorna el mensaje que permite recibir como respuesta el estado de la locomotora.
     */
    public static byte[] lok_State(int addr) {
        byte lowLokAddress = (byte) (addr & 0x00FF);
        byte highLokAddress = (byte) (addr >> 8 & 0xFF);
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LOCO_ADR);
        miss.setByte(1, (byte) highLokAddress);
        miss.setByte(2, (byte) lowLokAddress);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite cambiar el estado de un desvio o un semáforo.
     * @param addr Identificador del desvio o semáforos del que se quiere cambiar el estado.
     * @param on_off_value Nuevo estado del desvio o semáforo.
     * @return Retorna el mensaje que permite cambiar el estado de un desvio o semáforo.
     */
    public static byte[] trn_Message(int addr, ON_OFF on_off_value) {
        Message miss = new Message(4);
        addr--;
        miss.setByte(0, (byte) OPC_SW_ACK);
        miss.setByte(1, (byte) (addr & 0x7F));
        miss.setByte(2, (byte) (on_off_value.value | (addr >> 7 & 0x0F)));
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite obtener el estado de un desvio o de un semáforo.
     * @param addr Identificador del desvio o semáforo del que se quiere conocer el estado.
     * @return Retorna el mensaje que permite conocer el estado de un desvio o semáforo.
     */
    public static byte[] trn_State(int addr) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_SW_STATE);
        miss.setByte(1, (byte) (addr & 0x7F));
        miss.setByte(2, (byte) ((addr >> 7 & 0x0F)));
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite conocer el estado de un slot.
     * @param slot Dirección del slot del que se quiere conocer el estado.
     * @return Retorna el mensaje que permite conocer el estado de un slot.
     */
    public static byte[] slot_State(int slot) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_RQ_SL_DATA);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) 0x00);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite mover la información de un slot determinado a otro.
     * @param src Dirección del slot que contiene la información de los parámetros que se moverán.
     * @param dst Dirección del slot de destino que recibirá la información movida.
     * @return Retorna el mensaje que permite mover la información de un slot a otro.
     */
    public static byte[] slot_Mov(int src, int dst) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_MOVE_SLOT);
        miss.setByte(1, (byte) src);
        miss.setByte(2, (byte) dst);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite lincar dos slots, de forma que cualquier modificación 
     * en uno, se refleja también en el otro.
     * @param slt1 Dirección del primer slot a lincar.
     * @param slt2 Dirección del segundo slot a lincar.
     * @return Retorna el mensaje que permite lincar los dos slots.
     */
    public static byte[] slot_Link(int slt1, int slt2) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LINK_SLOTS);
        miss.setByte(1, (byte) slt1);
        miss.setByte(2, (byte) slt2);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite deslincar dos slots.
     * @param slt1 Dirección del primer slot a deslincar.
     * @param slt2 Dirección del segundo slot a deslincar.
     * @return Retorna el mensaje que permite deslincar los dos slots.
     */
    public static byte[] slot_Unlink(int slt1, int slt2) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_UNLINK_SLOTS);
        miss.setByte(1, (byte) slt1);
        miss.setByte(2, (byte) slt2);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite asignar el estado de las funciones consist.
     * @param slot Dirección de memoria del decoder donde se van a modificar los parámtros del consist.
     * @param dirf Dirección y funciones a modificar.
     * @return Retorna el mensaje que permite modificar las funciones del consist.
     */
    public static byte[] consist_Func(int slot, int dirf) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_CONSIST_FUNC);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) dirf);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite escribir un estado de Slot.
     * @param slot Dirección del slot en el que se quiere escribir.
     * @param stat Nuevo estado del slot.
     * @return Retorna el mensaje que permite escribir el slot indicado.
     */
    public static byte[] slot_WriteStat(int slot, int stat) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_SLOT_STAT1);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) stat);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * 
     * @param lopc
     * @param ack
     * @return
     */
    public static byte[] longAck(int lopc, int ack) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LONG_ACK);
        miss.setByte(1, (byte) lopc);
        miss.setByte(2, (byte) ack);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite escribir los valores de un sensor.
     * @param addr Dirección del sensor.
     * @param sI Fuente de entrada.
     * @param sL Nivel del sensor.
     * @param sX Bit de control.
     * @return Retorna el mensaje que permite escribir los valores de un sensor.
     */
    public static byte[] sensor_Write(int addr, boolean sI, boolean sL, boolean sX) {
        int stateI = 0;
        if (sI) {
            stateI = 1;
        }
        int stateL = 0;
        if (sL) {
            stateL = 1;
        }
        int stateX = 0;
        if (sX) {
            stateX = 1;
        }

        byte stateILX = (byte) ((byte) (((stateI << 1) | stateL) << 1) | stateX);

        Message miss = new Message(4);
        addr--;
        miss.setByte(0, (byte) OPC_INPUT_REP);
        miss.setByte(1, (byte) (addr & 0x7F));
        miss.setByte(2, (byte) ((stateILX << 4) | (addr >> 7 & 0x0F)));
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite conocer el estado de un desvio o semáforo.
     * @param addr Dirección del sensor.
     * @param IO Por defecto este valor es 1.
     * @param sI Fuente de entrada.
     * @param sL Bit de control.
     * @return Retorna el mensaje que permite conocer el estado de un desvio o un semáforo.
     */
    public static byte[] sensor_State(int addr, boolean IO, boolean sI, boolean sL) {
        int stateIO = 0;
        if (IO) {
            stateIO = 1;
        }
        int stateI = 0;
        if (sI) {
            stateI = 1;
        }
        int stateL = 0;
        if (sL) {
            stateL = 1;
        }

        byte stateIL = (byte) ((byte) (((stateIO << 1) | stateI) << 1) | stateL);
        Message miss = new Message(4);
        addr--;
        miss.setByte(0, (byte) OPC_SW_REP);
        miss.setByte(1, (byte) (addr & 0x7F));
        miss.setByte(2, (byte) ((stateIL << 4) | (addr >> 7 & 0x0F)));
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * 
     * @param addr
     * @param on_off_value
     * @return
     */
    public static byte[] swich_Req(int addr, ON_OFF on_off_value) {
        Message miss = new Message(4);
        addr--;
        miss.setByte(0, (byte) OPC_SW_REQ);
        miss.setByte(1, (byte) (addr & 0x7F));
        miss.setByte(2, (byte) (on_off_value.value | (addr >> 7 & 0x0F)));
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite permite activar la bocina de una locomotora, este 
     * método solo funciona en aquellas locomotoras que tengan esta función.
     * @param slot Dirección de memória del decoder donde se guardan los parámetros de la locomotora.
     * @param sound Valor del slot de sonido.
     * @return Retorna el mensaje que permite activar la bocina de la locomotora.
     */
    public static byte[] lok_Sound(int slot, int sound) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LOCO_SND);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) sound);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite cambiar el valor de las funciones del slot de 
     * dirección.
     * @param slot Dirección de memória del decoder donde se guardan los parámetros de la locomotora. 
     * @param dirf Valor de la dirección de locomotora y funciones.
     * @return Retorna el mensaje que permite cambiar el valor de las funciones del slot de dirección.
     */
    public static byte[] lok_DirF(int slot, int dirf) {
        //Dirf = 0 Direction number 1 of locomotive
        //Dirf = 32 dirction number 2 of locomotive
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LOCO_DIRF);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) dirf);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite cambiar la velocidad de una locomotora.
     * @param slot Dirección de memória del decoder donde se guardan los parámetros de la locomotora.
     * @param speed Nueva velocidad de la locomotora.
     * @return
     */
    public static byte[] lok_Speed(int slot, int speed) {
        Message miss = new Message(4);
        miss.setByte(0, (byte) OPC_LOCO_SPD);
        miss.setByte(1, (byte) slot);
        miss.setByte(2, (byte) speed);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * Método que permite escribir el valor de un slot.
     * @param slot Dirección de memoria donde se guardan los parámetros de la locomotora.
     * @param stat 
     * @param Lok
     * @param speed
     * @param dirf
     * @param trk
     * @param ss2
     * @param addr2
     * @param snd
     * @param id1
     * @param id2
     * @return
     */
    public static byte[] slot_DataWrite(int slot, int stat, int Lok, int speed,
            int dirf, int trk, int ss2, int addr2, int snd, int id1, int id2) {
        Message miss = new Message(14);
        miss.setByte(0, (byte) OPC_WR_SL_DATA);
        miss.setByte(1, (byte) 0x0E);
        miss.setByte(2, (byte) slot);
        miss.setByte(3, (byte) stat);
        miss.setByte(4, (byte) Lok);
        miss.setByte(5, (byte) speed);
        miss.setByte(6, (byte) dirf);
        miss.setByte(7, (byte) trk);
        miss.setByte(8, (byte) ss2);
        miss.setByte(9, (byte) addr2);
        miss.setByte(10, (byte) snd);
        miss.setByte(11, (byte) id1);
        miss.setByte(12, (byte) id2);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     *
     * @param slot
     * @param stat
     * @param Lok
     * @param speed
     * @param dirf
     * @param trk
     * @param ss2
     * @param addr2
     * @param snd
     * @param id1
     * @param id2
     * @return
     */
    public static byte[] slot_DataRead(int slot, int stat, int Lok, int speed,
            int dirf, int trk, int ss2, int addr2, int snd, int id1, int id2) {
        Message miss = new Message(14);
        miss.setByte(0, (byte) 0xE7);
        miss.setByte(1, (byte) 0x0E);
        miss.setByte(2, (byte) slot);
        miss.setByte(3, (byte) stat);
        miss.setByte(4, (byte) Lok);
        miss.setByte(5, (byte) speed);
        miss.setByte(6, (byte) dirf);
        miss.setByte(7, (byte) trk);
        miss.setByte(8, (byte) ss2);
        miss.setByte(9, (byte) addr2);
        miss.setByte(10, (byte) snd);
        miss.setByte(11, (byte) id1);
        miss.setByte(12, (byte) id2);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     *
     * @param src
     * @param dstl
     * @param dsth
     * @param pxct1
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param pxct2
     * @param d5
     * @param d6
     * @param d7
     * @param d8
     * @return
     */
    public static byte[] peerXFer(int src, int dstl, int dsth, int pxct1,
            int d1, int d2, int d3, int d4, int pxct2, int d5, int d6, int d7,
            int d8) {
        Message miss = new Message(14);
        miss.setByte(0, (byte) 0xE5);
        miss.setByte(1, (byte) 0x0F);
        miss.setByte(2, (byte) src);
        miss.setByte(3, (byte) dstl);
        miss.setByte(4, (byte) dsth);
        miss.setByte(5, (byte) pxct1);
        miss.setByte(6, (byte) d1);
        miss.setByte(7, (byte) d2);
        miss.setByte(8, (byte) d3);
        miss.setByte(9, (byte) d4);
        miss.setByte(10, (byte) pxct2);
        miss.setByte(11, (byte) d5);
        miss.setByte(12, (byte) d6);
        miss.setByte(13, (byte) d7);
        miss.setByte(14, (byte) d8);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     *
     * @param reps
     * @param dhi
     * @param im1
     * @param im2
     * @param im3
     * @param im4
     * @param im5
     * @return
     */
    public static byte[] immPacket(int reps, int dhi, int im1, int im2,
            int im3, int im4, int im5) {
        Message miss = new Message(14);
        miss.setByte(0, (byte) 0xED);
        miss.setByte(1, (byte) 0x0B);
        miss.setByte(2, (byte) 0x7F);
        miss.setByte(3, (byte) reps);
        miss.setByte(4, (byte) dhi);
        miss.setByte(5, (byte) im1);
        miss.setByte(6, (byte) im2);
        miss.setByte(7, (byte) im3);
        miss.setByte(8, (byte) im4);
        miss.setByte(9, (byte) im5);
        miss.calculateChecksum();
        return (miss.getMiss());
    }

    /**
     * 
     * @param b
     * @return
     */
    public static int messageSize(int b) {
        switch (b / 32) {
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 6;
            case 7:
                return b;
        }
        return 0;
    }

    /**
     * 
     * @param eventMessage
     * @return
     */
    public static EventObject toEventObject(EventMessage eventMessage) {
        //Just For Debuging
        //System.out.println(eventMessage);
        //
        int eventType = eventMessage.getEventType();
        if (eventType == Protocol.EVENT_SENSOR_STATE) {
            return newEventSensorState(eventMessage);
        }
        if (eventType == Protocol.EVENT_LOK_SPEED) {
            return newEventLokSpeed(eventMessage);
        }
        if (eventType == Protocol.EVENT_LOK_DIRECTION) {
            return newEventLokDirection(eventMessage);
        }
        if (eventType == Protocol.EVENT_LISSY_SIGNAL) {
            return newEventLissySignal(eventMessage);
        }
        if (eventType == Protocol.EVENT_LOK_F_STATE) {
            return newEventLokFstate(eventMessage);
        }
        if (eventType == Protocol.EVENT_LOK_GLOBAL_STATE) {
            return newEventLokGlobalState(eventMessage);
        }
        if (eventType == Protocol.EVENT_TRN_STATE) {
            return newEventTrnState(eventMessage);
        }
        if (eventType == Protocol.EVENT_SENSOR_STATE2) {
            return newEventSensorState2(eventMessage);
        }
        if (eventType == Protocol.EVENT_SLOT_STATE) {
            return newEventSlotState(eventMessage);
        }
        if ((eventType == 131) || (eventType == 133) || (eventType == 129) || (eventType == 130)) {
            return newEventDecoderState(eventMessage);
        }
        if (eventType == Protocol.EVENT_SLOT_CONSIST) {
            return newEventSlotConsist(eventMessage);
        }
        if (eventType == Protocol.EVENT_TURNOUT_STATE) {
            return newEventTrnState(eventMessage);
        }

        return new EventObject("Unknown");
    }

    private static EventObject newEventSlotConsist(EventMessage eventMessage) {
        int slot = eventMessage.getByte(1);
        byte consist_s = (byte) eventMessage.getByte(2);;
        return new EventSlotConsist(slot, consist_s);
    }

    private static EventObject newEventDecoderState(EventMessage eventMessage) {
        int id = eventMessage.getByte(1);
        int state = 4;

        switch (eventMessage.getByte(0)) {
            case 130:
                state = 0;
                break;
            case 131:
                state = 1;
                break;
            case 129:
                state = 2;
                break;
            case 133:
                state = 3;
                break;
        }
        return new EventDecoderState(id, state);
    }

    private static EventObject newEventSlotState(EventMessage eventMessage) {
        int slot = eventMessage.getByte(1);
        byte slot_s = (byte) (eventMessage.getByte(2) >> 7);
        byte consist_s = (byte) ((eventMessage.getByte(2) & 0x60) >> 5);
        byte busy_act = (byte) ((eventMessage.getByte(2) & 0x18) >> 3);
        byte type_cod = (byte) (eventMessage.getByte(2) & 0x07);
        return new EventSlotState(slot, slot_s, consist_s, busy_act, type_cod);
    }

    private static EventObject newEventTrnState(EventMessage eventMessage) {
        return new EventTrnState(eventMessage.getByte(1) + 1, (eventMessage.getByte(2) >> 5) == 0 ? false : true);
    }

    private static EventObject newEventLokGlobalState(EventMessage eventMessage) {
        int slot = eventMessage.getByte(2);
        int addr = eventMessage.getByte(4);
        int speed = eventMessage.getByte(5);
        boolean direction = ((eventMessage.getByte(6) << 5) == 0 ? false : true);
        int fstate = eventMessage.getByte(6) >> 3;
        return new EventLokGlobalState(addr, slot, speed, direction, fstate);
    }

    private static EventObject newEventLokFstate(EventMessage eventMessage) {
        int slot = eventMessage.getByte(1);
        int fstate = eventMessage.getByte(2) >> 3;
        return new EventLokFstate(slot, fstate);
    }

    private static EventObject newEventLissySignal(EventMessage eventMessage) {
        if (eventMessage.getByte(3) != 32) {
            return newEventLissySignalP1(eventMessage);
        }
        return newEventLissySignalP2(eventMessage);
    }

    private static EventObject newEventLissySignalP1(EventMessage eventMessage) {
        int id;
        int lok;
        boolean direction;
        id = eventMessage.getByte(4);
        lok = eventMessage.getByte(6);
        direction = eventMessage.getByte(3) != 32 ? false : true;
        return new EventLissySignalP1(id, lok, direction);
    }

    private static EventObject newEventLissySignalP2(EventMessage eventMessage) {
        int id;
        int lok;
        int speed = 0;
        id = eventMessage.getByte(4);
        lok = eventMessage.getByte(3);
        speed = eventMessage.getByte(6);
        return new EventLissySignalP2(id, lok, speed);
    }

    private static EventObject newEventLokDirection(EventMessage eventMessage) {
        int slot = eventMessage.getByte(1);
        boolean direction = ((eventMessage.getByte(2) >> 5) == 0 ? false : true);
        int func = (eventMessage.getByte(2) & ~(1 << 5));
        return new EventLokDirection(slot, direction, func);
    }

    private static EventObject newEventLokSpeed(EventMessage eventMessage) {
        int slot = eventMessage.getByte(1);
        int speed = eventMessage.getByte(2);
        return new EventLokSpeed(slot, speed);
    }

    private static EventObject newEventSensorState(EventMessage eventMessage) {
        int byteaddr = ((byte) (eventMessage.getByte(1) | ((eventMessage.getByte(2) & 0x0F) << 7)));
        int dispaddr = (byteaddr / 8) + 1;
        int s_l = (eventMessage.getByte(2) & 0x20) >> 5; //source
        boolean s_i = (((eventMessage.getByte(2) & 0x10) >> 4) == 0 ? false : true); //sensor level
        int sensoraddr = ((((byteaddr * 2) + 1) % 16) + s_l);
//	String codepos = dispaddr+"."+sensoraddr;
//      return new EventSensorState(byteaddr, codepos, s_i);
        int id = (dispaddr - 1) * 16 + sensoraddr;
        return new EventSensorState(id, s_i);
    }

    private static EventObject newEventSensorState2(EventMessage eventMessage) {
        int addr = ((byte) (eventMessage.getByte(1) | ((eventMessage.getByte(2) & 0x0F) << 7)));
        byte s_x = (byte) ((eventMessage.getByte(2) & 0x40) >> 6);
        byte s_i = (byte) ((eventMessage.getByte(2) & 0x20) >> 5); //source
        byte s_l = (byte) ((byte) (eventMessage.getByte(2) & 0x10) >> 4); //sensor level
        byte[] bitcontrol = new byte[3];
        bitcontrol[0] = s_x;
        bitcontrol[1] = s_i;
        bitcontrol[2] = s_l;
        return new EventSensorState(addr, bitcontrol);
    }

    /**
     * 
     * @param b
     * @return
     */
    public static boolean isOpcode(int b) {
        return (b & 0x80) != 0;
    }
}
