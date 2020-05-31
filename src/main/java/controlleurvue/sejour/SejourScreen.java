package controlleurvue.sejour;

import controlleurvue.Vue;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import principale.Controlleur;

public class SejourScreen implements Vue {
    public StackPane stack;
    public Pane pane_1;
    public Pane pane_2;
    public Pane pane_3;


    private Controlleur controlleur;

    public void souris_dessus_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");
    }

    public void souris_sort_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");
    }

    public void souris_sort_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void creerSejour(MouseEvent mouseEvent) {
        this.controlleur.creerSejour();

    }

    public void consultersejour(MouseEvent mouseEvent) {
        this.controlleur.consulterSejour();
    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void goback(MouseEvent mouseEvent) {
        this.controlleur.lancerPageAccueil();

    }

}
