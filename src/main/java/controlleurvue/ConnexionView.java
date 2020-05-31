package controlleurvue;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import principale.Controlleur;

public class ConnexionView implements Vue {
    public TextField nicknameTextField;
    public Button connexionButton;
    public Label errorLabel;

    private  Controlleur controlleur;
    public void connectKey(KeyEvent keyEvent) {
    }

    public void connect(ActionEvent actionEvent) {
    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }
}
