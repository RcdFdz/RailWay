package railway.controlsystem;

import railway.model.Locomotive;
import railway.model.Model;
import railway.model.Semaphore;
import railway.model.Turning;

/**
 * Clase para el control de los distintos dispositivos de la maqueta, vías,
 * desvios, semáforos y locomotoras.
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class RailwayControlSystemWrapper {

    private RailwayControlSystem rcs;
    private Model model;

    /**
     * 
     * @param rcs
     * @param model
     */
    public RailwayControlSystemWrapper(RailwayControlSystem rcs, Model model) {
        super();
        this.rcs = rcs;
        this.model = model;
    }

    /**
     * Método encargado de encender el decoder.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean rcsSetOn() {
        return rcs.rcsSetOn();
    }

    /**
     * Método encargado de apagar el decoder.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean rcsSetOff() {
        return rcs.rcsSetOff();
    }

    /**
     * Método encargado de realizar una parada de emergencia de la locomotora 
     * indicada.
     * @param lok Locomotora que debe realizar la para de emergencia.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokEmergenciStop(String lok) {
        return rcs.lokEmergenciStop(Integer.parseInt(lok));
    }

    /**
     * Método encargado de realizar una para de la locomotora indicada.
     * @param lok Locomotora que debe realizar la parada.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokStop(String lok) {
        return rcs.lokEmergenciStop(Integer.parseInt(lok));
    }

    /**
     * Método encargado de realizar el cambio de velocidad de la locomotora indicada.
     * @param lok Locomotora que debe realizar el cambio de velocidad.
     * @param speed Nueva velocidad de la locomotora.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetSpeed(String lok, String speed) {
        return rcs.lokSetSpeed(Integer.parseInt(lok), Integer.parseInt(speed));
    }

    /**
     * Método encargado de establecer el sentido de circulación 1 de la locomotora.
     * @param lok Locomotora en la que se quiere establecer el sentido de circulación 1.
     * @param dirf 
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetDirection1(String lok, String dirf) {
        return rcs.lokSetDirection1(Integer.parseInt(lok), Integer.parseInt(dirf));
    }

    /**
     * Método encargado de establecer el sentido de circulación 2 de la locomotora.
     * @param lok Locomotora a la que se le quiere establecer el sentido de circulación 2.
     * @param dirf 
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean lokSetDirection2(String lok, String dirf) {
        return rcs.lokSetDirection2(Integer.parseInt(lok), Integer.parseInt(dirf));
    }

    /**
     * 
     * @param lok
     * @return
     */
    public boolean lokState(String lok) {
        return rcs.lokState(Integer.parseInt(lok));
    }

    /**
     * Método encargado de cambiar el estado de un semáforo a verde.
     * @param sem Semáforo al que se le quiere cambiar el estado a verde.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean semSetGreen(String sem) {
        return rcs.semSetGreen(Integer.parseInt(sem));
    }

    /**
     * Método encargado de cambiar el estado de un semáforo a rojo.
     * @param sem Semáforo al que se le quiere cambiar el estado a rojo.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean semSetRed(String sem) {
        return rcs.semSetRed(Integer.parseInt(sem));
    }

    /**
     * Método que permite cambiar el estado de un desvio a abierto.
     * @param trn Desvio al que se le quiere cambiar el estado a abierto.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean trnSetOpen(String trn) {
        return rcs.trnSetOpen(Integer.parseInt(trn));
    }

    /**
     * Método que permite cambiar el estado de un devio a cerrado.
     * @param trn Desvio al que se le quiere cambiar el estado a cerrado.
     * @return Retorna true si se ha podido transmitir la orden al decoder, en caso contrario retorna false.
     */
    public boolean trnSetClose(String trn) {
        return rcs.trnSetClose(Integer.parseInt(trn));
    }

    /////////////////////
    /**
     * 
     */
    public void newClient() {
        String s = "ModelState";
        for (Locomotive lok : model.getLocomotives()) {
            s += ";" + lok;
        }

        for (Semaphore sem : model.getSemaphores()) {
            s += ";" + sem;
        }

        for (Turning trn : model.getTurnings()) {
            s += ";" + trn;
        }
        System.out.println(s);
    }

    /**
     * 
     * @return
     */
    public String toString() {
        return "RailwayControlSystemWrapper of: " + (rcs != null ? rcs : "null");
    }
}
