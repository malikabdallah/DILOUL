package daos;

import modele.Inscription;
import modele.Reservation;

import java.util.List;

public interface ReservationDao {
    List<Reservation> getReservations();

    int insererReservation(Reservation reservation);

    int supprimerParId(String toString);

    Reservation getReservationParId(String id);


    List<Reservation> getReservationsParIdClient(int id);

    Reservation getReservationParIdClientEtIdSejour(String id_client,String id_sejour);

    int nbReservationForId(String id);
}

