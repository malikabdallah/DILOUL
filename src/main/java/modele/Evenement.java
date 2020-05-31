package modele;

public class Evenement {
    public String id;
    public String codeclient;
    public String codesejour;
    public String event;
    public String somme;
    public String dateEvent;
    public String methode;





    public Evenement(String id, String codeclient, String codesejour, String event, String somme, String date
    ,String methode) {
       this.id = id;
        this.codeclient = codeclient;
        this.codesejour = codesejour;
        this.event = event;
        this.somme = somme;
        this.dateEvent = date;
        this.methode=methode;
    }



/*

    public Evenement(String id, String codeclient, String codesejour, String event, String somme, String date) {
        this.id = id;
        this.codeclient = codeclient;
        this.codesejour = codesejour;
        this.event = event;
        this.somme = somme;
        this.dateEvent = date;
    }



    public Evenement(String codeclient, String codesejour, String event, String somme, String date) {

        this.codeclient = codeclient;
        this.codesejour = codesejour;
        this.event = event;
        this.somme = somme;
        this.dateEvent = date;
    }
*/

    public Evenement() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(String codeclient) {
        this.codeclient = codeclient;
    }

    public String getCodesejour() {
        return codesejour;
    }

    public void setCodesejour(String codesejour) {
        this.codesejour = codesejour;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSomme() {
        return somme;
    }

    public void setSomme(String somme) {
        this.somme = somme;
    }

    public String getDate() {
        return dateEvent;
    }

    public void setDate(String date) {
        this.dateEvent = date;
    }
}
