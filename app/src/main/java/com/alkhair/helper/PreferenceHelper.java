package com.alkhair.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hossam on 11/19/2020.
 */
public class PreferenceHelper {

    private static String app_ref = "StoreAPP";
    private final String Token = "token";

    private final String customerid = "customerID";

    private final String language = "Language";
    private final String lang = "lang";
    // remove in logout
    private final String First_Name = "First_Name";
    private final String Last_Name = "Last_Name";
    private final String user_name = "user_name";
    private final String password = "password";
    private final String user_id = "user_id";
    private final String isLogin = "isLogin";
    private final String FirstTime = "FirstTime";

    private final String donation_amount = "donation_amount";


    private SharedPreferences app_prefs;
    private Context context;


    public PreferenceHelper(Context context) {
        this.context = context;
        try {
            app_prefs = context.getSharedPreferences(app_ref,
                    Context.MODE_PRIVATE);
        } catch (NullPointerException e) {
        }
    }

    public String getIsLogin() {
        return app_prefs.getString(isLogin, null);
    }

    public void setIsLogin(String API_ID) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(isLogin, API_ID);
        edit.apply();
    }

    public String getDonation_amount() {
        return app_prefs.getString(donation_amount, null);
    }

    public void setDonation_amount(String API_ID) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(donation_amount, API_ID);
        edit.apply();
    }

    public String getLang() {
        return app_prefs.getString(lang, null);
    }

    public void setLang(String API_ID) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(lang, API_ID);
        edit.apply();
    }

    public String getuser_id() {
        return app_prefs.getString(user_id, null);
    }

    public void setuser_id(String API_user_id) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(user_id, API_user_id);
        edit.apply();
    }

    public String getuser_name() {
        return app_prefs.getString(user_name, null);
    }

    public void setuser_name(String username) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(user_name, username);
        edit.apply();
    }

    public String getpassword() {
        return app_prefs.getString(password, null);
    }

    public void setpassword(String pass) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(password, pass);
        edit.apply();
    }

    public String getFirst_Time() {
        return app_prefs.getString(FirstTime, null);
    }

    public void setFirst_Time(String API_First_Name) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(FirstTime, API_First_Name);
        edit.apply();
    }
    public String getFirst_Name() {
        return app_prefs.getString(First_Name, null);
    }

    public void setFirst_Name(String API_First_Name) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(First_Name, API_First_Name);
        edit.apply();
    }

    public String getLast_Name() {
        return app_prefs.getString(Last_Name, null);
    }

    public void setLast_Name(String API_Last_Name) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Last_Name, API_Last_Name);
        edit.apply();
    }

    public String getlanguage() {
        return app_prefs.getString(language, null);
    }



    public void setlanguage(String API_language) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(language, API_language);
        edit.apply();
    }

    public void signOutUSer() {
        setFirst_Name(null);
        setuser_name(null);
        setLast_Name(null);
    }


    /**
     * This method to return String stored in SharedPreferences
     *
     * @param key Key of stored String
     * @return stored String for this key if exists and empty String "" if not exists
     */
    public String getData(String key) {

        String language = app_prefs.getString(key, "");

        return language;
    }

    /**
     * This method to add new String value to SharedPreferences
     *
     * @param key   Key for the new String
     * @param value Value of the new String
     */
    public void addData(String key, String value) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * This method to add new String value to SharedPreferences
     *
     * @param key   Key for the new String
     * @param value Value of the new Boolean
     */
    public void addData(String key, Boolean value) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    /**
     * This method to add new String value to SharedPreferences
     *
     * @param key   Key for the new String
     * @param value Value of the new Integer
     */
    public void addData(String key, Integer value) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    /**
     * This method to return boolean stored in SharedPreferences
     *
     * @param key Key of stored boolean
     * @return stored boolean for this key if exists and false if not exists
     */
    public Boolean getBoolean(String key) {

        Boolean language = app_prefs.getBoolean(key, false);

        return language;
    }

    /**
     * This method to return long stored in SharedPreferences
     *
     * @param key Key of stored long
     * @return stored long for this key if exists and 0 if not exists
     */
    public Long getLong(String key) {

        Long language = app_prefs.getLong(key, (long) 0.0);

        return language;
    }

    /**
     * This method to return float stored in SharedPreferences
     *
     * @param key Key of stored float
     * @return stored float for this key if exists and 0.0 if not exists
     */
    public Float getFloat(String key) {

        Float language = app_prefs.getFloat(key, (float) 0.0);

        return language;
    }

    /**
     * This method to return int stored in SharedPreferences
     *
     * @param key Key of stored int
     * @return stored int for this key if exists and 0 if not exists
     */
    public int getInt(String key) {

        int language = app_prefs.getInt(key, 0);

        return language;
    }

    /**
     * This method to clear all SharedPreferences
     */
    public void clearAllSharedPreferences() {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.clear();
        edit.apply();

    }

    /**
     * This method to delete specific key from SharedPreferences
     *
     * @param delete Key that will be deleted
     */
    public void clearSpecificSharedPreferences(String delete) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.remove(delete);
        edit.apply();

    }
}
