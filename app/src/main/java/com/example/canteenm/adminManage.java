//package com.example.canteenm;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class adminManage extends AppCompatActivity {
//
//    RecyclerView foodRecycleview;
//    ManageAdapter manageAdapter;
//    List<VegModel> list;
//
//    //firebase
//    private DatabaseReference reference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_manage);
//
//        foodRecycleview=findViewById(R.id.all);
//        //fooditems
//        foodRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
//
//        list= new ArrayList<>();
//        manageAdapter = new ManageAdapter(getApplicationContext(),list);
//        foodRecycleview.setAdapter(manageAdapter);
//
//        reference = FirebaseDatabase.getInstance().getReference("foodname");
//
//        reference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    VegModel vegModel=dataSnapshot.getValue(VegModel.class);
//                    list.add(vegModel);
//
//                }
//                manageAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//}