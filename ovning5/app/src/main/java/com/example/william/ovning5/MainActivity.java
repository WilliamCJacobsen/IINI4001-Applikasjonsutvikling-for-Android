package com.example.william.ovning5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.william.oving05.R;

public class MainActivity extends AppCompatActivity {
    private int guesses = 0;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeQuiz();
    }

    private void initializeQuiz() {
        Intent intent = getIntent();
        String question = intent.getStringExtra(Requests.REGISTER_DATA);
        name = intent.getStringExtra(Requests.USER_NAME);
        setQuestion(question);
        guesses = 0;

        // Guess button
        Button guessButton = findViewById(R.id.button_guess);
        guessButton.setOnClickListener(this::foresporsel);

        // Reset button
        Button resetButton = findViewById(R.id.button_restart);
        resetButton.setOnClickListener((v) -> {
            Intent data = new Intent(this, Registrering.class);
            startActivity(data);
            finish();
        });
        setResetButtonEnabled(false);
    }

    private void setQuestion(String q) {
        TextView questionText = findViewById(R.id.text_question);
        questionText.setText(q);
    }

    private void foresporsel(View v) {
        TextView guessInput = findViewById(R.id.input_guess);
        String guess = guessInput.getText().toString();
        if(validateGuess(guess)) {
            v.setEnabled(false);

            API.guessNumber(guess, (response) -> {
                handleResponse(v, response);
            });
        }
    }

    private void handleResponse(View button, String response) {
        guesses++;

        boolean status = validateResponse(response);
        if(status) { //Right answer
            setResetButtonEnabled(true);
            setVictoryText();
        }
        else if(guesses == 3) { // Game over
            setResetButtonEnabled(true);
        }
        else { // Wrong answer
            button.setEnabled(true);
        }
        setQuestion(response);
    }

    // Was the guess correct?...
    private boolean validateResponse(String response) {
        return response.startsWith(name);
    }

    // Is the guess a valid guess?
    private boolean validateGuess(String guess) {
        return (guess != null && guess.length() > 0);
    }

    private void setResetButtonEnabled(boolean status) {
        Button resetButton = findViewById(R.id.button_restart);
        resetButton.setEnabled(status);
    }

    private void setVictoryText () {
        TextView text = findViewById(R.id.text_title);
        text.setTextColor(0xff4cc462);
        text.setText(R.string.vant);
    }
}
