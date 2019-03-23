package org.tp.guessgame;

public interface Player {
    long askNextGuess();
    void respond (boolean lowerOrGreater);
}
