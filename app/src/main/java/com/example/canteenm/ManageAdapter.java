package com.example.canteenm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.myViewHolder>{

    final Context context;
    final List<VegModel> list;


    public ManageAdapter(Context context, List<VegModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ManageAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAdapter.myViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.foodimg);
        holder.foodname.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.type.setText(list.get(position).getFoodtype());

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.foodimg.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1000)
                        .create();

                //dialogPlus.show();

                View view1 = dialogPlus.getHolderView();

                EditText name = view1.findViewById(R.id.txtName);
                EditText price = view1.findViewById(R.id.txtPrice);

                Button button = view1.findViewById(R.id.update);


                name.setText(list.get(position).getName());

                price.setText(list.get(position).getPrice());

                String FoodName = String.valueOf(name.getText());
                System.out.println(FoodName);
                dialogPlus.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // System.out.println("update");
                        Map<String,Object>map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("price",price.getText().toString());

                        String NewName = name.getText().toString();
                        String NewPrice = price.getText().toString();

                        System.out.println(NewPrice);
                        System.out.println(NewName);
                        System.out.println(FoodName);
//                        FirebaseDatabase.getInstance().getReference().child("foodname")
//                                .child((list(position()).getKey()).updateChildren(map)
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("foodname");

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot snap : snapshot.getChildren())
                                {
                                   // System.out.println(snap.child("name"));
                                    if (Objects.equals(snap.child("name").getValue(), FoodName))
                                    {
                                        System.out.println(snap);
                                        HashMap<String , Object> map = new HashMap<String,Object>();
                                        map.put("name",NewName);
                                        map.put("price",NewPrice);
                                        map.put("date", Objects.requireNonNull(snap.child("date").getValue()).toString());
                                        map.put("image", Objects.requireNonNull(snap.child("image").getValue()).toString());
                                        map.put("foodtype", Objects.requireNonNull(snap.child("foodtype").getValue()).toString());
                                        map.put("time", Objects.requireNonNull(snap.child("time").getValue()).toString());
                                        map.put("key", Objects.requireNonNull(snap.child("key").getValue()).toString());
                                        map.put("status", Objects.requireNonNull(snap.child("status").getValue()).toString());
                                        System.out.println(map);
                                        databaseReference.child(Objects.requireNonNull(snap.getKey())).setValue(map);

                                        Toast.makeText(context.getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });

            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.foodname.getContext());
                builder.setTitle(" sure want to delete ?");
                builder.setMessage("deleted message can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("foodname");

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot snap : snapshot.getChildren()){
//                                    System.out.println(snap);
                                        if (Objects.requireNonNull(snap.child("name").getValue()).toString() == list.get(position).getName()){
                                            System.out.println(snap.getKey());
                                            databaseReference.child(Objects.requireNonNull(snap.getKey())).removeValue();
//                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        Toast.makeText(holder.foodname.getContext(), "deleted", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.foodname.getContext(), "canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView foodimg;
        TextView foodname;
        TextView price;
        TextView type;
        ImageButton imageButton1;
        ImageButton imageButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton1 = itemView.findViewById(R.id.update);
            imageButton = itemView.findViewById(R.id.delete);
            foodimg = itemView.findViewById(R.id.img1);
            foodname = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            type = itemView.findViewById(R.id.foodtype);

        }
    }
}
