package com.harshchourasiya.hackathon.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.adapter.LevelViewAdapter;
import com.harshchourasiya.hackathon.helper.DatabaseUtility;
import com.harshchourasiya.hackathon.schema.Level;
import com.harshchourasiya.hackathon.schema.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private FirebaseUser user;
    private RecyclerView levelRecyclerView;
    private TextView nameTextView, levelTextView, avatarTextView;
    private DatabaseReference mDatabase;
    private LevelViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initVariables();
    }

    private void initVariables() {

        user = FirebaseAuth.getInstance().getCurrentUser();
        levelRecyclerView = findViewById(R.id.levelRecyclerView);
        nameTextView = findViewById(R.id.nameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        mDatabase = new DatabaseUtility().getDB();
        setData();
    }

    private void setData() {
        setUserData(user.getUid());
    }

    public void setUserData(String userId) {
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                nameTextView.setText(user.getName());
                levelTextView.setText(String.valueOf(user.getLevel()));

                List<Level> levels = getLevels(user.getLevel());
                setRecyclerView(levels, user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public List<Level> getLevels(int totalLevel) {
        List<Level> levels = new ArrayList<>();

        mDatabase.child("levels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Level level = ds.getValue(Level.class);
                    levels.add(level);
                    if (adapter != null) adapter.notifyDataSetChanged();
                    if (totalLevel == i) break;
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return levels;
    }

    private void setRecyclerView(List<Level> levels, User user) {
        adapter = new LevelViewAdapter(this,levels,user);
        levelRecyclerView.setAdapter(adapter);
        levelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}