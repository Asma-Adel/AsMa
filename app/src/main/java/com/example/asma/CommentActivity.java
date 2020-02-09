package com.example.asma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    ListView commentView;
    public static ArrayList<Coment>comments = new ArrayList<>();
    public static CommentAdapter commentAdapter;

    EditText new_comment ;
    ImageView share_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        comments.add( new Coment("Asma","Very Goooooooood :)", R.drawable.post2, true) );
        comments.add(new Coment("Fatma","I Love It <3",R.drawable.post5,false));
        comments.add(new Coment("Ahmed","Ana Hnfo5kooooo :D",R.drawable.post5,false));

        commentAdapter = new CommentAdapter(this,comments);
        commentView = findViewById(R.id.comment_list_view);
        commentView.setAdapter(commentAdapter);

        new_comment = findViewById(R.id.comment_edit_text);
        share_comment = findViewById(R.id.comment_share_btn);

        new_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(new_comment.getText().toString().isEmpty())
                {
                    share_comment.setEnabled(false);
                    share_comment.setImageResource(R.drawable.share);

                }else
                {
                    share_comment.setEnabled(true);
                    share_comment.setImageResource(R.drawable.ic_send);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        share_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comments.add(new Coment("Ahmed",new_comment.getText().toString(),R.drawable.post5,false));
                commentAdapter.notifyDataSetChanged();
                new_comment.setText("");
            }
        });
    }
}
