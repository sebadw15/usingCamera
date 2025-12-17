
package com.example.myapplication;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class infoActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView image;
    private Button pic, go;
    private EditText name, address;
    private RadioButton male, female;
    private Spinner spinner;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        image = findViewById(R.id.imageView);
        pic = findViewById(R.id.button2);
        go = findViewById(R.id.button3);
        name = findViewById(R.id.editTextText);
        address = findViewById(R.id.editTextText2);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
        spinner = findViewById(R.id.spinner);



            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.age, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);



        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==pic) {
                    Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,1);
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameValue = name.getText().toString();
                String addressValue = address.getText().toString();

                if (nameValue.isEmpty() || addressValue.isEmpty()) {
                    Toast.makeText(infoActivity.this, "الرجاء ملء حقل الاسم والعنوان", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (spinner.getSelectedItem() == null) {
                    Toast.makeText(infoActivity.this, "الرجاء اختيار العمر", Toast.LENGTH_SHORT).show();
                    return;
                }
                String selectedAge = spinner.getSelectedItem().toString();


                Intent intent = new Intent(infoActivity.this, MainActivity.class);
                intent.putExtra("name", nameValue);
                intent.putExtra("address", addressValue);
                intent.putExtra("age", selectedAge);

                if (bitmap != null) {
                    intent.putExtra("bitmap", bitmap);
                }

                if (male.isChecked()) {
                    intent.putExtra("gender", "male");
                } else if (female.isChecked()) {
                    intent.putExtra("gender", "female");
                } else {
                    intent.putExtra("gender", "لم يحدد");
                }

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                image.setImageBitmap(bitmap);

            }
        }
    }
}
