package com.example.anjanbharadwaj.tinovationandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HomeScreen extends AppCompatActivity {
    public static ArrayAdapter<String> arrayAdapter;
    public static ArrayList<String> classhomework = new ArrayList<>();
    public static ArrayAdapter<String> arrayAdapter2;
    public static ArrayList<String> classnotes = new ArrayList<>();
    ListView v;

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        v = (ListView)findViewById();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classhomework);
        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classnotes);
        //arrayAdapter = new MyCustomAdapter(classhomework, this);
        listviewhw.setAdapter(arrayAdapter);
        listofnote.setAdapter(arrayAdapter2);

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    //create arraylist here 11/18 meeting
                    String key = (((DataSnapshot) i.next()).getKey());
                    String value = ((dataSnapshot).child(key).getValue().toString());
                    System.out.println((key+" has a value of  "+value));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
