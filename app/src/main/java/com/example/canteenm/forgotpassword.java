package com.example.canteenm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private EditText emailText;
    private Button resetB;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        emailText = (EditText) findViewById(R.id.email);
        resetB = (Button) findViewById(R.id.reset);

        auth = FirebaseAuth.getInstance();

        resetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetB();

            }
        });
    }
    private void resetB(){
        String email = emailText.getText().toString().trim();

        if (email.isEmpty()){
            emailText.setError("Email is required!");
            emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please provide a valid Email");
            emailText.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(forgotpassword.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(forgotpassword.this, "Try again somethig wrong happened!", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}


