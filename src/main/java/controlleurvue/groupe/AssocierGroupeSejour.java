package controlleurvue.groupe;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AssocierGroupeSejour implements Vue, Initializable {


    public StackPane stackepane;
    public JFXTreeTableView sejour;
    public JFXTextField chercherSejour;
    public JFXTextField chercherGroupe;
    public Label lduree;
    public Label lprenom;
    public Label type;
    public Label lgroupe;
    public Label centre;
    public int capaciteCentre;

    public Label prix;
    public Label id;
    public Label date;
    public Label lnomGroupe;
    public JFXTextField prixfixe;
    public JFXTreeTableView<Groupe> groupe;
    public JFXTreeTableView<Sejour> sejourarbre;
    public Label lage;
    public Label idgroupe;
    public JFXTextField nbplace;
    public Label codetiers;
    public Label refsejour;
    private Controlleur controlleur;


    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private SejourDao sejourDao;
    private AssociationGroupeSejourDao associationGroupeSejourDao;

    private InscriptionDao inscriptionDao;
    private ReservationDao reservationDao;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }



    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void validerAssociation(MouseEvent mouseEvent) {
        try {


            if (!this.prixfixe.getText().isEmpty() && !this.lduree.getText().isEmpty() && !this.lnomGroupe.getText().isEmpty() && !this.nbplace.getText().isEmpty()) {
                Integer.parseInt(this.prixfixe.getText());
                Integer.parseInt(this.nbplace.getText());
                int nbTotalResev_Insc = 0;
                int nbTotalResev_Insc_this_sejour = 0;

                List<String> listeIdSejour = associationGroupeSejourDao.testCapaciteCentre(this.id.getText());

                for (String id : listeIdSejour) {
                    int nb0 = associationGroupeSejourDao.nbReservationGroupSejourForId(id);
                    int nb1 = reservationDao.nbReservationForId(id);
                    int nb2 = inscriptionDao.nbInscriptionForId(id);
                    nbTotalResev_Insc += nb1 + nb2 + nb0;
                    if (id == this.id.getText()){
                        nbTotalResev_Insc_this_sejour=nbTotalResev_Insc;
                    }
                }

                Sejour sejour = sejourDao.getSejourParId(this.id.getText());
                if (Integer.parseInt(this.nbplace.getText()) + nbTotalResev_Insc_this_sejour > Integer.parseInt(sejour.capacite.get())) {
                    int diff = Integer.parseInt(sejour.capacite.get()) - nbTotalResev_Insc_this_sejour;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alerte");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous souhaitez reserver " + this.nbplace.getText() + " places alors qu'il ne vous reste plus que "
                            + diff + " places sur la capacite du séjour de " + sejour.capacite.get());
                }else {
                    if (Integer.parseInt(this.nbplace.getText()) + nbTotalResev_Insc > this.capaciteCentre) {
                        int dif = this.capaciteCentre - nbTotalResev_Insc;
                        JFXDialogLayout dialogLayout = new JFXDialogLayout();
                        dialogLayout.setHeading(new Text("Attention !"));
                        if (dif < 0) {
                            dialogLayout.setBody(new Text("Vous souhaitez reserver " + this.nbplace.getText() + " places alors que vous avez dejà depassé la capacite total du centre ( " + this.capaciteCentre +
                                    " places) de " + (-1) * dif + " places \npour tous les séjours ne finissants pas avant la date du debut de ce séjour y compris ce séjour.\nVoulez vous quand meme valider ?"));
                        } else {
                            dialogLayout.setBody(new Text("Vous souhaitez reserver" + this.nbplace.getText() + " places alors qu'il ne vous reste plus que "
                                    + dif + " places sur la capacite du centre de " + this.capaciteCentre + " \n" +
                                    "pour tous les séjours ne finissants pas avant la date du debut de ce sejour y compris ce séjour.\nVoulez vous quand meme valider ?"));
                        }
                        JFXButton ok = new JFXButton("oui");
                        JFXButton cancel = new JFXButton("non");

                        final JFXDialog dialog = new JFXDialog(stackepane, dialogLayout, JFXDialog.DialogTransition.CENTER);

                        ok.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(javafx.event.ActionEvent event) {
                                creerAssociationGroupeSejour();

                                dialog.close();


                            }
                        });
                        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                            public void handle(javafx.event.ActionEvent event) {
                                dialog.close();

                            }
                        });
                        dialogLayout.setActions(ok, cancel);
                        dialog.show();
                    } else {
                        creerAssociationGroupeSejour();
                    }
                }

            } else {
                Notification.affichageEchec("Donnees manquantes", "veuillez saisir le(s) champ(s) vide(s) ");
            }
        }catch (NumberFormatException e){
            Notification.affichageEchec("Echec", "veuillez saisir de(s) champ(s) valide(s) ");
        }
    }
    public void creerAssociationGroupeSejour() {

        Associationgroupesejour associationgroupesejour = new Associationgroupesejour(this.prixfixe.getText(), this.idgroupe.getText(), this.id.getText(), this.nbplace.getText());

        int res =0;
        res = associationGroupeSejourDao.inserrerAssociation(associationgroupesejour);
        if (res == 0) {
            Notification.affichageSucces("echec", "L ajout de l association n'a pas pu se faire ");

        } else {
            Notification.affichageSucces("succes insertion", "l  association entre groupe sejour à été fait avec succès");
            this.controlleur.lancerPageAssocierSejourGroupe();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        inscriptionDao= new InscriptionDaoImpl(DBconnexion.getConnection());
        genererLesSejours();
        genererLesGroupes();
    }


    public JFXTreeTableColumn<Groupe,String> genererGroupeCodTiers(){

        JFXTreeTableColumn<Groupe,String> groupe_id=new JFXTreeTableColumn<>(" Code tiers");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().code_tiers;
            }
        });
        return groupe_id;
    }


    public JFXTreeTableColumn<Groupe,String> genererGroupeId(){

        JFXTreeTableColumn<Groupe,String> groupe_id=new JFXTreeTableColumn<>(" Id");
        groupe_id.setPrefWidth(30);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return groupe_id;
    }

    public JFXTreeTableColumn<Groupe,String> genererGroupeNom(){

        JFXTreeTableColumn<Groupe,String> groupenom=new JFXTreeTableColumn<>("groupe nom");
        groupenom.setPrefWidth(100);
        groupenom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().nom_groupe;
            }
        });
        return groupenom;
    }


    private void genererLesGroupes() {

        JFXTreeTableColumn<Groupe,String> inscription_id=this.genererGroupeId();
        JFXTreeTableColumn<Groupe,String> inscription_dateinscription=this.genererGroupeNom();
        JFXTreeTableColumn<Groupe,String> groupetiers=this.genererGroupeCodTiers();

        ObservableList<Groupe> inscriptions = FXCollections.observableArrayList();
        List<Groupe> reservations=groupeDao.listeGroupes();
        for(Groupe groupe: reservations){


            inscriptions.add(groupe);
        }
        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(inscriptions, RecursiveTreeObject::getChildren);
        groupe.getColumns().setAll(inscription_id,inscription_dateinscription,groupetiers);
        groupe.setRoot(root);
        groupe.setShowRoot(false);
        optimiserRechercheGroupe();
        groupe.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsGroupe(newValue)
        );
    }

    private void showDetailsGroupe(TreeItem<Groupe> newValue) {
        if(newValue!=null){
            this.lnomGroupe.setText(newValue.getValue().nom_groupe.get());
            this.idgroupe.setText(newValue.getValue().id.get());

            this.codetiers.setText(newValue.getValue().code_tiers.get());
            this.idgroupe.setVisible(false);
        }
    }

    private void optimiserRechercheGroupe() {
        this.chercherGroupe.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                groupe.setPredicate(new Predicate<TreeItem<Groupe>>() {

                    @Override
                    public boolean test(TreeItem<Groupe> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().nom_groupe.get().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().code_tiers.getValue().toLowerCase().contains(newValue.toLowerCase());
                                ;


                        return flag;


                    }
                });
            }

        });
    }


    public JFXTreeTableColumn<Sejour,String> genererSejourId(){

        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>(" Id");
        sejour_id.setPrefWidth(30);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return sejour_id;
    }

    public JFXTreeTableColumn<Sejour,String> genererDateDebut(){

        JFXTreeTableColumn<Sejour,String> datedebutsejour=new JFXTreeTableColumn<>(" date debut");
        datedebutsejour.setPrefWidth(100);
        datedebutsejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });
        return datedebutsejour;
    }


    public JFXTreeTableColumn<Sejour,String> gerererRefSejour(){

        JFXTreeTableColumn<Sejour,String> datedebutsejour=new JFXTreeTableColumn<>(" ref sejour");
        datedebutsejour.setPrefWidth(100);
        datedebutsejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().refSejour;
            }
        });
        return datedebutsejour;
    }


    public JFXTreeTableColumn<Sejour,String> genererDateFin(){

        JFXTreeTableColumn<Sejour,String> datefinsejour=new JFXTreeTableColumn<>(" date fin");
        datefinsejour.setPrefWidth(100);
        datefinsejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });
        return datefinsejour;
    }


    public JFXTreeTableColumn<Sejour,String> genererTypeSejour(){

        JFXTreeTableColumn<Sejour,String> typesejour=new JFXTreeTableColumn<>(" type");
        typesejour.setPrefWidth(100);
        typesejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });
        return typesejour;
    }



    public JFXTreeTableColumn<Sejour,String> genererCentreSejour(){

        JFXTreeTableColumn<Sejour,String> typesejour=new JFXTreeTableColumn<>(" centre");
        typesejour.setPrefWidth(100);
        typesejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });
        return typesejour;
    }



    private void genererLesSejours() {
        JFXTreeTableColumn<Sejour,String> inscription_id=this.genererSejourId();
        JFXTreeTableColumn<Sejour,String> inscription_dateinscription=this.genererDateDebut();
        JFXTreeTableColumn<Sejour,String> inscription_client=this.genererDateFin();
        JFXTreeTableColumn<Sejour,String> inscription_sejour=this.genererTypeSejour();
        JFXTreeTableColumn<Sejour,String> refSejour=this.gerererRefSejour();

        ObservableList<Sejour> inscriptions = FXCollections.observableArrayList();
        List<Sejour> reservations=sejourDao.listeSejour();
        for(Sejour Sejour: reservations){


            inscriptions.add(Sejour);
        }
        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(inscriptions, RecursiveTreeObject::getChildren);
        sejourarbre.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour,refSejour);
        sejourarbre.setRoot(root);
        sejourarbre.setShowRoot(false);
        optimiserRechercheSejour();
        sejourarbre.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );
    }

    private void showDetailsSejour(TreeItem<Sejour> newValue) {

        if(newValue!=null) {
            this.refsejour.setText(newValue.getValue().refSejour.get());
            this.id.setVisible(false);
            this.id.setText(newValue.getValue().id.get());
            id.setStyle("-fx-font-weight: bold");
            Sejour sejour =sejourDao.getSejourParId(this.id.getText());
            Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
            this.centre.setText(centre.nom_centre.get());
            this.capaciteCentre= Integer.parseInt(centre.capacite_centre.get());

            this.date.setText("Du "+LocalDate.parse(newValue.getValue().date_debut.get()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE ))+
                    " au "+
                    LocalDate.parse(newValue.getValue().date_fin.get()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRANCE )));
            date.setStyle("-fx-font-weight: bold");

            this.lduree.setText(newValue.getValue().duree.get()+" jours");
            lduree.setStyle("-fx-font-weight: bold");
            this.type.setText(newValue.getValue().type.get());
            type.setStyle("-fx-font-weight: bold");

            this.lage.setText(newValue.getValue().ageMin.get()+" - "+newValue.getValue().ageMax.get() );
            lage.setStyle("-fx-font-weight: bold");

            this.prix.setText(newValue.getValue().prix.get()+" ");
            prix.setStyle("-fx-font-weight: bold");


        }
    }

    private void optimiserRechercheSejour() {
        this.chercherSejour.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                sejourarbre.setPredicate(new Predicate<TreeItem<Sejour>>() {

                    @Override
                    public boolean test(TreeItem<Sejour> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().type.get().toLowerCase().contains(newValue.toLowerCase())

                                ||t.getValue().date_fin.get().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().date_debut.get().toLowerCase().contains(newValue.toLowerCase())
                                ||t.getValue().refSejour.get().toLowerCase().contains(newValue.toLowerCase())
                                ;

                        return flag;


                    }
                });
            }

        });
    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }
}
