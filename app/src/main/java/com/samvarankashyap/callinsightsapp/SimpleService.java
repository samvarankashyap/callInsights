package com.samvarankashyap.callinsightsapp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleService extends Service {
    private static String TAG = "Simple.Service.Tag";
    public static final long NOTIFY_INTERVAL = 10 * 1000; // 10 seconds
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    public SimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    //@Override
    //public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
    //    super.onStart(intent, startId);
   // }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleStart(intent, startId);
        //return START_NOT_STICKY;
        return START_STICKY;
    }

    private void handleStart(Intent intent, int startId) {
        Log.d(TAG, "SimpleService started buddy");
        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
        //this.stopSelf();
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d(TAG, "FirstService destroyed");
    }

    private class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
                    Toast.makeText(getApplicationContext(), getDateTime(),
                            Toast.LENGTH_SHORT).show();
                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }

    }
}
