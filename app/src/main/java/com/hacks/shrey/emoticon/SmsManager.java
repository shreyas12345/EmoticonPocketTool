package com.hacks.shrey.emoticon;

/**
 * Created by shrey on 5/28/16.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class  SmsManager extends BroadcastReceiver {
    private String TAG = SmsManager.class.getSimpleName();
    public String message;


    Context mContext;

    VibrationManager vm = new VibrationManager(Config.context);


    public static String[] morse = MainActivity.morse;
    public static String[] text = MainActivity.text;

    protected long lastSeconds;
    
    static final String answerSign = "#";

    @Override
    public void onReceive(Context context, Intent intent) {

        mContext = context;

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
                    str += "\n";
                }

                // Display the entire SMS Message
                Log.d(TAG, str);
                System.out.println("Str = "  + str);
                System.out.println("message = " +  message);
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
        ArrayList<Integer> response = new ArrayList<>();
        
        numberString = numberString.toLowerCase();

        for (int i = 0; i < numberString.length(); i++){
            if (numberString.charAt(i) ==  '-'){
                response.add(-1);
            }
            else if (numberString.charAt(i) == '.'){
                response.add(-2);
            }
            else if (numberString.charAt(i) == '0'){
                response.add(-3);
            }
            else if (numberString.charAt(i) == 'a'){
                response.add(-4);
            }
            else if (numberString.charAt(i) == 'b'){
                response.add(-5);
            }
            else if (numberString.charAt(i) == 'c'){
                response.add(-6);
            }
            else if (numberString.charAt(i) == 'd'){
                response.add(-7);
            }
            else if (numberString.charAt(i) == 'e'){
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
                vm.vibrate(500, 1000);
            }
            else if((int)response.get(i)==-2){
                vm.vibrate(50, 1000);
            }

            else if((int)response.get(i)==-4){   //a
                vm.vibrate(50, 150);
                vm.vibrate(150, 450);
            }

            else if((int)response.get(i)==-5) {   //b
                vm.vibrate(150, 450);
                vm.vibrate(50, 450);
                vm.vibrate(50, 450);
                vm.vibrate(50, 450);
            }

            else if((int)response.get(i)==-6){   //c
                vm.vibrate(150, 450);
                vm.vibrate(50,  450);
                vm.vibrate(150, 450);
                vm.vibrate(50,  450);
            }

            else if ((int) response.get(i) == -7) {       //d
                vm.vibrate(150, 450);
                vm.vibrate(50,  450);
                vm.vibrate(50, 0);
            }
            else if ((int) response.get(i) == -8) {       //e
                vm.vibrate(50,  450);
            }

            else {
                for (int k = 0; k < (int) response.get(i); k++) {
                    vm.vibrate(300, 1000);
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
                vm.vibrate(50,  500);
            }
            if (vibList.get(i).equals("-")){
                vm.vibrate(500, 1000);
            }
            if (vibList.get(i).equals("/") || vibList.get(i).equals(" ")) {
                vm.vibrate(0, 500);
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
                }
                else if (currentLetter.substring(k, k+1).equals("-")){
                    System.out.println("Long vibration");
                    vibList.add("-");
                }
            }
            vibList.add(" ");
        }
    }
}
