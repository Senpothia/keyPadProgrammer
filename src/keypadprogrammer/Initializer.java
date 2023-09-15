/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keypadprogrammer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Michel
 */
public class Initializer {

    public Initialisation getInit() throws FileNotFoundException, IOException {

        Properties cloudProperpies = new Properties();

        FileReader reader = new FileReader("src\\main\\java\\remote.properties");
        // FileReader reader = new FileReader(".\\remote.properties");
        cloudProperpies.load(reader);

        String programmerDirectory = cloudProperpies.getProperty("programmerDirectory");
        String binaryLocation = cloudProperpies.getProperty("binaryLocation");
        String varEnv = cloudProperpies.getProperty("varEnv");

        Initialisation init = new Initialisation(programmerDirectory, Boolean.parseBoolean(varEnv), binaryLocation);

        return init;
    }

    public void update(String key, String value) {

        try {

            Properties cloudProperpies = new Properties();
            //first load old one:

            FileInputStream configStream = new FileInputStream("src\\main\\java\\params.properties");
            //   FileInputStream configStream = new FileInputStream(".\\remote.properties");
            cloudProperpies.load(configStream);
            configStream.close();

            //modifies existing or adds new property
            cloudProperpies.setProperty(key, value);

            //save modified property file
            FileOutputStream output = new FileOutputStream("src\\main\\java\\params.properties");
            //FileOutputStream output = new FileOutputStream(".\\remote.properties");
            cloudProperpies.store(output, "GALEO TESTER - Properties");
            output.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
