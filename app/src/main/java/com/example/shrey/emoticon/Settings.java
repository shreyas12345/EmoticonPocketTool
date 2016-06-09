package com.example.shrey.emoticon;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    Button saveButton;
    Button guideButton;
    EditText number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveButton = (Button) findViewById(R.id.save);
        number1 = (EditText) findViewById(R.id.number1);
        guideButton = (Button)findViewById(R.id.guide);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stuff = number1.getText().toString();
                Intent intent2 = new Intent(Settings.this, MainActivity.class);
                intent2.putExtra("phonenumber", stuff);
                System.out.println("I am here");
                startActivity(intent2);
            }
        });

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, Manual.class));
            }
        });
    }
}