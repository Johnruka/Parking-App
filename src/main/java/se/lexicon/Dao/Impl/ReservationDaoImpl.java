package se.lexicon.Dao.Impl;

import se.lexicon.Dao.Dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public Reservation create(Reservation reservation) {
        return null;
    }

    @Override
    public Optional<Reservation> find(String id) {
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public Reservation findByCustomerId(int customerId) {
        return null;
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        return null;
    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        return null;
    }
}
