package com.example.asus.projectdemo;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Asus on 5/16/2016.
 */
public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private RecyclerView recyclerViewLevel;
    private ArrayList<LevelCardItem> levelCardItems;
    private GridLayoutManager gridLayoutManager;
    int spanCount = 2;
    int spacing = 175;
    boolean includeEdge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        setDefaultDataLevel();
        setUpView();
        setAdapterForRecyclerView();
        registerEvent();
    }

    private void registerEvent() {
        recyclerViewLevel.addOnItemTouchListener(new RecyclerItemClickListener(HomeActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }

    private void setAdapterForRecyclerView() {
        gridLayoutManager=new GridLayoutManager(HomeActivity.this,2);
        recyclerViewLevel.setHasFixedSize(true);
        recyclerViewLevel.setLayoutManager(gridLayoutManager);
        GridViewLevelAdapter rcAdapter = new GridViewLevelAdapter(HomeActivity.this, levelCardItems);
        recyclerViewLevel.setAdapter(rcAdapter);
        recyclerViewLevel.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));
    }

    private void setDefaultDataLevel() {
        levelCardItems=new ArrayList<LevelCardItem>();
        LevelCardItem level1=new LevelCardItem("","level1");
        levelCardItems.add(level1);
        LevelCardItem level2=new LevelCardItem("","level2");
        levelCardItems.add(level2);
        LevelCardItem level3=new LevelCardItem("","level3");
        levelCardItems.add(level3);
        LevelCardItem level4=new LevelCardItem("","level4");
        levelCardItems.add(level4);

    }

    private void setUpView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        recyclerViewLevel=(RecyclerView)findViewById(R.id.recyclerview_level);
        mFragmentManager = getFragmentManager();
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);

    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0://Home
                break;
            case 1://Share
                shareApp();
                break;
            case 2://About
                showInfomationApp();
                break;
            case 3://feedback
                sendEmail();

            default:
                break;
        }
    }

    private void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.developer_email));
        email.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject_email));
        try {
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HomeActivity.this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void showInfomationApp() {

    }

    private void shareApp() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.link_google_play_for_share));

        startActivity(Intent.createChooser(share, getResources().getString(R.string.share_app_title)));
    }
}
