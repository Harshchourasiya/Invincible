package com.harshchourasiya.hackathon.activities;

import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL;
import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL_ID;
import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL_NAME;
import static com.harshchourasiya.hackathon.helper.DataFromSP.LEVEL_TASK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.adapter.CommentViewAdapter;
import com.harshchourasiya.hackathon.helper.DatabaseUtility;
import com.harshchourasiya.hackathon.schema.Comment;

import java.util.ArrayList;
import java.util.List;

public class ShareEXP extends AppCompatActivity {
    private TextView avatarTextView, levelNameTextView, levelTaskTextView;
    private FloatingActionButton shareEXPButton;
    private RecyclerView commentsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_exp);
        initVariables();
        setData();
    }

    private void initVariables() {
       avatarTextView = findViewById(R.id.avatarText);
       levelNameTextView = findViewById(R.id.levelNameTextView);
       levelTaskTextView= findViewById(R.id.levelTaskTextView);
       shareEXPButton = findViewById(R.id.shareExpButton);
       commentsRecyclerView = findViewById(R.id.commentRecyclerView);

    }

    private void setData() {
        int level = getIntent().getIntExtra(LEVEL, 0);
        int levelId = getIntent().getIntExtra(LEVEL_ID, 0);
        String levelName = getIntent().getStringExtra(LEVEL_NAME);
        String levelTask = getIntent().getStringExtra(LEVEL_TASK);

        avatarTextView.setText(String.valueOf(level));
        levelNameTextView.setText(levelName);
        levelTaskTextView.setText(levelTask);

        setRecyclerView(levelId);
    }

    private void setRecyclerView(int id) {
        List<Comment> comments = new ArrayList<>();
        CommentViewAdapter adapter = new CommentViewAdapter(this, comments);
        commentsRecyclerView.setAdapter(adapter);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new DatabaseUtility().getDB()
                .child("comments")
                .child(String.valueOf(id))
                .child("comments")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Comment comment = ds.getValue(Comment.class);
                            comments.add(comment);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}