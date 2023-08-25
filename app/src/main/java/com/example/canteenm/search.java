package com.example.canteenm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.google.firebase.database.DatabaseReference;

public class search extends AppCompatActivity {

    private AutoCompleteTextView searchb;
//    private ImageButton searchbtn;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}