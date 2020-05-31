package gestiondocuments;

import com.itextpdf.text.DocumentException;
import modele.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface GestionDocs {


    public void genereAttestationFacture(Client client, Sejour sejour);
    public void genereFactureReservation(Client client, Sejour sejour);
    public void genereConfirmationInscription(Client client, Sejour sejour);
    public void genereListeInscritPdf(List<Inscription> inscriptionListBySejour, List<GroupeSejourClient> groupeSejourClientListBySejour, Sejour sejour, Centre centre) throws IOException, DocumentException;
    public void genereListeInscritExcel(List<Inscription> inscriptionListBySejour, List<GroupeSejourClient> groupeSejourClientListBySejour, Sejour sejour, Centre centre);
    public void creerDossiers();


}
