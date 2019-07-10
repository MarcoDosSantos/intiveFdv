package com.example.fdvapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fdvapp.R;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewDetail;
    TextView tv_username, tv_first, tv_last, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        imageViewDetail = findViewById(R.id.image_view_detail);
        tv_username = findViewById(R.id.text_view_username_detail);
        tv_first = findViewById(R.id.text_view_firstname_detail);
        tv_last = findViewById(R.id.text_view_lastname_detail);
        tv_email = findViewById(R.id.text_view_email_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String userName = bundle.getString("userName");
        String email = bundle.getString("email");
        String largeImageUrl =  bundle.getString("largeImageUrl");

        tv_username.setText(userName);
        tv_username.animate().alpha(1f).setDuration(3000);
        tv_first.setText(firstName);
        tv_first.animate().alpha(1f).setDuration(4000);
        tv_last.setText(lastName);
        tv_last.animate().alpha(1f).setDuration(4000);
        tv_email.setText(email);
        tv_email.animate().alpha(1f).setDuration(5000);

        Glide.with(this).load(largeImageUrl).fitCenter().into(imageViewDetail);
        imageViewDetail.animate().alpha(1f).setDuration(2000);
    }
}
