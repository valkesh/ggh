/**
 * @author valekesh patel
 */
package com.quorg;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.quorg.utility.Constants;
import com.quorg.utility.Golly;
import com.quorg.utility.Pref;
import com.quorg.utility.UtilityPro;
import com.quorg.widgets.GGTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class ProfileScreen extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Login";
    private static final String PACKAGE = "com.quorg";
    private ProgressDialog mProgressDialog;
    GGTextView header_profile_next;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        UtilityPro.generateKey(ProfileScreen.this);
        Pref.setIsEdit(true);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    public void initView() {
//        header_profile_next =  (GGTextView) findViewById(R.id.header_profile_next);
//        header_profile_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (UtilityPro.isNetworkAvaliable(ProfileScreen.this)) {
////                Intent i = new Intent(Loginscreen.this, DashBoard.class);
////                startActivity(i);
////                finish();
//                System.out.println("=========Its next=============");
//                } else {
//                    UtilityPro.toast(ProfileScreen.this.getString(R.string.please_check_your_internet_connection));
//                }
//            }
//        });
    }



    // [START handleSignInResult]
   /* private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String authCode = acct.getId();
            String email = acct.getEmail();
            System.out.println("===Authcode===:" + authCode);
            Map<String, String> params = new HashMap<String, String>();
            params.put("google_id", acct.getId());
            params.put("email", acct.getEmail());
            params.put("device_token", Pref.getDeviceToken(Constants.DEVICE_TOKEN_GOOGLE));
            params.put("device_type", Constants.DEVICE_TYPE);
            params.put("login_type", Constants.LOGIN_WITH_GOOGLE_TYPE);
            logInWebCall(params);

//            updateUI(true);
        } else {
            try {
                signOutGoogle();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }*/

    // [START handleSignInResultLinkedIn]
    private void handleSignInResultLinkedIn() {
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        // Signed in successfully, show authenticated UI.
//            GoogleSignInAccount acct = result.getSignInAccount();
//            String authCode = acct.getId();
//            String email = acct.getEmail();
//            System.out.println("===Authcode===:" + authCode);
        Map<String, String> params = new HashMap<String, String>();
//            params.put("google_id", acct.getId());
//            params.put("email", acct.getEmail());
        params.put("device_token", Pref.getDeviceToken(Constants.DEVICE_TOKEN_GOOGLE));
        params.put("device_type", Constants.DEVICE_TYPE);
        params.put("login_type", Constants.LOGIN_WITH_LINKED_IN_TYPE);
//        logInWebCall(params);
    }




    /*=========================== Auth method Call Webservice=====================================*/
    /*public void logInWebCall(Map<String, String> params) {
        String Url = Constants.HOME_LOGIN;
        Golly.showDarkProgressDialog(ProfileScreen.this);
        Golly.FireDarkWebCall(Url, params, Request.Method.POST, "API_CALL_LOGIN",
                new Golly.GollyListner() {
                    @Override
                    public void successResponce(String response, String TAG,
                                                Boolean FROM_CAHCE) {
                        try {
                            try {
                                JSONObject responce_obj = new JSONObject(response);
                                if (responce_obj.getBoolean("status")) {
                                    JSONObject json2 = responce_obj.getJSONObject("data");
                                    Pref.setUserID(json2.getString("user_id"));
                                    Pref.setIsLogin(true);

                                    Intent i = new Intent(ProfileScreen.this, DashBoard.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    if (!UtilityPro.getDataFromJson(responce_obj, "message").equalsIgnoreCase("")) {
                                        UtilityPro.toast(UtilityPro.getDataFromJson(responce_obj, "message"));
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void errorResponce() {

                    }
                });
    }*/


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onStop() {
        super.onStop();


    }
}


//Ref Link:
//https://github.com/JhonatasMartins/social-login/blob/master/app/src/main/java/br/com/jhonatasmartins/social/LoginActivity.java

// Ref link for linkedin
// https://www.numetriclabz.com/android-linkedin-integration-login-tutorial/