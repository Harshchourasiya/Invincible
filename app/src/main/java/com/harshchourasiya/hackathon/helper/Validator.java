package com.harshchourasiya.hackathon.helper;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isEmail(String email) {
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isName(String name) {
        return !name.isEmpty() && name.length() > 3 && name.length() < 30;
    }

    public static boolean isPassword(String password) {
        return !password.isEmpty() && password.length() >= 5;
    }
}
