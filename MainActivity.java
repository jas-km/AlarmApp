package com.example.myalarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_1,btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1=(Button) findViewById(R.id.btn1);
        btn_2=(Button) findViewById(R.id.btn2);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicked();

                }
             public void onClicked(){
                Intent intent= new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
             }

        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent2);
            }
        });
    }
}