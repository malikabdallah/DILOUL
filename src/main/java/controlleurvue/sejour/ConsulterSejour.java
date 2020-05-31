package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Email;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import gestiondocuments.*;
import dto.ClientDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

public class ConsulterSejour implements Initializable, Vue {


    public GroupeSejourClientDao groupeSejourClientDao;
    public Label lcentre;
    public Label lsejour;
    public Label ldate;
    public Label lage;
    public Label lprix;
    public Label lcapacite;
    public JFXTreeTableView<Client> listeClientSejour;
    public Label idsejour;
    public Label prenomnom;
    public Label idclient;
    public Label dateclient;
    public Label numeroclient;
    public Label emailclient;
    public Label reste;
    public JFXTextField chercheclient;
    public JFXRadioButton tous;
    public JFXRadioButton regle;
    public JFXRadioButton retard;
    public Label refsejour;
    public Label numero;

    /**
     * Initializes the controller class.
     */

    private Controlleur controlleur;



    String status=null;
    @FXML
    private JFXTreeTableView<Sejour> treeView;
    @FXML
    private JFXTextField cherchersejour;


    @FXML
    private StackPane stackepane;


    private CentreDao centreDao;
    private GroupeDao groupeDao;

    private GestionDocs gestionDocs;
    private Client client = null;
    private Sejour sejour = null;


    private SejourDao sejourDao;
    private InscriptionDao inscriptionDao;
    private ClientDao clientDao;


    public JFXTreeTableColumn<Sejour,String> genererSejourId(){
        JFXTreeTableColumn<Sejour,String> sejour_id=new JFXTreeTableColumn<>("Id");
        sejour_id.setPrefWidth(20);
        sejour_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return  sejour_id;

    }



    public JFXTreeTableColumn<Sejour,String> genererSejourDuree(){
        JFXTreeTableColumn<Sejour,String> sejour_duree =new JFXTreeTableColumn<>("duree");
        sejour_duree.setPrefWidth(50);
        sejour_duree.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().duree;
            }
        });
        return sejour_duree;
    }




    public JFXTreeTableColumn<Sejour,String> genererDateDebut(){

        JFXTreeTableColumn<Sejour,String> sejour_datedebut=new JFXTreeTableColumn<>("debut");
        sejour_datedebut.setPrefWidth(80);
        sejour_datedebut.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_debut;
            }
        });
        return  sejour_datedebut;
    }



    public JFXTreeTableColumn<Sejour,String> genererDateFin(){
        JFXTreeTableColumn<Sejour,String> sejour_datefin=new JFXTreeTableColumn<>("fin");
        sejour_datefin.setPrefWidth(80);
        sejour_datefin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().date_fin;
            }
        });
        return sejour_datefin;
    }




    public JFXTreeTableColumn<Sejour,String> genererSejourType(){
        JFXTreeTableColumn<Sejour,String> sejour_type=new JFXTreeTableColumn<>(" type");
        sejour_type.setPrefWidth(110);
        sejour_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().type;
            }
        });

        return sejour_type;
    }



    public JFXTreeTableColumn<Sejour,String> genererCentre(){
        JFXTreeTableColumn<Sejour,String> sejour_centre=new JFXTreeTableColumn<>(" centre");
        sejour_centre.setPrefWidth(110);
        sejour_centre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Sejour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Sejour, String> param) {
                return param.getValue().getValue().nom_centre;
            }
        });

        return sejour_centre;
    }



    public void chargerTousLesSejours(){
        JFXTreeTableColumn<Sejour,String> sejour_id=this.genererSejourId();
        JFXTreeTableColumn<Sejour,String> sejour_duree =this.genererSejourDuree();
        JFXTreeTableColumn<Sejour,String> sejour_datedebut=this.genererDateDebut();
        JFXTreeTableColumn<Sejour,String> sejour_datefin=this.genererDateFin();
        JFXTreeTableColumn<Sejour,String> sejour_type=this.genererSejourType();
        JFXTreeTableColumn<Sejour,String> sejour_centre=this.genererCentre();
        ObservableList<Sejour> sejours = FXCollections.observableArrayList();
        List<Sejour> liste=sejourDao.listeSejour();
        for(Sejour sejour:liste){
            System.out.println("hihihihihihi");
            sejours.add(sejour);
        }

        final TreeItem<Sejour> root = new RecursiveTreeItem<Sejour>(sejours, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(sejour_id, sejour_duree,sejour_datedebut,sejour_datefin,sejour_type,sejour_centre);

        treeView.setRoot(root);
        treeView.setShowRoot(false);


    }


    public void initialize(URL location, ResourceBundle resources) {
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        gestionDocs = new GestionDocsImpl();

        groupeSejourClientDao=new GroupeSejourClientDaoImpl(DBconnexion.getConnection());

        this.retard.setSelected(true);
        this.regle.setSelected(false);
        this.tous.setSelected(false);

        retard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                regle.setSelected(false);
                tous.setSelected(false);
              //  remplirListeClient();
                    if(idsejour.getText()!=""){
                        Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                        RemplirClientSejour(sejour);


                    }
            }
        });



        tous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retard.setSelected(false);
                regle.setSelected(false);
                if(idsejour.getText()!=""){
                    Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                    RemplirClientSejour(sejour);
                }




            }
        });


        regle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                retard.setSelected(false);
                tous.setSelected(false);
                if(idsejour.getText()!=""){
                    Sejour sejour=sejourDao.getSejourParId(idsejour.getText());
                    RemplirClientSejour(sejour);
                }

            }
        });
        chargerTousLesSejours();


    }

    private void remplirListeClient() {
    }

    private void changerValue(boolean b) {
        retard.setSelected(b);
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
        this.controlleur.lancerPageSejour();

    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;

        this.treeView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );
        optimiserRecherche();
    }

    private void showDetails(TreeItem<Sejour> newValue) {
        if(newValue!=null) {
            sejour=sejourDao.getSejourParId(newValue.getValue().id.get());
            this.lage.setText(newValue.getValue().ageMin.get() + " - " + newValue.getValue().ageMax.get());
            this.lcapacite.setText(newValue.getValue().capacite.get());
            this.lcentre.setText(newValue.getValue().nom_centre.get());
            this.lprix.setText(newValue.getValue().prix.get());
            this.lsejour.setText(newValue.getValue().type.get());
            this.ldate.setText(newValue.getValue().date_debut.get() + " au " + newValue.getValue().date_fin.get());
            this.idsejour.setText(newValue.getValue().id.get());
            this.refsejour.setText(newValue.getValue().refSejour.get());
            this.numero.setText(newValue.getValue().numero.get());
            RemplirClientSejour(newValue.getValue());

        }
    }




    public JFXTreeTableColumn<Client,String> genererClientNom(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" nom");
        nom_client.setPrefWidth(60);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });

        return nom_client;
    }


    public JFXTreeTableColumn<Client,String> genererPrenomClient(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" prenom");
        nom_client.setPrefWidth(60);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });

        return nom_client;
    }



    public JFXTreeTableColumn<Client,String> genererIdClient(){
        JFXTreeTableColumn<Client,String> nom_client=new JFXTreeTableColumn<>(" id");
        nom_client.setPrefWidth(30);
        nom_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return nom_client;
    }



    public void RemplirClientSejour(Sejour value) {
        //Sejour sejour=sejourDao.getSejourParId(se);
       // this.refsejour.setText(sejour.refSejour.getValue());
        List<Inscription>listeInscription=this.inscriptionDao.getInscriptionsParIdSejour(value.id.get());
        List<Client>listeClient=new ArrayList<>();
        for(Inscription inscription:listeInscription){
            if(tous.isSelected()){
                Client client = clientDao.getClientParId(inscription.code_client.get());
                listeClient.add(client);
            }else{
                if(regle.isSelected()){
                    int payer=Integer.parseInt(inscription.paiement.get());
                    int prixTotal=Integer.parseInt(lprix.getText());


                    if(payer==prixTotal){
                        Client client=clientDao.getClientParId(inscription.code_client.get());
                        listeClient.add(client);

                    }

                }else{
                    int payer=Integer.parseInt(inscription.paiement.get());
                    int prixTotal=Integer.parseInt(lprix.getText());


                    if(payer<prixTotal){
                        Client client=clientDao.getClientParId(inscription.code_client.get());
                        listeClient.add(client);

                    }
                }
            }




            }



        JFXTreeTableColumn<Client,String> idclient=this.genererIdClient();
        JFXTreeTableColumn<Client,String> nomclient =this.genererClientNom();
        JFXTreeTableColumn<Client,String> prenomclient =this.genererPrenomClient();

        ObservableList<Client>clients=FXCollections.observableArrayList();
        for(Client client:listeClient){
            clients.add(client);
        }


        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);

        listeClientSejour.getColumns().setAll(idclient, nomclient,prenomclient);

        listeClientSejour.setRoot(root);
        listeClientSejour.setShowRoot(false);





        this.listeClientSejour.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue)
        );
          omptimiserRechercheClient();



    }
















    private void omptimiserRechercheClient() {

        this.chercheclient.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ConsulterSejour.this.listeClientSejour.setPredicate(new Predicate<TreeItem<Client>>() {

                    @Override
                    public boolean test(TreeItem<Client> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().prenom_client.get().toLowerCase().contains(newValue.toLowerCase()) ||
                                t.getValue().nom_client.get().toLowerCase().contains(newValue.toLowerCase());

                        return flag;


                    }
                });
            }

        });
    }

    private void showDetailsClient(TreeItem<Client> newValue) {

        if(newValue!=null) {
            client = clientDao.getClientParId(newValue.getValue().id.get());
            sejour = sejourDao.getSejourParId(this.idsejour.getText());
            Groupe groupe= groupeDao.getGroupeParId(client.groupe.get());


            this.idclient.setText(groupe.code_tiers.get());

            this.dateclient.setText(client.datenaissance.get());
            this.prenomnom.setText(client.prenom_client.get() + " " + client.nom_client.get());
            this.emailclient.setText(client.email.get());
            this.numeroclient.setText(client.numero.get());

            Inscription inscription = inscriptionDao.getInscriptionsParIdSejourEtIdClient(this.sejour.id.get(), this.client.id.get());
            int reste = Integer.parseInt(this.lprix.getText()) - Integer.parseInt(inscription.paiement.get());

            this.reste.setText(String.valueOf(reste));

            if (reste > 0) {
                this.reste.setTextFill(Color.web("#ff0000"));
            } else if (reste == 0) {
                this.reste.setTextFill(Color.web("#00ff00"));

            }
        }

    }


    private void optimiserRecherche() {
        this.cherchersejour.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                ConsulterSejour.this.treeView.setPredicate(new Predicate<TreeItem<Sejour>>() {

                    @Override
                    public boolean test(TreeItem<Sejour> t) {

                        boolean flag =t.getValue().id.get().toLowerCase().contains(newValue.toLowerCase())||
                                t.getValue().duree.get().toLowerCase().contains(newValue.toLowerCase()) ||
                        t.getValue().type.get().toLowerCase().contains(newValue.toLowerCase())
                                ||t.getValue().prix.get().toLowerCase().contains(newValue.toLowerCase())

                                ||t.getValue().nom_centre.get().toLowerCase().contains(newValue.toLowerCase())
                                ||t.getValue().date_fin.get().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().date_debut.get().toLowerCase().contains(newValue.toLowerCase())
;


                        return flag;


                    }
                });
            }

        });
    }



    public void genereliste(MouseEvent mouseEvent) throws IOException, DocumentException {
        if (this.idsejour.getText().isEmpty()) {
            Notification.affichageEchec("Message Echec", "Veuillez selectionner un sejour SVP  ");

        } else {

            Sejour sejour = sejourDao.getSejourParId(this.sejour.id.get());//this.idsejour.getText());

            List<Inscription> inscriptionsListBySejour = inscriptionDao.getInscriptionsParIdSejour(this.sejour.id.get());
            List<GroupeSejourClient> groupeSejourClientListBySejour = groupeSejourClientDao.getGroupeSejourClientByIdSejour(this.sejour.id.get());

            for (Inscription ins : inscriptionsListBySejour){
                System.out.println("Id Insc"+ins.id.get()+"Id Client"+ins.code_client.get()+"Id Sej"+ins.id_sejour.get());
            }

            for (GroupeSejourClient  grpins : groupeSejourClientListBySejour){
                System.out.println("Id Insc"+grpins.id+", Id Client"+grpins.idClient+",  Id Sej"+grpins.idSejour);
            }

            Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());

            gestionDocs.genereListeInscritPdf(inscriptionsListBySejour,groupeSejourClientListBySejour, sejour, centre);

        }
    }

    public void generelisteExcel(MouseEvent mouseEvent) throws IOException, DocumentException {
        if (this.idsejour.getText().isEmpty()) {
            Notification.affichageEchec("Message Echec", "Veuillez selectionner un sejour SVP  ");

        } else {

            Sejour sejour = sejourDao.getSejourParId(this.sejour.id.get());//this.idsejour.getText());

            List<Inscription> inscriptionsListBySejour = inscriptionDao.getInscriptionsParIdSejour(this.sejour.id.get());
            List<GroupeSejourClient> groupeSejourClientListBySejour = groupeSejourClientDao.getGroupeSejourClientByIdSejour(this.sejour.id.get());

            Centre centre = centreDao.getCentreParId(sejour.nom_centre.get());

            gestionDocs.genereListeInscritExcel(inscriptionsListBySejour,groupeSejourClientListBySejour, sejour, centre);


        }
    }

    public void envoieEmail(MouseEvent mouseEvent) {
        try {

               // this.idsejour.getText();
                //this.client.id.get();
                Email.idclient = "-1";
                Email.idSejour = this.sejour.id.get();//this.idsejour.getText();

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
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
           // Logger logger = Logger.getLogger(getClass().getName());
            //logger.log(Level.SEVERE, "Failed to create new Window.", e);
            Notification.affichageEchec("Message Echec", "Veuillez selectionner un  séjour et un client SVP  ");

    }
    }

    public void genereConvocation(MouseEvent mouseEvent) {
        if (!this.idclient.getText().isEmpty() && !this.idsejour.getText().isEmpty()) {
            gestionDocs.genereAttestationFacture(clientDao.getClientParId(client.id.get()), sejourDao.getSejourParId(this.sejour.id.get()));
        }else {
            Notification.affichageEchec("Message Echec", "Veuillez selectionner un client SVP  ");
        }
    }


    public void supprimer(MouseEvent mouseEvent) {
        if(!this.idsejour.getText().isEmpty()){
            int res=sejourDao.supprimerSejourParid(this.idsejour.getText());
            if(res!=0){
                Notification.affichageSucces("suppression reussi","le sejour a ete supprime avec succes");
                controlleur.consulterSejour();
            }else{
                Notification.affichageEchec("suppression non aboutit","le sejour n  a pas pu etre supprime");
            }
        }else {
            Notification.affichageEchec("Message Echec", "Veuillez selectionner un  séjour et un client SVP  ");
        }
    }

}

