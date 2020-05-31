package daos;

import modele.Annulation;

import java.util.List;

public interface AnnulationDao {
    int insererAnnulation(Annulation annulation);


    List<Annulation> getAnnulationsParId(int id);


    List<Annulation> getAnnulartions();
}
