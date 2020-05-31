package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaiementMarie  extends RecursiveTreeObject<PaiementMarie> {

    public StringProperty id;
    public StringProperty paiement;
    public StringProperty groupe;
    public StringProperty sejour;
    public StringProperty methode;



    public PaiementMarie(String groupe,String prix,  String sejour,String methode) {
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);
        this.paiement=new SimpleStringProperty(prix);
        this.methode=new SimpleStringProperty(methode);

    }


    public PaiementMarie(String id,String groupe,String prix,  String sejour,String methode) {
        this.id=new SimpleStringProperty(id);
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);
        this.paiement=new SimpleStringProperty(prix);
        this.methode=new SimpleStringProperty(methode);

    }

}
