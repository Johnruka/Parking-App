package se.lexicon.Sequencer;

public class ReservationSequencer {

    private static int sequencer = 10;

    public static int nextReservationId() {
        return ++sequencer;
    }

    public static int setReservationId(int i) {
        return ReservationSequencer.nextReservationId();
    }
}
