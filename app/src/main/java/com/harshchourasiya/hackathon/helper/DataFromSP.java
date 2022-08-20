package com.harshchourasiya.hackathon.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class DataFromSP {

    public static String LEVEL_ID = "levelId";
    public static String LEVEL_NAME = "levelname";
    public static String LEVEL = "level";
    public static String LEVEL_TASK = "levelTask";
    public static String SP_ID = "spid";
    public static String USER_ID_SP = "userid";


    public static String getUserIdFromSp(Context c) {
        return c.getSharedPreferences(SP_ID, Context.MODE_PRIVATE).getString(USER_ID_SP, null);
    }

    public static void setUserIdInSp(Context c, String userID) {
        c.getSharedPreferences(SP_ID, Context.MODE_PRIVATE).edit().putString(USER_ID_SP, userID).apply();
    }
}
