/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

import javax.ejb.Stateless;
import kiam.utils.parsers.AbstractParserString;
import kiam.utils.parsers.ParserMPCString;
import kiam.utils.parsers.ParserRESStrings;
import kiam.utils.parsers.ParserTCString;

/**
 *
 * @author User
 */
public class FormatClassificator {

    String formatType = "";

    public FormatClassificator() {
    }

    /**
     * Classificate the given String as one of RES, MPC or TC structure. Then
     * fill up the field of formatType in this class by the Structure name;
     *
     * @param s
     */
    public void classificate(String s) {
        AbstractParserString apRES = new ParserRESStrings(s);
        AbstractParserString apTC = new ParserTCString(s);
        AbstractParserString apMPC = new ParserMPCString(s);
        apRES.blockGenerator(s);
        apTC.blockGenerator(s);
        apMPC.blockGenerator(s);
        if (apRES.isValidDataBlocks()) {
            formatType = "res";
        }
        if (apMPC.isValidDataBlocks()) {
            formatType = "mpc";
        }
        if (apTC.isValidDataBlocks()) {
            formatType = "tc";
        }
    }

    public String getFormatType() {
        return formatType;
    }

}
