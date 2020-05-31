package controlleurvue;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principale.Controlleur;

public class AccueilScreen implements Vue {
    public Pane pane_51;
    public Pane pane_31;
    private Controlleur controlleur;
    public Pane pane_5;
    public Pane pane_4;
    public Pane pane_1;
    public Pane pane_2;
    public Pane pane_3;
    public StackPane stack;


    public void souris_dessus_7(MouseEvent mouseEvent) {
        pane_31.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }


    public void souris_sort_7(MouseEvent mouseEvent) {
        pane_31.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }




    public void souris_dessus_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");
    }

    public void souris_sort_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_2(MouseEvent mouseEvent) {

        pane_2.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_4(MouseEvent mouseEvent) {
        pane_4.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_4(MouseEvent mouseEvent) {
        pane_4.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_5(MouseEvent mouseEvent) {
        pane_5.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_5(MouseEvent mouseEvent) {
        pane_5.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }




    public void clientecran(MouseEvent mouseEvent) {
        Stage home=new Stage();
        Parent root=null;
        try{
            root= FXMLLoader.load(getClass().getResource("client.fxml"));

        }catch (Exception e){

        }


        Stage current=(Stage)pane_1.getScene().getWindow();
        Scene scene=new Scene(root);

        home.setScene(scene);

        current.hide();
        home.initStyle(StageStyle.TRANSPARENT);
        home.show();
    }

    public void deconnexion(MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez vous deconnecte ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stack,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {


                Stage home=new Stage();
                Parent root=null;
                try{
                    root= FXMLLoader.load(getClass().getResource("loginscreen.fxml"));

                }catch (Exception e){

                }


                Stage current=(Stage)pane_1.getScene().getWindow();
                Scene scene=new Scene(root);

                home.setScene(scene);

                home.initStyle(StageStyle.TRANSPARENT);
                current.hide();
                home.show();

            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();
    }

    public void exit(MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez partir ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stack,dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok,cancel);
        dialog.show();
    }

    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    public void centreecran(MouseEvent mouseEvent) {
/*

        Stage home=new Stage();
        Parent root=null;
        try{
            root= FXMLLoader.load(getClass().getResource("reservation.fxml"));

        }catch (Exception e){

        }


        Stage current=(Stage)pane_1.getScene().getWindow();
        Scene scene=new Scene(root);

        home.setScene(scene);

        current.hide();
        home.initStyle(StageStyle.TRANSPARENT);
        home.show();*/
        controlleur.lancerPageCentre();
    }

    public void gestiongroupe(MouseEvent mouseEvent) {

        controlleur.lancerPageGroupe();
    }

    public void gestionSejour(MouseEvent mouseEvent) {
        controlleur.lancerPageSejour();
    }

    public void gestionClient(MouseEvent mouseEvent) {
        this.controlleur.lancerPageClient();
    }

    public void gestionReservation(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void deconnexionAppli(MouseEvent mouseEvent) {
        this.controlleur.lancerLogin();
    }

    public void souris_dessus_6(MouseEvent mouseEvent) {
        pane_51.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_6(MouseEvent mouseEvent) {
        pane_51.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");


    }




    public void souris_dessus_31(MouseEvent mouseEvent) {
        pane_31.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_31(MouseEvent mouseEvent) {
        pane_31.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void optionavancee(MouseEvent mouseEvent) {
        controlleur.lancerOptionAvancerVue();
    }


}
