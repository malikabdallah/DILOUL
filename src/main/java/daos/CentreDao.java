package daos;

import exceptions.centre.CapaciteException;
import exceptions.centre.NomDejaPresentException;
import modele.Centre;

import java.util.List;

public interface CentreDao {



    int inserrerCentre(String nom_centre,String capacite);



    int supprimerCentre(String id);


    List<Centre> listeCentres();

    Centre trouverParNomCentre(String nom);

    Centre getCentreParId(String id);

    int mettreAjourCentre(String id, Centre centre);
}
