package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import modele.*;
import notification.Notification;
import principale.Controlleur;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class HistoriqueClient implements Initializable, Vue {


    public JFXTreeTableView<Annulation> annulations;
    public JFXTreeTableView <Reservation>reservations;
    public JFXTreeTableView <Inscription>inscriptions;
    public JFXRadioButton btnannulation;
    public JFXRadioButton btnreservation;
    public JFXRadioButton btninscription;
    private Controlleur controlleur;
    public  static  int id;

    public Label lnom;
    public Label lprenom;
    public Label ldatenaissance;
    public Label lgroupe;
    public Label lnumero;
    public Label lobservation;
    public Label lemail;
    public Label ladresse;
    public Label lcodepostale;
    private ClientDao clientDao;

    private ReservationDao reservationDao;
    private InscriptionDao inscriptionDao;
    private AnnulationDao annulationDao;
    private SejourDao sejourDao;



    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        annulationDao=new AnnulationDaoImpl(DBconnexion.getConnection());
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());

        Client client=clientDao.getClientParId(String.valueOf(id));
        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ladresse.setText(client.adresse.get());
        this.lcodepostale.setText(client.codePostale.get());
        this.ldatenaissance.setText(client.datenaissance.get());
        this.lnumero.setText(client.numero.get());
        this.lgroupe.setText(client.groupe.get());
        this.lobservation.setText(client.observation.get());
        this.lemail.setText(client.email.get());


        this.btninscription.setSelected(true);
        this.btnannulation.setSelected(false);
        this.btnreservation.setSelected(false);

        btninscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnannulation.setSelected(false);
                btnreservation.setSelected(false);
                reservations.setVisible(false);
                inscriptions.setVisible(true);
                annulations.setVisible(false);
            }
        });



        btnreservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnannulation.setSelected(false);
                btninscription.setSelected(false);
                reservations.setVisible(true);
                inscriptions.setVisible(false);
                annulations.setVisible(false);
            }
        });


        btnannulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btninscription.setSelected(false);
                btnreservation.setSelected(false);
                inscriptions.setVisible(false);
                annulations.setVisible(true);
                reservations.setVisible(false);
            }
        });




        remplirInscription();
        remplirReservation();

        remplirAnnulation();

    }

    private void remplirAnnulation() {

        JFXTreeTableColumn<Annulation,String> inscription_id=this.genererIdAnnulation();
        JFXTreeTableColumn<Annulation,String> inscription_dateinscription=this.genererMotifAnnulation();
        JFXTreeTableColumn<Annulation,String> inscription_client=this.genererIdSejour();
        JFXTreeTableColumn<Annulation,String> inscription_sejour=this.genererIdClient();
        ObservableList<Annulation> inscriptions = FXCollections.observableArrayList();
        List<Annulation> reservations=annulationDao.getAnnulationsParId(HistoriqueClient.id);
        for(Annulation annulation: reservations){


            Client client=clientDao.getClientParId(annulation.idclient.get());
            Sejour sejour=sejourDao.getSejourParId(annulation.idsejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();

            Sejour sejour1=sejourDao.getSejourParId(id_sejour);


            Annulation annulation1=new Annulation(annulation.id.get(),annulation.motif.get(),nom_client,sejour1.type.get());
            inscriptions.add(annulation1);
        }
        final TreeItem<Annulation> root = new RecursiveTreeItem<Annulation>(inscriptions, RecursiveTreeObject::getChildren);
        this.annulations.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour);
        this.annulations.setRoot(root);
        this.annulations.setShowRoot(false);

    }


    public JFXTreeTableColumn<Annulation,String>genererIdAnnulation(){

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


    public JFXTreeTableColumn<Annulation,String>genererMotifAnnulation(){

        JFXTreeTableColumn<Annulation,String> inscription_id=new JFXTreeTableColumn<>(" motif");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().motif;
            }
        });
        return inscription_id;
    }



    public JFXTreeTableColumn<Annulation,String>genererIdSejour (){

        JFXTreeTableColumn<Annulation,String> inscription_id=new JFXTreeTableColumn<>(" sejour");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().idclient;
            }
        });
        return inscription_id;
    }


    public JFXTreeTableColumn<Annulation,String>genererIdClient(){

        JFXTreeTableColumn<Annulation,String> inscription_id=new JFXTreeTableColumn<>(" client");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Annulation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Annulation, String> param) {
                return param.getValue().getValue().idclient;
            }
        });
        return inscription_id;
    }



    public JFXTreeTableColumn<Reservation,String>genererInscriptionIdR(){

        JFXTreeTableColumn<Reservation,String> inscription_id=new JFXTreeTableColumn<>(" Id");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return inscription_id;
    }




    public JFXTreeTableColumn<Reservation,String> genererDepartR(){
        JFXTreeTableColumn<Reservation,String> inscription_paiement=new JFXTreeTableColumn<>("depart");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_paiement;


    }





    public JFXTreeTableColumn<Reservation,String> genererDataInscriptioninscriptionR(){
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=new JFXTreeTableColumn<>("date inscription");
        inscription_dateinscription.setPrefWidth(110);
        inscription_dateinscription.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().dateinscription;
            }
        });
        return  inscription_dateinscription;
    }


    public JFXTreeTableColumn<Reservation,String> genererInscriptionClientR(){
        JFXTreeTableColumn<Reservation,String> inscription_client=new JFXTreeTableColumn<>(" Client");
        inscription_client.setPrefWidth(110);
        inscription_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().code_client;
            }
        });
        return inscription_client;
    }

    public  JFXTreeTableColumn<Reservation,String> genererInscriptionSejourR() {

        JFXTreeTableColumn<Reservation, String> inscription_sejour = new JFXTreeTableColumn<>(" Sejour");
        inscription_sejour.setPrefWidth(110);
        inscription_sejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Reservation, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });
        return inscription_sejour;
    }

    private void remplirReservation() {

        JFXTreeTableColumn<Reservation,String> inscription_id=this.genererInscriptionIdR();
        JFXTreeTableColumn<Reservation,String> inscription_dateinscription=this.genererDataInscriptioninscriptionR();
        JFXTreeTableColumn<Reservation,String> inscription_client=this.genererInscriptionClientR();
        JFXTreeTableColumn<Reservation,String> inscription_sejour=this.genererInscriptionSejourR();
        JFXTreeTableColumn<Reservation,String> inscription_depart=this.genererDepartR();
        ObservableList<Reservation> inscriptions = FXCollections.observableArrayList();
        List<Reservation> reservations=reservationDao.getReservationsParIdClient(HistoriqueClient.id);
        for(Reservation reservation: reservations){


            Client client=clientDao.getClientParId(reservation.code_client.get());
            Sejour sejour=sejourDao.getSejourParId(reservation.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();

            Sejour sejour1=sejourDao.getSejourParId(id_sejour);
            Reservation reservation1=new Reservation(reservation.id.get(),reservation.dateinscription.get(),
                    nom_client,sejour1.type.get(),reservation.depart.get());


            reservation1.setTriche(sejour1.id.get());
            reservation1.setTriche2(client.id.get());
            inscriptions.add(reservation1);
        }
        final TreeItem<Reservation> root = new RecursiveTreeItem<Reservation>(inscriptions, RecursiveTreeObject::getChildren);
        this.reservations.getColumns().setAll(inscription_id,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        this.reservations.setRoot(root);
        this.reservations.setShowRoot(false);




        this.inscriptions.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
        System.out.println(("clicker")));
    }

    private void remplirInscription() {

        JFXTreeTableColumn<Inscription,String> inscription_id=this.genererInscriptionId();
        JFXTreeTableColumn<Inscription,String> inscription_paiement=this.genererInscriptionPaiement();
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=this.genererDataInscriptioninscription();
        JFXTreeTableColumn<Inscription,String> inscription_client=this.genererInscriptionClient();
        JFXTreeTableColumn<Inscription,String> inscription_sejour=this.genererInscriptionSejour();
        JFXTreeTableColumn<Inscription,String> inscription_depart=this.genererDepart();
        ObservableList<Inscription> inscriptions = FXCollections.observableArrayList();
        List<Inscription> inscription=inscriptionDao.getInscriptiosnParIdClient(HistoriqueClient.id);
        for(Inscription inscription1: inscription){


            Client client=clientDao.getClientParId(inscription1.code_client.get());
            Sejour sejour=sejourDao.getSejourParId(inscription1.id_sejour.get());
            String nom_client=client.nom_client.get()+" "+client.prenom_client.get();
            String id_sejour=sejour.id.get();

            Sejour sejour1=sejourDao.getSejourParId(id_sejour);
            Inscription inscription2=new Inscription(inscription1.id.get(),inscription1.paiement.get(),
                    inscription1.dateinscription.get(),  nom_client,sejour1.type.get(),  inscription1.depart.get()
            );

            inscription2.setTriche(sejour1.id.get());
            inscription2.setTriche2(client.id.get());
            inscriptions.add(inscription2);
        }
        final TreeItem<Inscription> root = new RecursiveTreeItem<Inscription>(inscriptions, RecursiveTreeObject::getChildren);
        this.inscriptions.getColumns().setAll(inscription_id,inscription_paiement,inscription_dateinscription,inscription_client,inscription_sejour,inscription_depart);
        this.inscriptions.setRoot(root);
        this.inscriptions.setShowRoot(false);




    }






    public JFXTreeTableColumn<Inscription,String> genererInscriptionId(){

        JFXTreeTableColumn<Inscription,String> inscription_id=new JFXTreeTableColumn<>(" Id");
        inscription_id.setPrefWidth(100);
        inscription_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id;
            }
        });
        return inscription_id;
    }

    public JFXTreeTableColumn<Inscription,String> genererInscriptionPaiement(){
        JFXTreeTableColumn<Inscription,String> inscription_paiement=new JFXTreeTableColumn<>("paiement");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().paiement;
            }
        });
        return inscription_paiement;


    }


    public JFXTreeTableColumn<Inscription,String> genererDepart(){
        JFXTreeTableColumn<Inscription,String> inscription_paiement=new JFXTreeTableColumn<>("depart");
        inscription_paiement.setPrefWidth(100);
        inscription_paiement.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().depart;
            }
        });
        return inscription_paiement;


    }





    public JFXTreeTableColumn<Inscription,String> genererDataInscriptioninscription(){
        JFXTreeTableColumn<Inscription,String> inscription_dateinscription=new JFXTreeTableColumn<>("date inscription");
        inscription_dateinscription.setPrefWidth(110);
        inscription_dateinscription.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().dateinscription;
            }
        });
        return  inscription_dateinscription;
    }


    public JFXTreeTableColumn<Inscription,String> genererInscriptionClient(){
        JFXTreeTableColumn<Inscription,String> inscription_client=new JFXTreeTableColumn<>(" Client");
        inscription_client.setPrefWidth(110);
        inscription_client.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().code_client;
            }
        });
        return inscription_client;
    }

    public  JFXTreeTableColumn<Inscription,String> genererInscriptionSejour(){

        JFXTreeTableColumn<Inscription,String> inscription_sejour=new JFXTreeTableColumn<>(" Sejour");
        inscription_sejour.setPrefWidth(110);
        inscription_sejour.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inscription, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inscription, String> param) {
                return param.getValue().getValue().id_sejour;
            }
        });
        return  inscription_sejour;
    }



    public void retourmenuclient(MouseEvent mouseEvent) {
        controlleur.consulterClient();
    }

    public void visualiserinscription(MouseEvent mouseEvent) {
    }

    public void visualiserReservation(MouseEvent mouseEvent) {
    }

    public void visualiserannulation(MouseEvent mouseEvent) {
    }

    public void genererDocument(MouseEvent mouseEvent) {
        if(!this.inscriptions.getSelectionModel().isEmpty()){
            test();
        }else{
            Notification.affichageEchec("echec","il faut selectionner une inscription");
        }
    }





    private static String USER_NAME = "malikabdallah75019";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "Selamwait04"; // GMail password
    private static String RECIPIENT = "malik.mahamoud-abdallah@etu.univ-orleans.fr";

    public  void test() {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        sendFromGMail(from, pass, to, subject, body);
    }

    private  void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {

            // Create a default MimeMessage object.

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(HistoriqueClient.RECIPIENT));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/home/manisha/file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

        /*    message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);



            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();*/
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }


    public void back(MouseEvent mouseEvent) {
        controlleur.consulterClient();
    }
}
