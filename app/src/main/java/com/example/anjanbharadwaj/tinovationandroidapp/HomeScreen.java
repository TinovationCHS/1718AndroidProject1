package com.example.anjanbharadwaj.tinovationandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeScreen extends AppCompatActivity {
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        User user = new User("Ranesh","Islazy");
        root.setValue(user);


    }
}
