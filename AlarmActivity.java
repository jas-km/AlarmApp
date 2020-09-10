package com.example.myalarmapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import android.app.AlarmManager;


import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    PendingIntent myPendingIntent;
    private TimePicker myTimePicker;
    Button save,cancel;
    Intent myIntent =new Intent(AlarmActivity.this, AlarmBroadcast.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        //finding views by their id
        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        myTimePicker=(TimePicker) findViewById(R.id.my_time_picker);
        final Calendar cal=Calendar.getInstance();



        //handling the click of the set button
        save=(Button) findViewById(R.id.btn_1);
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                cal.set(Calendar.HOUR_OF_DAY,myTimePicker.getHour());
                cal.set(Calendar.MINUTE,myTimePicker.getMinute());


                //saving the time picked as int values
                int hour=myTimePicker.getHour();
                int min=myTimePicker.getMinute();

                //converting the int values to string
                String hour_string=String.valueOf(hour);
                String min_string=String.valueOf(min);

                //changing time format
                if(hour>12){
                    hour_string=String.valueOf(hour-12);
                }
                if(min<10){
                    min_string="0"+ min;
                }

                Toast.makeText(AlarmActivity.this,"The alarm is set to"+" "+hour_string+":"+min_string,Toast.LENGTH_SHORT).show();

                myIntent.putExtra("extra","on");
                myPendingIntent=PendingIntent.getBroadcast(AlarmActivity.this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),myPendingIntent);

            }
        });


        //handling click of the cancel button
        cancel=(Button) findViewById(R.id.btn_2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmManager != null) {
                    alarmManager.cancel(myPendingIntent);
                    finish();

                    myIntent.putExtra("extra","off");
                    //stopping the ringtone on cancel button click
                    sendBroadcast(myIntent);

                     }

                }
            });

        }

    }










