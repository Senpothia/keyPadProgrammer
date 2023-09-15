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
public class Initialisation {

    private String programmerDirectory;
    private boolean varEnv;
    private String binaryLocation;

    public Initialisation(String programmerDirectory, boolean varEnv, String binaryLocation) {
        this.programmerDirectory = programmerDirectory;
        this.varEnv = varEnv;
        this.binaryLocation = binaryLocation;
    }

    public String getProgrammerDirectory() {
        return programmerDirectory;
    }

    public void setProgrammerDirectory(String programmerDirectory) {
        this.programmerDirectory = programmerDirectory;
    }

    public boolean isVarEnv() {
        return varEnv;
    }

    public void setVarEnv(boolean varEnv) {
        this.varEnv = varEnv;
    }

    public String getBinaryLocation() {
        return binaryLocation;
    }

    public void setBinaryLocation(String binaryLocation) {
        this.binaryLocation = binaryLocation;
    }

}
