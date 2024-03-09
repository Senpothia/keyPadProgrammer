/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keypadprogrammer;

/**
 *
 * @author Michel
 */
public class Constants {

    static String RESET_HARDWARE = "0";
    static String START = "1";
    static String AQC = "4";
    static String OK = "2";
    static String KO = "3";
    static String PROG = "7";
    static String ERASE = "8";
    static String END_PROG = "9";
    static String END_ERASE = "5";
    static String ERR_PROG = "6";

    static String RESPONSE = "@:ACQ";
    static String FIN = "@:END";
    static String ERREUR = "@:ERROR:";
    static String INTERROGATION = "@INTER:";
    static String CONFORME = "@:OK";
    static String DEFAUT = "@:KO";

    static String LOG_DIRECTORY = ".//logs";

    //********************************************************************************************************
    // ERREURS TRACE1
    static String E1_LOG1 = "Error: FUS_STATE_ERR_UNKNOWN: Unknown error";
    static String E2_LOG1 = "Error: Fus is not yet running, try again";

    static String[] ERREURS_LOG1 = {E1_LOG1, E2_LOG1};

    // REQUIS TRACE1
    //********************************************************************************************************
    // ERREURS TRACE2
    
    
    // REQUIS TRACE2
    static String R1_LOG2 = "Success with Time elapsed during Reconnect:";
    static String R2_LOG2 = "Firmware Upgrade Success";

    static String[] REQUIS_LOG2 = {R1_LOG2, R2_LOG2};

    //********************************************************************************************************
    // ERREURS TRACE3
    
    
    // REQUIS TRACE3
    static String R1_LOG3 = "Success with Time elapsed during Reconnect:";
    static String R2_LOG3 = "FusStartWS activated successfully";
    static String R3_LOG3 = "startwirelessStack command execution finished";

    static String[] REQUIS_LOG3 = {R1_LOG3, R2_LOG3, R3_LOG3};

    //********************************************************************************************************
    
    // ERREURS TRACE4
    
    
    // REQUIS TRACE4
    static String R1_LOG4 = "File download complete";
    static String R2_LOG4 = "Software reset is performed";
   
    static String[] REQUIS_LOG4 = {R1_LOG4, R2_LOG4};

    //********************************************************************************************************
}
