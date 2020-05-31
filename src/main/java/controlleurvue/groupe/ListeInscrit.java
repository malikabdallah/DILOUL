package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controlleurvue.Vue;
import daos.*;
import daos.impl.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListeInscrit implements Initializable, Vue {


    public static  String id_sejour;
    public static  String id_groupe;
    public static String assoc_id;
    public  static  String reste;

    public JFXTreeTableView inscriptions;
    public Label sejour;
    public Label duree;
    public Label debut;
    public Label fin;
    public Label centre;
    public Label groupe;
    public Label resteapayer;
    private Controlleur controlleur;
    public JFXTreeTableView<Client> paiementMarieJFXTreeTableView;
    private PaiementMairieDao paiementMairieDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private GroupeDao groupeDao;
    private ClientDao clientDao;
    private GroupeSejourClientDao groupeSejourClientDao;
    private ReservationDao reservationDao;
    private  InscriptionDao inscriptionDao;


    @Override
    public void setController(Controlleur controller) {

        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        groupeSejourClientDao=new GroupeSejourClientDaoImpl(DBconnexion.getConnection());
        paiementMairieDao=new PaiementMairieDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());

        remplirData();
        chargerPaiement();

    }

    private void remplirData() {


        Sejour sejour=sejourDao.getSejourParId(id_sejour);
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        Groupe groupe=groupeDao.getGroupeParId(id_groupe);

this.resteapayer.setText(ListeInscrit.reste);
        this.duree.setText(sejour.duree.get());
        this.centre.setText(centre.nom_centre.get());
        this.debut.setText(sejour.date_debut.get());
        this.fin.setText(sejour.date_fin.get());
        this.groupe.setText(groupe.nom_groupe.get());
        this.sejour.setText(sejour.type.get());
    }


    private JFXTreeTableColumn<Client, String> client_id() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("id");
        capaciteCentre.setPrefWidth(20);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().id;
            }
        });

        return capaciteCentre;

    }

    private JFXTreeTableColumn<Client, String> client_nom() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("nom");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().nom_client;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<Client, String> client_prenom() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("prenom");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().prenom_client;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<Client, String> client_date_naissance() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("date naissance");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().datenaissance;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<Client, String> client_adresse() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("adresse");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().adresse;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<Client, String> creationCodePostale() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("code postale");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().codePostale;
            }
        });

        return capaciteCentre;

    }



    private JFXTreeTableColumn<Client, String> numero() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("numero");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().numero;
            }
        });

        return capaciteCentre;

    }


    private JFXTreeTableColumn<Client, String> creationCollumMethode() {
        JFXTreeTableColumn<Client, String> capaciteCentre = new JFXTreeTableColumn<>("email");
        capaciteCentre.setPrefWidth(110);
        capaciteCentre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
                return param.getValue().getValue().email;
            }
        });

        return capaciteCentre;

    }



    public void loadallcentre() {






    }

    private void chargerPaiement() {
        JFXTreeTableColumn<Client,String> client_id=this.client_id();
        JFXTreeTableColumn<Client,String> client_nom=this.client_nom();
        JFXTreeTableColumn<Client,String> client_prenom= this.client_prenom();
        JFXTreeTableColumn<Client,String> client_groupe=this.client_date_naissance();
        JFXTreeTableColumn<Client,String> client_datenaissance=this.client_adresse();
        JFXTreeTableColumn<Client,String> client_code=this.creationCodePostale();
        JFXTreeTableColumn<Client,String> client_numero=this.numero();

        List<GroupeSejourClient>liste=groupeSejourClientDao.getGroupeSejourClient(id_groupe,id_sejour);

        ObservableList<Client> centres = FXCollections.observableArrayList();
        for (GroupeSejourClient groupeSejourClient : liste) {
            Client client=clientDao.getClientParId(groupeSejourClient.idClient);
            centres.add(client);
        }
        final TreeItem<Client> root = new RecursiveTreeItem<Client>(centres, RecursiveTreeObject::getChildren);


        paiementMarieJFXTreeTableView.getColumns().setAll(client_id,client_nom,client_prenom,client_groupe,client_datenaissance,client_code,client_numero);
        paiementMarieJFXTreeTableView.setRoot(root);
        paiementMarieJFXTreeTableView.setShowRoot(false);

    }


    public void retourmenuclient(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }

    public void validerInscription(MouseEvent mouseEvent) {
        int res=Integer.parseInt(this.resteapayer.getText());
        if(res>0){
            Notification.affichageEchec("echec","le paiement n est pas complet pour se groupe");
        }else{
            List<GroupeSejourClient>liste=groupeSejourClientDao.getGroupeSejourClient(id_groupe,id_sejour);
            for(GroupeSejourClient groupeSejourClient:liste){


                Client client=clientDao.getClientParId(groupeSejourClient.idClient);
                Reservation reservation=reservationDao.getReservationParIdClientEtIdSejour(client.id.get(),id_sejour);
                if(reservation!=null){
                    String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                    Notification.affichageSucces("reservaation trouve ","reservation trouve");
                    Inscription inscription=new Inscription("0",aujourdhui,client.id.get(),id_sejour,groupeSejourClient.depart);

                    Inscription inscription1=inscriptionDao.getInscriptionsParIdSejourEtIdClient(id_sejour,client.id.get());
                    if(inscription1!=null){
                        Notification.affichageSucces("inscription trouve ","inscription trouve");

                    }else{
                        int ress=inscriptionDao.insererInscription(inscription);
                        if(ress!=0){
                            Notification.affichageSucces("inscription effectue ","inscription effectue");


                        }else {
                            Notification.affichageEchec("aucune inscription ", "aucune inscription");
                        }

                    }
                    reservationDao.supprimerParId(reservation.id.get());


                }else{
                  //  Notification.affichageEchec("aucune reservation ","aucune reservation");
                    String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                    Inscription inscription=new Inscription("0",aujourdhui,client.id.get(),id_sejour,groupeSejourClient.depart);
                    Inscription inscription1=inscriptionDao.getInscriptionsParIdSejourEtIdClient(id_sejour,client.id.get());
                    if(inscription1!=null){
                        Notification.affichageSucces("inscription trouve ","inscription trouve");

                    }else{
                        int ress=inscriptionDao.insererInscription(inscription);
                        if(ress!=0){
                            Notification.affichageSucces("inscription effectue ","inscription effectue");

                        }else {
                            Notification.affichageEchec("aucune inscription ", "aucune inscription");
                        }

                    }
                }



            }


        }
    }

    public void validerreservation(MouseEvent mouseEvent) {
       // Notification.affichageEchec("deja present database","en avant les reservations");

        List<GroupeSejourClient>liste=groupeSejourClientDao.getGroupeSejourClient(id_groupe,id_sejour);

        ObservableList<Client> centres = FXCollections.observableArrayList();
        String aujourdhui = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        for (GroupeSejourClient groupeSejourClient : liste) {
            //Notification.affichageEchec("test 2","test 2");

            Client client=clientDao.getClientParId(groupeSejourClient.idClient);
            GroupeSejourClient groupeSejourClient1=groupeSejourClientDao.getGroupeSejourClient(id_groupe,id_sejour,client.id.get());
            Reservation reservation=new Reservation(aujourdhui,client.id.get(),id_sejour,groupeSejourClient1.depart);

            Reservation reservation2= reservationDao.getReservationParIdClientEtIdSejour(client.id.get(),id_sejour);
            if(reservation2==null){

            //    Notification.affichageEchec("pas present database","pas present");


                reservationDao.insererReservation(reservation);
                Notification.affichageSucces("ajout reservation","reservation faite");
            }else{
               // Notification.affichageEchec("deja present database","deja present");
            }
            centres.add(client);
        }
    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }
}
