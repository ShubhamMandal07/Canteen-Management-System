package com.example.canteenm;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class veg extends AppCompatActivity {

RecyclerView  foodRecycleview;
    VegAdapter vegAdapter;
    List<VegModel> list;

    private Button button1,button2,button3;
    //firebase
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);
        foodRecycleview=findViewById(R.id.veg);

        button1=findViewById(R.id.vegbut);
        button2=findViewById(R.id.nonvegbut);
        button3=findViewById(R.id.bevbut);

        //fooditems
        foodRecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));

        list= new ArrayList<>();
        vegAdapter = new VegAdapter(getApplicationContext(),list);
        foodRecycleview.setAdapter(vegAdapter);

       reference = FirebaseDatabase.getInstance().getReference("foodname");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    VegModel vegModel=dataSnapshot.getValue(VegModel.class);
                    list.add(vegModel);

                }
                vegAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        button1.setOnClickListener(view -> {
            list= new ArrayList<>();
            vegAdapter = new VegAdapter(getApplicationContext(),list);
            foodRecycleview.setAdapter(vegAdapter);

            reference = FirebaseDatabase.getInstance().getReference("foodname");


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        System.out.println(dataSnapshot.child("foodtype"));
                        if (Objects.requireNonNull(dataSnapshot.child("foodtype").getValue()).toString().equals("veg")){
                            System.out.println(dataSnapshot.child("foodtype"));
                            VegModel vegModel=dataSnapshot.getValue(VegModel.class);
                            list.add(vegModel);
                        }

                    }
                    vegAdapter.notifyDataSetChanged();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        });


        button2.setOnClickListener(view -> {
            list= new ArrayList<>();
            vegAdapter = new VegAdapter(getApplicationContext(),list);
            foodRecycleview.setAdapter(vegAdapter);

            reference = FirebaseDatabase.getInstance().getReference("foodname");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        System.out.println(dataSnapshot.child("foodtype"));
                        if (Objects.requireNonNull(dataSnapshot.child("foodtype").getValue()).toString().equals("non-veg")){
                            System.out.println(dataSnapshot.child("foodtype"));
                            VegModel vegModel=dataSnapshot.getValue(VegModel.class);
                            list.add(vegModel);
                        }

                    }
                    vegAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        });

        button3.setOnClickListener(view -> {
            list= new ArrayList<>();
            vegAdapter = new VegAdapter(getApplicationContext(),list);
            foodRecycleview.setAdapter(vegAdapter);

            reference = FirebaseDatabase.getInstance().getReference("foodname");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        System.out.println(dataSnapshot.child("foodtype"));
                        if (Objects.requireNonNull(dataSnapshot.child("foodtype").getValue()).toString().equals("Bevrages")){
                            System.out.println(dataSnapshot.child("foodtype"));
                            VegModel vegModel=dataSnapshot.getValue(VegModel.class);
                            list.add(vegModel);
                        }

                    }
                    vegAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.search,menu);
//
//        MenuItem item=menu.findItem(R.id.search);
//
//        SearchView searchView=(SearchView)item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                processsearch(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                processsearch(s);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void processsearch(String s) {
//
//        FirebaseRecyclerOptions<VegModel> options =
//                new FirebaseRecyclerOptions.Builder<VegModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().orderByChild("name"), VegModel.class)
//                        .build();
//
//        adapter = new VegAdapter(options);
//    }
}

