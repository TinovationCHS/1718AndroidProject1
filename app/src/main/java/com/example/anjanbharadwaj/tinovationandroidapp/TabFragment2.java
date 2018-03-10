package com.example.anjanbharadwaj.tinovationandroidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
