package daos;

import modele.Centre;
import modele.Client;

import java.util.List;

public interface ClientDao {




    int insererClient(Client client);

    int[] insererClientMairie(Client client);
    int supprimerClient(String id);


    List<Client> listeClient();


    Client getClientParId(String id);

    Client getClientParNomPrenomAnneeNaissance(String text, String text1, String text2);

    Client getClientParNomEtPrenom(String arg, String arg1);

    Client getClient(String nom, String prenom, String dN, String idGrp);


    int mettreAjourClient(String id,Client client);
}
