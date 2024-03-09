/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keypadprogrammer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michel
 */
public class ProgController {

    public int createLogFolder(String path) {

        try {
            Path location = Paths.get(path);

            //java.nio.file.Files;
            Files.createDirectories(location);
            return 1;

        } catch (IOException ex) {
            Logger.getLogger(ProgController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;

        }

    }

    public int find(String logfile, String[] erreurs) {

        try {
            FileReader fileReader = new FileReader(logfile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;

            try {

                line = reader.readLine();

            } catch (IOException ex) {

                Logger.getLogger(ProgController.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (line != null) {

                try {
                    line = reader.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ProgController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProgController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

}
