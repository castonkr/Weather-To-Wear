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


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {

    public static final String FIREBASE_PATH = "FIREBASE_PATH";
    public static final String FIREBASE_USER_ID = "FIREBASE_USER_ID";
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
    private boolean mBooleanArray[] = new boolean[21];
    private List<OutfitItem> userClothingOptions;
    //private OutfitAdapter mAdapter;


    public PreferencesFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        Log.d("FP", firebasePath);
        mUser = getArguments().getString(FIREBASE_USER_ID);
        Log.d("PK", mUser);
        if (firebasePath == null || firebasePath.isEmpty()) {
            mUserRef = FirebaseDatabase.getInstance().getReference();
        } else {
        mUserRef = FirebaseDatabase.getInstance().getReference().child(firebasePath);
        }
        currentTemp = HomeFragment.tempF;
        weatherID = HomeFragment.weatherID;




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
                zipCode.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mUserRef.child("hotTemp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] hotTemps = getResources().getStringArray(R.array.hot_spinner);
                int pos = 0;
                for (int i = 0; i < hotTemps.length; i++) {
                    if (dataSnapshot.getValue().toString().equals(hotTemps[i])) {
                        pos = i;
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
                String[] coldTemps = getResources().getStringArray(R.array.cold_spinner);
                int pos = 0;
                for (int i = 0; i < coldTemps.length; i++) {
                    if (dataSnapshot.getValue().toString().equals(coldTemps[i])) {
                        pos = i;
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
                Calculations calc = new Calculations(hotTemp, coldTemp, currentTemp, weatherID, mBooleanArray);
                userClothingOptions = calc.createNewOutfits();
                Log.d("linde", userClothingOptions.get(0).getKey());
                mUserRef.child("newOutfits").setValue(userClothingOptions);

                hideKeyboard();

//                mUserRef.child("newOutfits").child("Outfit2").setValue(userClothingOptions.get(0));
//                mUserRef.child("newOutfits").child("Outfit1").child("type1").setValue(userClothingOptions.get(0).getmType1());
//                mUserRef.child("newOutfits").child("Outfit1").child("type2").setValue(userClothingOptions.get(0).getmType2());
//                mUserRef.child("newOutfits").child("Outfit1").child("type3").setValue(userClothingOptions.get(0).getmType3());
//                mUserRef.child("newOutfits").child("Outfit1").child("type4").setValue(userClothingOptions.get(0).getmType4());
//                mUserRef.child("newOutfits").child("Outfit1").child("type5").setValue(userClothingOptions.get(0).getmType5());
//                mUserRef.child("newOutfits").child("Outfit1").child("type6").setValue(userClothingOptions.get(0).getmType6());



        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(zipCode.getWindowToken(), 0);
    }
}



