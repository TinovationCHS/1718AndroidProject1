package com.example.tinovation.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CommentsActivity extends AppCompatActivity {
    TextView comments;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        String text = getIntent().getStringExtra("Notes");
        comments = (TextView)findViewById(R.id.textView);
        comments.setText(text);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}
