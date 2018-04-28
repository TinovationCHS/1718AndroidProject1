package com.example.anjanbharadwaj.androidapp;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinovation.androidapp.NoteDetailActivity;
import com.example.tinovation.androidapp.R;
import com.example.tinovation.androidapp.RecyclerViewClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TabFragment2 extends Fragment {

    ListView listView;
    ArrayAdapter<String> mAdapter;
    ArrayList<Subject> list = new ArrayList<>();
    RecyclerView recList;
      ArrayList<Subject> mItems = new ArrayList<>();
    RecyclerViewClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Create an Intent to the BookDetail Activity, and pass in the info about the specific Book that was clicked
                Intent i = new Intent(getContext(), NoteDetailActivity.class);
                final Subject s = list.get(position);
                i.putExtra("Subject", s);
                //Get the image from the book's image url

                startActivity(i);
            }
        };
        return inflater.inflate(R.layout.fragment_tab_fragment2, container, false);


    }
//    RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<CustomViewHolder>() {
//        @Override
//        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_card
//                    , viewGroup, false);
//            return new CustomViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(CustomViewHolder viewHolder, int i) {
//            viewHolder.className.setText(mItems.get(i));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mItems.size();
//        }
//
//    };
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // initialise our views and set various attributes/layouts/listeners
       // listView = (ListView) view.findViewById(R.id.listview);
        recList = (RecyclerView)view.findViewById(R.id.rv);
        recList.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {

            }
        });
        // specify an adapter (see also next example)

//        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
        //recList.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        update();


    }
    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView className;

        public CustomViewHolder(View itemView) {
            super(itemView);

            className = (TextView) itemView.findViewById(R.id.class_name);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        update();

    }
    public void update(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        if(ref.child("Classes")!=null) {
            ref = ref.child("Classes");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mItems.clear();
                    Iterator i = dataSnapshot.getChildren().iterator();
                    while (i.hasNext()) {
                        DataSnapshot child = (DataSnapshot) (i.next());
                        String className = child.getKey().toString();
                        String description = child.getValue().toString();
                        Log.i("Subject ",className);
                        mItems.add(new Subject(className, description));
                    }
                    showCards();
                    //mAdapter.notifyItemInserted(mItems.size() - 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), "You don't have that in the database" , Toast.LENGTH_LONG).show();
        }

    }
    private void showCards() {
        SubjectAdapter subjectAdapter = new SubjectAdapter(mItems, listener);
        recList.setAdapter(subjectAdapter);
    }
}
class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<Subject> subjects;
    private RecyclerViewClickListener mListener;
    //Default constructor
    SubjectAdapter(ArrayList<Subject> subjects, RecyclerViewClickListener listener) {
        this.subjects = subjects;
        mListener = listener;
        Log.i("Subject Adapter", subjects.toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder bookViewHolder, int i) {
        //Set each field to its corresponding attribute
        Subject subject = subjects.get(i);
        Log.i("On Bind View Holder", subject.getClassName());
        bookViewHolder.class_name.setText(subject.className);
        bookViewHolder.description.setText(subject.classDescription);
    }
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Inflate the view using the proper xml layout
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.class_card, viewGroup, false);

        return new SubjectViewHolder(itemView, mListener);
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView class_name;
        TextView author;
        TextView description;
        RatingBar ratingBar;
        ImageView bookImage;

        private RecyclerViewClickListener mListener;

        SubjectViewHolder(View v, RecyclerViewClickListener mListener) {
            super(v);
            //instantiation of views
            cardView = (CardView) v.findViewById(R.id.cardView);
            class_name =  (TextView) v.findViewById(R.id.class_name);
            description = (TextView) v.findViewById(R.id.class_description);


            this.mListener = mListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }
}


class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.SubjectViewHolder> {
    private ArrayList<Subject> subjects;
    private RecyclerViewClickListener mListener;
    //Default constructor
    NoteAdapter(ArrayList<Subject> subjects, RecyclerViewClickListener listener) {
        this.subjects = subjects;
        mListener = listener;
        Log.i("Subject Adapter", subjects.toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder bookViewHolder, int i) {
        //Set each field to its corresponding attribute
        Subject subject = subjects.get(i);
        Log.i("On Bind View Holder", subject.getClassName());
        bookViewHolder.class_name.setText(subject.className);
        bookViewHolder.description.setText(subject.classDescription);
    }
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Inflate the view using the proper xml layout
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.class_card, viewGroup, false);

        return new SubjectViewHolder(itemView, mListener);
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView class_name;
        TextView author;
        TextView description;
        RatingBar ratingBar;
        ImageView bookImage;

        private RecyclerViewClickListener mListener;

        SubjectViewHolder(View v, RecyclerViewClickListener mListener) {
            super(v);
            //instantiation of views
            cardView = (CardView) v.findViewById(R.id.cardView);
            class_name =  (TextView) v.findViewById(R.id.class_name);
            description = (TextView) v.findViewById(R.id.class_description);


            this.mListener = mListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }
}