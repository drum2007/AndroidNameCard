package com.example.navadon.androidnamecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initinstance();
    }

    private void initinstance() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btn1) {
            startActivity(new Intent(this, VorasonActivity.class));
        }
        if (v == btn2) {
            startActivity(new Intent(this, WarakornActivity.class));
        }
        if (v == btn3) {
            startActivity(new Intent(this, FrontActivity.class));
        }

    }
}
