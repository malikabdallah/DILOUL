package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
import daos.impl.Dao;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import modele.Centre;
import notification.Notification;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerCentre implements Vue {


    public StackPane stackepane;
    public JFXTextField nom;
    public JFXTextField capacite;
    private Controlleur controlleur;
    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageCentre();
    }
    public void close(MouseEvent mouseEvent) {
    }
    public void book(MouseEvent mouseEvent) {
        int res=0;
        try {
                String nomC = nom.getText();
                String capaciteC = capacite.getText();
                Integer.parseInt(capaciteC);

            if (!testCentreExiste(nomC)) {
                if (nomC.length() >= 3) {
                    res = centreDaoImpl.inserrerCentre(nomC, capaciteC);
                    if (res > 0) {
                        Notification.affichageSucces("succes", "centre creer avec succes");
                        back(mouseEvent);
                    } else {
                        Notification.affichageEchec("echec", "echec dans la creation du centre");
                    }
                }else {
                    Notification.affichageEchec("Problème de donnees", "Le nom du centre incorrect");
                }

            }else {
                Notification.affichageEchec("echec", "echec dans la creation, le centre existe deja");
            }
        }catch (NullPointerException | NumberFormatException e){
            Notification.affichageEchec("Problème de donnees","veuillez saisir de(s) champ(s) non vide(s) et valide(s) ");
        }
    }

    public boolean testCentreExiste(String nomC){
        if(centreDaoImpl.trouverParNomCentre(nomC) != null){
            return true;
        }
        return false;
    }
    private CentreDao centreDaoImpl;
    public void setController(Controlleur controller) {
        this.centreDaoImpl=new CentreDaoImpl(DBconnexion.getConnection());
        this.controlleur=controller;

    }
}
