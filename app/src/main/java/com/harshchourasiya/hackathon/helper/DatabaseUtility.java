package com.harshchourasiya.hackathon.helper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.harshchourasiya.hackathon.schema.User;

import java.util.TimeZone;
import java.util.UUID;

public class DatabaseUtility {
    private DatabaseReference mDatabase;

    public DatabaseUtility() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void AddUser(String userId,String email, String password, String name) {
        String timezone = TimeZone.getDefault().getID();
        User user = new User(userId,email, password, name, timezone,0,false);
        mDatabase.child("users").child(userId).setValue(user);
    }

}
