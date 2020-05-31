package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import enumerations.Paiement;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Pair;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class consulterAssociationGroupeSejour implements Initializable, Vue {

    public JFXTreeTableView<Associationgroupesejour> association;
    public JFXTextField chercherAssoc;
    public Label nomgroupe;
    public Label typesejour;
    public Label date;
    public Label duree;
    public Label age;
    public Label nombre_place;
    public Label prix_unitaire;
    public Label resteapayer;
    public Label idgroupe;
    public Label idsejour;
    public Label idassoc;
    public StackPane stackepane;
    public Label prixtotal;
    public Label resteapayer1;
    public Label refsejour;
    public Label codetiers;

    private  Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerGroupeId(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>(" Id");
        groupe_id.setPrefWidth(30);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocPrixUnitaire(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>(" prix");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().prix_unitaire;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocSejour(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("sejour");
        groupe_id.setPrefWidth(160);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().sejour;
            }
        });
        return groupe_id;
    }



    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocNbPlace(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("nb place");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().nbPlace;
            }
        });
        return groupe_id;
    }

    private JFXTreeTableColumn<Associationgroupesejour,String> creerAssocGroupe(){
        JFXTreeTableColumn<Associationgroupesejour,String> groupe_id=new JFXTreeTableColumn<>("groupe");
        groupe_id.setPrefWidth(100);
        groupe_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Associationgroupesejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Associationgroupesejour, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return groupe_id;
    }





    public void loadallassociation() {


        JFXTreeTableColumn<Associationgroupesejour, String> assoc_id = this.creerGroupeId();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_nbplace = this.creerAssocNbPlace();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_sejour = this.creerAssocSejour();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_groupe = this.creerAssocGroupe();
        JFXTreeTableColumn<Associationgroupesejour, String> assoc_prixunitaire = this.creerAssocPrixUnitaire();
        ObservableList<Associationgroupesejour> groupes = FXCollections.observableArrayList();
        List<Associationgroupesejour> liste = this.associationGroupeSejourDao.getListes();
        for (Associationgroupesejour associationgroupesejour : liste) {
            Groupe groupe = groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
            Sejour sejour = sejourDao.getSejourParId(associationgroupesejour.sejour.get());

            if (associationgroupesejour.id == null) {
                Notification.affichageEchec("null", "variables a null");
            }

            groupes.add(new Associationgroupesejour(associationgroupesejour.id.get(), associationgroupesejour.prix_unitaire.get(),
                    groupe.nom_groupe.get(), sejour.type.get(), associationgroupesejour.nbPlace.get()));
        }
        final TreeItem<Associationgroupesejour> root = new RecursiveTreeItem<Associationgroupesejour>(groupes, RecursiveTreeObject::getChildren);
        this.association.getColumns().setAll(assoc_id, assoc_nbplace, assoc_sejour, assoc_groupe, assoc_prixunitaire);
        association.setRoot(root);
        association.setShowRoot(false);


        optimiserRechercheSejour();
        this.association.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSejour(newValue)
        );

    }





        private void showDetailsSejour(TreeItem< Associationgroupesejour > newValue) {
            if(newValue!=null) {
                //this.l
                Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(newValue.getValue().id.get());
                Sejour sejour=sejourDao.getSejourParId(associationgroupesejour.sejour.get());

                this.refsejour.setText(sejour.refSejour.get());


                this.age.setText(sejour.ageMin.get()+"-"+sejour.ageMax.get());
                this.duree.setText(sejour.duree.get()+" jours");
                this.date.setText(sejour.date_debut.get()+" au "+sejour.date_fin.get());
                this.typesejour.setText(sejour.type.get());
                this.idsejour.setText(associationgroupesejour.sejour.get());
                this.idsejour.setVisible(false);
                this.idassoc.setText(newValue.getValue().id.get());
                this.idassoc.setVisible(false);

                this.prix_unitaire.setText(newValue.getValue().prix_unitaire.get());
                Groupe groupe=groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
                this.idgroupe.setText(groupe.id.get());
                this.codetiers.setText(groupe.code_tiers.get());
                this.idgroupe.setVisible(false);
                this.nomgroupe.setText(newValue.getValue().groupe.get());
                this.prix_unitaire.setText(newValue.getValue().prix_unitaire.get());
                this.nombre_place.setText(newValue.getValue().nbPlace.get());
                int prix_total=Integer.parseInt(associationgroupesejour.prix_unitaire.get())*Integer.parseInt(associationgroupesejour.nbPlace.get());
                this.prixtotal.setText(String.valueOf(prix_total));
                List<PaiementMarie>list=paiementMairieDao.listePaimenent(Integer.parseInt(idsejour.getText()),Integer.parseInt(idgroupe.getText()));
                int prixPaye=0;
                for(PaiementMarie paiementMarie:list){
                    prixPaye+=Integer.parseInt(paiementMarie.paiement.get());
                }
                this.resteapayer1.setText(String.valueOf(prix_total-prixPaye));

            }
        }

        private void optimiserRechercheSejour() {
            this.chercherAssoc.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    association.setPredicate(new Predicate<TreeItem<Associationgroupesejour>>() {


                        @Override
                        public boolean test(TreeItem<Associationgroupesejour> t) {

                            boolean flag =
                                    t.getValue().groupe.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().sejour.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().nbPlace.getValue().toLowerCase().contains(newValue.toLowerCase())
                                            || t.getValue().id.getValue().toLowerCase().equals(newValue.toLowerCase())
                            ||t.getValue().prix_unitaire.getValue().toLowerCase().equals(newValue.toLowerCase());


                            return flag;


                        }
                    });
                }

            });
        }






private EvenementDao evenementDao;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        evenementDao=new EvenementDaoImpl(DBconnexion.getConnection());
        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        paiementMairieDao=new PaiementMairieDaoImpl(DBconnexion.getConnection());
        loadallassociation();
    }

    private PaiementMairieDao paiementMairieDao;
    private GroupeDao groupeDao;
    private SejourDao sejourDao;
    private AssociationGroupeSejourDao associationGroupeSejourDao;

    public void retour(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void close(MouseEvent mouseEvent) {
    }

    public void validerinscription(MouseEvent mouseEvent) {
    }

    public void ajoutenfant(MouseEvent mouseEvent) {

        if (this.idsejour.getText().isEmpty()){
            Notification.affichageEchec("Message", "Veuillez selectionner un sejour(un groupe) SVP  ");
        }else {
            this.controlleur.ajouterEnfantMairie(this.nomgroupe.getText(), this.idsejour.getText(), this.idassoc.getText());
        }    }


    public void paiement(MouseEvent mouseEvent) {
        if (this.idsejour.getText().isEmpty()){
            Notification.affichageEchec("Message", "Veuillez selectionner un sejour(un groupe) SVP  ");
        }else {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("paiement");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 10, 10, 10));

            Label label = new Label("somme paye");
            Label label2 = new Label("methode");

            TextField from = new TextField();
            from.setPromptText("From");
            TextField to = new TextField();
            to.setPromptText("To");

            ComboBox comboBox = new ComboBox();
            for (Paiement paiement : Paiement.values()) {
                comboBox.getItems().add(paiement);

            }
    /*        gridPane.add(from, 0, 0);
            gridPane.add(new Label("To:"), 1, 0);
            gridPane.add(to, 2, 0);
            gridPane*/
            gridPane.add(label, 0, 0);
            gridPane.add(from, 1, 0);
            gridPane.add(comboBox, 1, 3);
            gridPane.add(label2, 0, 3);


            dialog.getDialogPane().setContent(gridPane);

            // Request focus on the username field by default.
            Platform.runLater(() -> from.requestFocus());

            // Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    try {
                        Pair<String, String> p = new Pair<>(from.getText(), comboBox.getValue().toString());
                        if (from.getText().isEmpty() || comboBox.getValue().toString().isEmpty()){
                            Notification.affichageEchec("Message d'erreur", "Veuillez saisir le(s) champs vide(s)");
                            //dialog.close();
                            paiement(mouseEvent);
                        }else {
                            try {
                                int mt = Integer.parseInt(from.getText());
                                if (mt <=0){
                                    Notification.affichageEchec("Message d'erreur", "Le montant doit etre supperieur à 0");
                                    paiement(mouseEvent);
                                }else
                                    return p;
                            }catch (NumberFormatException e){
                                Notification.affichageEchec("Message d'erreur", "Veuillez saisir un montant svp");
                                paiement(mouseEvent);
                            }

                        }
                    }catch (NullPointerException e){
                        Notification.affichageEchec("Champ(s) vide(s)", "Veuillez saisir les champs vides :)");
                        //dialog.close();
                        paiement(mouseEvent);
                    }
                }
                return null;
            });


            Optional<Pair<String, String>> result = dialog.showAndWait();


            result.ifPresent(pair -> {



                PaiementMarie paiementMarie = new PaiementMarie(String.valueOf(idgroupe.getText()), pair.getKey(), String.valueOf(idsejour.getText()), pair.getValue());
                String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                int res = paiementMairieDao.inserrerPaiement(paiementMarie);

                Groupe groupe = groupeDao.getGroupeParId(idgroupe.getText());
                Evenement evenement=new Evenement("1",groupe.code_tiers.get(),idsejour.getText(),"paiement groupe mairie",pair.getKey(),aujourdhui,pair.getValue());
                evenementDao.insererEvenement(evenement);
                if (res != 0) {
                    Notification.affichageSucces("succes", "le paiement a bien ete pris en compte");
                }
            });

        }


    }

    private void annulerReservation(MouseEvent mouseEvent, JFXDialog dialog) {
    }

    public void historiquePaiement(MouseEvent mouseEvent) {
        if (this.idsejour.getText().isEmpty()){
            Notification.affichageEchec("Message", "Veuillez selectionner un sejour(un groupe) SVP  ");
        }else {
            this.controlleur.lancerHistoriquePaiementGroupeSejour(idsejour, idgroupe);
        }    }



    public void supprimer(MouseEvent mouseEvent) {

        if (this.idsejour.getText().isEmpty()) {
            Notification.affichageEchec("Message", "Veuillez selectionner un sejour(un groupe) SVP  ");
        } else {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("ferme"));
            dialogLayout.setBody(new Text("vous voulez vraiment annuler cette asssociation ?"));

            JFXButton ok = new JFXButton("oui");
            JFXButton cancel = new JFXButton("non");

            final JFXDialog dialog = new JFXDialog(stackepane, dialogLayout, JFXDialog.DialogTransition.CENTER);

            ok.setOnAction(MouseEvent -> annulerFinal(dialog));
            cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                public void handle(javafx.event.ActionEvent event) {
                    dialog.close();
                }
            });
            dialogLayout.setActions(ok, cancel);
            dialog.show();

        }
    }

    private void annulerFinal(JFXDialog dialog){

            int res = associationGroupeSejourDao.supprimerById(this.idassoc.getText());
            if (res > 0) {
                Notification.affichageSucces("succes", "association supprimer avec succes");
                this.loadallassociation();
                dialog.close();
                return;
            } else {
                Notification.affichageEchec("echec", "echec dans la suppresion de l association");
                dialog.close();
                return;


            }


    }





    public void listeinscrit(MouseEvent mouseEvent) {
        if (this.idsejour.getText().isEmpty()){
            Notification.affichageEchec("Message", "Veuillez selectionner un sejour(un groupe) SVP  ");
        }else {
            ListeInscrit.assoc_id = this.idassoc.getText();
            ListeInscrit.id_sejour = this.idsejour.getText();
            ListeInscrit.id_groupe = this.idgroupe.getText();
            ListeInscrit.reste = this.resteapayer1.getText();
            this.controlleur.lancerListeInscritSejourGroupe();
        }

    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageGroupe();
    }

    public void editerAssoc(MouseEvent mouseEvent) {
        if(this.idassoc.getText().isEmpty()){
            Notification.affichageEchec("Message", "Veuillez selectionner une association (un groupe) SVP  ");

        }else{
            Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(this.idassoc.getText());
            if(associationgroupesejour==null){
                Notification.affichageEchec("id incorrecte","aucune association avec cet id n'à été trouve");
            }else {
                EditerAssociation.id = this.idassoc.getText();
                controlleur.lancerEditionAssociation();
            }
        }
    }

}
