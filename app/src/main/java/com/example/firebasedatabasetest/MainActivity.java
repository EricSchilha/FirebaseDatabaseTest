//SOURCES
//Setting up Firebase Database: https://firebase.google.com/docs/database/android/start/
//
//
//
//
//
//

package com.example.firebasedatabasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.getReference("message");
    private static final String TAG = "MainActivity";
    String sValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRef.setValue("Hello, World!");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TextView textView = (TextView) findViewById(R.id.textView);
                sValue = dataSnapshot.getValue(String.class);
                if(sValue != textView.getText().toString()) {
                    textView.setText(sValue);
                }
                Log.d(TAG, "Value is: " + sValue);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        sValue = editText.getText().toString();
        myRef.setValue(sValue);
    }

// Read from the database

}
