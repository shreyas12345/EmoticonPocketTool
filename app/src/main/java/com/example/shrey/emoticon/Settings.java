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
    EditText number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveButton = (Button)findViewById(R.id.save);
        number1 = (EditText) findViewById(R.id.number1);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stuff = number1.getText().toString();

                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("phonenumber", stuff);
                startActivity(intent);
            }
        });


        ArrayList<String> phoneNumberList = new ArrayList<String>();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = number1.getText().toString();

               /* try {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/phonenumbers.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fileWriter = new FileWriter(file.getName(), true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(number);
                    bufferedWriter.close();


                }catch(Exception e){
                    e.printStackTrace();
                }

                */

            }
        });

        /*
        try {
            BufferedReader br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory()+"phonenumbers.txt"));

            String line = br.readLine();

            while (line != null) {
                phoneNumberList.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (phoneNumberList.size()>0) {
            number1.setText(phoneNumberList.get(0));
        }

        */

    }
}