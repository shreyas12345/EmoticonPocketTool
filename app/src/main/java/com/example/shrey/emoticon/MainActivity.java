package com.example.shrey.emoticon;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.*;
import android.telephony.SmsManager;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RelativeLayout parentlayout;
    ArrayList<Integer> number = new ArrayList<Integer>();
    int counter;
    int operationcounter = 0;
    int mode = 0;
    float send;
    int questionNumber;
    int multiplechoice;
    int y = 0;
    String phoneNumber;
    ArrayList<Integer> timerList = new ArrayList<Integer>();
    int timeCounter = 0;
    private ArrayList<String> values = new ArrayList<String>();
    Button settings;

    TextView modeText;

    //  ArrayList<Integer> response = new ArrayList<Integer>();

    String[] modeNames = {"Calculator","Share Answers", "Clock", "Morse Messenger"};

    String timeString;

    /*
    Mode 0: Calculator
    Mode 1: Clock
    Mode 2: Multiple Choice
     */
    float firstNumber;
    float secondNumber;

    int shortTapLength = 50;
    int longTapLength = 50;
    int switchingModesLength = 75;
    int dotLength = 50;
    int dashLength = 500;
    int decimalLength = 25;
    int timerEndLength = 5000;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        settings = (Button) findViewById(R.id.button);

        modeText = (TextView)findViewById(R.id.modeText);

        modeText.setText("Mode: " + modeNames[mode]);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        parentlayout = (RelativeLayout) findViewById(R.id.layout);

        parentlayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            vibrate(shortTapLength, 0);
            counter = counter + 1;
            if (counter >= 10) {
                counter = 0;
            }
        }
        });

        parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            vibrate(longTapLength, 0);
            number.add(counter);
            counter = 0;
            return true;
        }
        });


    }

    public int createNumber(ArrayList number) {

        int finalValue = 0;
        int input = 0;
        int x = 0;

        while(x<number.size()){
            input = (Integer)number.get(x);
            finalValue = finalValue * 10 + input;
            x++;
        }
        return finalValue;
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //how to increase counter effectively
        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            String[] modeNames = {"Calculator","Share Answers", "Clock", "Morse Messenger"};

            mode++;

            if (mode > 3) {
                mode = 0;
            }
            modeText.setText("Mode: " + modeNames[mode]);

            if (mode == 0) {
                vibrate(switchingModesLength, 450);
            }
            if (mode == 1) {
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
            }
            if (mode == 2) {
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
            }
            if (mode == 3) {
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
                vibrate(switchingModesLength, 450);
            }
        }

        if (mode == 0) {
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibrate(shortTapLength, 0);
                    counter = counter + 1;
                    if (counter >= 10) {
                        counter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate(longTapLength, 0);
                    number.add(counter);
                    counter = 0;
                    return true;
                }
            });

            if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                if (number.size() != 0) {
                    if (firstNumber == 0) {
                        firstNumber = createNumber(number);
                        operationcounter = 1;
                        counter = 0;
                        number.clear();
                    } else {
                        secondNumber = createNumber(number);
                        counter = 0;
                        number.clear();

                        if (operationcounter == 1) {
                            send = addNumbers(firstNumber, secondNumber);

                            digitsToVibrate(digits(addNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 2) {
                            send = subNumbers(firstNumber, secondNumber);
                            digitsToVibrate(digits(subNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 3) {
                            multiplyNumbers(firstNumber, secondNumber);
                            send = multiplyNumbers(firstNumber, secondNumber);
                            digitsToVibrate(digits(multiplyNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }

                        if (operationcounter == 4) {
                            divideNumbers(firstNumber, secondNumber);
                            send = divideNumbers(firstNumber, secondNumber);
                            digitsToVibrate(digits(divideNumbers(firstNumber, secondNumber)));
                            number.clear();
                            firstNumber = 0;
                        }
                    }
                } else {
                    operationcounter++;
                }
            }

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

                number.clear();
                firstNumber = 0;
                secondNumber = 0;
                operationcounter = 0;
            }
            return true;
        }

        if (mode == 2) {
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibrate(shortTapLength, 0);
                    counter = counter + 1;
                    if (counter >= 10) {
                        counter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate(longTapLength, 0);
                    timerList.add(counter);
                    counter = 0;
                    return true;
                }
            });

            if (timerList.size() != 0) {

                if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                    createNumber(timerList);
                    com.example.shrey.emoticon.Clock.timer(createNumber(timerList) * 1000, getApplicationContext()); //makes the timer in minutes
                    timerList.clear();
                }
            } else {
                if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                    Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    timeString = com.example.shrey.emoticon.Clock.getTime();
                    vibrateTime(v2);
                }
                return true;
            }

            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                timerList.clear();
            }
        }
        if (mode == 1) { //SMS
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibrate(shortTapLength, 0);
                    counter = counter + 1;
                    if (counter >= 10) {
                        counter = 0;
                    }
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    vibrate(longTapLength, 0);
                    number.add(counter);
                    counter = 0;
                    return true;
                }
            });

            if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
                questionNumber = createNumber(number);
                sendSMSMessage();
               number.clear();
                questionNumber = 0;
            }

            if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
                multiplechoice++;
                if(multiplechoice>5){
                    multiplechoice = 1;
                }
                return true;
            }

        }

        if(mode == 3){ //Morse Code Messenger
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator v7 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    values.add(".");
                    Toast.makeText(getApplicationContext(), "dot", Toast.LENGTH_SHORT).show();
                    vibrate(shortTapLength, 0);
                }
            });

            parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    values.add("-");
                    System.out.println(values);
                    vibrate(longTapLength, 0);
                    return true;
                }
            });
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Toast.makeText(getApplicationContext(), "backspace", Toast.LENGTH_SHORT).show();
            int size = values.size();
            if (size != 0) {
                values.remove(size - 1);
                return true;
            } else
                Toast.makeText(getApplicationContext(), "Enter morse code", Toast.LENGTH_LONG).show();
        }

        if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
            Toast.makeText(getApplicationContext(), "space", Toast.LENGTH_SHORT).show();
            values.add("/");
            return true;
        }

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {       //Go to next favorite number
            if (values.size() != 0) {
                sendMorseMessage();
            } else {
                Toast.makeText(getApplicationContext(), "Enter a message please", Toast.LENGTH_SHORT).show();
            }
        }

        return true;

    }

    public ArrayList<Integer> digits(float number){
        String numberString = Float.toString(number);
        ArrayList<Integer> response = new ArrayList<Integer>();
        for (int i = 0; i < numberString.length(); i++){
            if (numberString.substring(i, i+1).equals("-")){
                response.add(-1);
            }
            else if (numberString.substring(i, i+1).equals(".")){
                response.add(-2);
            }
            else if (numberString.substring(i, i+1).equals("0")){
                response.add(10);
            }
            else{
                response.add(Integer.parseInt(numberString.substring(i, i+1)));
            }
        }
        return response;
    }

    public void digitsToVibrate(ArrayList response){
        Vibrator v5 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        for (int i = 0; i < response.size(); i++){
            if ((int)response.get(i)==-1){
                //Vibrate for negative sign
                vibrate(1000, 500);
            }
            if((int)response.get(i)==-2){
                vibrate(50, 500);
            }
            else{
                for (int k = 0; k < (int)response.get(i); k++){
                    vibrate(300, 500);
                }
            }
            vibrate(0, 1000);
        }
    }



    public float addNumbers(float a, float b){
        return a+b;
    }

    public float subNumbers(float a, float b){
        return a - b;
    }

    public float multiplyNumbers(float a, float b) {
        return a * b;
    }

    public float divideNumbers(float a, float b){
        return a/b;
    }


    public void vibrateTime(Vibrator v){
        //"04:51"


        String time = timeString;

        System.out.println(Integer.parseInt(time.substring(0, 1)));

        ArrayList<Integer>timeList = new ArrayList<Integer>();


        for (int i = 0; i < time.length(); i++){
            System.out.println(time.substring(i, i+1));
            if (time.substring(i, i+1).equals(":")){
                timeList.add(-1);
            }
            else if (time.substring(i, i+1).equals("0")){
                timeList.add(10);
            }
            else{
                timeList.add(Integer.parseInt(time.substring(i, i+1)));
            }
        }

        for (int i = 0; i < 5; i++){
//            System.out.println(timeList.get(i));
            if (timeList.get(i)==-1){
                vibrate(500, 500);
            }
            else{
                for (int k = 0; k < timeList.get(i); k++){
                    vibrate(100, 500);
                }
            }
            vibrate(0, 500);
        }




    }

    protected void sendSMSMessage() {
        String message = "";
        try {

            Bundle extras = getIntent().getExtras();
            phoneNumber = extras.getString("phonenumber");
            String phoneNo = phoneNumber;

            if (multiplechoice == 0) {
                message = Integer.toString(questionNumber) + "-- " + Float.toString(send);
            } else if (multiplechoice == 1) {
                message = Integer.toString(questionNumber) + "--" + "A";
                multiplechoice = 0;
            }
            if (multiplechoice == 2) {
                message = Integer.toString(questionNumber) + "--" + "B";
                multiplechoice = 0;
            }
            if (multiplechoice == 3) {
                message = Integer.toString(questionNumber) + "--" + "C";
                multiplechoice = 0;
            }
            if (multiplechoice == 4) {
                message = Integer.toString(questionNumber) + "--" + "D";
                multiplechoice = 0;
            }
            if (multiplechoice == 5) {
                message = Integer.toString(questionNumber) + "--" + "E";
                multiplechoice = 0;
            }
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.",
            "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..", "/", ".-.-.-", "--..--", "---...",
            "..--..", ".----.", ".-..-.", "-----", ".----", "..---",
            "...--", "....-", ".....", "-....", "--...", "---..", "----."};
    public static String[] text = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z", " ", ".", ",", ":", "?", "'", "\"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String toEnglish(List<String> morseText) {

        List<String> newMorseText = new ArrayList<String>();

        String word = "";
        for (int i = 0; i < morseText.size(); i++) {

            if (morseText.get(i) != "/") {
                word += morseText.get(i);
                System.out.println(word);
            }
            if (morseText.get(i).equals("/") || i == morseText.size() - 1) {
                newMorseText.add(word);
                word = "";
            }
        }

        morseText = newMorseText;

        StringBuilder sb = new StringBuilder();
        String finalMessage = "";

        for (int i = 0; i < morseText.size(); i++) {       //go through user inputted morse code
            for (int j = 0; j < morse.length; j++) {
                if (morseText.get(i).equals(morse[j])) {
                    sb.append(text[j]);
                }
            }
        }

        finalMessage = sb.toString();

        return finalMessage;
    }


    protected void sendMorseMessage() {
        Log.i("Send SMS", "");
        //String phoneNo = txtphoneNo.getText().toString();
        try {

           Bundle extras = getIntent().getExtras();
            phoneNumber = extras.getString("phonenumber");
            String phoneNo = phoneNumber;
            System.out.println(values);
            String message = toEnglish(values);
            System.out.println(message);



            android.telephony.SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
           // Toast.makeText(getApplicationContext(), "Please Enter Your Numbers in the Settings.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        values.clear();

    }

    public void vibrate(int length, int threadLength){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(length);
        try {
            Thread.sleep(threadLength);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}



