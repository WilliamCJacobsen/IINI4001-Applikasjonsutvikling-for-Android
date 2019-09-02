package com.example.william.ovning5;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.william.oving05.R;

public class Registrering extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        EditText inputName = findViewById(R.id.input_name);
        EditText inputCredit = findViewById(R.id.input_credit);

        Button startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener((v) -> {
            // Start game here
            initializeGame(inputName.getText().toString(), inputCredit.getText().toString());
        });
    }

    private void initializeGame(String name, String credit) {
        if(!validateInput(name, credit)) {
            return;
        }

        Button startButton = findViewById(R.id.button_start);
        startButton.setEnabled(false);
        API.registerPlayer(name, credit, (response) -> {
            startButton.setEnabled(true);
            Intent data = new Intent(this, MainActivity.class);
            data.putExtra(Requests.REGISTER_DATA, response);
            data.putExtra(Requests.USER_NAME, name);
            startActivity(data);
            finish();
        });
    }

    private boolean validateInput(String name, String credit) {
        return (name != null && credit != null && name.length() > 0 && credit.length() > 0);
    }
}
