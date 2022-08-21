package com.harshchourasiya.hackathon.activities;

import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL;
import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL_ID;
import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.helper.DatabaseUtility;

public class AddCommentActivity extends AppCompatActivity {

    private TextView avatarTextView, levelNameTextView;
    private TextInputEditText comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        initVariables();
        setData();

        findViewById(R.id.shareButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShareButtonAction();
            }
        });
    }


    private void initVariables() {
        avatarTextView = findViewById(R.id.avatarText);
        levelNameTextView = findViewById(R.id.levelNameTextView);
        comment = findViewById(R.id.comment);
    }

    private void setData() {
        avatarTextView.setText(String.valueOf(getIntent().getIntExtra(LEVEL, 0)));
        levelNameTextView.setText(getIntent().getStringExtra(LEVEL_NAME));
    }

    private void setShareButtonAction() {
        if (comment.getText().length() == 0 || comment.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Comment", Toast.LENGTH_LONG).show();
            return ;
        }
        new DatabaseUtility().addComment(
                getIntent().getIntExtra(LEVEL_ID, 12340),

                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                comment.getText().toString()
        );

        onBackPressed();
    }


}