package com.example.asma;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    ImageView Post_Delete , Post_Love , Post_Fav , Post_Share , Post_Comment ,The_Post;
    boolean love;
    public static ArrayList<Post> posts = new ArrayList<>();
    private CircleImageView Post_Profile_Img;
    public static PostAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        posts.add( new Post("Asma",R.drawable.post5 , R.drawable.post5 , true , false) );
        posts.add( new Post("ahmed",R.drawable.post1 , R.drawable.post1 , false , false) );
        posts.add( new Post("mohamed",R.drawable.post2 , R.drawable.post2 , true , false) );
        posts.add( new Post("yasmeen",R.drawable.post3 , R.drawable.post3 , true , false) );
        posts.add( new Post("ay7aga",R.drawable.post4 , R.drawable.post4 , true , false) );

        adapter = new PostAdapter(this , posts);

        listView = findViewById(R.id.home_list_view);

        listView.setAdapter(adapter);


        love = false;
        Post_Profile_Img = findViewById(R.id.post_profile_image);
        Post_Delete = findViewById(R.id.post_delete);
        Post_Delete = findViewById(R.id.post_delete);
        Post_Fav = findViewById(R.id.post_nofav);
        Post_Love = findViewById(R.id.post_nolove);
        Post_Share = findViewById(R.id.post_share);
        Post_Comment = findViewById(R.id.post_comment);
        The_Post = findViewById(R.id.the_post);



        Post_Profile_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bitmapDrawable =((BitmapDrawable)Post_Profile_Img.getDrawable());
                Intent photoActivity = new Intent(HomeActivity.this,PhotoActivity.class);
                photoActivity.putExtra("photo",encodeImage(bitmapDrawable.getBitmap()));
                startActivity(photoActivity);
            }
        });




    }

    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }
}
    /*
     * convert Bitmap to String

    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


    /**
     * to convert string to Bitmap
     **
    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }*/

