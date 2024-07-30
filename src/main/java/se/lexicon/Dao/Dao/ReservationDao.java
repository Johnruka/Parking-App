package se.lexicon.Dao.Dao;

import se.lexicon.model.Reservation;

import java.util.Optional;

public interface ReservationDao {

    Reservation create(Reservation reservation);

    Optional<Reservation> find(String id);

    boolean remove(String id);

    Reservation findByCustomerId(int customerId);

    Reservation findByVehicleLicensePlate(String licensePlate);

    Reservation findByParkingSpotNumber(int spotNumber);
}
