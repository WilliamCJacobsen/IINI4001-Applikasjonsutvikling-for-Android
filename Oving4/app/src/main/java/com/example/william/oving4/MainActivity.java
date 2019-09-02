package com.example.william.oving4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Top_Fragment.OnDataPass{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void createList(int position) {
        Bottom_fragment bottom_fragment = (Bottom_fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        bottom_fragment.chooseImage(position);
    }
}
