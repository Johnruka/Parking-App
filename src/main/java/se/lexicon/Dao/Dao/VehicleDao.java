package se.lexicon.Dao.Dao;

import se.lexicon.model.Vehicle;

import java.util.Optional;

public interface VehicleDao {

    Optional<Vehicle> find(String licensePlate, int customerId);

    Vehicle create(Vehicle vehicle);

    boolean remove(String licensePlate, int customerId);

    void update(Vehicle vehicle);

    int findByCustomerId(int customerId);
}
