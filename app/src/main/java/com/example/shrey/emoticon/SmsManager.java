package com.example.shrey.emoticon;

/**
 * Created by shrey on 5/28/16.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class  SmsManager extends BroadcastReceiver {
    private String TAG = SmsManager.class.getSimpleName();
    public String message;

    Context mContext;

    public SmsManager(/*Context mContext*/) {
        //   this.mContext = mContext;
    }



    public static String[] morse = MainActivity.morse;/*{".-", "-...",  "-.-.", "-..",  ".", "..-.",
            "--.",   "....",  "..",   ".---", "-.-",  ".-..",
            "--",    "-.",    "---",  ".--.", "--.-", ".-.",
            "...",   "-",    "..-",  "...-", ".--",  "-..-",
            "-.--",  "--..", "/", ".-.-.-", "--..--", "---...",
            "..--..", ".----.", ".-..-.", "-----", ".----", "..---",
            "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-....-"}; */
    public static String[] text = MainActivity.text; /*{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z", " ", ".", ",", ":", "?", "'", "\"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-"};*/


  //  protected Vibrator v = (Vibrator) Config.context.getSystemService(Config.context.VIBRATOR_SERVICE);


    protected long lastSeconds;


    static String answerSign = "#";



    @Override
    public void onReceive(Context context, Intent intent) {

            // Get the data (SMS data) bound to intent
            Bundle bundle = intent.getExtras();

            SmsMessage[] msgs = null;

            String str = "";

            if (bundle != null) {
                // Retrieve the SMS Messages received
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];

                // For every SMS message received
                for (int i=0; i < msgs.length; i++) {
                    // Convert Object array
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    // Sender's phone number
                    str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                    // Fetch the text message
                    str += msgs[i].getMessageBody().toString();
                    message = msgs[i].getMessageBody().toString();
                    // Newline <img src="http://codetheory.in/wp-includes/images/smilies/simple-smile.png" alt=":-)" class="wp-smiley" style="height: 1em; max-height: 1em;">
                    str += "\n";
                }

                // Display the entire SMS Message
                Log.d(TAG, str);
                System.out.println(str);
                if (!message.substring(0, answerSign.length()).equals(answerSign)) {
                    processVibration(message, context);
                }
            }

            // Display the entire SMS Message
            Log.d(TAG, str);
            System.out.println(str);

            if (message.substring(0, answerSign.length()).equals(answerSign)) {
                //cut message so it no have -n
                message = message.substring(answerSign.length(), message.length());
                message = message.replaceAll(" ", "");
                digitsToVibrate(digits(message), context);
            }
        }


    public ArrayList<Integer> digits(String numberString){
        ArrayList<Integer> response = new ArrayList<Integer>();

        for (int i = 0; i < numberString.length(); i++){
            if (numberString.substring(i, i+1).equals("-")){
                response.add(-1);
            }
            else if (numberString.substring(i, i+1).equals(".")){
                response.add(-2);
            }
            else if (numberString.substring(i, i+1).equals("0")){
                response.add(-3);
            }
            else if (numberString.substring(i, i+1).toLowerCase().equals("a")){
                response.add(-4);
            }
            else if (numberString.substring(i, i+1).toLowerCase().equals("b")){
                response.add(-5);
            }
            else if (numberString.substring(i, i+1).toLowerCase().equals("c")){
                response.add(-6);
            }
            else if (numberString.substring(i, i+1).toLowerCase().equals("d")){
                response.add(-7);
            }
            else if (numberString.substring(i, i+1).toLowerCase().equals("e")){
                response.add(-8);
            }
            else{
                response.add(Integer.parseInt(numberString.substring(i, i+1)));
            }
        }

        return response;
    }

    public void digitsToVibrate(ArrayList response, Context context){           //FOR texting answer/number with #"
        final Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        for (int i = 0; i < response.size(); i++){
            if ((int)response.get(i)==-1){
                //Vibrate for negative sign
                v.vibrate(500);
                try {
                    Thread.sleep(1000);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else if((int)response.get(i)==-2){
                v.vibrate(50);
                try {
                    Thread.sleep(1000);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            else if((int)response.get(i)==-4){   //a
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(150);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            else if((int)response.get(i)==-5) {   //b
                v.vibrate(150);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            else if((int)response.get(i)==-6){   //c
                v.vibrate(150);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(150);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            else if ((int) response.get(i) == -7) {       //d
                v.vibrate(150);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                v.vibrate(50);
            }
            else if ((int) response.get(i) == -8) {       //e
                v.vibrate(50);
                try {
                    Thread.sleep(450);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            else {
                for (int k = 0; k < (int) response.get(i); k++) {
                    v.vibrate(300);
                    try {
                        Thread.sleep(1000);                 //1000 milliseconds is one second.
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public String getMessage(){
        return message;
    }

    public static List<String> toMorse (String englishText) {

        englishText = englishText.toUpperCase();

        List<String> finalMorse = new ArrayList<String>();

        String[] englishTextArray = englishText.split("");

        for (int i = 0; i < englishTextArray.length; i++) {
            System.out.println(englishTextArray[i]);
            System.out.println("Hello");
        }
        for (int i = 1; i < englishTextArray.length; i++){
            finalMorse.add(morse[Arrays.asList(text).indexOf(englishTextArray[i])]);
        }

        return finalMorse;
    }


    public void processVibration(String message, Context context){

        final Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);


        List<String> vibList = new ArrayList<String>();

        List<String> convertedMorse = toMorse(message);

        System.out.println(convertedMorse);

        String[] morseToVibrate = new String[convertedMorse.size()];

        for (int l = 0; l < convertedMorse.size(); l++){
            morseToVibrate[l] = convertedMorse.get(l);
            System.out.println(morseToVibrate[l]);
        }

        morseToVibration(morseToVibrate, vibList);

        for (int i = 0; i < vibList.size(); i++){
            if (vibList.get(i).equals(".")){
                //vibrate
                v.vibrate(50);

                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            }
            if (vibList.get(i).equals("-")){
                //vibrate
                v.vibrate(500);

                try {
                    Thread.sleep(1000);                 //was 500
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            if (vibList.get(i).equals("/") || vibList.get(i).equals(" ")) {
                try {
                    Thread.sleep(500);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    public void morseToVibration (String[] morseToVibrate, List<String> vibList){


        for (int i = 0; i < morseToVibrate.length; i++){
            String currentLetter = morseToVibrate[i];

            for (int k = 0; k < currentLetter.length(); k++){
                if (currentLetter.substring(k, k+1).equals(".")) {
                    System.out.println("Short vibration.");
                    vibList.add(".");
                    //                 v.vibrate(50);
                }

                else if (currentLetter.substring(k, k+1).equals("-")){
                    System.out.println("Long vibration");
                    vibList.add("-");
                    //              v.vibrate(200);
                }

                //   else {
                //     System.out.println("Space between vibrations.");

                // }
            }
            vibList.add(" ");
        }
    }


}
