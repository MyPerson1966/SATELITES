/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.sweb.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author PSEVO tochka
 */
public class MessageUtils {

    /**
     * Generates a message on actions
     *
     * @param mainMSG
     * @param detailMSG
     */
    public void messageGenerator(String mainMSG, String detailMSG) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mainMSG, detailMSG);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Generates a message on actions
     *
     * @param mainMSG
     * @param detailMSG
     */
    public void messageGeneratorError(String mainMSG, String detailMSG) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mainMSG, detailMSG);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
