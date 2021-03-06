package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import edu.rosehulman.schaffll.weathertowear.OutfitAdapter;
import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.R;
import edu.rosehulman.schaffll.weathertowear.Weather.Calculations;

import static edu.rosehulman.schaffll.weathertowear.Constants.FIREBASE_PATH;
import static edu.rosehulman.schaffll.weathertowear.Constants.FIREBASE_USER_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {

    String mUser;
    private DatabaseReference mUserRef;
    private EditText zipCode;
    private Spinner hotSpinner;
    private Spinner coldSpinner;
    private Button saveButton;
    private int hotTemp;
    private int coldTemp;
    private float currentTemp;
    private int weatherID;
    private int weatherCond;
    private boolean mBooleanArray[] = new boolean[21];
    private List<OutfitItem> userClothingOptions;

    public PreferencesFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        mUser = getArguments().getString(FIREBASE_USER_ID);
        if (firebasePath == null || firebasePath.isEmpty()) {
            mUserRef = FirebaseDatabase.getInstance().getReference();
        } else {
        mUserRef = FirebaseDatabase.getInstance().getReference().child(firebasePath);
        }
        currentTemp = HomeFragment.tempF;
        //weatherID = HomeFragment.weatherID;
        //weatherCond = HomeFragment.




        DatabaseReference booleanRef = mUserRef.child("booleanArray");
        booleanRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int position = Integer.parseInt(dataSnapshot.getKey());
                mBooleanArray[position] = (boolean) dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
        saveButton = (Button) view.findViewById(R.id.buttonPreferences);
        zipCode = (EditText) view.findViewById(R.id.zipCodeEditText);
        hotSpinner = (Spinner) view.findViewById(R.id.spinnerHot);
        coldSpinner = (Spinner) view.findViewById(R.id.spinnerCold);
        hotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                hotTemp = Integer.parseInt(adapterView.getItemAtPosition(pos).toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        coldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                coldTemp = Integer.parseInt(adapterView.getItemAtPosition(pos).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        saveButton.setOnClickListener(this);

        mUserRef.child("zipcode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    zipCode.setText(dataSnapshot.getValue().toString());
                }
                catch (NullPointerException e){
                    zipCode.setText("47803");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mUserRef.child("hotTemp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] hotTemps;
                try{
                hotTemps = getResources().getStringArray(R.array.hot_spinner);}
                catch (IllegalStateException e){
                    hotTemps = new String[11];
                    {
                        hotTemps[0] = "50";
                        hotTemps[1] = "55";
                        hotTemps[2] = "60";
                        hotTemps[3] = "65";
                        hotTemps[4] = "70";
                        hotTemps[5] = "75";
                        hotTemps[6] = "80";
                        hotTemps[7] = "85";
                        hotTemps[8] = "90";
                        hotTemps[9] = "95";
                        hotTemps[10] = "100";
                    }
                }
                int pos = 0;
                for (int i = 0; i < hotTemps.length; i++) {
                    try{
                    if (dataSnapshot.getValue().toString().equals(hotTemps[i])) {
                        pos = i;
                    }}
                    catch (NullPointerException e){
                        pos = 0;
                    }
                }
                hotSpinner.setSelection(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mUserRef.child("coldTemp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] coldTemps;
                try{
                coldTemps = getResources().getStringArray(R.array.cold_spinner);}
                catch (IllegalStateException e){
                    coldTemps = new String[11];
                    {
                        coldTemps[0] = "0";
                        coldTemps[1] = "5";
                        coldTemps[2] = "10";
                        coldTemps[3] = "15";
                        coldTemps[4] = "20";
                        coldTemps[5] = "25";
                        coldTemps[6] = "30";
                        coldTemps[7] = "35";
                        coldTemps[8] = "40";
                        coldTemps[9] = "45";
                        coldTemps[10] = "50";
                    }

                }
                int pos = 0;
                for (int i = 0; i < coldTemps.length; i++) {
                    try{
                    if (dataSnapshot.getValue().toString().equals(coldTemps[i])) {
                        pos = i;
                    }}
                    catch (NullPointerException e){
                        pos = 0;
                    }
                }
                coldSpinner.setSelection(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPreferences:
                mUserRef.child("zipcode").setValue(zipCode.getText().toString());
                mUserRef.child("hotTemp").setValue(hotTemp);
                mUserRef.child("coldTemp").setValue(coldTemp);
                Calculations calc = new Calculations(hotTemp, coldTemp, currentTemp, mBooleanArray);
                userClothingOptions = calc.createNewOutfits();
                mUserRef.child("newOutfits").setValue(userClothingOptions);

                hideKeyboard();


        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(zipCode.getWindowToken(), 0);
    }
}



