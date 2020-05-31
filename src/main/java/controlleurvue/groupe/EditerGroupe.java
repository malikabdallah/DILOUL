package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import controlleurvue.Vue;
import daos.GroupeDao;
import daos.impl.GroupeDaoImpl;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modele.Centre;
import modele.Groupe;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerGroupe  implements Vue, Initializable {

    public static String id;
    public JFXTextField nomgroupe;
    public JFXTextField codetiers;
    public JFXTextField commune;
    private Controlleur controlleur;

    private GroupeDao groupeDao;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        Groupe groupe=groupeDao.getGroupeParId(id);
        this.nomgroupe.setPromptText(groupe.nom_groupe.get());
        this.codetiers.setPromptText(groupe.code_tiers.get());


    }

    public void retour(MouseEvent mouseEvent) {
        controlleur.consulterGroupe();
    }

    public void valider(MouseEvent mouseEvent) {
        if(this.nomgroupe.getText()!="" && this.codetiers.getText()!="" && this.commune.getText()!=""){
            Groupe groupe=new Groupe();
            groupe.setCode_tiers(this.codetiers.getText());
            groupe.setNom_groupe(this.nomgroupe.getText());
            groupe.setCommune(this.commune.getText());


            int res=  groupeDao.mettreAjourGroupe(String.valueOf(id),groupe);
            if(res!=0){
                Notification.affichageSucces("mise a jour reussite","le groupe  a ete mis a jour");

                controlleur.consulterGroupe();

            }else {
                Notification.affichageEchec("mise a jour echoue"," la mise a jour n a pas eu lieu");
            }
        }
    }
}
