package com.example.mrbank.detecttouch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyService extends Service {
   public  View mDummyView;
   private boolean isRunning;   //Servisi durdurabilmek için
    public static ArrayList<String> touchDetails;    // to show every touch
    private static int activeTime;                   // Active seconds
    private Date lastTouch;


    //private final IBinder mBinder = new MyBinder();
    //private ArrayList<String> list = new ArrayList<String>();

    /*
    public MyService() {

    }*/

    @Override
    public void onCreate() {

        touchDetails = new ArrayList<String>();
        activeTime = 0;
        lastTouch = null;

        Context mContext = getBaseContext();
        WindowManager mwindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                1, /* width */
                1, /* height */
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );
        params.gravity = Gravity.LEFT | Gravity.TOP;


        mDummyView = new LinearLayout(mContext);
        mDummyView.setLayoutParams(params);

        mwindowManager.addView(mDummyView, params);
        isRunning = true;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            mDummyView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (isRunning) {
                        SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss a");
                        String format = s.format(new Date());
                        //Date currentDate = new Date(System.currentTimeMillis());


                        //Touch date added to array
                        touchDetails.add(format);
                        //lastTouch = s;

                        Toast.makeText(getBaseContext(), format , Toast.LENGTH_SHORT).show();

                        return true;
                    } else {
                        Toast.makeText(getBaseContext(), "Service was destroyed", Toast.LENGTH_SHORT).show();
                        mDummyView.setOnTouchListener(null);
                        stopSelf();
                        return true;

                    }


                }
            });




        return Service.START_NOT_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
        //return mBinder;
    }

   /* public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;

        }

    }

    public List<String> getWordList() {
        return list;
    } */




    @Override
    public void onDestroy() {
        isRunning = false;

    }

    // Ikı dokunuş arası 15 saniyeden fazla ise ikinci dokunuştan itibaren yeni seri başlatılır
   /* public int findDifference(Date startDate,Date endDate) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long seconds = different / 1000;

        // Ekran en az 15 saniye sonra otomatik olarak kapatıldığı içn 15 seçildi
        if(seconds > 15 ) {
            activeTime = activeTime + 15;
        }
        else {
            //
        }




    } */



}
