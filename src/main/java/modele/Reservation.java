package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation  extends RecursiveTreeObject<Reservation> {

    private  String triche="";
    private String triche2="";
    public StringProperty id;
    public StringProperty  dateinscription;
    public StringProperty code_client;
    public StringProperty id_sejour;
    public  StringProperty depart;







    public Reservation(String id, String dateinscription, String code_client, String id_sejour, String depart) {
        this.id = new SimpleStringProperty(id);
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }


    public Reservation(  String dateinscription, String code_client, String id_sejour, String depart) {
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
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

    @Override
    public String toString() {
        return "Reservation{" +
                "dateinscription=" + dateinscription.get() +
                ", code_client=" + code_client.get() +
                ", id_sejour=" + id_sejour.get() +
                ", depart=" + depart.get() +
                '}';
    }
}
