package edu.rosehulman.schaffll.weathertowear;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import edu.rosehulman.schaffll.weathertowear.fragments.HomeFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.MyClosetFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.PreferencesFragment;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnStartPressedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_main, new HomeFragment());
            ft.commit();
        }





//        buttonOutfitOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent savedOutfitsIntent = new Intent(getBaseContext(), MySavedOutfits.class);
//                startActivity(savedOutfitsIntent);
//            }
//        });
//
//        buttonOutfitTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent savedOutfitsIntent = new Intent(getBaseContext(), MySavedOutfits.class);
//                startActivity(savedOutfitsIntent);
//            }
//        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment switchTo = null;

        switch (item.getItemId()){
            case R.id.nav_main:
                switchTo = new HomeFragment();
                break;
            case R.id.nav_pref:
                switchTo = new PreferencesFragment();
                break;
            case R.id.nav_my_closet:
                switchTo = new MyClosetFragment();
                break;

        }

        if (item.getItemId() == R.id.nav_saved_outfits){
            Intent savedOutfitsIntent = new Intent(this, MySavedOutfits.class);
            startActivity(savedOutfitsIntent);
        }

        if(item.getItemId() == R.id.nav_new_outfits) {
            Intent newOufitsIntent = new Intent(this, MyNewOutfits.class);
            startActivity(newOufitsIntent);
        }


//        int id = item.getItemId();
//
//        if (id == R.id.nav_pref) {
//            // Handle the camera action
//        } else if (id == R.id.nav_my_closet) {
//
//        } else if (id == R.id.nav_saved_outfits) {
//
//        } else if (id == R.id.nav_new_outfits) {
//
//        }

        if (switchTo != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, switchTo);
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount();i++){
                getSupportFragmentManager().popBackStackImmediate();
            }
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStartPressed() {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment fragment = new MyClosetFragment();
//        ft.replace(R.id.content_main, fragment);
//        //ft.addToBackStack("list");
//        ft.commit();

            Intent savedOutfitsIntent = new Intent(this, MySavedOutfits.class);
            startActivity(savedOutfitsIntent);

    }
}
