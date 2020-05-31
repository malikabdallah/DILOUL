package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import daos.ClientDao;
import daos.GroupeDao;
import daos.impl.ClientDaoImpl;
import daos.impl.GroupeDaoImpl;
import enumerations.Depart;
import enumerations.Sexe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import modele.Client;
import modele.Groupe;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreerClient  implements Initializable, Vue {




    public StackPane stack;
    public TextField nom;
    public ComboBox sexe;

    private String id_Groupe;
    public TextField prenom;
    public TextField portable;
    public TextField observation;
    public TextField email;
    public TextField poste;
    public DatePicker annee;
    public TextField adresse;
    public Label groupelabel;
    public JFXTreeTableView<Groupe> tablegroupe;
    public JFXTextField chercher;

    private Controlleur controlleur;

    private GroupeDao groupeDao;
    private ClientDao clientDao;


    @Override
    public void setController(Controlleur controller) {
        groupeDao =new GroupeDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;
        List<Groupe> liste=groupeDao.listeGroupes();

    }
    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageClient();
    }
    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {
        try {

        //    Groupe groupe=groupeDao.trouverGroupeParNomGroupe(this.groupe.getValue().toString().trim());

            String prenom;
            String nom;
          //  String id_group=groupe.id.get();
            String portable;
            String observation;
            String email;
            String adresse;
            String poste;
            String dateNaissance;
            // groupeL=this.groupelabel.getText();

            if (!this.nom.getText().isEmpty() && !this.prenom.getText().isEmpty() && !this.portable.getText().isEmpty() && !this.adresse.getText().isEmpty() && !this.poste.getText().isEmpty() && !this.annee.getValue().toString().isEmpty()) {

                prenom=this.prenom.getText();
                nom=this.nom.getText();
                portable=this.portable.getText();
                observation=this.observation.getText();
                email=this.email.getText();
                adresse=this.adresse.getText();
                poste=this.poste.getText();
                dateNaissance=this.annee.getValue().toString();

                if ( !id_Groupe.isEmpty()) {
                        if (isEmailAdress(email)){
                            if (isCodePostale(poste)){
                                Client cl = testClientExiste(nom , prenom, dateNaissance, id_Groupe);
                                if ( cl== null) {
                                    Client client = new Client("", nom, prenom, id_Groupe, portable, observation, email, adresse, poste, dateNaissance,this.sexe.getValue().toString());

                                    int res = this.clientDao.insererClient(client);

                                    if (res > 0) {
                                        Notification.affichageSucces("succes", "client creer avec succes");
                                        this.controlleur.creerInscription();
                                    } else {
                                        Notification.affichageEchec("erreur", "il y a eu une erreur au moment de la creation");
                                    }
                                }else {
                                    Notification.affichageEchec("erreur", "le client existe déjà avec ce groupe ");
                                }
                            }else {
                                Notification.affichageEchec("erreur","le code poste est incorrect ");
                            }
                        }
                        else {
                            Notification.affichageEchec("erreur","l'Email est incorrect ");
                        }
                }else {
                    Notification.affichageEchec("erreur", "Veuillez selectionner un groupe");

                }
            }else {
                Notification.affichageEchec("Problème de donnees", "veuillez saisir de(s) champ(s) non vide(s)");
            }
        }catch (NullPointerException | NumberFormatException e){
            e.printStackTrace();
            Notification.affichageEchec("Données manques ","veuillez saisir de(s) champ(s) non vide(s)et valide(s) ");
        }

    }



    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m;
        m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    public static boolean isCodePostale(String codePostale){
        Pattern p = Pattern.compile("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$");
        Matcher m;
        m = p.matcher(codePostale.toUpperCase());
        return m.matches();
    }

    public Client testClientExiste(String nom , String prenom, String dN, String idGrp){
        return clientDao.getClient( nom , prenom, dN, idGrp);
    }


    private JFXTreeTableColumn<Groupe,String> creernomgroupe(){
        JFXTreeTableColumn<Groupe,String> groupe_nom=new JFXTreeTableColumn<>("nom du groupe");
        groupe_nom.setPrefWidth(110);
        groupe_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().nom_groupe;
            }
        });

        return groupe_nom;
    }


    private JFXTreeTableColumn<Groupe,String> creerGroupeId(){
        JFXTreeTableColumn<Groupe,String> groupe_nom=new JFXTreeTableColumn<>("id");
        groupe_nom.setPrefWidth(110);
        groupe_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return groupe_nom;
    }



    private JFXTreeTableColumn<Groupe,String> creerGroupeCodeTiers(){
        JFXTreeTableColumn<Groupe,String> groupe_nom=new JFXTreeTableColumn<>("code tiers");
        groupe_nom.setPrefWidth(110);
        groupe_nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Groupe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Groupe, String> param) {
                return param.getValue().getValue().code_tiers;
            }
        });

        return groupe_nom;
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        for(Sexe sexe:Sexe.values()){
            this.sexe.getItems().add(sexe);
        }
        remplirGroupe();

    }

    private void remplirGroupe() {

        JFXTreeTableColumn<Groupe,String> groupe_nom=this.creernomgroupe();
        JFXTreeTableColumn<Groupe,String> groupe_id=this.creerGroupeId();
        JFXTreeTableColumn<Groupe,String> groupe_codetiers=this.creerGroupeCodeTiers();

        ObservableList<Groupe> groupes = FXCollections.observableArrayList();
        List<Groupe> liste=groupeDao.listeGroupes();
        for(Groupe groupe:liste){
            groupes.add(groupe);
        }
        final TreeItem<Groupe> root = new RecursiveTreeItem<Groupe>(groupes, RecursiveTreeObject::getChildren);
        this.tablegroupe.getColumns().setAll(groupe_id,groupe_nom,groupe_codetiers);
        tablegroupe.setRoot(root);
        tablegroupe.setShowRoot(false);

        optimiserRecherClient();
        tablegroupe.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue));


    }



    private void showDetailsClient(TreeItem<Groupe> newValue) {
        if(newValue!=null) {
            this.groupelabel.setText(newValue.getValue().nom_groupe.get());
            this.id_Groupe=newValue.getValue().id.get();
        }
    }
    private void optimiserRecherClient() {
        this.chercher.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tablegroupe.setPredicate(new Predicate<TreeItem<Groupe>>() {

                    @Override
                    public boolean test(TreeItem<Groupe> t) {
                        boolean flag=t.getValue().nom_groupe.getValue().toLowerCase().contains(newValue.toLowerCase())
                                || t.getValue().code_tiers.getValue().toLowerCase().contains(newValue.toLowerCase())
                              ;


                        return flag ;


                    }
                });
            }

        });
    }



}
