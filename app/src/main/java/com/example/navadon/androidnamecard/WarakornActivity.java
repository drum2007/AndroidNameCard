package com.example.navadon.androidnamecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WarakornActivity extends AppCompatActivity {
    private TextView teltv, emailtv, fbtv, igtv;
    private View.OnClickListener onClickListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("warakorn");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warakorn);
        initOnClickListener();
        findViewById(R.id.layoutInfoW).setOnClickListener(onClickListener);
        initinstance();
    }

    private void initOnClickListener() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.layoutInfoW:
                        startWarakornFront();
                        break;
                }
            }
        };
    }

    private void startWarakornFront() {
        startActivity(new Intent(this, WarakornFrontActivity.class));
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

                teltv = findViewById(R.id.telw);
                emailtv = findViewById(R.id.emailw);
                fbtv = findViewById(R.id.fbw);
                igtv = findViewById(R.id.igw);

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
