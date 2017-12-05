package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.example.cmput301f17t30.habitrabbit.Activities.LoginActivity;
import com.robotium.solo.Solo;

import static com.example.cmput301f17t30.habitrabbit.Activities.MainActivity.userController;

/**
 * Created by gaoxin on 2017/12/1.
 */

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public LoginTest(){
        super(LoginActivity.class);
    }

    public void setUp()throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testLogin(){
        solo.assertCurrentActivity("Not User!", LoginActivity.class);
        solo.enterText((EditText) solo.getView(R.id.userName), "New User");
        solo.clickOnView((Button) solo.getView(R.id.loginButton));
        solo.sleep(2000);
        assertTrue(userController.getUsername().equals("New User"));
        solo.clickOnView((Button) solo.getView(R.id.loginButton));
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
