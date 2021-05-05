package com.hotpatch.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Calculator mCal;
    private Button mBtnCalculate;

    //参考
    //https://blog.csdn.net/asialiyazhou/article/details/70495589
    //https://blog.csdn.net/asiaLIYAZHOU/article/details/70495593
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnCalculate = (Button)findViewById(R.id.btnCalculate);

        mBtnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCal == null) {
                    mCal = new Calculator();
                }

                Toast.makeText(MainActivity.this, String.valueOf(mCal.calculate()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
