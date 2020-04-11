package com.google.sps.data;

import com.google.gson.Gson;


public interface Json {

    public default String toJson() {
        Gson  gson = new Gson();
        return gson.toJson(this);
    }
}