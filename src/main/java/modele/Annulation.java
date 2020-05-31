package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Annulation extends RecursiveTreeObject<Annulation> {


    public StringProperty id;
    public StringProperty motif;
    public StringProperty  idsejour;
    public StringProperty idclient;


    private  String triche="";
    private String triche2="";

    public Annulation(StringProperty id, StringProperty motif, StringProperty idsejour, StringProperty idclient) {
        this.id = id;
        this.motif = motif;
        this.idsejour = idsejour;
        this.idclient = idclient;
    }

    public Annulation(String s, String text, String lidclientText) {
        this.id=new SimpleStringProperty(s);
        this.motif=new SimpleStringProperty(s);
        this.idsejour=new SimpleStringProperty(text);
        this.idclient=new SimpleStringProperty(lidclientText);


    }


    public Annulation(String s, String string, String string1, String string2) {
        this.id=new SimpleStringProperty(s);
        this.motif=new SimpleStringProperty(string);
        this.idsejour=new SimpleStringProperty(string1);
        this.idclient=new SimpleStringProperty(string2);

    }

    public String getTriche() {
        return triche;
    }

    public void setTriche(String triche) {
        this.triche = triche;
    }

    public String getTriche2() {
        return triche2;
    }

    public void setTriche2(String triche2) {
        this.triche2 = triche2;
    }
}
