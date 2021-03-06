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
    //ImageView Post_Delete , Post_Love , Post_Fav , Post_Share , Post_Comment ,The_Post;
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

    }


}
