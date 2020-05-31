package dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientDto  extends RecursiveTreeObject<ClientDto> {

public StringProperty id_client;
public StringProperty nom_client;
public  StringProperty prenom_client;
public StringProperty groupe;
public StringProperty datenaissance;


        public ClientDto(String s, String s1, String s2, String s3) {
                this.nom_client=new SimpleStringProperty(s);
                this.prenom_client=new SimpleStringProperty(s1);
                this.groupe=new SimpleStringProperty(s2);
                this.datenaissance=new SimpleStringProperty(s3);
        }



        public ClientDto(String id ,String s, String s1, String s2, String s3) {
                this.id_client=new SimpleStringProperty(id);
                this.nom_client=new SimpleStringProperty(s);
                this.prenom_client=new SimpleStringProperty(s1);
                this.groupe=new SimpleStringProperty(s2);
                this.datenaissance=new SimpleStringProperty(s3);
        }
}
