package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proje.AnaEkran.CustomBaseAdapter;
import com.example.proje.AnaEkran.Methods;
import com.example.proje.AnaEkran.Model;
import com.example.proje.AnaEkran.RetrofitClient;
import com.example.proje.GoruntulemeEkranıp.GoruntulemeEkrani;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    String nonFilteredBreads[];
    ArrayList<String> breads = new ArrayList<String>();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();

        listView = (ListView) findViewById(R.id.customListView);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                int i = 0;


                nonFilteredBreads = response.body().getResult().toString().split(",");



                String ustIrk = "";


                for(int y = 0; y < nonFilteredBreads.length; y++){


                    if(nonFilteredBreads[y].contains("[]")){
                        breads.add(nonFilteredBreads[y].replaceAll("[^A-Za-z0]", ""));
                    }

                    else{

                        if(nonFilteredBreads[y].contains("[") && nonFilteredBreads[y].contains("]")){
                            breads.add(nonFilteredBreads[y].replaceAll("\\=","/").replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ",""));
                        }
                        //
                        else{

                            if(nonFilteredBreads[y].contains("=")){

                                ustIrk = nonFilteredBreads[y].split("\\=")[0].replaceAll(" ","");
                                breads.add(nonFilteredBreads[y].replaceAll("\\=","/").replaceAll("\\[","").replaceAll(" ",""));
                            }
                            else {
                                breads.add(ustIrk + "/" +nonFilteredBreads[y].replaceAll(" ","").replaceAll("\\]",""));
                            }
                        }
                    }

                }





                CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),breads);
                listView.setAdapter(customBaseAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        TextView textView = (TextView) view.findViewById(R.id.textView);
                        String text = textView.getText().toString();
                        


                        Intent intent = new Intent(MainActivity.this, GoruntulemeEkrani.class);

                        intent.putExtra("extra", text);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());

            }
        });



    }
}