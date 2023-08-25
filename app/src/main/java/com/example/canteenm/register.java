package com.example.canteenm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class register extends AppCompatActivity {
    EditText useremail;
    EditText userpass;
    EditText username;
    EditText usernumber;
    EditText confirmpass;
    TextView login;
    Button button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        useremail = (EditText) findViewById(R.id.email);
        userpass=(EditText) findViewById(R.id.Password);
        confirmpass=(EditText)findViewById(R.id.confirmPassword);
        username=(EditText)findViewById(R.id.name);
        usernumber=(EditText)findViewById(R.id.number);
        button=(Button)findViewById(R.id.reset);
        login=(TextView)findViewById(R.id.back2);
        mAuth = FirebaseAuth.getInstance();

button.setOnClickListener(view ->{
    registerUser();
});

        login.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(), login.class));
        });

    }



    private void registerUser() {


        String  email =useremail.getText().toString().trim();
        String passw=userpass.getText().toString().trim();
        String cpass=confirmpass.getText().toString().trim();
        String name =username.getText().toString().trim();
        String number=usernumber.getText().toString().trim();

        if (name.isEmpty()){
            username.setError(" user name is required");
            username.requestFocus();
            return;
        }
        if (email.isEmpty()){
            useremail.setError(" user email is required");
            useremail.requestFocus();

            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            useremail.setError("please enter valid email");
            useremail.requestFocus();
            return;
        }
        String[] text = email.split("@");
        System.out.println(text[1]);
        if (!Objects.equals(text[1], "chowgules.ac.in")){
            useremail.setError("please enter your chowgules email");
            useremail.requestFocus();
            return;
        }

        if (passw.isEmpty()){
            userpass.setError(" user password is required");
            userpass.requestFocus();
            return;
        }
        if(passw.length()<6){
            userpass.setError("Min password length is 6 character");
            userpass.requestFocus();
            return;
        }
        if (!passw.equals(cpass)){
            userpass.setError("please enter same password");
            confirmpass.setError("plz");
            userpass.requestFocus();

            return;
        }
        if (number.isEmpty()){
            usernumber.setError(" user number is required");
            usernumber.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,passw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "User with this email already exist.", Toast.LENGTH_SHORT).show();

                        }else if(task.isSuccessful()) {
                            users users = new users(name,email,number);

//                            Query checkUserName = exist.child("userName").equalTo(email);
//                            exist =FirebaseDatabase.getInstance("Users").equals("email")
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Sucessfully registered", Toast.LENGTH_LONG).show();

                                                //

                                            } else  {
                                                Toast.makeText(getApplicationContext(), "please enter valid email and password or  try again!", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });



    }
}
