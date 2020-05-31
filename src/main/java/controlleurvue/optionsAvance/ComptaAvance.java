package controlleurvue.optionsAvance;

import com.jfoenix.controls.JFXDatePicker;
import controlleurvue.Vue;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import notification.Notification;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class ComptaAvance implements Initializable, Vue {

    public JFXDatePicker date;

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
