package com.lecturer.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Context mContext;

        // shared pref mode
        int PRIVATE_MODE = 0;

        // Shared preferences file name
        private static final String PREF_NAME = "lecturer";

        private static final String IS_LOGGED_IN = "isLoggedIn";
        public static final String LOGIN_ID = "loginId";

        public PrefManager(Context context) {
                this.mContext = context;
                pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = pref.edit();
        }

        public void setLoggedIn(boolean isLoggedin) {
                editor.putBoolean(IS_LOGGED_IN, isLoggedin);
                editor.commit();
        }

        public boolean isLoggedIn() {
                return pref.getBoolean(IS_LOGGED_IN, false);
        }

        public void setInt(String prefName,int prefvalue){
                editor.putInt(prefName, prefvalue);
                editor.commit();
        }

        public int getInt(String prefName){
                return pref.getInt(prefName,0);
        }
        public void setString(String prefName,String prefvalue){
                editor.putString(prefName, prefvalue);
                editor.commit();
        }

        public String getString(String prefName){
                return pref.getString(prefName,null);
        }
}