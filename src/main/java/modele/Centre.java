package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Centre extends RecursiveTreeObject<Centre> {



    public StringProperty id;
    public StringProperty nom_centre;
    public StringProperty capacite_centre;

    public Centre(){
        super();
    }

    public Centre(String id, String name,String capacite) {
        this.id = new SimpleStringProperty(id);
        this.nom_centre = new SimpleStringProperty(name);
        this.capacite_centre=new SimpleStringProperty(capacite);

    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNom_centre() {
        return nom_centre.get();
    }

    public StringProperty nom_centreProperty() {
        return nom_centre;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = new SimpleStringProperty(nom_centre);
    }

    public String getCapacite_centre() {
        return capacite_centre.get();
    }

    public StringProperty capacite_centreProperty() {
        return capacite_centre;
    }

    public void setCapacite_centre(String capacite_centre) {
        this.capacite_centre = new SimpleStringProperty(capacite_centre);
    }
}
