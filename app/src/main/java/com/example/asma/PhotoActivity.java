package com.example.asma;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoActivity extends AppCompatActivity {

    ImageView Photo_Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Photo_Activity = findViewById(R.id.photo_activity);


        String img  = getIntent().getStringExtra("photo");

        Toast.makeText(this, "" + img , Toast.LENGTH_SHORT).show();

        Photo_Activity.setImageBitmap( StringToBitMap(img) );
    }

    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
}
}
