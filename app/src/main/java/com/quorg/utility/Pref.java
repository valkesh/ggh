/**
 * @author saltinteractive
 */

package com.quorg.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    final static String PREF_NAME = "Quorg";
    private static Context context = Golly.context;
    private static String USER_ID;

    static SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
    static SharedPreferences.Editor editor = pref.edit();

    static SharedPreferences offline_pref = context.getSharedPreferences(
            "offline" + PREF_NAME, 0);
    static SharedPreferences.Editor offline_editor = offline_pref.edit();

    public static void setUserID(String value) {
        editor.putString("user_id", value);
        editor.commit();
    }

    public static String getUserID() {
        return pref.getString("user_id", "");
    }

    public static void setIsLogin(Boolean value) {
        editor.putBoolean(getUserID() + "IsLogin", value);
        editor.commit();
    }

    public static Boolean getIsLogin() {

        return pref.getBoolean(getUserID() + "IsLogin", false);
    }




    public static void setIsEdit(Boolean value) {
        editor.putBoolean(getUserID() + "Isedit", value);
        editor.commit();
    }

    public static Boolean setIsEdit() {
        return pref.getBoolean(getUserID() + "Isedit", false);
    }


    public static void setSubscribeApp(Boolean value) {
        editor.putBoolean(getUserID() + "Subscribe_user", value);
        editor.commit();
    }

    public static Boolean isSubscribeApp() {
        return pref.getBoolean(getUserID() + "Subscribe_user", false);
    }


    public static String getOfflineData(String URL) {

        return offline_pref.getString(URL, "");
    }

    public static void setOfflineData(String URL, String value) {
        offline_editor.putString(URL, value);
        offline_editor.commit();
    }

    public static String getDeviceToken(String tokenValue) {

        return offline_pref.getString(tokenValue, "");
    }

    public static void setDeviceToken(String deviceToken, String value) {
        offline_editor.putString(deviceToken, value);
        offline_editor.commit();
    }

    public static String getTabList(String tabList) {
        return offline_pref.getString(tabList, "");
    }

    public static void setTabList(String tabList, String value) {
        offline_editor.putString(tabList, value);
        offline_editor.commit();
    }

    public static String getSubscrptionDate(String date) {

        return offline_pref.getString(date, "");
    }

    public static void setSubscrptionDate(String date, String value) {
        offline_editor.putString(date, value);
        offline_editor.commit();
    }




    public static String getHomedataOffline(String home_response) {
        return offline_pref.getString(home_response, "");
    }

    public static void setHomedataOffline(String home_responce, String value) {
        offline_editor.putString(home_responce, value);
        offline_editor.commit();
    }

}