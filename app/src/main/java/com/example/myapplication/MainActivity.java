package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;

    ImageView image;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        image = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        textView=findViewById(R.id.textView);


        Intent intent=getIntent();


        image.setImageBitmap(getIntent().getParcelableExtra("bitmap"));

        textView.setText("hello, your name is:"+getIntent().getStringExtra("name") + "\n" +"you live in:"+ getIntent().getStringExtra("address")+
                "\n"+"your gender is:"+getIntent().getStringExtra("gender")+"\n"+"and your age is:"+getIntent().getStringExtra("age"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, infoActivity.class);
                startActivity(intent);
            }
        });



    }

}