package modele;

public class GroupeSejourClient {

    public String id;
    public String idGroupe;
    public String idSejour;
    public String idClient;
    public String depart;




    public GroupeSejourClient( String idGroupe, String idSejour, String idClient) {
        this.id = id;
        this.idGroupe = idGroupe;
        this.idSejour = idSejour;

        this.idClient = idClient;
    }



    public GroupeSejourClient( String idGroupe, String idSejour, String idClient,String depart) {
        this.idGroupe = idGroupe;
        this.idSejour = idSejour;
        this.idClient = idClient;
        this.depart=depart;
    }


    public GroupeSejourClient( String id,String idGroupe, String idSejour, String idClient,String depart) {
        this.id=id;
        this.idGroupe = idGroupe;
        this.idSejour = idSejour;
        this.idClient = idClient;
        this.depart=depart;
    }
}
