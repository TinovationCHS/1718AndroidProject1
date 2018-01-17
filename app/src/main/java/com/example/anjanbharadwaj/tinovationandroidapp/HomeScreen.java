package com.example.anjanbharadwaj.tinovationandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    public static ArrayList<String> notes = new ArrayList<>();
    ListView v;

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        v = (ListView)findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        v.setAdapter(arrayAdapter);



        //OLD DATABASE CODE
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                notes.clear();
                while(i.hasNext()){
                    String key = (((DataSnapshot) i.next()).getKey());
                    String value = ((dataSnapshot).child(key).getValue().toString());
                    System.out.println((key+" has a value of  "+value));
                    notes.add(key + ": " + value);
                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //NEW DATABASE CODE SHOULD BE SORTED INTO CLASSES
        //WHEN YOU  TAP ON SOMETHING, IT SHOULD CLEAR THE LISTVIEW AND REPOPULATE IT WITH NEW DATA FROM INSIDE THAT CLASS

    }
}
