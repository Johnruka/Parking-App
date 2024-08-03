package se.lexicon.Dao;

import se.lexicon.model.Reservation;

import java.util.Optional;

public interface ReservationDao {

    Reservation create(Reservation reservation);

    Optional<Reservation> find(String id);


    Reservation findByVehicleLicensePlate(String licensePlate);

    Reservation findByParkingSpotNumber(int spotNumber);
}
