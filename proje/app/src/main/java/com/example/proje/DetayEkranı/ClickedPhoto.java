package com.example.proje.DetayEkranı;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proje.R;


public class ClickedPhoto extends AppCompatActivity {

    String url;
    ImageView secilenResim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_photo);

        Intent intent = getIntent();


        secilenResim = findViewById(R.id.secilenResim);

        if(intent.getExtras() != null){
            url = (String) intent.getSerializableExtra("extra");

            Glide.with(this).load(url).into(secilenResim);
        }
    }
}