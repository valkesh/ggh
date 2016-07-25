package com.quorg.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.quorg.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by jigar.pandya on 18/7/16.
 */
public class Preference
{
    public static String KEY_USER_DATA = "userdetails";

    public static String getStringDateUserFromObjectUserDetail(Context ct, User mUd) {

        Gson gson = new Gson();
        String json = gson.toJson(mUd);
        return json;
    }


    public static User getObjectDateUserFRomStringUserDetail(Context ct, String json1) {

        Type type = new TypeToken<User>() {
        }.getType();
        User inpList = new Gson().fromJson(json1, type);

        return inpList;
    }


    public static void setUser(Context c,User u)
    {
        SharedPreferences sp = c.getSharedPreferences("userpref", c.MODE_WORLD_READABLE);
        SharedPreferences.Editor peditor = sp.edit();

        String udata = getStringDateUserFromObjectUserDetail(c, u);

        peditor.putString(KEY_USER_DATA, udata);

        peditor.commit();
    }


    public static User getUser(Context ct)
    {
        // TODO Auto-generated method stub
        @SuppressWarnings({"static-access", "deprecation"})
        SharedPreferences sp = ct.getSharedPreferences("userpref", ct.MODE_WORLD_READABLE);

        User mUserDetail = null;

        mUserDetail = getObjectDateUserFRomStringUserDetail(ct, sp.getString(KEY_USER_DATA, "null"));

        return mUserDetail;

    }


    public static void ClearUser(Context ct)
    {
        SharedPreferences sp = ct.getSharedPreferences("userpref", ct.MODE_WORLD_READABLE);
        SharedPreferences.Editor peditor = sp.edit();

        peditor.clear();
        peditor.commit();

    }


    public static boolean getCredentialsRemember(Context c)
    {

        SharedPreferences sp = c.getSharedPreferences("userpref", c.MODE_WORLD_READABLE);

        boolean s = sp.getBoolean("usercredentialsremember", false);

        return s;
    }


    public static void setCredentialsRemember(Context c,boolean s)
    {
        SharedPreferences sp = c.getSharedPreferences("userpref", c.MODE_WORLD_READABLE);
        SharedPreferences.Editor peditor = sp.edit();

        peditor.putBoolean("usercredentialsremember", s);

        peditor.commit();
    }

}
