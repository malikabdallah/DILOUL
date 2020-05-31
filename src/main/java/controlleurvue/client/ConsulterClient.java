package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Email;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.centre.CreerCentre;
import controlleurvue.centre.EditerCentre;
import daos.ClientDao;
import daos.impl.ClientDaoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.*;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsulterClient implements Initializable, Vue {



    public JFXTextField search_text2;
    public JFXTextField search_text3;
    public Label lnom;
    public Label lprenom;
    public Label ladresse;
    public Label ldate;
    public Label lportable;
    public Label lobservation;
    public Label lemail;
    public Label lcode;
    public Label lgroupe;
    public Label idclient;
    public JFXTextField chercherclient;
    /**
     * Initializes the controller class.
     */
    private Controlleur controlleur;
    @FXML
    private JFXTreeTableView<Client> treeView;
    @FXML
    private JFXTextField search_text;
    @FXML
    private StackPane stackepane;
    private ClientDao clientDao;


    public JFXTreeTableColumn<Client ,String> genererId(){
        JFXTreeTableColumn<Client,String> client_id=new JFXTreeTableColumn<>("Id");
        client_id.setPrefWidth(30);
        client_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return client_id;

    }

    public JFXTreeTableColumn<Client ,String> genererNom(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>("nom");
        nom_client.setPrefWidth(100);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });
        return nom_client;
    }


    public JFXTreeTableColumn<Client ,String> genererPrenom(){

        JFXTreeTableColumn<Client,String> prenom_client=new JFXTreeTableColumn<>("prenom");
        prenom_client.setPrefWidth(110);
        prenom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });

        return prenom_client;
    }





    public JFXTreeTableColumn<Client ,String> genererGroupe(){

        JFXTreeTableColumn<Client,String> client_groupe=new JFXTreeTableColumn<>(" groupe");
        client_groupe.setPrefWidth(110);
        client_groupe.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().groupe;
            }
        });
        return client_groupe;
    }


    public JFXTreeTableColumn<Client ,String> genererDateNaissance(){

        JFXTreeTableColumn<Client,String> client_datenaissance=new JFXTreeTableColumn<>(" date naissance");
        client_datenaissance.setPrefWidth(110);
        client_datenaissance.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().datenaissance;
            }
        });
        return client_datenaissance;
    }



    public void loadAllClient(){
        JFXTreeTableColumn<Client,String> client_id=this.genererId();
        JFXTreeTableColumn<Client,String> client_nom=this.genererNom();
        JFXTreeTableColumn<Client,String> client_prenom= this.genererPrenom();
        JFXTreeTableColumn<Client,String> client_groupe=this.genererGroupe();
        JFXTreeTableColumn<Client,String> client_datenaissance=this.genererDateNaissance();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        List<Client> liste=clientDao.listeClient();
        for(Client client:liste){
            clients.add(client);
        }
        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(client_id,client_nom,client_prenom,client_groupe,client_datenaissance);
        treeView.setRoot(root);
        treeView.setShowRoot(false);


        optimiserRecherClient();
        treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue));



    }


    private void showDetailsClient(TreeItem<Client> newValue) {
        if(newValue!=null) {
            this.ldate.setText(newValue.getValue().datenaissance.get());
            this.lemail.setText(newValue.getValue().email.get());
            this.lgroupe.setText(newValue.getValue().groupe.get());
            this.lnom.setText(newValue.getValue().nom_client.get());
            this.lprenom.setText(newValue.getValue().prenom_client.get());
            this.ladresse.setText(newValue.getValue().adresse.get());
            this.lobservation.setText(newValue.getValue().observation.get());
            this.lcode.setText(newValue.getValue().codePostale.get());
            this.idclient.setText(newValue.getValue().id.get());
            this.lportable.setText(newValue.getValue().numero.get());
        }
    }
    private void optimiserRecherClient() {
        this.chercherclient.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeView.setPredicate(new Predicate<TreeItem<Client>>() {

                    @Override
                    public boolean test(TreeItem<Client> t) {
                        boolean flag=t.getValue().prenom_client.getValue().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().nom_client.getValue().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().groupe.getValue().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().datenaissance.getValue().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().id.getValue().toLowerCase().contains(newValue.toLowerCase());


                        return flag ;


                    }
                });
            }

        });
    }



    public void initialize(URL location, ResourceBundle resources) {

        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        loadAllClient();
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
        this.controlleur.lancerPageClient();
    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
    }

    public void cherchecentreparid(MouseEvent mouseEvent) {


    }

    public void EditerCentre(MouseEvent mouseEvent) {
    }

    public void SupprimerCentre(MouseEvent mouseEvent) {
        if (this.idclient.getText().isEmpty()){
            Notification.affichageEchec("Alerte","Veuillez séléctionner un client");
        }else {

            int res = clientDao.supprimerClient(this.idclient.getText());

            if (res > 0) {
                Notification.affichageSucces("succes", "client supprimer avec succes");

                loadAllClient();

                //updateStatus();
            } else {
                Notification.affichageEchec("echec", "il y a eu erreur dans la suppression");

            }
        }
    }

    public void registAction(ActionEvent actionEvent) {
    }

    public void hideSignupPane(ActionEvent actionEvent) {
    }

    public void historiqueClient(MouseEvent mouseEvent) {

        if (this.idclient.getText().isEmpty()){
            Notification.affichageEchec("Alerte","Veuillez séléctionner un client");
        }else {
            controlleur.lancerPageSejourHistoriqueClient(this.idclient.getText());
        }
    }

    public void envoieEmail(MouseEvent mouseEvent) {
        //this.controlleur.envoyerEmail(this.idclient.getText());
        Email.idclient=this.idclient.getText();
        try {
            if (!this.idclient.getText().isEmpty()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vue/email.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);

                FileChooser fileChooser = new FileChooser();


                Stage stage = new Stage();

                stage.setTitle("email");
                stage.setScene(scene);
                // Email.stage=stage;
                stage.show();
            }else {
                Notification.affichageEchec("Alerte","Veuillez séléctionner un client");
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
            Notification.affichageEchec("Erreur","Problème d'envoie de mail ");

        }
    }




    public void editerClient(MouseEvent mouseEvent) {
        if(this.idclient.getText().isEmpty()) {
            Notification.affichageEchec("Alerte", "Veuillez séléctionner un client");
        }else {
            Client client=clientDao.getClientParId(idclient.getText());
            if(client==null){
                Notification.affichageEchec("Alerte","aucun client avec cette id à été trouvé");

            }else{
                EditerClient.id=this.idclient.getText();
                controlleur.lancerEditionClient();
            }
        }

    }

}
