package fabrique;

import controlleurvue.*;
import controlleurvue.centre.CentreScreen;
import controlleurvue.centre.CentreVueSpe;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.centre.EditerCentre;
import controlleurvue.client.*;
import controlleurvue.groupe.*;
import controlleurvue.inscription.*;
import controlleurvue.optionsAvance.ComptaAvance;
import controlleurvue.optionsAvance.Comptabilite;
import controlleurvue.optionsAvance.OptionScreen;
import controlleurvue.sejour.ConsulterSejour;
import controlleurvue.sejour.CreerSejour;
import controlleurvue.sejour.SejourScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import principale.Controlleur;

import java.io.IOException;
import java.net.URL;

public class FabriqueVue{

        private Stage stage;
        private Controlleur controlleur;


        public FabriqueVue(Stage stage, Controlleur controller){
            this.stage = stage;
            this.controlleur = controller;
        }


       public void createConnexionView(){
          load(ConnexionView.class.getResource("/vue/connexion.fxml"), "Login");
     }



    public void creerLoginVue(){
        //load(ConsulterSejour.class.getResource("/vue/consulterSejour.fxml"), "ecran.");


        load(Loginscreen.class.getResource("/vue/loginscreen.fxml"), "LOGIN.");
    }



        private Vue load(URL url, String title){
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = null;
            try{
                root = loader.load();
            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }
            Vue view = loader.getController();
            view.setController(controlleur);
            stage.setTitle(title);
            Scene scene=new Scene(root);
            stage.setScene(scene);
          //  stage.setFullScreen(true);
            stage.centerOnScreen();
            stage.show();
            return view;
        }

    public void creerPageAcceuil() {
        load(AccueilScreen.class.getResource("/vue/pageAcceuil.fxml"), "Accueil.");

    }

    public void creerEcranVue() {
        load(AccueilScreen.class.getResource("/vue/centre.fxml"), "gestion centre.");

    }

    public void creerCentreVue() {
        load(CentreScreen.class.getResource("/vue/creerCentre.fxml"), "creer centre.");

    }

    public void creerConsulterCentreVue() {
        load(ConsulterCentre.class.getResource("/vue/consulterCentre.fxml"), "consulter centre.");

    }

    public void creerVueCentreSpe() {
        load(CentreVueSpe.class.getResource("/vue/centreVueSpe.fxml"), "ecran.");

    }


    public void creerVueGroupe() {
        load(GroupeScreen.class.getResource("/vue/groupeScreen.fxml"), "gestion groupe.");

    }



    public void creervuecreationgroupe() {
        load(CreerGroupe  .class.getResource("/vue/creerGroupe.fxml"), "creer groupe.");

    }

    public void creerConsulterGroupeVue() {
        load(ConsulterGroupe.class.getResource("/vue/consulterGroupe.fxml"), "consulter groupe.");

    }

    public void creerVueSejour() {
        load(SejourScreen.class.getResource("/vue/sejourScreen.fxml"), "gestion sejour.");

    }

    public void creerVuecreerSejour() {
        load(CreerSejour.class.getResource("/vue/creerSejour.fxml"), "creer sejour.");

    }

    public void creerConsulterSejourVue() {
        load(ConsulterSejour.class.getResource("/vue/consulterSejour.fxml"), "consulter sejour.");

    }

    public void creerVueClient() {
        load(ClientScreen.class.getResource("/vue/clientScren.fxml"), "gestion client.");

    }

    public void creerVueCreerClient() {
        load(CreerClient.class.getResource("/vue/creerClient.fxml"), "creer client.");

    }

    public void creerConsulterClientVue() {
        load(CreerClient.class.getResource("/vue/consulterClient.fxml"), "consulter client.");



    }

    public void creerVueInscription() {
        load(InscriptionScreen.class.getResource("/vue/inscriptionScreen.fxml"), "gestion inscription.");

    }

    public void creerVueInscriptionSejour() {
        load(CreerInscriptionSejour.class.getResource("/vue/creerInsriptionSejourClient.fxml"), "faire une inscription.");

    }

    public void creerConsulterInscriptionVue() {
        load(ConsulterInscription.class.getResource("/vue/consulterInscription.fxml"), "consulter inscription.");

    }

    public void creerVueConsulterReservation() {
        load(ConsulterReservation.class.getResource("/vue/consulterReservation.fxml"), "consulter reservation.");

    }

    public void creerVueHistorique() {
        load(HistoriqueClient.class.getResource(    "/vue/historiqueClient.fxml"),"client historique");
    }

    public void creerVueConsulterAnnulation() {
        load(ConsulterAnnulation.class.getResource("/vue/consulterAnnulation.fxml"),"consulter annulation");
    }

    public void creerVueEmail() {
        load(Email.class.getResource("/vue/email.fxml")," email");


    }

    public void creerVueSejourGroupe() {
        load(AssocierGroupeSejour.class.getResource("/vue/associerGroupeSejour.fxml")," associer groupe sejour");

    }

    public void creerVueGroupeSejourConsulter() {
        load(consulterAssociationGroupeSejour.class.getResource("/vue/consulterAssociationGroupeSejour.fxml")," consulter association groupe sejour");

    }

    public void creerVueLieMairieSejourEnfant() {
        load(AjoutSejourMairieClient.class.getResource("/vue/ajoutSejourMairie.fxml")," associer enfant a un groupe");

    }

    public void lancerVueHistoriquePaiementMairie() {
        load(Historique.class.getResource("/vue/historiquePaiementMairieSejour.fxml")," historique paiement");

    }

    public void lancerListeSejourClient() {
        load(ListeInscrit.class.getResource("/vue/listeInscritSejour.fxml"),"liste des enfants associe au groupe");

    }

    public void lancerSplach() {
        load(Loginscreen.class.getResource("/vue/splash.fxml"),"chargement ...");

    }

    public void lancerEditionCentre() {
        load(EditerCentre.class.getResource("/vue/EditerCentre.fxml")," editer centre");

    }

    public void lancerEditionGroupe() {
        load(EditerGroupe.class.getResource("/vue/EditerGroupe.fxml")," editer groupe");

    }

    public void lancerEditionAssociation() {
        load(EditerAssociation.class.getResource("/vue/editerAssociationGroupeSejour.fxml")," editer association");

    }

    public void lancerEditionClient() {
        load(EditerClient.class.getResource("/vue/editerClient.fxml")," editer client");

    }

    public void lancerVueOptionAvancer() {
        load(OptionScreen.class.getResource("/vue/optionAvanceeScreen.fxml"),"option avance menu");
    }

    public void vueGenererDocCompta() {
        load(Comptabilite.class.getResource("/vue/comptabilite.fxml"),"comptabilite");

    }

    public void vueComptaAvance() {
        load(ComptaAvance.class.getResource("/vue/comptaAvance.fxml"),"comptabilite");

    }
}
