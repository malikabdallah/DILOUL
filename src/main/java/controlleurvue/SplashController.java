
package controlleurvue;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author MOHAMMED
 */
public class SplashController implements Vue, Initializable {

    public Controlleur controlleur;

    @FXML
    private AnchorPane splashAnchorPane;
   
         Main m;
        Stage stage;
        
      public  void Main(Main m, Stage stage){
        this.stage=stage;
        this.m=m;
        
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       new splash().start();
    }

    @Override
    public void setController(Controlleur controller) {
          this.controlleur=controller;

    }

    class splash extends Thread{

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(100);
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(3), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();
                        
                        fadeout.setOnFinished(e ->{
                            controlleur.lancerLogin();

                        });
                       
                    } 
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    
    }
    
}
