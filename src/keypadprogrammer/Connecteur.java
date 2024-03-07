/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keypadprogrammer;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michel
 */
public class Connecteur extends Observable {

    public static String portName = null;
    private SerialPort[] ports = null;
    public SerialPort portComm;
    private int baudeRate = 9600;
    private int numDatabits = 8;
    private int parity = 0;
    private int stopBits = 1;
    private int newReadTimeout = 1000;
    private int newWriteTimeout = 0;

    OutputStream outputStream;

    private String inputLine;

    public static String getPortName() {
        return portName;
    }

    public static void setPortName(String portName) {
        Connecteur.portName = portName;
    }

    public int getBaudeRate() {
        return baudeRate;
    }

    public void setBaudeRate(int baudeRate) {
        this.baudeRate = baudeRate;
    }

    public int getNumDatabits() {
        return numDatabits;
    }

    public void setNumDatabits(int numDatabits) {
        this.numDatabits = numDatabits;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int makeConnection(String portName, int baudeRate, int numDataBits, int parity, int stopBits) {

        try {

            if (portName == null) {

                System.out.println("makeConnection() - Port non sélectionné");
                return 0;
            }

            for (SerialPort p : ports) {

                //System.out.println("Interface.makeConnection() - getSystemPortName: " + p.getSystemPortName() + " // " + portName);
                if (p.getSystemPortName().equals(portName)) {

                    portComm = p;
                }
            }

            portComm.setBaudRate(baudeRate);
            portComm.setNumDataBits(numDatabits);
            portComm.setParity(parity);
            portComm.setNumStopBits(stopBits);
            portComm.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, newReadTimeout, newWriteTimeout);
            portComm.openPort();

            if (portComm.isOpen()) {

                System.out.println("Connexion réussie!");
                // return 99;

            } else {

                System.out.println("Connexion échouée!");
                return -1;
            }

        } catch (Exception e) {

            System.out.println("Connexion échouée!");
            return -2;
        }

        portComm.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }

                try {

                    byte[] readBuffer = new byte[100];

                    int numRead = portComm.readBytes(readBuffer,
                            readBuffer.length);
                    byte[] lecture = new byte[numRead];
                    for (int i = 0; i < numRead; i++) {

                        lecture[i] = readBuffer[i];
                    }
                    inputLine = new String(lecture, StandardCharsets.UTF_8);

                    System.out.println("Received -> " + numRead + "bits lus - " + inputLine);
                    notifierResultat();

                } catch (Exception e) {   // Traitement des exceptions

                    System.err.println(e.toString());
                }
            }
        });

        return 99;

    }

    public int disconnect() {

        if (portComm != null) {
            portComm.closePort();
        }
        return 0;

    }

    public List<String> getListPorts() {

        List<String> portNames = new ArrayList<>();
        ports = SerialPort.getCommPorts();
        for (SerialPort p : ports) {

            portNames.add(p.getSystemPortName());
        }

        return portNames;

    }

    public int envoyerData(String dataToSend) {

        outputStream = portComm.getOutputStream();

        try {

            //    System.out.println("Interface.envoyerData(), données: " + dataToSend);
            outputStream.write(dataToSend.getBytes());
            return 1;

        } catch (IOException e) {

            // infoText.setForeground(Color.red);
            //infoText.setText("Erreur de transmission");
            return -1;

        }

    }

    public String getInputLine() {
        return inputLine;
    }

    public void setInputLine(String inputLine) {
        this.inputLine = inputLine;
    }

    public void notifierResultat() {

        this.setChanged();
        this.notifyObservers(this.getInputLine());

    }

    void resetTestBoard() {

    }

    void flushBuffer() {

        portComm.flushIOBuffers();
    }

    public void programmationCompleted(Integer operation) {

        this.setChanged();
        this.notifyObservers(operation);

    }

    public void program(String hexLocation, String bleLocation, boolean envVariable, String programmerLocation) {

        envoyerData(Constants.PROG);
        try {

            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        Runtime runtime = Runtime.getRuntime();
        try {

            if (envVariable) {

                // 
                Process startFUS = runtime.exec("STM32_Programmer_CLI.exe -c port=SWD -startFUS");
                try {

                    Thread.sleep(5000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fin startFUS");

                // 
                Process upgradeBLE = runtime.exec("STM32_Programmer_CLI.exe -c port=SWD mode=UR -ob nSWboot0=0 nboot1=1 nboot0=1 -fwupgrade" + bleLocation + " " + bleLocation + " 0x080CE000 firstinstall=0 -v");

                try {

                    Thread.sleep(15000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fin updateBLE");

                //
                Process startStack = runtime.exec("STM32_Programmer_CLI.exe -c port=SWD -startwirelessstack");

                try {

                    Thread.sleep(5000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fin startStack");

                //
                Process programFirmware = runtime.exec("STM32_Programmer_CLI.exe -c port=SWD -w" + " " + hexLocation + " 0x08000000 -Rst");
                System.out.println("Fin programmation firmware");

            } else {

            }

        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        programmationCompleted(new Integer(10));
        envoyerData(Constants.END_PROG);

    }

    public void erase(boolean envVariable, String programmerLocation) {

        envoyerData(Constants.ERASE);
        try {

            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        Runtime runtime = Runtime.getRuntime();

        try {

            if (envVariable) {

                Process process = runtime.exec("STM32_Programmer_CLI.exe -c port=SWD -e all");
            } else {

            }

        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        programmationCompleted(new Integer(50));
        envoyerData(Constants.END_ERASE);

    }

}
