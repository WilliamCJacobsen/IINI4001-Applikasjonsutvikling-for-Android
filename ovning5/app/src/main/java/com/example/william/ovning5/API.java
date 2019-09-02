package com.example.william.ovning5;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class API {

    private static String URL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private static Http http = new Http(URL);

    public static void registerPlayer(String name, String credit, Http.Callback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("navn",name);
        parameters.put("kortnummer", credit);

        log("Starting registration of {" + name + "} with {" + credit + "}");
        http.runHttpRequestInThread(Http.HttpRequestType.HTTP_GET, parameters, callback);
    }

    public static void guessNumber(String number, Http.Callback callback) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tall", number);
        http.runHttpRequestInThread(Http.HttpRequestType.HTTP_GET, parameters, callback);
    }

    private static void log(String s) {
        Log.d("API", s);
    }
}
