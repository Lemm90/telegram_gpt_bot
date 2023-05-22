package ru.khorolskii.home.telegrambot.converter;

import com.google.gson.Gson;
import org.json.JSONObject;

public class JsonToStringConverterUtils {

    public JsonToStringConverterUtils() {
        //no-op
    }

    public static String jsonToString(JSONObject jsonObject){
        return new Gson().toJson(jsonObject);
    }
}
