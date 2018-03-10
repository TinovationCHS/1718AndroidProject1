package com.example.anjanbharadwaj.androidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class TabFragment2 extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment2, container, false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // initialise our views and set various attributes/layouts/listeners
        listView = (ListView) view.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        if(ref.child("Classes")!=null) {
            ref = ref.child("Classes");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list.clear();
                    Iterator i = dataSnapshot.getChildren().iterator();
                    while (i.hasNext()) {
                        DataSnapshot child = (DataSnapshot) (i.next());
                        String className = child.getKey().toString();
                        list.add(className);
                        Log.v("CLASS NAME",className);
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), "You don't have that in the database" , Toast.LENGTH_LONG).show();
        }
//
//        startActivity(new Intent(getActivity().getApplicationContext(), AddClassActivity.class));
//        v2 = (ListView)findViewById(R.id.listview2);
//        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
//        v2.setAdapter(arrayAdapter2);
//
//        root.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterator i = dataSnapshot.getChildren().iterator();
//                classes.clear();
//                while(i.hasNext()){
//                    String className = (((DataSnapshot) i.next()).getKey());
//                    System.out.println((className));
//                    classes.add(className);
//                }
//                arrayAdapter2.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        v2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parentView, View childView, int position, long id) {
//                String className = v2.getItemAtPosition(position).toString();
//                Intent intent = new Intent(getApplicationContext(), ClassDetailActivity.class);
//                intent.putExtra("ClassName", className);
//                startActivity(intent);
//            }
//        });
//
//
//        //NEW DATABASE CODE SHOULD BE SORTED INTO CLASSES
//        //WHEN YOU  TAP ON SOMETHING, IT SHOULD CLEAR THE LISTVIEW AND REPOPULATE IT WITH NEW DATA FROM INSIDE THAT CLASS
//    }
    }

}
