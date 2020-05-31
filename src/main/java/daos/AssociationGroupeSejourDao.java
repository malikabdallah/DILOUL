package daos;

import controlleurvue.groupe.AssocierGroupeSejour;
import modele.Associationgroupesejour;

import java.util.List;

public interface AssociationGroupeSejourDao {



    int inserrerAssociation(Associationgroupesejour associationgroupesejour);


    List<Associationgroupesejour> getListes();

    Associationgroupesejour getById(String s);


    int supprimerById(String text);

    List<String> testCapaciteCentre(String id);

    int nbReservationGroupSejourForId(String id);

    int mettreAJourAssociation(String id ,String prix,String nbPlace);

}
