package com.harshchourasiya.hackathon.helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.harshchourasiya.hackathon.schema.Comment;
import com.harshchourasiya.hackathon.schema.Level;
import com.harshchourasiya.hackathon.schema.User;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class DatabaseUtility {
    private DatabaseReference mDatabase;

    public DatabaseUtility() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDB() {
        return mDatabase;
    }
    public void AddUser(String userId,String email, String password, String name) {
        String timezone = TimeZone.getDefault().getID();
        User user = new User(userId,email, password, name, timezone,1,false);
        mDatabase.child("users").child(userId).setValue(user);
    }

    public void completeTask(User user) {
        mDatabase.child("users").child(user.getUserId()).setValue(user);
    }

    public void addComment(int levelId, String userID, String comment) {
        mDatabase.child("users").child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Comment commentObj = new Comment(user.getName(), String.valueOf(user.getLevel()), comment);

                        mDatabase.child("comments")
                                .child(String.valueOf(levelId))
                                .child("comments").push().setValue(commentObj);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}
