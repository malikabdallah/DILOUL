package controlleurvue.client;

import controlleurvue.Vue;
import daos.ClientDao;
import daos.GroupeDao;
import daos.impl.ClientDaoImpl;
import daos.impl.GroupeDaoImpl;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modele.Client;
import modele.Groupe;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerClient implements Initializable, Vue {


    public static String id;

    public Label nom;
    public Label prenom;
    public Label groupe;
    public Label date;
    private Controlleur controlleur;

    public TextField observation;
    public TextField portable;
    public TextField email;
    public TextField poste;
    public TextField adresse;
    public ComboBox depart;
    public Label lnom;
    public Label lprenom;
    public Label ldate;

    public void back(MouseEvent mouseEvent) {
        this.controlleur.consulterClient();
    }

    public void creer(MouseEvent mouseEvent) {
    }

    private ClientDao clientDao;
    private GroupeDao groupeDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientDao =new ClientDaoImpl(basededonnee.DBconnexion.getConnection());
        Client client=clientDao.getClientParId(id);
        groupeDao=new GroupeDaoImpl(basededonnee.DBconnexion.getConnection());
        this.email.setPromptText(client.email.get());
        this.adresse.setPromptText(client.adresse.get());
        Groupe groupe=groupeDao.getGroupeParId(client.groupe.get());
        this.groupe.setText(groupe.nom_groupe.get()+" "+groupe.code_tiers.get());
        this.nom.setText(client.nom_client.get());
        this.prenom.setText(client.prenom_client.get());
        this.date.setText(client.datenaissance.get());
        this.observation.setPromptText(client.observation.get());
        this.poste.setPromptText(client.codePostale.get());
        this.portable.setPromptText(client.numero.get());

    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }


    public void editer(MouseEvent mouseEvent) {
        if(this.observation.getText()!="" && this.portable.getText()!="" && this.poste.getText()!=""
        && this.email.getText()!="" && this.adresse.getText()!="" ){

            Client client=new Client();

           client.setAdresse(this.adresse.getText());
           client.setCodePostale(this.poste.getText());
           client.setEmail(this.email.getText());
           client.setObservation(this.observation.getText());
           client.setNumero(this.portable.getText());

           int res=clientDao.mettreAjourClient(id,client);
            if(res!=0){
                Notification.affichageSucces("mise a jour reussite","le client  a ete mis a jour");

                controlleur.consulterClient();

            }else {
                Notification.affichageEchec("mise a jour echoue"," la mise a jour n a pas eu lieu");
            }
        }
    }
}
