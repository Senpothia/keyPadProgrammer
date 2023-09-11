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
public class Controller {

    private boolean isCompteur;
    private boolean isActifs;
    private boolean isArret;
    private boolean isPause;
    private boolean isErreur;
    private boolean isSequence;
    private boolean isAcquittement;
    private boolean isFichier;
    private boolean isFin;
    private boolean isFermeture;
   

    public void parser(String inputLine){

        inputLine = inputLine.trim();
        /*
        isCompteur = inputLine.startsWith(Constants.TOTAL);
        isActifs = inputLine.startsWith(Constants.ACTIFS);
        isArret = inputLine.startsWith(Constants.ARRETS);
        isPause = inputLine.startsWith(Constants.PAUSES);
        isErreur = inputLine.startsWith(Constants.ERREUR);
        isSequence = inputLine.startsWith(Constants.SEQUENCE);
        isAcquittement = inputLine.startsWith(Constants.ACQUITTEMENT);
        isFichier = inputLine.startsWith(Constants.FICHIER);
        isFin = inputLine.startsWith(Constants.FIN);
        isFermeture = inputLine.startsWith(Constants.ACQ_FERMER);
        rapport.setAcquittement(false);

        System.out.println("isCompteur: " + isCompteur);
        System.out.println("isActif: " + isActifs);
        System.out.println("isArret: " + isArret);
        System.out.println("isArret: " + isPause);
        System.out.println("isErreur: " + isErreur);
        System.out.println("isSequence: " + isSequence);
        System.out.println("isFin: " + isFin);
        System.out.println("isFermeture: " + isFermeture);
        System.out.println("isAcquittement: " + isAcquittement);

        if (isCompteur) {

            System.out.println("inputLine: " + inputLine);
            gestionCompteurs(inputLine);

        }

        if (isSequence) {

            gestionSequence(inputLine, formSceance);

        }

        if (isActifs) {

            gestionActifs(inputLine);

        }
        if (isArret) {

            gestionArrets(inputLine);

        }

        if (isPause) {

            gestionPauses(inputLine);

        }

        if (isErreur) {

            gestionErreurs(inputLine);

        }

        if (isAcquittement) {

            gestionAcquittement(inputLine);

        }

        if (isFichier) {

        }

        if (isFin) {

            gestionFin(inputLine);
        }

        if (isFermeture) {
            gestionFermeture(inputLine);

        }

        rapport.setFormSeance(formSceance);
        // context.setFormSceance(formSceance);
        return rapport;
        */
    }

    

    private void gestionSequence(){

        /*
        rapport.setLog("FIN DE SEQUENCE");
        rapport.setColor(Color.RED);
        rapport.setMessage(0);
        rapport.setSauvegarde(true);
        enregistreur.sauvegarderLocal(rapport);                                     //  sauvegardes en locale
        if (!context.isWithoutRemote()) {

            remoteController.sauvegarderSequence(formSceance, context.getLogin());  // sauvegarde en cloud

        }
        */
    }

  

   
    

   

  

   

    private String[] extraire(String inputLine) {

        String[] extraction = inputLine.split(":");

        return extraction;

    }

    private void gestionFin(String inputLine) {

      

    }

    private void gestionAcquittement(String inputLine) {

      
    }

    private void gestionFermeture(String inputLine) {

     

    }

    

   

 

  

   
  

    public void enregistrerSceanceLocal() {

       // enregistreur.sauvegarderSceanceLocal(sceance);
    }

   

}
