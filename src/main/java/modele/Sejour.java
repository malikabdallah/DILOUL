package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sejour extends RecursiveTreeObject<Sejour> {

    public StringProperty id;
    public StringProperty duree;
    public  StringProperty date_debut;
    public StringProperty  date_fin;
    public StringProperty type;

    public StringProperty nom_centre;
    public StringProperty prix;
    public StringProperty ageMin;
    public StringProperty ageMax;
    public StringProperty capacite;

    public StringProperty refSejour;
    public StringProperty numero;

    public Sejour(){
        super();
    }



    public Sejour(String id, String duree, String date_debut, String date_fin,
                  String type, String nom_centre,String prix,
                  String ageMin, String ageMax, String capacite,String refSejour,String numero) {
        this.id = new SimpleStringProperty(id);
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty( date_fin);
        this.type = new SimpleStringProperty(type);
        this.nom_centre =new SimpleStringProperty( nom_centre);
        this.ageMax =new SimpleStringProperty( ageMax);
        this.ageMin =new SimpleStringProperty( ageMin);
        this.capacite =new SimpleStringProperty( capacite);
        this.prix = new SimpleStringProperty(prix);
        this.refSejour=new SimpleStringProperty(refSejour);
        this.numero=new SimpleStringProperty(numero);
    }


    public Sejour(String duree, String date_debut, String date_fin,
                  String type, String nom_centre,String prix,
                  String ageMax, String ageMin, String capacite,String refSejour,String numero) {
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty( date_fin);
        this.type = new SimpleStringProperty(type);
        this.nom_centre =new SimpleStringProperty( nom_centre);
        this.ageMax =new SimpleStringProperty( ageMax);
        this.ageMin =new SimpleStringProperty( ageMin);
        this.capacite =new SimpleStringProperty( capacite);
        this.prix = new SimpleStringProperty(prix);
        this.refSejour=new SimpleStringProperty(refSejour);
        this.numero=new SimpleStringProperty(numero);
    }
    @Override
    public String toString() {
        return "Sejour{" +
                "id=" + id +
                ", duree=" + duree +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", type=" + type +
                ", nom_centre=" + nom_centre +
                ", prix=" + prix +
                ", ageMin=" + ageMin +
                ", ageMax=" + ageMax +
                ", capacite=" + capacite +
                '}';
    }
}
