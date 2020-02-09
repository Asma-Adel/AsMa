package com.example.asma;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.example.asma.HomeActivity.adapter;
import static com.example.asma.HomeActivity.posts;

class PostAdapter extends ArrayAdapter<Post> {
    AlertDialog dialog;
    Context Vcontext;
    boolean p = false;
    LayoutInflater layoutInflater;

    private static final String TAG = "PostAdapter";

    public PostAdapter(@NonNull Context context, @NonNull List<Post> objects) {
        super(context, 0, objects);

        Vcontext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            layoutInflater = (LayoutInflater) Vcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.post, parent, false);

        }

        final ImageView myprofileImg = convertView.findViewById(R.id.post_profile_image);
        TextView myusername = convertView.findViewById(R.id.post_username);
        ImageView thepost = convertView.findViewById(R.id.the_post);
        final ImageView love = convertView.findViewById(R.id.post_nolove);
        final ImageView rate = convertView.findViewById(R.id.post_nofav);
        final ImageView delete = convertView.findViewById(R.id.post_delete);
        final ImageView comment = convertView.findViewById(R.id.post_comment);
        final Post currentPost = getItem(position);


        myprofileImg.setImageResource(currentPost.getUser_profile());
        myusername.setText(currentPost.getUsername());
        thepost.setImageResource(currentPost.getPost_image());
        love.setImageResource(currentPost.isLove() ? R.drawable.love : R.drawable.nolove);
        rate.setImageResource(currentPost.isRate() ? R.drawable.fav : R.drawable.nofav);


        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getContext() , CommentActivity.class);
                getContext().startActivity(i);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentPost.setLove(!currentPost.isLove());
                love.setImageResource(currentPost.isLove() ? R.drawable.love : R.drawable.nolove);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Vcontext);
                View mview = layoutInflater.inflate(R.layout.smile, null);

                SmileRating smileRating = (SmileRating) mview.findViewById(R.id.smile_rating);

                smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
                    @Override
                    public void onRatingSelected(int level, boolean reselected) {
                        Toast.makeText(Vcontext, "" + level, Toast.LENGTH_SHORT).show();
                        currentPost.setRate(true);
                        rate.setImageResource(currentPost.isRate() ? R.drawable.fav : R.drawable.nofav);
                        dialog.cancel();
                    }
                });
                alert.setView(mview);
                dialog = alert.create();
                dialog.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posts.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        thepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p) {
                    currentPost.setLove(!currentPost.isLove());
                    love.setImageResource(currentPost.isLove() ? R.drawable.love : R.drawable.nolove);
                }
                p = true;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p = false;
                    }
                }, 150);
            }
        });
        myprofileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PhotoActivity.class);

                BitmapDrawable bitmapDrawable = ((BitmapDrawable) myprofileImg.getDrawable());
                i.putExtra("photo",encodeImage( bitmapDrawable.getBitmap()));

                getContext().startActivity(i);
            }
        });
        return convertView;
    }

    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

}
