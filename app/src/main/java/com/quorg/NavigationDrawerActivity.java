package com.quorg;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.quorg.adapter.NavigationDrawerAdapter;
import com.quorg.fragment.HomeFragment;
import com.quorg.model.NavDrawerItem;
import com.quorg.utility.CommonClass;
import com.quorg.utility.Preference;
import com.quorg.widgets.GGTextView;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity  {

    private static String[] titles = null;
    RecyclerView recyclerView;
    NavigationDrawerAdapter adapter;
    DrawerLayout drawer;
    ImageView iv_menu;
    GGTextView toolbar_title,tv_nd_email;
    Context c;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        c = NavigationDrawerActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBar actionBar = getSupportActionBar();
       // actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = (GGTextView)toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("STORE");

        tv_nd_email = (GGTextView)findViewById(R.id.tv_nd_email);
        if(Preference.getUser(c)!=null)
           tv_nd_email.setText(Preference.getUser(c).getEmail());

        recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        titles = getResources().getStringArray(R.array.nav_drawer_labels);
        iv_menu = (ImageView)findViewById(R.id.iv_menu);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.END))
                {

                }
                else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });
        List<NavDrawerItem> data = new ArrayList<NavDrawerItem>();
        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++)
        {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
           // System.out.println(titles[i]);
        }

        adapter = new NavigationDrawerAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NavigationDrawerActivity.this));


        HomeFragment homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frame,homeFragment,"Home");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        // display the first navigation drawer view on app launch
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (menu != null) {
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
                case android.R.id.home:

                    return true;

        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed()
    {
        alertDialog(NavigationDrawerActivity.this, "Do you want to exit from the app?", true,true);
    }



    private com.android.volley.Response.Listener<String> SuccessLisner() {
        return new com.android.volley.Response.Listener<String>() {
            private String responseMessage = "";

            @Override
            public void onResponse(String response) {
                Log.d("SuccessLisner Json", "==> " + response);

            }
        };
    }

    private com.android.volley.Response.ErrorListener ErrorLisner() {
        return new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Json Error", "==> " + error.getMessage());

            }
        };
    }
    public void alertDialog(final Activity context, String steMessage, boolean flagNo, final boolean isExit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(steMessage);
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                if (isExit)
                {
                    context.finish();
                    System.exit(0);
                }else
                {

                    Preference.ClearUser(c);
                    Intent intent = new Intent(NavigationDrawerActivity.this, NavigationDrawerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });
        if (flagNo) {
            builder.setNegativeButton("No", null);
        }
        builder.show();
    }



    protected void onResume()
    {
        super.onResume();

        ((NavigationDrawerAdapter) adapter).setOnItemClickListener(new NavigationDrawerAdapter.MyClickListener()
        {
            public void onItemClick(int position, View v)
            {
                CommonClass.showSimpleToast(c,position+"");
                drawer.closeDrawer(GravityCompat.END);
            }
        });
    }

}
