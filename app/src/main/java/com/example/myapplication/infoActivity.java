package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class infoActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView image;
    private Button pic,go;
    private EditText name,address;
    private RadioButton male,female;
    private boolean f,m;
    private Spinner spinner;
    private String name1,address1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);

        pic=findViewById(R.id.button2);
        name=findViewById(R.id.editTextText);
        address=findViewById(R.id.editTextText2);
        male=findViewById(R.id.radioButton);
        female=findViewById(R.id.radioButton2);
        spinner=findViewById(R.id.spinner);
        name1 = name.getText().toString();
        address1 = address.getText().toString();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        String selectedLevel = spinner.getSelectedItem().toString();


        pic.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             if (view == pic) {
                 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(intent, 1);
             }
         }
     });



        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m = true;
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = true;
            }
        });


             go.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(infoActivity.this, MainActivity.class);
                     intent.putExtra("bitmap", bitmap);
                     intent.putExtra("name", name1);
                     intent.putExtra("address", address1);
                     if (f) {
                         intent.putExtra("gender", "female");
                     }
                     if (m) {
                         intent.putExtra("gender", "male");
                     }
                     startActivity(intent);
                 }

             });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            bitmap=data.getParcelableExtra("data");

        }
    }
}