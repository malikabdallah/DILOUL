package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ConsulterAnnulation implements Initializable, Vue {
    public JFXTextField search_text2;
    public JFXTextField search_text3;

    public Label ldates;
    public Label ltype;
    public Label lcentre;
    public Label lcapacite;
    public Label lprix;

    public Label lnom;
    public Label lprenom;
    public Label ldate;
    public Label lnumero;
    public Label lemail;
    public Label lreste;
    public Label idinscription;
    public Label lidclient;
    public Label lidsejour;
    public Label lmotif;
    private GroupeDao groupeDao;

    /**
     * Initializes the controller class.
     */


    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Annulation> treeView;
    @FXML
    private JFXTextField search_text;



    @FXML
    private StackPane stackepane;



    public JFXTreeTableColumn<Annulation,String> genererInscriptionId(){

        JFXTreeTableColumn<Annulation,String> inscription_id=new JFXTreeTableColumn<>(" Id");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return inscription_id;
    }




    public JFXTreeTableColumn<Annulation,String> geenererIdSejour(){
        JFXTreeTableColumn<Annulation,String> inscription_paiement=new JFXTreeTableColumn<>("type sejour");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().idsejour;
            }
        });
        return inscription_paiement;


    }





    public JFXTreeTableColumn<Annulation,String> genererIdClient(){
        JFXTreeTableColumn<Annulation,String> inscription_dateinscription=new JFXTreeTableColumn<>("client");
        inscription_dateinscription.setPrefWidth(110);
        inscription_dateinscription.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().idclient;
            }
        });
        return  inscription_dateinscription;
    }


    public JFXTreeTableColumn<Annulation,String> genererInscriptionClient(){
        JFXTreeTableColumn<Annulation,String> inscription_client=new JFXTreeTableColumn<>(" motif");
        inscription_client.setPrefWidth(110);
        inscription_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().motif;
            }
        });
        return inscription_client;
    }



    public void chargertouslesinscriptions(){
        JFXTreeTableColumn<Annulation,String> inscription_id=this.genererInscriptionId();
        JFXTreeTableColumn<Annulation,String> inscription_dateinscription=this.geenererIdSejour();
        JFXTreeTableColumn<Annulation,String> inscription_client=this.genererIdClient();
        JFXTreeTableColumn<Annulation,String> inscription_sejour=this.genererInscriptionClient();
        ObservableList<Annulation> inscriptions = FXCollections.observableArrayList();
        List<Annulation> reservations=annulationDao.getAnnulartions();
        for(Annulation annulation: reservations){


            Client client=clientDao.getClientParId(annulation.idclient.get());
            Sejour sejour=sejourDao.getSejourParId(annulation.idsejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();

            Sejour sejour1=sejourDao.getSejourParId(id_sejour);
            Annulation reservation1=new Annulation(annulation.id.get(),annulation.motif.get(),sejour.type.get(),
                    nom_client);


            reservation1.setTriche(sejour1.id.get());
            reservation1.setTriche2(client.id.get());
            inscriptions.add(reservation1);
        }
        final TreeItem<Annulation> root = new RecursiveTreeItem<Annulation>(inscriptions, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


        optimiserRechercheSejour();
        treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );

    }

    private void showDetailsSejour(TreeItem<Annulation> newValue) {

        //this.idinscription.setText(newValue.getValue().id.get());
        Sejour sejour=sejourDao.getSejourParId(newValue.getValue().getTriche());
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        this.lprix.setText(sejour.prix.get());
        this.ldates.setText(sejour.date_debut.get()+" "+sejour.date_fin.get());
        this.ltype.setText(sejour.type.get());
        this.lcentre.setText(centre.nom_centre.get());
        this.lcapacite.setText(centre.capacite_centre.get());


        Client client=clientDao.getClientParId(newValue.getValue().getTriche2());
        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ldate.setText(client.datenaissance.get());
        this.lemail.setText(client.email.get());
        this.lnumero.setText(client.numero.get());

        this.lidclient.setText(client.id.get());
        this.lidsejour.setText(sejour.id.get());
        this.lmotif.setText(newValue.getValue().motif.get());

        int x=Integer.parseInt(sejour.prix.get());


    }

    private void optimiserRechercheSejour() {
        search_text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeView.setPredicate(new Predicate<TreeItem<Annulation>>() {

                    @Override
                    public boolean test(TreeItem<Annulation> t) {

                        boolean flag =
                                t.getValue().id.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().idclient.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().idsejour.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().motif.getValue().toLowerCase().contains(newValue.toLowerCase());



                        return flag;


                    }
                });
            }

        });
    }



    private ClientDao clientDao;
    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private AnnulationDao annulationDao;
    private EvenementDao evenementDao;


    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        annulationDao=new AnnulationDaoImpl(DBconnexion.getConnection());
        evenementDao=new EvenementDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        chargertouslesinscriptions();
    }






    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez partir ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                System.exit(0);
            }
        });
        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();
    }

    public void goBack(javafx.scene.input.MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();

    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {
        chargertouslesinscriptions();
    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void paiement(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("barre de paiement");
        dialog.setHeaderText("mettre a jour paiement ");
        dialog.setContentText("somme paye:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            Reservation reservation=reservationDao.getReservationParId(this.idinscription.getText());
            Inscription inscription=new Inscription(result.get(),reservation.dateinscription.get(),this.lidclient.getText(),
                    this.lidsejour.getText(),reservation.depart.get());
            reservationDao.supprimerParId(this.idinscription.getText());

            inscriptionDao.insererInscription(inscription);
            chargertouslesinscriptions();


        }

// The Java 8 way to get the response value (with lambda expression).
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }

    public void annuler(MouseEvent mouseEvent) {

        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez vraiment annuler cette reservation ?"));

        JFXButton ok=new JFXButton("oui");
        JFXButton cancel=new JFXButton("non");

        final JFXDialog dialog=new JFXDialog(stackepane,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(MouseEvent ->annulerReservation(mouseEvent,dialog));
        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();
    }

    private void annulerReservation(MouseEvent mouseEvent,JFXDialog dialogLayout) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("annulation reservation");
        dialog.setHeaderText("indiquer la raison ");
        dialog.setContentText("motif:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){

            Annulation annulation=new Annulation(result.get(),this.lidsejour.getText(),this.lidclient.getText(),this.lidsejour.getText());
            int res=annulationDao.insererAnnulation(annulation);
            if(res==0){
                Notification.affichageEchec("echec annulation ", "il y a eu une erreur ");

            }else{


                Notification.affichageSucces("annulation","l annulation a bien ete effectue");

                Client client=clientDao.getClientParId(this.lidclient.getText());
                Groupe groupe=groupeDao.getGroupeParId(client.groupe.get());
                Sejour sejour=sejourDao.getSejourParId(this.lidsejour.getText());

                Evenement evenement=new Evenement("1",groupe.code_tiers.get(),sejour.refSejour.get(),
                        "annulation","0",new Date().toString(),"annultation");
                evenementDao.insererEvenement(evenement);
                int bis=reservationDao.supprimerParId(this.idinscription.getText());
                this.chargertouslesinscriptions();

            }
        }


        dialogLayout.close();


    }
}
