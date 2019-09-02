package com.example.william.hangman;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class HangMan extends AppCompatActivity {
    private boolean language = true;
    ImageView image;
    TextView scoreView;
    public char[] secret;
    public String real;
    public String guessed = "";
    public int wrong = 0;
    public int score = 0;
    private Random rnd = new Random();
    private TypedArray images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        final String[] array;
        TextView lan = (TextView)findViewById(R.id.languagetext);
        image = (ImageView) findViewById(R.id.picture);
        scoreView = (TextView)findViewById(R.id.scoreView);
        language = (boolean)getIntent().getExtras().getBoolean("english");

        //Henter alle bildeRefferansenee og setter inn et random ord.
        images  = getResources().obtainTypedArray(R.array.images);
        image.setImageResource(images.getResourceId(wrong,0));
        scoreView.setText(Integer.toString(score));
        if (language) {
            array = getResources().getStringArray(R.array.guesses);
            lan.setText("english");
        }else{
            array = getResources().getStringArray(R.array.gjettinger);
            lan.setText("Norsk");
        }

        final EditText letter  = (EditText) findViewById(R.id.guesstext);
        final int randomNr = rnd.nextInt(array.length);

        createSecret(array[randomNr-1]);

        final TextView tv1 = (TextView)findViewById(R.id.secret);
        final TextView guessedLetters = (TextView)findViewById(R.id.guessedLetters);

        String show  = "";

        for(char ga: secret){
            show += Character.toString(ga);
            show += " ";
        }

        tv1.setText(show);

        final Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        final Button checkChar = findViewById(R.id.check);

        checkChar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean done = false;
                if(letter.length() > 1 || letter.length()  == 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please inset ONE letter please",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }else{
                    String c = letter.getText().toString();
                    c=c.toLowerCase();
                    guessed+=c;

                    char[] s = c.toCharArray();
                    search(s[0]);
                    String show  = "";

                    for(char ga: secret){
                        show += Character.toString(ga);
                        show += " ";
                    }

                    tv1.setText(show);
                    guessedLetters.setText(guessed);
                    image.setImageResource(images.getResourceId(wrong,0));

                    for(int i = 0; i<real.length(); i++){
                        if(real.charAt(i) != secret[i]){
                            done = false;
                            break;
                        }
                        done = true;
                    }

                    if(done){
                        guessed= "";
                        score++;
                        wrong = 0;
                        scoreView.setText(Integer.toString(score));
                        createSecret(array[randomNr-1]);
                        show = "";
                        for(char ga: secret){
                            show += Character.toString(ga);
                            show += " ";
                        }

                        tv1.setText(show);

                        if(score >= 10){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "YOU WIN!",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }


                    if(wrong > 6){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "YOU LOSE!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }

            }


        });

    }

    private void search(char c){
        boolean found = false;
        for(int i = 0 ; i< this.real.length(); i++){
            if(this.real.charAt(i) == Character.toLowerCase(c)){
                found = true;
                secret[i] = c;
            }
        }
        if(!found)
            this.wrong++;
    }

    private void createSecret(String real) {
        this.real = real;
        secret = new char[real.length()];

        for (int i = 0; i < real.length(); i++) {
            this.secret[i] = '_';
        }
    }


}
