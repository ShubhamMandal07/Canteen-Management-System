package com.example.canteenm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText email;
    EditText Password;
    TextView forgotPassword, signup;
    Button loginB;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        Password=(EditText) findViewById(R.id.Password);
        forgotPassword=(TextView) findViewById(R.id.forgotpassword);
        loginB=(Button) findViewById(R.id.reset);
        signup=(TextView) findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();

            loginB.setOnClickListener(view -> {
                login();
        });

            forgotPassword.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), forgotpassword.class));
            });
            signup.setOnClickListener(view ->{
                startActivity(new Intent(getApplicationContext(), register.class));
            });

    }

    private void login() {
        String useremail=email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (useremail.isEmpty()){
            email.setError(" user email is required");
            email.requestFocus();

            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
            email.setError("please enter valid email");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            Password.setError(" user password is required");
            Password.requestFocus();
            return;
        }
        if(password.length()<6){
            Password.setError("Min password length is 6 character");
            Password.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(useremail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        Intent i=new Intent(getApplicationContext(),Splash2.class);
                        startActivity(i);

                    }else{

                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(),"check your email to verify your account",Toast.LENGTH_LONG).show();

                    }

                    Intent i=new Intent(getApplicationContext(),Splash2.class);
                    startActivity(i);

                }else {

                    Toast.makeText(getApplicationContext(), "Failed to login please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
