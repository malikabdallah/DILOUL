package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Groupe  extends RecursiveTreeObject<Groupe> {



    public StringProperty id;
    public StringProperty nom_groupe;
    public StringProperty code_tiers;
    public StringProperty commune;

    public Groupe(){
        super();
    }

/*
    public Groupe(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.nom_groupe = new SimpleStringProperty(name);


    }

*/



    public Groupe(String id, String name,String codeTiers,String commune) {
        this.id = new SimpleStringProperty(id);
        this.nom_groupe = new SimpleStringProperty(name);
        this.code_tiers=new SimpleStringProperty(codeTiers);
        this.commune=new SimpleStringProperty(commune);


    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id=new SimpleStringProperty(id);
    }

    public String getNom_groupe() {
        return nom_groupe.get();
    }

    public StringProperty nom_groupeProperty() {
        return nom_groupe;
    }

    public void setNom_groupe(String nom_groupe) {
        this.nom_groupe=new SimpleStringProperty(nom_groupe);
    }

    public String getCode_tiers() {
        return code_tiers.get();
    }

    public StringProperty code_tiersProperty() {
        return code_tiers;
    }

    public void setCode_tiers(String code_tiers) {
        this.code_tiers=new SimpleStringProperty(code_tiers);
    }

    public void setCommune(String text) {
        this.commune=new SimpleStringProperty(text);
    }
}
