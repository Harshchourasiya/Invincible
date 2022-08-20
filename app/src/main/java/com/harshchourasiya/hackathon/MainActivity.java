package com.harshchourasiya.hackathon;

import static com.harshchourasiya.hackathon.helper.DataFromSP.getUserIdFromSp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.harshchourasiya.hackathon.activities.HomeActivity;
import com.harshchourasiya.hackathon.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIfUserIsLogin();
    }


    private void checkIfUserIsLogin() {
        if (getUserIdFromSp(this) == null) {
            openLoginActivity();
        } else {
            openHomeActivity();
        }
    }

    private void openLoginActivity() {
        openActivity(LoginActivity.class);
    }

    private void openHomeActivity() {
        openActivity(HomeActivity.class);
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}