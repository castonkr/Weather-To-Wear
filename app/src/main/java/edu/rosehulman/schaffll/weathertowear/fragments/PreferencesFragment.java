package edu.rosehulman.schaffll.weathertowear.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public PreferencesFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        Log.d("FP", firebasePath);
        mUser = getArguments().getString(FIREBASE_USER_ID);
        Log.d("PK", mUser);
//        if (firebasePath == null || firebasePath.isEmpty()) {
//            mUserRef = FirebaseDatabase.getInstance().getReference();
//        } else {
        mUserRef = FirebaseDatabase.getInstance().getReference().child(firebasePath);
//        }
        currentTemp = HomeFragment.tempF;
        weatherID = HomeFragment.weatherID;



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
                //also pass in mUserRef
                Calculations calc = new Calculations(hotTemp, coldTemp, currentTemp, weatherID, mUserRef);
                calc.createNewOutfits();





        }
    }
}



