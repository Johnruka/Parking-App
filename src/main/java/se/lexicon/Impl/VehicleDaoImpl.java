package se.lexicon.Impl;


import se.lexicon.Dao.VehicleDao;
import se.lexicon.model.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class VehicleDaoImpl implements VehicleDao {

    private List<Vehicle> storage = new ArrayList<>();

    private static VehicleDaoImpl instance;

    private VehicleDaoImpl() {
    }

    public static VehicleDaoImpl getInstance() {
        if (instance == null) {
            instance = new VehicleDaoImpl();
        }
        return instance;


    }


    @Override
    public Optional<Vehicle> find(String licensePlate, int customerId) {
        for (Vehicle vehicle : storage) {
            if (vehicle.getLicensePlate().equals(licensePlate) && vehicle.getCustomer().getId() == customerId) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    @Override
    public Vehicle create(Vehicle vehicle) {

        for (Vehicle v : storage) {
            if (v.getLicensePlate().equals(vehicle.getLicensePlate()) && v.getCustomer().getId() == vehicle.getCustomer().getId()) {

                v.setLicensePlate(vehicle.getLicensePlate());
                v.setType(vehicle.getType());
                v.setCustomer(vehicle.getCustomer());

                return v;
            }
        }
        storage.add(vehicle);
        return vehicle;
    }

    @Override
    public boolean remove(String licensePlate, int customerId) {
        Optional<Vehicle> vehicleOptional = find(licensePlate, customerId);
        if (vehicleOptional.isPresent()) {
            storage.remove(vehicleOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public void update(Vehicle vehicle) {
        for (Vehicle storedVehicle : storage) {
            if (storedVehicle.getLicensePlate().equals(vehicle.getLicensePlate())) {
                storedVehicle.setType(vehicle.getType());
                storedVehicle.setCustomer(vehicle.getCustomer());

                break;
            }
        }

    }

    @Override
    public Collection<Vehicle> findByCustomerId(int customerId) {
        List<Vehicle> vehiclesByCustomer = new ArrayList<>();
        for (Vehicle vehicle : storage) {
            if (vehicle.getCustomer().getId() == customerId) {
                vehiclesByCustomer.add(vehicle);
            }
        }
        return vehiclesByCustomer;
    }
}

