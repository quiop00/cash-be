package com.ryu.common.utils;

import com.google.gson.Gson;

public class JsonConverter {
    public static final Gson gson = new Gson();

    public static String serializeObject(Object object) {
        return gson.toJson(object);
    }

    public static <T> T deserializeObject(String object, Class<T> clazz) {
        return gson.fromJson(object, clazz);
    }
}
