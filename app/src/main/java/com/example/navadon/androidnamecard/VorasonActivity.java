package com.example.navadon.androidnamecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VorasonActivity extends AppCompatActivity {
    private TextView teltv, emailtv, fbtv, igtv;
    private View.OnClickListener onClickListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("vorason");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorason);
        initOnClickListener();
        findViewById(R.id.layoutInfoV).setOnClickListener(onClickListener);
        initinstance();
    }

    private void initOnClickListener() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.layoutInfoV:
                        startVorasonFront();
                        break;
                }
            }
        };
    }

    private void startVorasonFront() {
        startActivity(new Intent(this, VorasonFrontActivity.class));
        finish();
    }


    private void initinstance() {

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String tel = dataSnapshot.child("tel").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String fb = dataSnapshot.child("fb").getValue(String.class);
                String ig = dataSnapshot.child("ig").getValue(String.class);

                teltv = findViewById(R.id.tel);
                emailtv = findViewById(R.id.email);
                fbtv = findViewById(R.id.fb);
                igtv = findViewById(R.id.ig);

                teltv.setText(tel);
                emailtv.setText(email);
                fbtv.setText(fb);
                igtv.setText(ig);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}
