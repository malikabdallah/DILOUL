package notification;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class Notification {




    public static void affichageSucces(String titre,String text){
        Image image=new Image("img/mooo.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                //.hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();
    }




    public static void affichageEchec(String titre,String text){
        Image image=new Image("img/delete.png");
        Notifications notification=Notifications.create()
                .title(titre)
                .text(text)
                //.hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_LEFT)
                .graphic(new ImageView(image));
        notification.darkStyle();
        notification.show();
    }
}
