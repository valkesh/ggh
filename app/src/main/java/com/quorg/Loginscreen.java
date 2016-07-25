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

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Loginscreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Login";
    private static final String PACKAGE = "com.quorg";
    private ProgressDialog mProgressDialog;
    private static final int RC_GOOGLE_IN = 9001;
    private static final int RC_LINKED_IN = 9002;
    private ImageView btnGoogle, btnLinkedin;
    private Button signupBtn;
    private EditText edtEmail;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateHashkey();
        System.out.println("=====hask_key====" + generateHashkey());
        setContentView(R.layout.activity_login_screen);
        UtilityPro.generateKey(Loginscreen.this);
        initView();


//        tokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken1) {
//
//            }
//        };
//        profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile profile, Profile profile1) {
//                System.out.println("profile_id" + profile.getId());
//                System.out.println("profile_name" + profile.getName());
//            }
//        };
//        tokenTracker.startTracking();
//        profileTracker.startTracking();


        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestId()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();
        // [END build_client]
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        try {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
            if (opr.isDone()) {
                // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
                // and the GoogleSignInResult will be available instantly.
                Log.d(TAG, "Got cached sign-in");
                GoogleSignInResult result = opr.get();
                // handleSignInResult(result , false);
            } else {
                // If the user has not previously signed in on this device or the sign-in has expired,
                // this asynchronous branch will attempt to sign in the user silently.  Cross-device
                // single sign-on will occur in this branch.

                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(GoogleSignInResult googleSignInResult) {
                        hideProgressDialog();
                        //       handleSignInResult(googleSignInResult , t);
                    }
                });
            }
        } catch (Exception e) {

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Loginscreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.quorg/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
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
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        btnGoogle = (ImageView) findViewById(R.id.btnGoogle);
        btnLinkedin = (ImageView) findViewById(R.id.btnLinkedin);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilityPro.isNetworkAvaliable(Loginscreen.this)) {

                    boolean valid = UtilityPro.ValidateTask(edtEmail.getText().toString().trim(), edtEmail.getText().toString().trim(), Loginscreen.this);
                    if (valid) {
                        //call webcall Login
                        if (UtilityPro.isNetworkAvaliable(Loginscreen.this) == true) {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", edtEmail.getText().toString().trim());
                            params.put("device_token", Pref.getDeviceToken(Constants.DEVICE_TOKEN_GOOGLE));
                            params.put("device_type", Constants.DEVICE_TYPE);
                            params.put("login_type", Constants.LOGIN_WITH_APP_TYPE);
                            logInWebCall(params);
                        }
                    }
                } else {
                    UtilityPro.toast(Loginscreen.this.getString(R.string.please_check_your_internet_connection));
                }
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilityPro.isNetworkAvaliable(Loginscreen.this)) {
//                Intent i = new Intent(Loginscreen.this, DashBoard.class);
//                startActivity(i);
//                finish();
                    signInGoogle();
                } else {
                    UtilityPro.toast(Loginscreen.this.getString(R.string.please_check_your_internet_connection));
                }
            }
        });
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilityPro.isNetworkAvaliable(Loginscreen.this)) {
                    signInLinkedIn();
                } else {
                    UtilityPro.toast(Loginscreen.this.getString(R.string.please_check_your_internet_connection));
                }
            }
        });
    }

    public void signInLinkedIn() {
        LISessionManager.getInstance(getApplicationContext()).init(this,
                buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {

//                    Toast.makeText(getApplicationContext(), "success" +
//                            LISessionManager.getInstance(getApplicationContext().getSession().getAccessToken().toString(), Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onAuthError(LIAuthError error) {

                        Toast.makeText(getApplicationContext(), "failed " + error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }, true);
    }

// This method is used to make permissions to retrieve data from linkedin

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS, Scope.R_FULLPROFILE);
    }



    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
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
    }

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
        logInWebCall(params);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_IN) {
            if (data != null) {
                try {
                    GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    handleSignInResult(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            LISessionManager.getInstance(getApplicationContext()).onActivityResult(this,
                    requestCode, resultCode, data);
            handleSignInResultLinkedIn();
        }
    }


    /**
     * Validates that there is a reasonable server client ID in strings.xml, this is only needed
     * to make sure users of this sample follow the README.
     * Facebook = facebook_id,email,device_token,device_type,login_type
     * email,device_token,device_type,login_type
     */

    /*=========================== Auth method Call Webservice=====================================*/
    public void logInWebCall(Map<String, String> params) {
        String Url = Constants.HOME_LOGIN;
        Golly.showDarkProgressDialog(Loginscreen.this);
        Golly.FireDarkWebCall(Url, params, Request.Method.POST, "API_CALL_LOGIN",
                new Golly.GollyListner() {
                    @Override
                    public void successResponce(String response, String TAG,
                                                Boolean FROM_CAHCE) {
                        try {
                            try {
                                JSONObject responce_obj = new JSONObject(response);
                                if (responce_obj.getBoolean("status")) {
                                   // JSONObject json2 = responce_obj.getJSONObject("data");
//                                    Pref.setUserID(json2.getString("user_id"));
                                    Pref.setIsLogin(true);
                                    Intent profile_screen = new Intent(Loginscreen.this, ProfileScreen.class);
                                    startActivity(profile_screen);
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
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }




    // [START signIn]
    private void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOutGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
//                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }


    // [START revokeAccess]
    private void revokeAccessGoogle() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
//                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }


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

    public String generateHashkey() {
        String hash_key = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    PACKAGE,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                hash_key = Base64.encodeToString(md.digest(),
                        Base64.NO_WRAP);
//                ((TextView) findViewById(R.id.package_name)).setText(info.packageName);
//                ((TextView) findViewById(R.id.hash_key)).setText(Base64.encodeToString(md.digest(),
//                        Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage(), e);
        }
        return hash_key;
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Loginscreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.quorg/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        mGoogleApiClient.disconnect();
    }
}


//Ref Link:
//https://github.com/JhonatasMartins/social-login/blob/master/app/src/main/java/br/com/jhonatasmartins/social/LoginActivity.java

// Ref link for linkedin
// https://www.numetriclabz.com/android-linkedin-integration-login-tutorial/