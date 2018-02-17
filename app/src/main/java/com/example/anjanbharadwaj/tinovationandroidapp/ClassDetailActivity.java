package com.example.anjanbharadwaj.tinovationandroidapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class ClassDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String className = "Hi";
                //Create an Intent to the BookDetail Activity, and pass in the info about the specific Book that was clicked
                Intent i = new Intent(getApplicationContext(), NoteDetailActivity.class);
                i.putExtra("Class", className);
                startActivity(i);
            }
        };

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_teacher_book, container, false);
    }

}
class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CardView cardView;
    TextView title;
    TextView author;
    TextView description;
    RatingBar ratingBar;
    ImageView bookImage;

    private RecyclerViewClickListener mListener;

    BookViewHolder(View v, RecyclerViewClickListener mListener) {
        super(v);
        //instantiation of views
        cardView = (CardView)       v.findViewById(R.id.cardView);
        description = (TextView)    v.findViewById(R.id.bookDescription);
        bookImage = (ImageView)     v.findViewById(R.id.bookImageView);

        this.mListener = mListener;
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, getAdapterPosition());
    }
}