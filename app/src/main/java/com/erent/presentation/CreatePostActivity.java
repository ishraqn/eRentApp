package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.erent.R;
import com.erent.application.Services;
import com.erent.logic.IPostLogic;
import com.erent.logic.PostLogic;
import com.erent.objects.Post;

public class CreatePostActivity extends AppCompatActivity {

    IPostLogic postLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.create_post);

        postLogic = Services.getPostLogic();
    }

    public void createPost(View view) {
        EditText titleField;
        EditText descriptionField;
        EditText priceField;
        EditText rentalDaysField;
        String title;
        String description;
        String priceString;
        String rentalDaysString;
        int price;
        int rentalDays;

        /* get input fields */
        titleField = (EditText) findViewById(R.id.inputButtonTitle);
        descriptionField = (EditText) findViewById(R.id.inputButtonDescription);
        priceField = (EditText) findViewById(R.id.inputButtonPrice);
        rentalDaysField = (EditText) findViewById(R.id.inputButtonDays);

        /* get text from input fields */
        title = titleField.getText().toString();
        description = descriptionField.getText().toString();
        priceString = priceField.getText().toString();
        rentalDaysString = rentalDaysField.getText().toString();

        boolean valid = true;

        /* ensure that stuff was actually typed in */
        if(title.length() == 0) {
            titleField.setError("Enter a title");
            valid = false;
        }
        if (priceString.length() == 0){
            priceField.setError("Enter a price");
            valid = false;
        }
        if (rentalDaysString.length() == 0){
            rentalDaysField.setError("Enter a rental period");
            valid = false;
        }

        if(valid) {
            price = Integer.parseInt(priceString);
            rentalDays = Integer.parseInt(rentalDaysString);

            /* create the post */
            Post newPost = postLogic.createNewPost(title,description, price, rentalDays);

            /* if the post was created switch back to the homepage */
            if(newPost != null) {
                Intent switchActivityIntent = new Intent(this, MainActivity.class);
                switchActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(switchActivityIntent);
            } else {
                titleField.setError("Post was not created");
            }
        }
    }
}
