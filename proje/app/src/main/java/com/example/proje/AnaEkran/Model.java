package com.example.proje.AnaEkran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {


    @SerializedName("message")
    @Expose
    private Map<String, List> result;

    public Map<String, List> getResult() {
        return result;
    }

    public void setResult(Map<String, List> result) {
        this.result = result;
    }
}
