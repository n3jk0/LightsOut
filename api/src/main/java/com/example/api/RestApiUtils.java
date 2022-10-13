package com.example.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created 12.10.2022
 * @author Nejc Kozlevčar
 */
public class RestApiUtils {
    public static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
