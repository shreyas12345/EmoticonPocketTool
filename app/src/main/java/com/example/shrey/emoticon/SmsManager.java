package com.example.shrey.emoticon;

/**
 * Created by shrey on 5/28/16.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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


public class SmsManager extends BroadcastReceiver {
    private String TAG = SmsManager.class.getSimpleName();
    public String message;




    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();
        System.out.println("Yo soy una manzana y tuu");

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

    public void digitsToVibrate(ArrayList response, Context context){
        final Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        for (int i = 0; i < response.size(); i++){
            if ((int)response.get(i)==-1){
                //Vibrate for negative sign
                v.vibrate(1000);
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
}
