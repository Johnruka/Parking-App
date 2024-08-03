package se.lexicon.Sequencer;

public class CustomerSequencer {

    private static int sequencer = 10;

    public static int nextId() {
        return ++sequencer;
    }
}
