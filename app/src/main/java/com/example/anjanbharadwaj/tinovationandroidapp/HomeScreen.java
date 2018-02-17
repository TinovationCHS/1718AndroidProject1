package com.example.anjanbharadwaj.tinovationandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
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
//    public static ArrayAdapter<String> arrayAdapter;
//    public static ArrayList<String> notes = new ArrayList<>();
    public static ArrayAdapter<String> arrayAdapter2;
    public static ArrayList<String> classes = new ArrayList<>();
    //ListView v;
    ListView v2;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //v = (ListView)findViewById(R.id.listview);
        v2 = (ListView)findViewById(R.id.listview2);
        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
        v2.setAdapter(arrayAdapter2);

        //OLD DATABASE CODE
        root.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                classes.clear();
                while(i.hasNext()){
                    String className = (((DataSnapshot) i.next()).getKey());
                    System.out.println((className));
                    classes.add(className);
                }
                arrayAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        v2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentView, View childView, int position, long id) {
                String className = v2.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(), ClassDetailActivity.class);
                intent.putExtra("ClassName", className);
                startActivity(intent);
            }
        });

        //NEW DATABASE CODE SHOULD BE SORTED INTO CLASSES
        //WHEN YOU  TAP ON SOMETHING, IT SHOULD CLEAR THE LISTVIEW AND REPOPULATE IT WITH NEW DATA FROM INSIDE THAT CLASS

    }
}
