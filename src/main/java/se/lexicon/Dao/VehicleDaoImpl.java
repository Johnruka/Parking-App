package se.lexicon.Dao;

import se.lexicon.Dao.Dao.VehicleDao;
import se.lexicon.model.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class VehicleDaoImpl implements VehicleDao {

    private List<Vehicle> storage = new ArrayList<>();

    @Override
    public Optional<Vehicle> find(String licensePlate, int customerId) {
        return Optional.empty();
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    public boolean remove(String licensePlate, int customerId) {
        return false;
    }

    @Override
    public void update(Vehicle vehicle) {

    }

    @Override
    public Collection<Vehicle> findByCustomerId(int customerId) {
        return List.of();
    }
}
