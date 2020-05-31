package daos;

import modele.PaiementMarie;

import java.util.List;

public interface PaiementMairieDao {

    int inserrerPaiement(PaiementMarie paiementMarie);


    List<PaiementMarie> listePaimenent(int idSejour, int idGroupe);
    List<PaiementMarie> listePaimenentBis(int idSejour, int idGroupe);

}

