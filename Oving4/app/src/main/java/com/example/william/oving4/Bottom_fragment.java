package com.example.william.oving4;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Bottom_fragment extends Fragment {
    ImageView imageView;
    TextView textView;

    public Bottom_fragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment,container,false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textView = (TextView) view.findViewById(R.id.textView);
        return view;
    }
    public void chooseImage(int pos){
        switch (pos){
            case 0:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.flowerboy));
                textView.setText(R.string.tyler_text);
                break;
            case 1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.wolf));
                textView.setText(R.string.wolf_text);
                break;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.pokemon));
                textView.setText(R.string.pokemon_text);
                break;
        }

    }

}




