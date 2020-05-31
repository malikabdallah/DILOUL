package controlleurvue.groupe;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXTextField;
import controlleurvue.Vue;
import daos.AssociationGroupeSejourDao;
import daos.CentreDao;
import daos.GroupeDao;
import daos.SejourDao;
import daos.impl.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Associationgroupesejour;
import modele.Centre;
import modele.Groupe;
import modele.Sejour;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerAssociation implements Vue, Initializable {
    
    public Label centre;
    public Label type;
    public Label age;
    public Label date;
    public Label prix;
    public JFXTextField nombreplace;
    public JFXTextField prixAssoc;
    public Label nom;
    public Label code;
    private Controlleur controlleur;
    public static String id;
    private AssociationGroupeSejourDao associationGroupeSejourDao;
    private SejourDao sejourDao;
    private CentreDao centreDao;
    private GroupeDao groupeDao;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        associationGroupeSejourDao=new AssociationGroupeSejourDaoImpl(DBconnexion.getConnection());
        Associationgroupesejour associationgroupesejour=associationGroupeSejourDao.getById(id);
        sejourDao=new SejourDaoImpl(DBconnexion.getConnection());
        centreDao=new CentreDaoImpl(DBconnexion.getConnection());
        Sejour sejour=sejourDao.getSejourParId(associationgroupesejour.sejour.get());
        Centre centre=centreDao.getCentreParId(sejour.nom_centre.get());
        this.centre.setText(centre.nom_centre.get());
        this.type.setText(sejour.type.get());
        this.age.setText(sejour.ageMin.get()+" - "+sejour.ageMax.get());
        this.date.setText(sejour.date_debut.get()+" - "+sejour.date_fin.get());
        this.prix.setText(sejour.prix.get());

        GroupeDao groupeDao=new GroupeDaoImpl(DBconnexion.getConnection());
        Groupe groupe=groupeDao.getGroupeParId(associationgroupesejour.groupe.get());
        this.nom.setText(groupe.nom_groupe.get());
        this.code.setText(groupe.code_tiers.get());

        nombreplace.setPromptText(associationgroupesejour.nbPlace.get());
        prixAssoc.setPromptText(associationgroupesejour.prix_unitaire.get());

        


    }

    public void retour(MouseEvent mouseEvent) {
        //controlleur.
        controlleur.lancerPageConsulterGroupeSejour();
    }

    public void valider(MouseEvent mouseEvent) {

        if(this.nombreplace.getText()!="" && this.prixAssoc.getText()!=""){

            int res=  associationGroupeSejourDao.mettreAJourAssociation(String.valueOf(id),prixAssoc.getText(),nombreplace.getText());
            if(res!=0){
                Notification.affichageSucces("mise a jour reussite","l association  a ete mis a jour");

                controlleur.lancerPageConsulterGroupeSejour();

            }else {
                Notification.affichageEchec("mise a jour echoue"," la mise a jour n a pas eu lieu");
            }
        }
    }
}
