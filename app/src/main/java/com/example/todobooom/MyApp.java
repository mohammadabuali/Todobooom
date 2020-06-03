package com.example.todobooom;
import android.app.Application;


import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MyApp extends Application {
    public ArrayList<Todo> lst;
    public FireBaseManager fire;
    @Override

    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
//        FireBaseManager fire = new FireBaseManager();
//        System.out.println(fire.getTodoList());




//
//        AndroidThreeTen.init(this);
//        Gson gson = new Gson();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        String json = sp.getString("kkk", null);
//        Type type = new TypeToken<ArrayList<Todo>>(){}.getType();
//        lst = gson.fromJson(json, type);
//        if(lst == null){
//            lst= new ArrayList<Todo>();
//        }
//        Log.d("some_key", String.format("there are %d todo items", lst.size()));

    }
}
