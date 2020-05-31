package controlleurvue.sejour;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import daos.CentreDao;
import daos.SejourDao;
import daos.impl.CentreDaoImpl;
import daos.impl.SejourDaoImpl;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import modele.Centre;
import modele.Sejour;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerSejour implements Initializable, Vue {


    public StackPane stack;
    public TextField type;
    public DatePicker dateD;
    public DatePicker dateF;
    public ComboBox centre;
    public String duree;
    public TextField capacite;
    public TextField agemax;
    public TextField agemin;
    public TextField prix;
    public TextField refsejour;
    public JFXTextField numero;


    private SejourDao sejourDao;
    private CentreDao centreDao;

    private  Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        Connection connection= DBconnexion.getConnection();
        List<Centre> centres=centreDao.listeCentres();
        for(Centre centre:centres){
            this.centre.getItems().add(centre.nom_centre.get());
        }
    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageSejour();
    }

    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {

        try{
            String centr =this.centre.getValue().toString();
            String datedebut= this.dateD.getValue().toString();
            String datefin= this.dateF.getValue().toString();

            String type=this.type.getText();
            String max=this.agemax.getText();
            String min=this.agemin.getText();
            String prix=this.prix.getText();
            String capacite=this.capacite.getText();
            String refSejour=this.refsejour.getText();

            Integer.parseInt(max);
            Integer.parseInt(min);
            Integer.parseInt(prix);
            Integer.parseInt(capacite);

            Centre centre=centreDao.trouverParNomCentre(centr);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAvant = sdf.parse(dateD.getValue().toString());
            Date dateApres = sdf.parse(dateF.getValue().toString());
            long diff = dateApres.getTime() - dateAvant.getTime();
            long nbJours = (diff / (1000*60*60*24));
            String duree = String.valueOf(nbJours);

            if (controleDate(datedebut, datefin)){
                if (Integer.parseInt(min) >= 4 && Integer.parseInt(min) <= 17){
                    if (type.length() >=5){
                        if (Integer.parseInt(capacite) <= Integer.parseInt(centre.capacite_centre.get())){
                            Sejour sejour = new Sejour(duree, datedebut, datefin, type, centre.id.get(), prix, max, min, capacite, refSejour,this.numero.getText());
                            int res = sejourDao.insererSejour(sejour);
                            if (nbJours > 0) {
                                Notification.affichageSucces("Succes", "Séjour créé avec succès");
                                this.back(mouseEvent);

                            } else {
                                Notification.affichageEchec("Echec", "echec dans la création du sejour");
                            }
                        }else{
                            Notification.affichageEchec("Echec", "Capacité du séjour doit être inferieur ou égal à celle du centre");
                        }
                    }else {
                        Notification.affichageEchec("Echec", "Le champs doit avoir au moins 5 caractères");

                    }
                } else{
                    Notification.affichageEchec("Echec", "L'âge doit être entre 4 et 17 ans");

                }
            }else {
                Notification.affichageEchec("echec","Dates incorrectes");

            }
        }catch (NullPointerException | NumberFormatException | ParseException e ){
            Notification.affichageEchec("Problème de donnees","veuillez saisir de(s) champ(s) non vides et valide(s) ");

        }


        
    }

    public boolean controleDate(String dateDebut, String dateFin){

        Boolean b = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dD=null;
            dD = sdf.parse(dateDebut);
            Date dF=null;
            dF = sdf.parse(dateFin);
            
            if(!(dD.after(new Date()))){
                Notification.affichageEchec("echec","la date du debut est incorrecte");
                b=false;
                return b;
            }else if(!(dF.after(dD))){
                Notification.affichageEchec("echec","la date de fin doit etre apres celle du debut");
                b=false;
                return b;
            }else{
                return b;
            }
        }catch(ParseException e){
            Notification.affichageEchec("echec","echec le format de la date incorrect");
        }
        return b;

    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
