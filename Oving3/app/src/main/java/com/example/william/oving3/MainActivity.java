package com.example.william.oving3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    private static ArrayList<String> navn = new ArrayList<>();
    private static ArrayList<String> fodselsdato = new ArrayList<>();

    public void addNavn(String n){
        navn.add(n);
    }

    public void addfodselsdato(String f){
        fodselsdato.add(f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.nameList);
        final ListView fodelsdager = findViewById(R.id.listView2);

        final  TextView newName = (TextView) findViewById(R.id.editName);
        final TextView newFodeselsdato = findViewById(R.id.editFodsels);
        listView.deferNotifyDataSetChanged();

        Button button = findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String navn = bundle.getString("name");
            String fodselsdato1 = bundle.getString("fodselsdato");
            addNavn(navn);
            addfodselsdato(fodselsdato1);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nN = newName.getText().toString();
                    if(nN.equals(""))
                        Toast.makeText(getApplicationContext(), "Vennligst skriv inn navnet du vil endre til" ,Toast.LENGTH_LONG).show();
                    else
                        navn.set(position,nN);
                    fillList();

            }
        });


        fodelsdager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nN = newFodeselsdato.getText().toString();
                if(nN.equals(""))
                    Toast.makeText(getApplicationContext(), "Vennligst skriv inn datoen du vil endre til" ,Toast.LENGTH_LONG).show();
                else
                    fodselsdato.set(position,nN);
                fillList();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
            }
        });
        fillList();
    }



    private void fillList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,navn);
        adapter.notifyDataSetChanged();
        ListView listView = (ListView) findViewById(R.id.nameList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,fodselsdato);
        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(adapter1);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

}
