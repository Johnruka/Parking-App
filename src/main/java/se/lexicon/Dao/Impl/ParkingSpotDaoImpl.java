package se.lexicon.Dao.Impl;

import se.lexicon.Dao.Dao.ParkingSpotDao;
import se.lexicon.Dao.Sequencer.ParkingSpotSequencer;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private List<ParkingSpot> storage = new ArrayList<>();


    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
       int id = ParkingSpotSequencer.nextSpotNumber();
       ParkingSpot.setSpotNumber(ParkingSpotSequencer.nextSpotNumber());

        storage.add((ParkingSpot) ParkingSpot.setSpotNumber);
        return (ParkingSpot) ParkingSpot.setSpotNumber;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for (ParkingSpot parkingSpot : storage) {
            if (parkingSpot.getSpotNumber() == spotNumber) {
                return Optional.of(parkingSpot);
            }
        }
        return Optional.empty();

    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> ParkingSpotOptional = find(spotNumber);
        if (!ParkingSpotOptional.isPresent()) return false;
        storage.remove(ParkingSpotOptional.get());
        return true;

    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(storage);

    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        return List.of();
    }

    @Override
    public void occupyParkingSpot(int spotNumber) {

    }

    @Override
    public void vacateParkingSpot(int spotNumber) {

    }
}
