package com.erent.presentation;

import android.content.Intent;
import android.os.Bundle;
import com.erent.R;

import com.erent.application.Services;
import com.erent.logic.IUserLogic;
import com.erent.logic.UserLogic;
import com.erent.objects.User;
import com.erent.persistence.stubs.PostPersistence;
import com.erent.persistence.stubs.UserPersistence;
import com.erent.persistence.utils.DBHelper;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private IUserLogic uLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login);

        DBHelper.copyDatabaseToDevice(this);

        uLogic = new UserLogic(Services.getUserPersistence());
    }

    public void login(View view) {
        EditText usernameField;
        EditText passwordField;
        String username;
        String password;

        /* get the fields the text is typed into */
        usernameField = (EditText) findViewById(R.id.inputButtonUsername);
        passwordField = (EditText) findViewById(R.id.inputButtonPassword);

        /* get the text from the fields */
        username = usernameField.getText().toString();
        password = passwordField.getText().toString();

        if(uLogic.authenticateUser(username, password)) {
            Services.setCurrentUser(username);

            /* Switch to the homepage once logged in */
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        } else {
            usernameField.setError("Incorrect username and/or password");
        }
    }

    public void goToSignUp(View view) {
        Intent switchActivityIntent = new Intent(this, SignUpActivity.class);
        startActivity(switchActivityIntent);
    }
}