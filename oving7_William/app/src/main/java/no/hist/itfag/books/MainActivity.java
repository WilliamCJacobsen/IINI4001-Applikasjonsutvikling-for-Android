package no.hist.itfag.books;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends Activity {
    private DatabaseManager database;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button book;
        final Button writer;
        final Button alt;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setBGColour();

        writer = findViewById(R.id.forfatter);
        book = findViewById(R.id.bok);
        alt = findViewById(R.id.alt);
        ll = findViewById(R.id.list);

        book.setOnClickListener(e -> showRes(database.getAllBooks()));
        writer.setOnClickListener(e -> showRes(database.getAllAuthors()));
        alt.setOnClickListener(e -> showRes(database.getAllBooksAndAuthors()));

        try {
            database = new DatabaseManager(this);
            database.clean();
            readFileToDB("data.txt");
        } catch (Exception e) {
            String tekst = e.getMessage();
        }
    }


    public void showRes(ArrayList<String> res) {
        ll.removeAllViews();

        for(String s:res) {
            TextView a = new TextView(ll.getContext());
            a.setText(s);
            ll.addView(a);
        }
    }


    public void readFileToDB(String path) {
        File dir = getFilesDir();
        File file = new File(dir, path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                Log.i("fileRead", line);
                String[] fields = line.split("-");
                database.insert(fields[0], fields[1]);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
        }

    }

    public void setBGColour() {
        SharedPreferences appPrefs = getDefaultSharedPreferences(this);
        String c = appPrefs.getString("Colour", null);
        View v = findViewById(R.id.asd);
        switch (c) {
            case "green":
                v.setBackgroundColor(0xFF006400);
                break;
            case "red":
                v.setBackgroundColor(0xFF8B0000);
                break;
            case "blue":
                v.setBackgroundColor(0xFF00008B);
                break;
            default:
                v.setBackgroundColor(0xFF000000);
                break;

        }
        v.invalidate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        SharedPreferences appPrefs = getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        if (id == R.id.green) {
            prefsEditor.putString("Colour", "green");
        } else if (id == R.id.blue) {
            prefsEditor.putString("Colour", "blue");
        } else if (id == R.id.red) {
            prefsEditor.putString("Colour", "red");
        }
        prefsEditor.commit();
        setBGColour();

        return super.onOptionsItemSelected(item);
    }
}
