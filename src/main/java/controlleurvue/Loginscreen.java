package controlleurvue;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Loginscreen implements Vue, Initializable {


    public TextField login;
    public TextField password;
    public StackPane stackpane;
    public AnchorPane ancre;
    private Controlleur controlleur;


    public void loginbouton(MouseEvent mouseEvent) {


        if (login.getText().toString().equals("")) {
            Image imageView = new Image("img/delete.png");
            System.out.println("vide");
            Notifications notifications = Notifications.create()
                    .title("ERROR")
                    .text("pseudo vide")
                    .hideAfter(Duration.millis(3000))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(imageView));

            notifications.darkStyle();
            notifications.show();
        } else if (password.getText().toString().equals("")) {
            Image imageView = new Image("img/delete.png");
            System.out.println("vide");
            Notifications notifications = Notifications.create()
                    .title("ERROR")
                    .text("mot de passe vide")
                    .hideAfter(Duration.millis(3000))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(imageView));

            notifications.darkStyle();
            notifications.show();
        } else {
            boolean isExist = false;
            String userPass = "";
            String userType = "";
            String sql = "select * from user where login='" + login.getText().toString().trim() + "'";
            Connection connexion = DBconnexion.getConnection();
            try {
                PreparedStatement ps = (PreparedStatement) connexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    isExist = true;
                    userPass = rs.getString(3);
                    userType = rs.getString(4);

                }
                if (isExist) {
                    if (password.getText().toString().trim().equals(userPass)) {
                        if (userType.equals("admin")) {
                            /*Stage adminscreen = new Stage();
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("adminScreen.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage current = (Stage) login.getScene().getWindow();
                            Scene scene = new Scene(root, 950, 720);
                            adminscreen.setScene(scene);
                            current.hide();
                            adminscreen.show();

                             */
                                this.controlleur.lancerPageAccueil();

                        } else {
                           /* Stage userScreen = new Stage();
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource(
                                        "homeScreen.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage current = (Stage) login.getScene().getWindow();
                            Scene scene = new Scene(root, 950, 720);
                            userScreen.setScene(scene);
                            current.hide();
                            userScreen.show();*/

                        }
                    }
                }else{


                    Image imageView = new Image("img/delete.png");
                    System.out.println("vide");
                    Notifications notifications = Notifications.create()
                            .title("ERROR")
                            .text("le login ou/et mot de passe correspondent pas")
                            .hideAfter(Duration.millis(3000))
                            .position(Pos.BOTTOM_LEFT)
                            .graphic(new ImageView(imageView));

                    notifications.darkStyle();
                    notifications.show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void quitter(MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("ferme"));
        dialogLayout.setBody(new Text("vous voulez partir ?"));

        JFXButton ok=new JFXButton("ok");
        JFXButton cancel=new JFXButton("annule");

        final JFXDialog dialog=new JFXDialog(stackpane,dialogLayout, JFXDialog.DialogTransition.CENTER);

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
}
