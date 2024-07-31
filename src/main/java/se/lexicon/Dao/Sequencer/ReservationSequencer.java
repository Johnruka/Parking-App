package se.lexicon.Dao.Sequencer;

public class ReservationSequencer {

    private static int sequencer = 10;

    public static int nextReservationId() {
        return ++sequencer;
    }
}
