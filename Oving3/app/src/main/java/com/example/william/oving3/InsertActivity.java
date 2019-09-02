package com.example.william.oving3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        Button register = findViewById(R.id.registrer);

        Button back = findViewById(R.id.backTomain);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView fodselsdato =  findViewById(R.id.editText2);
                TextView navn = findViewById(R.id.editText);

                String name = navn.getText().toString();
                String birth = fodselsdato.getText().toString();

                if(name.equals("") || birth.equals("")){
                    Toast.makeText(getApplicationContext(), "Vennligst skriv inn navn og/eller fødselsdag" ,Toast.LENGTH_LONG).show();
                }else{
                    intent = new Intent(getApplicationContext(), MainActivity.class);

                    intent.putExtra("name",name);
                    intent.putExtra("fodselsdato",birth);
                    startActivity(intent);
                }
            }
        });
    }

}
