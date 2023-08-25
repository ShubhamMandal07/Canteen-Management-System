package com.example.canteenm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class adminlogin extends AppCompatActivity {

    Button AdminLogin;
    String AdminEmail;
    String AdminPassword;
    DatabaseReference database;

    EditText Email,Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        AdminLogin = findViewById(R.id.AdminLogin);
        Email = findViewById(R.id.email);
        Pass = findViewById(R.id.Password);


        database = FirebaseDatabase.getInstance().getReference();



        AdminLogin.setOnClickListener(view -> {

            String email = Email.getText().toString();
            String pass  = Pass.getText().toString();

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   AdminEmail = snapshot.child("adminEmail").getValue().toString();
                   AdminPassword = snapshot.child("adminPass").getValue().toString();
                    System.out.println(AdminEmail);
                    System.out.println(AdminPassword);

                    if(AdminEmail.equals(email))
                    {
                        if(AdminPassword.equals(pass))
                        {
                            startActivity(new Intent(getApplicationContext(),adminpanel.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Admin Password is Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Admin Email is Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });
    }
}