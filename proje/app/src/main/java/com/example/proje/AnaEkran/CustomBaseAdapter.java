package com.example.proje.AnaEkran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proje.R;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;

    String breads[];
    LayoutInflater inflater;



    public CustomBaseAdapter(Context ctx, ArrayList<String> breads){
        this.context = ctx;
        this.breads = breads.toArray(new String[0]);
        inflater = LayoutInflater.from(ctx);

    }


    @Override
    public int getCount() {
        return breads.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView txtView = (TextView) view.findViewById(R.id.textView);


        txtView.setText(breads[i]);

        return view;
    }
}
