package com.example.william.oving2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AdderSub extends AppCompatActivity {
    protected Button adder;
    protected Button multipliser;
    protected EditText anwser;
    protected EditText topLimit;
    protected int nummer1;
    protected int nummer2;
    protected int topgrense = 10;

    Random random = new Random();
    int sum;

    private void newNumbers(int ovregrense){
        nummer1 = random.nextInt(ovregrense);
        nummer2 = random.nextInt(ovregrense);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder_sub);

        adder = findViewById(R.id.adder);
        multipliser = findViewById(R.id.multipliser);

        anwser = findViewById(R.id.editText2);
        topLimit = findViewById(R.id.editText3);

        TextView adderingText = findViewById(R.id.textView);
        TextView multiText = findViewById(R.id.textView2);

        newNumbers(topgrense);

        adderingText.setText(Integer.toString(nummer1));
        multiText.setText(Integer.toString(nummer2));

        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String s = anwser.getText().toString();
                    String top = topLimit.getText().toString();
                    sum = nummer1 + nummer2;

                    TextView adderingText = findViewById(R.id.textView);
                    TextView multiText = findViewById(R.id.textView2);

                    if(s.equals("")){
                        s = "0";
                    }

                    if(!top.equals("")){
                        topgrense = Integer.valueOf(top);
                    }

                    if (s.equals(Integer.toString(sum))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.riktig), Toast.LENGTH_LONG).show();
                        newNumbers(topgrense);

                        adderingText.setText(Integer.toString(nummer1));
                        multiText.setText(Integer.toString(nummer2));

                    } else
                        Toast.makeText(getApplicationContext(), getString(R.string.feil) + " " + sum, Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        multipliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String s = anwser.getText().toString();
                    String top = topLimit.getText().toString();
                    sum = nummer1 * nummer2;

                    TextView adderingText = findViewById(R.id.textView);
                    TextView multiText = findViewById(R.id.textView2);

                    if(s.equals("")){
                        s = "0";
                    }

                    if(!top.equals("")){
                        topgrense = Integer.valueOf(top);
                    }

                    if (s.equals(Integer.toString(sum))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.riktig), Toast.LENGTH_LONG).show();
                        newNumbers(topgrense);

                        adderingText.setText(Integer.toString(nummer1));
                        multiText.setText(Integer.toString(nummer2));

                    } else
                        Toast.makeText(getApplicationContext(), getString(R.string.feil) + " " + sum, Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

}
