package principale;

import controlleurvue.client.HistoriqueClient;
import controlleurvue.groupe.AjoutSejourMairieClient;
import controlleurvue.groupe.Historique;
import fabrique.FabriqueVue;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modele.Sejour;


public class Controlleur {


    private FabriqueVue fabriqueVue;

    public Controlleur(Stage stage){
        this. fabriqueVue= new FabriqueVue(stage, this);
        this.fabriqueVue.creerPageAcceuil();//lancerSplach();
    }

    public  void lancerEditionAssociation() {
        this.fabriqueVue.lancerEditionAssociation();
    }

    public void lancerPageAccueil() {
        this.fabriqueVue.creerPageAcceuil();
    }

    public void lancerPageCentre() {
        this.fabriqueVue.creerEcranVue();
    }


    public void creerCentre() {
        this.fabriqueVue.creerCentreVue();
    }

    public void consulterCentre() {
        this.fabriqueVue.creerConsulterCentreVue();
    }

    public void lancerPageGroupe() {
        this.fabriqueVue.creerVueGroupe();
    }

    public void creerVueGroupeCreation() {
        this.fabriqueVue.creervuecreationgroupe();
    }

    public void consulterGroupe() {
        this.fabriqueVue.creerConsulterGroupeVue();
    }

    public void lancerPageSejour() {
        this.fabriqueVue.creerVueSejour();
    }

    public void creerSejour() {
        this.fabriqueVue.creerVuecreerSejour();
    }

    public void consulterSejour() {
        this.fabriqueVue.creerConsulterSejourVue();
    }

    public void lancerPageClient() {
        this.fabriqueVue.creerVueClient();
    }

    public void creerInscription() {
        this.fabriqueVue.creerVueCreerClient();
    }

    public void consulterClient() {
        this.fabriqueVue.creerConsulterClientVue();
    }

    public void lancerPageInscription() {
        this.fabriqueVue.creerVueInscription();
    }

    public void creerInscriptionSejourVue() {
        this.fabriqueVue.creerVueInscriptionSejour();
    }

    public void consulterInscription() {
        this.fabriqueVue.creerConsulterInscriptionVue();

    }

    public void creerVueConsulterReservation() {
        this.fabriqueVue.creerVueConsulterReservation();
    }

    public void lancerPageSejourHistoriqueClient(String text) {
        HistoriqueClient.id=Integer.parseInt(text);
        fabriqueVue.creerVueHistorique();
    }

    public void creerVueConsulterAnnulation() {
        this.fabriqueVue.creerVueConsulterAnnulation();
    }

    public void lancerLogin() {
        this.fabriqueVue.creerLoginVue();
    }

    public void envoyerEmail(String text) {

        fabriqueVue.creerVueEmail();
    }

    public void lancerPageAssocierSejourGroupe() {
        fabriqueVue.creerVueSejourGroupe();
    }

    public void lancerPageConsulterGroupeSejour() {
        this.fabriqueVue.creerVueGroupeSejourConsulter();
    }

    public void ajouterEnfantMairie(String groupe, String sejour, String nombre) {

        AjoutSejourMairieClient.assocId=nombre;
        AjoutSejourMairieClient.sejourId=sejour;
        AjoutSejourMairieClient.groupe=groupe;

        this.fabriqueVue.creerVueLieMairieSejourEnfant();
    }

    public void lancerHistoriquePaiementGroupeSejour(Label idsejour, Label idgroupe) {
        Historique.id_groupe=idgroupe.getText();
        Historique.id_sejour=idsejour.getText();
        System.out.println("historique groupe "+Historique.id_groupe);
        System.out.println("historique sejour "+Historique.id_sejour);

        this.fabriqueVue.lancerVueHistoriquePaiementMairie();

    }

    public void lancerListeInscritSejourGroupe() {
        this.fabriqueVue.lancerListeSejourClient();
    }

    public void lancerEditionGroupe() {
        this.fabriqueVue.lancerEditionGroupe();
    }

    public void lancerEditionCentre() {
        this.fabriqueVue.lancerEditionCentre();

    }

    public void lancerEditionClient() {
        this.fabriqueVue.lancerEditionClient();
    }

    public void lancerOptionAvancerVue() {
        this.fabriqueVue.lancerVueOptionAvancer();
    }

    public void lancerVueComptabilite() {
        this.fabriqueVue.vueGenererDocCompta();
    }

    public void lancerComptaAvanance() {
        this.fabriqueVue.vueComptaAvance();
    }
}
