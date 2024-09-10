package se.lexicon.controller;


import se.lexicon.Dao.CustomerDao;
import se.lexicon.Dao.ParkingSpotDao;
import se.lexicon.Dao.ReservationDao;
import se.lexicon.Dao.VehicleDao;
import se.lexicon.Impl.CustomerDaoImpl;
import se.lexicon.Impl.ParkingSpotDaoImpl;
import se.lexicon.Impl.ReservationDaoImpl;
import se.lexicon.Impl.VehicleDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Vehicle;
import se.lexicon.view.ConsoleUI;
import se.lexicon.view.MainMenuAction;

import java.util.List;
import java.util.Optional;


public class Controller implements IController {

    private ConsoleUI consoleUI; // Instance of ConsoleUI for user interaction
    private ParkingSpotDao parkingSpotDao; // Data access object for ParkingSpot entities
    private CustomerDao customerDao; // Data access object for Customer entities
    private VehicleDao vehicleDao; // Data access object for Vehicle entities
    private ReservationDao reservationDao; // Data access object for Reservation entities


    public Controller() {
        consoleUI = new ConsoleUI();
        parkingSpotDao = ParkingSpotDaoImpl.getInstance();
        customerDao = CustomerDaoImpl.getInstance();
        vehicleDao = VehicleDaoImpl.getInstance();
        reservationDao = ReservationDaoImpl.getInstance();


    }

    @Override
    public void startApplication() {
        createParkingSpots();

        while (true) {
            MainMenuAction action = consoleUI.displayMenu();
            switch (action) {
                case DISPLAY:
                    displayAllParkingSpots();
                    break;
                case REGISTER:
                    registerCustomer();
                    break;
                case RESERVE:
                    reserveParkingSpot();
                    break;
                case DISPLAY_AND_VACATE:
                    displayAndVacateReservedParking();
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    @Override
    public void createParkingSpots() {
        // Create and add parking spots to the system
        for (int i = 0; i < 9; i++) {
            ParkingSpot parkingSpot = new ParkingSpot(i, 15000);
            parkingSpotDao.create(parkingSpot);
        }

        consoleUI.displaySuccessMessage("Parking spots created successfully.");

    }

    @Override
    public void displayAllParkingSpots() {
        List<ParkingSpot> parkingSpots = parkingSpotDao.findByAreaCode(15000);

        if (parkingSpots.isEmpty()) {
            consoleUI.displayErrorMessage("No parking spots available.");
        } else {
            consoleUI.displaySuccessMessage("List of All Parking Spots:");
            consoleUI.displayParkingSpots(parkingSpots, 47001);
        }

    }

    @Override
    public void registerCustomer() {
        // Get customer data from the user
        Customer customerData = consoleUI.customerPrompt();

        // Register the customer by adding them to the database
        Customer registeredCustomer = customerDao.create(customerData);

        if (registeredCustomer != null) {
            consoleUI.displaySuccessMessage("Customer registered successfully.");
            consoleUI.displaySuccessMessage(registeredCustomer.getDescription());
        } else {
            consoleUI.displayErrorMessage("Failed to register customer. Please try again.");
        }

    }

    @Override
    public void reserveParkingSpot() {
        consoleUI.displayMessage("Enter your customer id:");
        int customerId = consoleUI.getNumber();

        Optional<Customer> foundCustomerOptional = customerDao.find(customerId);

        if (!foundCustomerOptional.isPresent()) {
            consoleUI.displayErrorMessage("Customer not found!");
        } else {
            Customer foundCustomer = foundCustomerOptional.get();
            if (foundCustomer.getReservation() != null) {
                consoleUI.displayErrorMessage("Customer has already reserve a parking!");
            } else {


                Reservation reservationData = consoleUI.reservationPrompt();

                Vehicle vehicle = reservationData.getAssociatedVehicle();
                Optional<Vehicle> foundVehicleOptional = vehicleDao.find(vehicle.getLicensePlate(), foundCustomer.getId());
                if (!foundVehicleOptional.isPresent()) {
                    vehicleDao.create(vehicle);
                }
                vehicle.setCustomer(foundCustomer);


                Optional<ParkingSpot> optionalParkingSpot = parkingSpotDao.find(reservationData.getParkingSpot().getSpotNumber(), reservationData.getParkingSpot().getAreaCode());
                if (optionalParkingSpot.isPresent()) {

                    reservationData.reserve();


                    parkingSpotDao.occupyParkingSpot(reservationData.getParkingSpot().getSpotNumber(), reservationData.getParkingSpot().getAreaCode());


                    Reservation createdReservation = reservationDao.create(reservationData);
                    foundCustomer.setReservation(createdReservation);
                    customerDao.update(foundCustomer);

                    consoleUI.displaySuccessMessage("Parking spot reserved successfully.");
                    consoleUI.displayCustomerReservation(foundCustomer);

                } else {

                    consoleUI.displayErrorMessage("Selected parking area and spot not found.");
                }


            }

        }

    }

    @Override
    public void displayAndVacateReservedParking() {

        consoleUI.displayMessage("Enter your customer id:");
        int customerId = consoleUI.getNumber();


        Optional<Customer> foundCustomerOptional = customerDao.find(customerId);


        if (!foundCustomerOptional.isPresent()) {

            consoleUI.displayErrorMessage("Customer not found!");
        } else {
            Customer foundCustomer = foundCustomerOptional.get();


            if (foundCustomer.getReservation() == null) {

                consoleUI.displayErrorMessage("There is no reserved Parking for customer id: " + customerId);
            } else {
                Optional<Reservation> reservationOptional = reservationDao.find(foundCustomer.getReservation().getId());

                if (!reservationOptional.isPresent()) {
                    consoleUI.displayErrorMessage("Reservation data not found.");
                } else {

                    Reservation reservation = reservationOptional.get();


                    consoleUI.displayCustomerReservation(reservation.getAssociatedVehicle().getCustomer());

                    reservation.close();

                    parkingSpotDao.vacateParkingSpot(reservation.getParkingSpot().getSpotNumber(), reservation.getParkingSpot().getAreaCode());
                    customerDao.update(reservation.getAssociatedVehicle().getCustomer());


                }

            }
        }

    }
}