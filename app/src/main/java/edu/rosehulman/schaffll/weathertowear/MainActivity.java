package edu.rosehulman.schaffll.weathertowear;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.rosehulman.schaffll.weathertowear.fragments.HomeFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.ClosetFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.LoginFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.PreferencesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnStartPressedListener {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final String FIREBASE_PATH = "FIREBASE_PATH";
    public static final String FIREBASE_USER_ID = "FIREBASE_USER_ID";
    private OnCompleteListener mOnCompleteListener;
    private static final int RC_ROSEFIRE_LOGIN = 1;
    private String mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        initializeListeners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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
    }

    private void initializeListeners(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //Util.setCurrentUser(MainActivity.this, user.getUid());
                    Log.d("PB", "user isn't null, would login");
                    //switchToPicListFragment("users/" + user.getUid());
                    mUser = user.getUid();
                    switchToHomeFragment("users/" + user.getUid(), user.getUid());

                }
                else{
                    switchToLoginFragment();
                }

            }
        };
        mOnCompleteListener = new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (!task.isSuccessful()){
                    showLoginError("Login failed");
                }
            }
        };
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void switchToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new LoginFragment(), "Login");
        ft.commit();
    }

    private void switchToHomeFragment(String path, String id) {
        Log.d("PB", "in switch to home fragment");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(FIREBASE_PATH, path);
        args.putString(FIREBASE_USER_ID, id);
        homeFragment.setArguments(args);
        ft.replace(R.id.content_main, homeFragment, "Home");
        ft.commit();
    }

    public void onLogin(String email, String password){
        Log.d("PB", "inside of onLogin");
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mOnCompleteListener);
    }

    private void showLoginError(String message) {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("Login");
        loginFragment.onLoginError(message);
    }


    @Override
    public void onLogout() {
        mAuth.signOut();
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
        int id = item.getItemId();
        Fragment switchTo = null;
        String tag = "";
        Bundle args = new Bundle();
        args.putString(FIREBASE_PATH, "users/" + mUser);
        args.putString(FIREBASE_USER_ID, mUser);
        //homeFragment.setArguments(args);

        if (id == R.id.nav_home) {
            Log.d("WTW", "home has been called");
            switchTo = new HomeFragment();
            // Handle the camera action
        } else if (id == R.id.nav_pref) {
            switchTo = new PreferencesFragment();
            tag = "pref";
        } else if (id == R.id.nav_closet) {
            switchTo = new ClosetFragment();
            tag = "closet";
        } else if (id == R.id.nav_saved_outfits) {

        } else if (id == R.id.nav_new_outfits) {

        } else if (id == R.id.nav_logout){
            onLogout();
            //switchTo = new LoginFragment();
            tag = "login";
        }

        if (switchTo != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switchTo.setArguments(args);
            ft.replace(R.id.content_main, switchTo, tag);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStartPressed(){
        //
    }

}
