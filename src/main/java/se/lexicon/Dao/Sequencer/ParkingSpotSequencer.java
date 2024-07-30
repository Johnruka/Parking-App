package se.lexicon.Dao.Sequencer;

public class ParkingSpotSequencer {

    private static int sequencer = 10;

    public static int nextSpotNumber() {
        return ++sequencer;
    }
}
