/**
 * @author saltinteractive
 */
package com.quorg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.quorg.adapter.SlideAdapter;
import com.quorg.fragment.HomeFragment;
import com.quorg.model.SideMenuModel;

import java.util.ArrayList;
import java.util.List;


public class DashBoard extends FragmentActivity implements
        SlideAdapter.OnMNuSelectedListener {

    BroadcastReceiver updateUIReciver;

    public static SlidingMenu menu;
    public static ImageView sliding_menu_btn, small_logo;
    Dialog alert;
    final String TRUE = "1";
    final String FALSE = "0";
    public static TextView screen_title = null;
    public static View navigation_header_container;


    ImageButton facebook_link,
            twitter_link,
            tnstagrame_link;

    String ftag;
    private String[] mPlanetTitles;
    private String[] mTabPlanetTitles;
    ImageView home_base;

    int[] mFlags = new int[]{R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar};
    int[] mFlagsClicked = new int[]{R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar,
            R.drawable.menu_bar, R.drawable.menu_bar};


    boolean[] mTabFlagsSetFlag = new boolean[]{
            false,
            false,
            false,
            false,
            false,
            false
    };
    public static SlideAdapter.OnMNuSelectedListener mdListener;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public static List<SideMenuModel> mList = new ArrayList<SideMenuModel>();
    public static SlideAdapter mAdapter;
    public static ListView mDrawerList;
    View mCustomView;
    public static Context mcon;
    String notification_type = "";
    IntentFilter filter;
    private ImageView imgProfileDrw;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mcon = DashBoard.this;

        filter = new IntentFilter();
        filter.addAction("com.123.action");
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();


        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mList.clear();
        for (int i = 0; i < mPlanetTitles.length; i++) {
            if (i != 0) {
                mList.add(new SideMenuModel(mPlanetTitles[i], mFlags[i],
                        mFlagsClicked[i], false));
            } else {
                mList.add(new SideMenuModel(mPlanetTitles[i], mFlags[i],
                        mFlagsClicked[i], true));
            }
        }

        mdListener = this;
        LayoutInflater mInflater = LayoutInflater.from(this);
        mCustomView = mInflater.inflate(R.layout.toolbar, null);
        sliding_menu_btn = (ImageView) findViewById(R.id.iv_menu);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setTouchmodeMarginThreshold(180);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.89f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        sliding_menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu.toggle(true);
            }
        });
        final LayoutInflater inflat = getLayoutInflater();
        View menu_layout = inflat.inflate(R.layout.slider_menu_layout,
                null);

        mDrawerList = (ListView) menu_layout.findViewById(R.id.mDrawerList);
        imgProfileDrw = (ImageView) menu_layout.findViewById(R.id.imgProfileDrw);
        mAdapter = new SlideAdapter(this, mdListener, mList);
        mDrawerList.setAdapter(mAdapter);
        navigation_header_container = findViewById(R.id.navigation_header_container);

        HomeFragment homefragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.add(R.id.FlContainer_framelayout, homefragment, "Home");
        transaction.commit();
//        categories_list = new Categories();

        imgProfileDrw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homefragment = new HomeFragment();
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
                transaction.add(R.id.FlContainer_framelayout, homefragment, "Home");
                transaction.commit();
            }
        });



        menu.setMenu(menu_layout);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            onNewIntent(getIntent());
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            notification_type = extras.getString("content_type");
            extras.getString("content_id");
        }
    }


    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.toggle();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            }
        }
        super.onBackPressed();
    }

    @Override
    public void onMNuSelectedListener(final int index) {
        // mDrawerLayout.closeDrawer(mDrawerList);fragment = new Aboutus();

        new Handler().postDelayed(new Runnable() {

                                      @Override
                                      public void run() {
                                          Fragment fragment = null;
                                          Bundle data = new Bundle();

                                          for (int i = 0; i < mList.size(); i++) {
                                              if (index == i) {
                                                  mList.get(i).setClicked(true);
                                              } else {
                                                  mList.get(i).setClicked(false);
                                              }
                                              // mTitle = mList.get(index).getTitle().toString();
                                          }

                                          if (index == 0) {


                                          } else if (index == 1) {

                                          }
                                          if (fragment != null) {

                                              fragment.setArguments(data);
                                              FragmentManager fragmentManager = getSupportFragmentManager();
                                              FragmentTransaction tran = fragmentManager
                                                      .beginTransaction();
                                              tran.replace(R.id.FlContainer_framelayout, fragment, ftag);
                                              // tran.addToBackStack(null);
                                              if (ftag.equals("Home")) {
                                                  for (int i = 0; i < fragmentManager
                                                          .getBackStackEntryCount(); ++i) {
                                                      fragmentManager.popBackStack();
                                                  }
                                              } else {
                                                  tran.addToBackStack(ftag);
                                              }
                                              tran.commit();
                                          } else {
                                              // error in creating fragment
                                              Log.e("MainActivity", "Error in creating fragment");
                                          }
                                      }
                                  }

                , 320);
    }

    boolean is_login_resume = false;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}