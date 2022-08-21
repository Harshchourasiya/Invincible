package com.harshchourasiya.hackathon.activities;

import static com.harshchourasiya.hackathon.MainActivity.TAG;
import static com.harshchourasiya.hackathon.helper.DataFromSP.setUserIdInSp;
import static com.harshchourasiya.hackathon.helper.Validator.isEmail;
import static com.harshchourasiya.hackathon.helper.Validator.isName;
import static com.harshchourasiya.hackathon.helper.Validator.isPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.harshchourasiya.hackathon.MainActivity;
import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.helper.DatabaseUtility;

import java.util.UUID;

public class CreateUserActivity extends AppCompatActivity {

    private TextInputLayout emailInputLayout, nameInputLayout, passwordInputLayout;
    private TextInputEditText emailEditText, nameEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        assignVariables();
        setOnCreateButtonClick();
    }

    private void assignVariables() {
        emailInputLayout = findViewById(R.id.emailInputLayout);
        nameInputLayout = findViewById(R.id.nameInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);

        emailEditText = findViewById(R.id.emailEditText );
        nameEditText  = findViewById(R.id.nameEditText );
        passwordEditText = findViewById(R.id.passwordEditText);

    }


    private void emailWarning(int color) {
        emailInputLayout.setErrorEnabled(true);
        emailInputLayout.setError("Enter Valid Email");
        emailInputLayout.setErrorTextColor(ColorStateList.valueOf(color));
    }

    private void nameWarning(int color) {
        nameInputLayout.setErrorEnabled(true);
        nameInputLayout.setError("Enter Valid Name");
        nameInputLayout.setErrorTextColor(ColorStateList.valueOf(color));
    }

    private void passwordWarning(int color) {
        passwordInputLayout.setErrorEnabled(true);
        passwordInputLayout.setError("Entered Password Must Contains 5 Letter");
        passwordInputLayout.setErrorTextColor(ColorStateList.valueOf(color));
    }


    private void setOnCreateButtonClick() {
        findViewById(R.id.createButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private void validateData() {
        String email = emailEditText.getText().toString(), password = passwordEditText.getText().toString(),
                name = nameEditText.getText().toString();

        if (!isEmail(email)) {
            emailWarning(Color.RED);
            return ;
        }

        if (!isName(name)) {
            nameWarning(Color.RED);
            return ;
        }

        if (!isPassword(password)) {
            passwordWarning(Color.RED);
            return ;
        }

        emailWarning(Color.BLACK);
        nameWarning(Color.BLACK);
        passwordWarning(Color.BLACK);

        storeDataInDatabase(email, password, name);
    }

    private void storeDataInDatabase(String email, String password, String name) {
        authenticateUserUsingFirebaseAuth(email, password, name);
    }

    private void authenticateUserUsingFirebaseAuth(String email, String password, String name) {
        FirebaseAuth mAuth =  FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(CreateUserActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            successfullyUserIsCreated(mAuth.getCurrentUser(),
                                    email,
                                    password,
                                    name);
                        } else {
                            Toast.makeText(CreateUserActivity.this, "Unable to create Account",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void successfullyUserIsCreated(FirebaseUser user, String email, String password,String name) {
        new DatabaseUtility().AddUser(user.getUid(), email, password, name);
        setUserIdInSp(CreateUserActivity.this,user.getUid());
        openMainActivity();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}