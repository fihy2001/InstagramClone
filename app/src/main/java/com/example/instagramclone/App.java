package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    public void onCreate(){

        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("59EknWByDwlf3Ao1QgdLuqlengXZ79MwUd2y7Jyi")
                // if defined
                .clientKey("Vu9Pm4mbMJykWvhZoIsgq6KkWHiWXEvx54ozeBm8")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
