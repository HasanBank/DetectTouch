package com.example.mrbank.detecttouch;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    public ListView listView;
    ArrayAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.ListView);





    }





    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }


    public void getDetails(View view) {
        try {
            adapter = new ArrayAdapter<>(this, R.layout.activity_listview, MyService.touchDetails);
            listView.setAdapter(adapter);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            Toast.makeText(this,"No Info",Toast.LENGTH_SHORT).show();

        }
    }










}