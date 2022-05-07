package com.simona.sp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText wordED, letterED;
    Button guessBtn, tryThisLetterBtn, newGameBtn;
    TextView numberOfTriesTV, gameOverTV;

    RecyclerView rv;
    Adapter myAdapter;

    String wordToBeGuessed;
    GuessLettersAndGameManagement game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    void initViews() {
        numberOfTriesTV = findViewById(R.id.numberOfTriesTextView);

        wordED = findViewById(R.id.wordEditText);
        letterED = findViewById(R.id.letterEditText);
        guessBtn = findViewById(R.id.goToGuessButton);
        guessBtn.setOnClickListener(this);
        tryThisLetterBtn = findViewById(R.id.guessLetterButton);
        tryThisLetterBtn.setOnClickListener(this::onClick);
        rv = findViewById(R.id.freeSpacesRV);
        gameOverTV = findViewById(R.id.gameOverTextView);

        newGameBtn = findViewById(R.id.newGameButton);
        newGameBtn.setOnClickListener(this::onClick);

        letterED.setVisibility(View.INVISIBLE);
        tryThisLetterBtn.setVisibility(View.INVISIBLE);
        numberOfTriesTV.setVisibility(View.INVISIBLE);
        gameOverTV.setVisibility(View.INVISIBLE);
        newGameBtn.setVisibility(View.INVISIBLE);
    }

    void preiaCuvantDinInputGenereazaSpatiiGoale() {
        wordToBeGuessed = wordED.getText().toString().trim();
        game = new GuessLettersAndGameManagement(wordToBeGuessed.length());
        rv.setLayoutManager(new GridLayoutManager(this, wordToBeGuessed.length()));
        myAdapter = new Adapter(game.getGenerateWordWithLettersAndSpaces().getSirCuvantCuSpatii());
        rv.setAdapter(myAdapter);

        numberOfTriesTV.setVisibility(View.VISIBLE);
        guessBtn.setVisibility(View.INVISIBLE);
        wordED.setVisibility(View.INVISIBLE);
        gameOverTV.setVisibility(View.INVISIBLE);
        newGameBtn.setVisibility(View.INVISIBLE);
        letterED.setVisibility(View.VISIBLE);
        tryThisLetterBtn.setVisibility(View.VISIBLE);
        wordED.setText(null);
        numberOfTriesTV.setText("0 / 13 incercari");
        letterED.requestFocus();
    }

    @Override
    public void onClick(View view) {
        int clickedID = view.getId();
        switch (clickedID) {
            case R.id.goToGuessButton:
                preiaCuvantDinInputGenereazaSpatiiGoale();
                break;
            case R.id.guessLetterButton:
                String literaIntrodusa = letterED.getText().toString().trim();
                game.checkIfLetterFits(this, wordToBeGuessed, literaIntrodusa);
                letterED.setText(null);
                letterED.requestFocus();
                if (game.isGameLost()) {
                    gameOverTV.setText("Ai depasit numarul de incercari! ");
                    questionNewGame();
                } else if (game.isGameWon()) {
                    gameOverTV.setText("Ai castigat din " + game.getNumberOftries() + " incercari!");
                    numberOfTriesTV.setVisibility(View.INVISIBLE);
                    questionNewGame();
                } else {
                    numberOfTriesTV.setText(game.getNumberOftries() + " / 13 incercari");
                    numberOfTriesTV.setVisibility(View.VISIBLE);
                }
                myAdapter.setCells(game.getGenerateWordWithLettersAndSpaces().getSirCuvantCuSpatii());
                myAdapter.notifyDataSetChanged();
                rv.setAdapter(myAdapter);
                break;
            case R.id.newGameButton:
                wordED.setVisibility(View.VISIBLE);
                guessBtn.setVisibility(View.VISIBLE);
                gameOverTV.setVisibility(View.INVISIBLE);
                newGameBtn.setVisibility(View.INVISIBLE);
                game = new GuessLettersAndGameManagement(0);
                myAdapter.setCells(new ArrayList<>(0));
                numberOfTriesTV.setVisibility(View.INVISIBLE);
                wordED.requestFocus();
                break;
            default:
                return;
        }
    }

    void questionNewGame() {
        wordED.setText(null);
        letterED.setVisibility(View.INVISIBLE);
        tryThisLetterBtn.setVisibility(View.INVISIBLE);
        gameOverTV.setVisibility(View.VISIBLE);
        newGameBtn.setVisibility(View.VISIBLE);
    }


}