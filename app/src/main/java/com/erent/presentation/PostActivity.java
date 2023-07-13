package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.erent.R;
import com.erent.logic.IPostLogic;
import com.erent.logic.PostLogic;
import com.erent.objects.Post;
import com.erent.persistence.stubs.PostPersistence;

public class PostActivity extends AppCompatActivity {

    private IPostLogic postLogic;

    private TextView titleView;
    private TextView rentPeriodView;
    private TextView priceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myIntent = getIntent();
        Post post;

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.post);

        postLogic = new PostLogic(new PostPersistence());

        titleView = this.findViewById(R.id.title);
        rentPeriodView = this.findViewById(R.id.rent_period);
        priceView = this.findViewById(R.id.price);

        post = postLogic.getPostByID(myIntent.getIntExtra("postID", 0));

        titleView.setText(post.getPostName());
        rentPeriodView.setText(post.getRentDuration() + " Days");
        priceView.setText("$" + post.getPrice());
    }

    public void returnToHomepage(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

}