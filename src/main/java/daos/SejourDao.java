package daos;

import javafx.beans.property.StringProperty;
import modele.Groupe;
import modele.Sejour;

import java.util.List;

public interface SejourDao {

    int insererSejour(Sejour sejour);




    List<Sejour> listeSejour();



    Sejour getSejourParId(String id_sejour);

    int supprimerSejourParid(String toString);

    List<Sejour> getSejourParType(String nom);

    List<Sejour> getSejourParTypeEtDate(String value, String arg, String arg1);

    List<Sejour> getSejourParCentre(String id);

    List<Sejour> getSejourParTypeEtDuree(Object value, String newItem);

    Sejour getSejourPartypeetdureeetdate(Object value, Object value1, String arg, String arg1);
}

