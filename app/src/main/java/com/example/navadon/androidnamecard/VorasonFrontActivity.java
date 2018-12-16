package com.example.navadon.androidnamecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VorasonFrontActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView vorasonName;
    private ImageButton imgbtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("vorason");

    private final String TAG = "Fuck you android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorason_front);
        imgbtn = findViewById(R.id.imageV);

        initinstance();

        imgbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == imgbtn) {
            startActivity(new Intent(this, VorasonActivity.class));
            finish();
        }
    }

    private void initinstance() {

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String name = dataSnapshot.child("name").getValue(String.class);

                vorasonName = findViewById(R.id.name1);
                vorasonName.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
