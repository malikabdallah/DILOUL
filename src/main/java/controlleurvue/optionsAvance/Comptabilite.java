package controlleurvue.optionsAvance;

import com.jfoenix.controls.JFXDatePicker;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Comptabilite implements Initializable, Vue {

    public JFXDatePicker date;
    public JFXDatePicker datefin;

    private Controlleur controlleur;


        @Override
        public void initialize(URL location, ResourceBundle resources) {
          //  this.date.setValue(new LocalDate());

        }

        @Override
        public void setController(Controlleur controller) {
            this.controlleur=controller;
        }

        public void retour(MouseEvent mouseEvent) {
            controlleur.lancerOptionAvancerVue();
        }

        public void valider(MouseEvent mouseEvent) {
          Notification.affichageSucces("date",this.date.getValue().toString());
        }

        public void back(MouseEvent mouseEvent) {
        }
}
