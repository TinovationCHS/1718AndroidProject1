package com.example.anjanbharadwaj.tinovationandroidapp;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ClassDetailActivity extends AppCompatActivity {

    //store an arraylist of NoteItem.
    ArrayList<NoteItem> notes = new ArrayList<>();

    //create an adapter for the list view.
    ArrayAdapter<NoteItem> adapter;

    //store the context for use later on.
    Context context;

    //list view to store notes.
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        //initialize the context.
        this.context = this.getApplicationContext();
        //initialize the listView.
        listView = (ListView) findViewById(R.id.NoteManagerListView);
        //load the cards into the listView from Firebase.

        loadNoteData();
    }

    private void loadNoteData() {
        //initialize the adapter with the context and set the data source as the accounts array list.
        adapter = new NoteArrayAdapter(context, 0, notes);

        //create a database reference so we can access the firebase database.
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        final ArrayList<String> classes = new ArrayList<>();

        database.child("Classes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String className = dataSnapshot.getKey().toString();

                final ArrayList<String> dates = new ArrayList<>();
                final ArrayList<String> noteDescriptions = new ArrayList<>();
                classes.add(className);

                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()) {
                    DataSnapshot d = (DataSnapshot) i.next();
                    String date = d.getKey().toString();
                    String noteDescription = d.child(date).getValue().toString();

                    dates.add(date);
                    noteDescriptions.add(noteDescription);


                    notes.add(new NoteItem("Note", R.mipmap.ic_launcher, className, noteDescription, date));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        //notify the adapter that we have new data so we can update the UI.
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


    }
}

class NoteItem {

    private int noteImage;
    private String textSnippet;
    private String classAndPeriod;
    private String date;


    public NoteItem(String title, int noteImage, String classAndPeriod, String textSnippet, String date) {
        this.noteImage = noteImage;
        this.classAndPeriod = classAndPeriod;
        this.textSnippet = textSnippet;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(int noteImage) {
        this.noteImage = noteImage;
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    public String getClassAndPeriod() {
        return classAndPeriod;
    }

    public void setClassAndPeriod(String classAndPeriod) {
        this.classAndPeriod = classAndPeriod;
    }
}

class NoteArrayAdapter extends ArrayAdapter<NoteItem> {

    private Context context;
    private List<NoteItem> noteList;

    public NoteArrayAdapter(Context context, int resource, List<NoteItem> noteList) {
        super(context, resource, noteList);
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NoteItem account = noteList.get(position);

        //create the layout inflator to create view cards.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.note, null);

        //initialize parts of the card.
        TextView classAndPeriod = (TextView) view.findViewById(R.id.classAndPeriod);
        TextView description = (TextView) view.findViewById(R.id.bookDescription);
        ImageView bookImage = (ImageView) view.findViewById(R.id.bookImageView);
        TextView date = (TextView) view.findViewById(R.id.date);

        date.setText(account.getDate() + "");
        classAndPeriod.setText(account.getClassAndPeriod() + "");
        description.setText(account.getTextSnippet() + "");
        bookImage.setImageResource(account.getNoteImage());

        return view;
    }
}