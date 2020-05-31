package daos;

import modele.Inscription;

import java.util.List;
import java.util.Optional;

public interface InscriptionDao {


    List<Inscription> getInscriptions();

    int insererInscription(Inscription inscription2);

    int supperimerParId(String toString);


    int mettreAjourPaiement(String idInscription,String result);

    List<Inscription> getInscriptionsParIdSejour(String s);

    Inscription getInscriptionsParIdSejourEtIdClient(String text, String s);

    List<Inscription> getInscriptiosnParIdClient(int id);

    int nbInscriptionForId(String id);
    Inscription getInscritptionParIdInscription(String text);

}
