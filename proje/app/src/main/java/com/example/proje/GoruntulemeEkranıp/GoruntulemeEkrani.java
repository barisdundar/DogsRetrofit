package com.example.proje.GoruntulemeEkranıp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proje.DetayEkranı.ClickedPhoto;
import com.example.proje.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GoruntulemeEkrani extends AppCompatActivity {

    GridView gridView;

    String photoUrl[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goruntuleme_ekrani);



        String extra = getIntent().getStringExtra("extra");


        gridView = findViewById(R.id.gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(GoruntulemeEkrani.this, ClickedPhoto.class).putExtra("extra",photoUrl[i]));
            }
        });

        GoruntulenmeMethods methods = GoruntulenmeRetrofitClient.getRetrofitInstance().create(GoruntulenmeMethods.class);



        Call<GoruntulenmeModel> call = methods.getAllData("api/breed/"+extra+"/images");


        call.enqueue(new Callback<GoruntulenmeModel>() {
            @Override
            public void onResponse(Call<GoruntulenmeModel> call, Response<GoruntulenmeModel> response) {

                photoUrl = response.body().getResult().toString().replaceAll("\\[","").replaceAll("\\s","").replaceAll("\\]","").split(",");


                CustomAdapter customAdapter = new CustomAdapter(photoUrl, GoruntulemeEkrani.this);

                gridView.setAdapter(customAdapter);


            }

            @Override
            public void onFailure(Call<GoruntulenmeModel> call, Throwable t) {
            }
        });
    }

    public class CustomAdapter extends BaseAdapter{

        private String[] photoUrl;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] photoUrl, Context context) {
            this.context = context;
            this.photoUrl = photoUrl;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return photoUrl.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null){
                view = layoutInflater.inflate(R.layout.activity_row_grid_items,viewGroup,false);
            }
            ImageView imageView = view.findViewById(R.id.imageView);



            Glide.with(context).load(photoUrl[i].toString()).into(imageView);
            return view;
        }
    }
}
