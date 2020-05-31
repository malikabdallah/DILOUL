package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Centre;
import modele.Reservation;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsulterCentre  implements Initializable, Vue {
    public JFXTextField search_text2;

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageCentre();
    }


    public JFXTextField textnom;
    public JFXTextField textcapacite;
    public Label lnom;
    public Label lcapacite;
    public Label lid;
    private Controlleur controlleur;


    @FXML
    private JFXTreeTableView<Centre> treeView;
    @FXML
    private JFXTextField search_text;
    @FXML
    private StackPane stackepane;


    private JFXTreeTableColumn<Centre, String> creationTableColumCentreI() {

        JFXTreeTableColumn<Centre, String> table = new JFXTreeTableColumn<>("Centre Id");
        table.setPrefWidth(100);
        table.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return table;
    }


    private JFXTreeTableColumn<Centre, String> creationTableColumnomcentre() {
        JFXTreeTableColumn<Centre, String> colonneNomCentre = new JFXTreeTableColumn<>("nom du centre");
        colonneNomCentre.setPrefWidth(110);
        colonneNomCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });

        return colonneNomCentre;

    }


    private JFXTreeTableColumn<Centre, String> creationTableColumnomcCapacite() {
        JFXTreeTableColumn<Centre, String> capaciteCentre = new JFXTreeTableColumn<>("capacite du centre");
        capaciteCentre.setPrefWidth(150);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Centre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Centre, String> param) {
                return param.getValue().getValue().capacite_centre;
            }
        });

        return capaciteCentre;


    }


    public void loadallcentre() {

        JFXTreeTableColumn<Centre, String> centre_id = this.creationTableColumCentreI();
        JFXTreeTableColumn<Centre, String> centre_nom = this.creationTableColumnomcentre();
        JFXTreeTableColumn<Centre, String> centre_capacite = this.creationTableColumnomcCapacite();

        ObservableList<Centre> centres = FXCollections.observableArrayList();
        List<Centre> liste = centreDao.listeCentres();
        for (Centre centre : liste) {
            centres.add(centre);
        }
        final TreeItem<Centre> root = new RecursiveTreeItem<Centre>(centres, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(centre_id, centre_nom, centre_capacite);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


        optimiserRechercheSejour();



    }



    private void optimiserRechercheSejour() {
        search_text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeView.setPredicate(new Predicate<TreeItem<Centre>>() {

                    @Override
                    public boolean test(TreeItem<Centre> t) {

                        boolean flag =
                                t.getValue().id.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().capacite_centre.getValue().toLowerCase().contains(newValue.toLowerCase())
                                        || t.getValue().nom_centre.getValue().toLowerCase().contains(newValue.toLowerCase());

                        return flag;


                    }
                });
            }

        });
    }


    public void loadallcentreParId() {
        JFXTreeTableColumn<Centre, String> centre_id = this.creationTableColumCentreI();
        JFXTreeTableColumn<Centre, String> centre_nom = this.creationTableColumnomcentre();
        JFXTreeTableColumn<Centre, String> centre_capacite = this.creationTableColumnomcCapacite();

        ObservableList<Centre> centres = FXCollections.observableArrayList();
        Centre centre = centreDao.getCentreParId(search_text.getText().toString());
        if (centre != null) {
            centres.add(centre);

            final TreeItem<Centre> root = new RecursiveTreeItem<Centre>(centres, RecursiveTreeObject::getChildren);
            treeView.getColumns().setAll(centre_id, centre_nom, centre_capacite);
            treeView.setRoot(root);
            treeView.setShowRoot(false);
        } else {
            Notification.affichageEchec("echec", "aucun centre avec cette id en base");
            loadallcentre();

        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        this.centreDao = new CentreDaoImpl(DBconnexion.getConnection());
        loadallcentre();
    }


    public void close(javafx.scene.input.MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez partir ?"));

        JFXButton ok = new JFXButton("ok");
        JFXButton cancel = new JFXButton("annule");

        final JFXDialog dialog = new JFXDialog(stackepane, dialogLayout, JFXDialog.DialogTransition.CENTER);

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
        dialogLayout.setActions(ok, cancel);
        dialog.show();
    }


    public void goBack(javafx.scene.input.MouseEvent mouseEvent) {
        this.controlleur.lancerPageCentre();

    }

    private CentreDao centreDao;

    public void setController(Controlleur controller) {

        this.centreDao = new CentreDaoImpl(DBconnexion.getConnection());
        this.controlleur = controller;
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {

        if (search_text.getText().length() == 0) {
            loadallcentre();
        }
        loadallcentreParId();

    }

    public void EditerCentre(MouseEvent mouseEvent) {

        if (this.lid.getText() != null) {
            try {
                String id = this.lid.getText();
                Centre centre = centreDao.getCentreParId(id);
                Integer.parseInt(this.textcapacite.getText());
                if (this.textnom.getText().length() >= 3) {
                    centre.nom_centre.setValue(this.textnom.getText());
                    centre.capacite_centre.setValue(this.textcapacite.getText());
                    int res = centreDao.mettreAjourCentre(this.lid.getText(), centre);
                    if (res != 0) {
                        Notification.affichageSucces("succes", "centre mis a jour");
                        //loadallcentre();
                        this.controlleur.consulterCentre();
                        return;
                    } else {
                        Notification.affichageEchec("echec", "echec mise a jour centre");
                        this.controlleur.consulterCentre();
                        return;

                    }

                } else {

                    Notification.affichageEchec("echec", "Nom du centre incorrect");
                    return;
                }
            } catch (NullPointerException | NumberFormatException e) {
                Notification.affichageEchec("Problème de donnees", "veuillez saisir de(s) champ(s) non vide(s) et valide(s) ");
            }
        }else {
            Notification.affichageEchec("echec", "il faut selectionner un centre");

        }
    }


    public void SupprimerCentre(MouseEvent mouseEvent) {
        try {
            String s=this.search_text2.getText();
            Integer.parseInt(s);
            lancerSuppresion(mouseEvent);
            /*if (s != null && s != "") {
                lancerSuppresion(mouseEvent);

            } else {
                Notification.affichageEchec("echec", "il faut selectionner un centre");
                return;
            }*/
        }catch (Exception  e){
            Notification.affichageEchec("echec", "Veuillez saisir l'id du centre SVP");

        }

    }

    private void lancerSuppresion(MouseEvent mouseEvent) {

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("Voulez vous vraiment supprimer ce centre ?"));

        JFXButton ok = new JFXButton("oui");
        JFXButton cancel = new JFXButton("non");

        final JFXDialog dialog = new JFXDialog(stackepane, dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(MouseEvent -> finaliser(mouseEvent, dialog));
        cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                dialog.close();

            }
        });
        dialogLayout.setActions(ok, cancel);
        dialog.show();

        //this.controlleur.consulterCentre();
    }



    private void finaliser(MouseEvent mouseEvent, JFXDialog dialogLayout) {

        int res = 0;

        String s=this.search_text2.getText();
        res = centreDao.supprimerCentre(s);

        if (res > 0) {
            Notification.affichageSucces("succes", "centre supprimer avec succes");
            loadallcentre();
            dialogLayout.close();
            this.controlleur.consulterCentre();
            return;
        } else {
            Notification.affichageEchec("echec", "Le Centre que vous souhaitez supprimer n'existe pas ou à déjà été supprimé\"");
            dialogLayout.close();
            return;


        }


    }

    public void editerCentre(MouseEvent mouseEvent) {
        if(this.search_text2.getText()!="" && search_text2.getText()!=null){
            Centre centre=centreDao.getCentreParId(search_text2.getText());
            if(centre==null){
                Notification.affichageEchec("id incorrecte","aucun centre avec cette id à été trouvé");

            }else{
                EditerCentre.id=Integer.parseInt(search_text2.getText());
                controlleur.lancerEditionCentre();
            }
        }
    }
}
