package com.example.william.oving2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    Random rand = new Random();
    int randomized;
    Intent intent;

    protected Button button;
    protected Button nextAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextAssignment = findViewById(R.id.button1);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                randomized = rand.nextInt(100);
                String random = Integer.toString(randomized);

                Toast.makeText(getApplicationContext(), random ,Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), ActivityMessage.class);

                intent.putExtra("random", Integer.toString(randomized)); // Sending random number to the new Activity
                Log.d("Randomized Number", random);  // Commenting in LOGCAT
                startActivity(intent); // starting new activity
            }
        });

        nextAssignment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Velkomen til den nye delen av øvingen" ,Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), AdderSub.class);
                startActivity(intent); // starting new activity
                finish(); // finishing this activity
            }
        });
    }
}
