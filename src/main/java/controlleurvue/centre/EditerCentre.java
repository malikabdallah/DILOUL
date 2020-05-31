package controlleurvue.centre;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import controlleurvue.Vue;
import daos.CentreDao;
import daos.impl.CentreDaoImpl;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import modele.Centre;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerCentre implements Vue, Initializable {
    public static int id ;

    private CentreDao centreDao;
    private Controlleur controlleur;
    public JFXTextField nomcentre;
    public JFXTextField capacite;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());

        Centre centre=centreDao.getCentreParId(String.valueOf(id));
        nomcentre.setPromptText(centre.nom_centre.get());
        capacite.setPromptText(centre.capacite_centre.get());
    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void retour(MouseEvent mouseEvent) {
        controlleur.consulterCentre();
    }

    public void valider(MouseEvent mouseEvent) {
        if(this.nomcentre.getText()!="" && this.capacite.getText()!=""){
            Centre centre=new Centre();
            centre.setCapacite_centre(capacite.getText());
            centre.setNom_centre(nomcentre.getText());
          int res=  centreDao.mettreAjourCentre(String.valueOf(id),centre);
            if(res!=0){
                Notification.affichageSucces("mise a jour reussite","le centre a ete mis a jour");

                controlleur.consulterCentre();

            }else {
                Notification.affichageEchec("mise a jour echoue"," la mise a jour n a pas eu lieu");
            }
        }
    }

    public void back(MouseEvent mouseEvent) {
    }
}
