package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends RecursiveTreeObject<Client> {



    public StringProperty id;
    public StringProperty nom_client;
    public  StringProperty prenom_client;
    public  StringProperty groupe;
    public StringProperty datenaissance;
    public StringProperty numero;
    public  StringProperty observation;
    public StringProperty email;
    public StringProperty adresse;
    public StringProperty codePostale;

    public StringProperty sexe;





//nom,prenom,groupe,datenaissance,numero,observation,email,adresse,codepostale

    //prenom_client
    //groupe_client
    //numero
    //observation
    //email
    //nom_client
    //adresse
    //code_postale
    //datenaissance
    public Client(String id,String nom_client, String prenom_client, String groupe,
                  String numero,String observation, String email, String adresse, String codePostale,
                  String datenaissance,String sexe) {
        this.id=new SimpleStringProperty(id);
        this.nom_client = new SimpleStringProperty(nom_client);
        this.prenom_client = new SimpleStringProperty(prenom_client);
        this.groupe = new SimpleStringProperty(groupe);
        this.datenaissance = new SimpleStringProperty(datenaissance);
        this.numero =new SimpleStringProperty( numero);
        this.observation =new SimpleStringProperty( observation);
        this.email = new SimpleStringProperty(email);
        this.adresse =new SimpleStringProperty( adresse);
        this.codePostale = new SimpleStringProperty(codePostale);
        this.sexe=new SimpleStringProperty(sexe);
    }

    public Client() {

    }

    public void setAdresse(String text) {
        this.adresse=new SimpleStringProperty(text);
    }

    public void setCodePostale(String text) {
        this.codePostale=new SimpleStringProperty(text);
    }

    public void setEmail(String text) {
        this.email=new SimpleStringProperty(text);
    }


    public void setObservation(String text) {
        this.observation=new SimpleStringProperty(text);
    }


    public void setNumero(String text) {
        this.numero=new SimpleStringProperty(text);
    }
}
