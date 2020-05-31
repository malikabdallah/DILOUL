package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Associationgroupesejour extends RecursiveTreeObject<Associationgroupesejour> {


    public StringProperty id;
    public StringProperty prix_unitaire;
    public StringProperty groupe;
    public StringProperty sejour;
    public StringProperty nbPlace;

    public Associationgroupesejour(String prix, String groupe, String sejour,String nbPlace) {
        this.prix_unitaire = new SimpleStringProperty(prix);
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);
        this.nbPlace=new SimpleStringProperty(nbPlace);

    }




    public Associationgroupesejour(String id,String prix, String groupe, String sejour,String nbPlace) {
        this.id=new SimpleStringProperty(id);
        this.prix_unitaire = new SimpleStringProperty(prix);
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);
        this.nbPlace=new SimpleStringProperty(nbPlace);

    }
}
