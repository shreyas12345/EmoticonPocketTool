package com.hacks.shrey.emoticon;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Brian on 6/11/2016.
 */
public class VibrationManager {

  //  Context mContext = Config.context;
    Context mContext;
    public VibrationManager(Context mContext){
        if (mContext!=null) {
            this.mContext = mContext;
        }
    }

    public void vibrate(int length, int threadLength){
        Vibrator v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(length);
        try {
            Thread.sleep(threadLength);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
