package se.lexicon.Dao.Dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotDao {

    ParkingSpot create(ParkingSpot parkingSpot);

    Optional<ParkingSpot> find(int spotNumber);

    boolean remove(int spotNumber);

    List<ParkingSpot> findAll();

    List<ParkingSpot> findByAreaCode(int areaCode);

    void occupyParkingSpot(int spotNumber);

    void vacateParkingSpot(int spotNumber);
}
