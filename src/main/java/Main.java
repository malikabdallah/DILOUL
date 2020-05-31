

import javafx.application.Application;
import javafx.stage.Stage;
import principale.Controlleur;


public class Main extends Application{

    public static void main(String... args){
        // Méthode issus d'application qui permet de lancer l'application.
        launch(args);
       //  test
    }


    @Override
    public void start(Stage stage) throws Exception {
        new Controlleur(stage);
    }
}
