package com.simona.sp3;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class GuessLettersAndGameManagement {

    private GenerateWordWithSpaces generateWordWithLettersAndSpaces;
    private boolean gameLost;
    private boolean gameWon;
    private int unguessedLetters;
    private int numberOftries;

    public GuessLettersAndGameManagement(int wordToBeGuessedlength) {
        generateWordWithLettersAndSpaces = new GenerateWordWithSpaces(wordToBeGuessedlength);
        unguessedLetters = wordToBeGuessedlength;
    }

    public void checkIfLetterFits(Context ctx, String wordToBeGuessed, String letter) {
        if (letter.length() > 1) {
            Toast.makeText(ctx, "Introdu doar o singura litera !", Toast.LENGTH_SHORT).show();
            return;
        }
        // restrictie sa nu se introduca o cifra
        if (!letter.equals("") && !letter.equals(" ")) {
            boolean alreadyGuessedLetter = false;
            for (int i = 0; i < wordToBeGuessed.length(); i++) {
                if (String.valueOf(wordToBeGuessed.charAt(i)).equalsIgnoreCase(letter)) {
                    if (generateWordWithLettersAndSpaces.cellAtIndex(i).getValue().equals(" _ ")) {
                        unguessedLetters--;
                        generateWordWithLettersAndSpaces.cellAtIndex(i).setValue(letter);
                    } else {
                        alreadyGuessedLetter = true;
                    }
                }
            }
            if (!alreadyGuessedLetter) {
                numberOftries++;
            }
        }
        if (numberOftries == 13 && unguessedLetters > 0) {
            gameLost = true;
        }
        if (unguessedLetters == 0) {
            gameWon = true;
        }
    }

    public int getNumberOftries() {
        return numberOftries;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public GenerateWordWithSpaces getGenerateWordWithLettersAndSpaces() {
        return generateWordWithLettersAndSpaces;
    }

}
