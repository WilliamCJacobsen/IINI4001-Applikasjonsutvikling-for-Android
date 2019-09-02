package com.example.william.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean language = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.start);
        final Button languageButton = findViewById(R.id.language);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HangMan.class);
                intent.putExtra("english",language);
                startActivity(intent);
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                language = !language;

                if(language){
                    languageButton.setText("English");
                }else{
                    languageButton.setText("Norsk");
                }
            }
        });


    }
}
