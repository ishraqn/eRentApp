package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.erent.R;
import com.erent.application.Services;
import com.erent.logic.IBookmarkLogic;
import com.erent.logic.IPostLogic;
import com.erent.logic.IRentalLogic;
import com.erent.logic.PostLogic;
import com.erent.logic.RentalLogic;
import com.erent.objects.Post;
import com.erent.persistence.stubs.PostPersistence;

public class PostActivity extends AppCompatActivity {

    private IPostLogic postLogic;
    private IBookmarkLogic bookmarkLogic;
    private IRentalLogic rentalLogic;
    private Post post;

    private TextView titleView;
    private TextView rentPeriodView;
    private TextView priceView;
    private TextView descriptionView;
    
    private Button rentButton;
    private Button deleteButton;
    private ImageButton bookmarkButton;
    private boolean bookmarked;
    private boolean rented;
    private boolean isRenting; //is this user the one renting it
    private boolean bookmarkedChanged = false;
    private boolean rentedChanged = false;
    private boolean onBookmarkPage;
    private boolean onRentalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent myIntent = getIntent();

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.post);

        postLogic = Services.getPostLogic();
        bookmarkLogic = Services.getBookmarkLogic();
        rentalLogic = Services.getRentalLogic();
        post = postLogic.getPostByID(myIntent.getIntExtra("postID", 0));
        onBookmarkPage = myIntent.getBooleanExtra("onBookmarkPage", false);
        onRentalPage = myIntent.getBooleanExtra("onRentalPage", false);
        bookmarked = bookmarkLogic.bookmarked(post.getPostID());
        rented = post.getIsRental();
        isRenting = rentalLogic.isUserRenting(post, Services.getCurrentUser());

        titleView = this.findViewById(R.id.title);
        rentPeriodView = this.findViewById(R.id.rent_period);
        priceView = this.findViewById(R.id.price);
        descriptionView = this.findViewById(R.id.description);
        
        rentButton = this.findViewById(R.id.buttonRent);
        deleteButton = this.findViewById(R.id.buttonDelete);
        bookmarkButton = this.findViewById(R.id.buttonBookmark);

        if (post.getPostedBy().equals(Services.getCurrentUser())) {
            rentButton.setVisibility(View.GONE);
            bookmarkButton.setVisibility(View.GONE);
        } else {
            deleteButton.setVisibility(View.GONE);

            //if someone is renting it and you are not that person get rid of the button
            if(rented && !isRenting) {
                rentButton.setVisibility(View.GONE);
            }

            if(bookmarked) {
                bookmarkButton.setImageResource(R.drawable.icon_bookmark_filled);
            }

            if(rented) {
                rentButton.setText("Return");
            } else {
                rentButton.setText("Rent");
            }
        }

        titleView.setText(post.getPostName());
        rentPeriodView.setText(post.getRentDuration() + " Days");
        priceView.setText("$" + post.getPrice());
        descriptionView.setText(post.getDescription());
    }

    public void returnToHomepage(View view) {
        /* If the bookmark status changed and we were on the bookmark page we need to reload it when returning */
        if(bookmarkedChanged && onBookmarkPage) {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);

            switchActivityIntent.putExtra("onBookmarkPage", onBookmarkPage);
            switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(switchActivityIntent);
        } else if(rentedChanged && onRentalPage) {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);

            switchActivityIntent.putExtra("onRentalPage", onRentalPage);
            switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(switchActivityIntent);
        } else {
            /* otherwise just go back to the last page */
            finish();
        }
    }

    public void deletePost (View view) {
        boolean deleted = postLogic.deletePost(post.getPostID());

        if (deleted) {
            Intent switchActivityIntent = new Intent(this, MainActivity.class);

            switchActivityIntent.putExtra("postDeleted", true);
            switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(switchActivityIntent);
        }
    }

    public void bookmark (View view) {
        if(bookmarked) {
            boolean deleted = bookmarkLogic.deleteBookmark(post.getPostID());

            if(deleted) {
                bookmarked = false;
                bookmarkedChanged = !bookmarkedChanged;
                bookmarkButton.setImageResource(R.drawable.icon_bookmark_unfilled);
            }
        } else {
            boolean created = bookmarkLogic.createBookmark(post.getPostID());

            if(created) {
                bookmarked = true;
                bookmarkedChanged = !bookmarkedChanged;
                bookmarkButton.setImageResource(R.drawable.icon_bookmark_filled);
            }
        }
    }

    public void rent(View view)
    {
        if(rented) {
            boolean deleted = rentalLogic.deleteRental(post.getPostID());

            if(deleted) {
                rented = false;
                rentedChanged = !rentedChanged;
                rentButton.setText("Rent");
            }
        } else {
            boolean created = rentalLogic.createRental(post.getPostID());

            if(created) {
                rented = true;
                rentedChanged = !rentedChanged;
                rentButton.setText("Return");
            }
        }
    }
}