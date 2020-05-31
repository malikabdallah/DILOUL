package daos;

import modele.Centre;
import modele.Groupe;

import java.util.List;

public interface GroupeDao {



    int inserrerGroupe(String nom_groupe, String tiers,String commune);



    int mettreAjourGroupe(String id, Groupe groupe);

    int supprimerGroupe(String id);



    List<Groupe> listeGroupes();

    Groupe trouverGroupeParNomGroupe(String nom_groupe);

    Groupe trouverGroupeParCodeTiers(String tiers);

    Groupe getGroupeParId(String id);

}
