/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        super.onCreate(savedInstanceState);
        userController.clearUser();
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginListener);

    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {

            EditText usernameText = (EditText)findViewById(R.id.userName);
            String name = usernameText.getText().toString();

            if (name.trim().length() == 0) {
                //Toast.makeText(LoginActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.profile_layout), R.string.enter_valid_username, Snackbar.LENGTH_LONG);
                mySnackbar.show();
            }

            else if (name.trim().length() > 20) {
                //Toast.makeText(LoginActivity.this, "Please enter a name of 20 characters or less", Toast.LENGTH_SHORT).show();
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.profile_layout), R.string.too_long_username, Snackbar.LENGTH_LONG);
                mySnackbar.show();
            }

            else {
                 //Intent returnIntent = getIntent();
                userController.setUser(name);
                habitController.clearHabits();
                //setResult(RESULT_OK, returnIntent);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", name);
                editor.apply();
                //String language = getApplicationContext().getSharedPreferences("language", 0).toString();
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
             }


        }
    };

    @Override
    public void onBackPressed() {
        
    }
}
