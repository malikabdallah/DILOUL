package controlleurvue;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import daos.ClientDao;
import daos.InscriptionDao;
import daos.SejourDao;
import daos.impl.ClientDaoImpl;
import daos.impl.InscriptionDaoImpl;
import daos.impl.SejourDaoImpl;
import facade.facadeEmail;
import facade.impl.FacadeEmailImpl;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Client;
import modele.Inscription;
import notification.Notification;
import principale.Controlleur;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Email implements Initializable ,Vue{
    public Label emeteur;
    public Label recepteur;
   public static  String idclient;
   public static String idSejour;




    public JFXTextField sujet;
    public JFXTextArea message;
    public StackPane pane;
    public Button joindre;
    public Label doc;
    private Controlleur controlleur;
    private facadeEmail facadeEmail;
    private File file;
    private SejourDao sejourDao;
    private InscriptionDao inscriptionDao;
    private ClientDao clientDao;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sejourDao = new SejourDaoImpl(DBconnexion.getConnection());
        inscriptionDao = new InscriptionDaoImpl(DBconnexion.getConnection());
        clientDao = new ClientDaoImpl(DBconnexion.getConnection());

        if(!idclient.equals("-1")) {

            Client client = clientDao.getClientParId(idclient);

            System.out.println("id client "+idclient);
            this.recepteur.setText(client.email.get());


        }else{
            List<Inscription> inscription=inscriptionDao.getInscriptionsParIdSejour(idSejour);
            Client client=clientDao.getClientParId(inscription.get(0).code_client.get());
            this.recepteur.setText(client.email.get()+",...");


        }



        this.emeteur.setText("oultest6@gmail.com");
        facadeEmail = new FacadeEmailImpl();
        FileChooser fileChooser = new FileChooser();

        this.joindre.setOnAction(e -> {
             file = fileChooser.showOpenDialog(pane.getScene().getWindow());
            System.out.println("chemin "+file.getPath());
            Notification.affichageSucces("file choisit ","file choisit");
            this.doc.setText(file.getName());
        });


    }

    public void envoieEmail(MouseEvent mouseEvent) {
        if(!idclient.equals("-1")) {
            String[] tab = new String[1];
            tab[0] = this.recepteur.getText();
            System.out.println("recepteur email " + tab[0]);
            this.facadeEmail.sendFromGMail("oultest6", "25mars1993", tab, sujet.getText(), message.getText(), file);




        }else{
            List<Inscription> inscription=inscriptionDao.getInscriptionsParIdSejour(idSejour);
            String tab[]=new String[inscription.size()];
            int cpt=0;
            for(Inscription inscription1:inscription){
                Client client=clientDao.getClientParId(inscription1.code_client.get());
                tab[cpt]=client.email.get();
                cpt++;
            }


            System.out.println("tab "+tab);
            this.facadeEmail.sendFromGMail("oultest6", "25mars1993", tab, sujet.getText(), message.getText(), file);





        }

    }
}
