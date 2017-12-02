package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Created by gaoxin on 2017/12/1.
 */

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public LoginTest(){
        super(com.example.cmput301f17t30.habitrabbit.LoginActivity.class);
    }

    public void setUp()throws Exception{
        super.setUp();
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
        super.tearDown();
        solo.finishOpenedActivities();
    }

}
