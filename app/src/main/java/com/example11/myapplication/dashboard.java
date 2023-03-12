package com.example11.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;



public class dashboard extends AppCompatActivity {


    Button btnsignout, ime;
    FirebaseAuth mAuth;
    TextView field1, field2, field4, field5, field6, field3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//----------------------------------------------------------------------

        ime = findViewById(R.id.move);
        ime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,Dashboard2.class);
                startActivity(intent);
            }
        });
//--------------------------------------------------------------------------



        //TextView
        field1 = findViewById(R.id.stateC);
        field2 = findViewById(R.id.stateV);
        field3 = findViewById(R.id.stateW);
        field4 = findViewById(R.id.stateR);
        field5 = findViewById(R.id.apparentState);
        field6 = findViewById(R.id.factorState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mRoot = FirebaseDatabase.getInstance();
        DatabaseReference mRootRef = mRoot.getReference();

        mRootRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    Map map = (Map)snapshot.getValue();
                    String current = map.get("Current").toString();
                    String voltage = map.get("Voltage").toString();
                    String kilowatt = map.get("KWh").toString();
                    String Real = map.get("Real Power").toString();
                    String apparent = map.get("Apparent Power").toString();
                    String factor = map.get("Power Factor").toString();

                    field1.setText(current);
                    field2.setText(voltage);
                    field3.setText(kilowatt);
                    field4.setText(Real);
                    field5.setText(apparent);
                    field6.setText(factor);

                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnsignout = findViewById(R.id.button7);

        btnsignout.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(dashboard.this, "Signing out...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(dashboard.this, MainActivity.class));
        });






    }
}