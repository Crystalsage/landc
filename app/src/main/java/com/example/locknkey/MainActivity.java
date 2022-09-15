package com.example.locknkey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MaterialButton first_challenge_button, second_challenge_button, third_challenge_button, fourth_challenge_button, fifth_challenge_button, sixth_challenge_button;
    Map<String, Object> url_data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("colors")
                .document("00ffff");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        url_data = document.getData();
                        Log.d("FIREBASE", "This is your data" + url_data);

                    } else {
                        Toast.makeText(MainActivity.this, "Document's missing! :)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Document get() failed! :)", Toast.LENGTH_SHORT).show();
                }
            }
        });


        first_challenge_button = findViewById(R.id.challenge_1_button);
        second_challenge_button = findViewById(R.id.challenge_2_button);
        third_challenge_button = findViewById(R.id.challenge_3_button);
        fourth_challenge_button = findViewById(R.id.challenge_4_button);
        fifth_challenge_button = findViewById(R.id.challenge_5_button);
        sixth_challenge_button = findViewById(R.id.challenge_6_button);

        first_challenge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = (String) url_data.get("challenge_1");

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}