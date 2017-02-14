package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.rosehulman.schaffll.weathertowear.OutfitAdapter;
import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.R;
import edu.rosehulman.schaffll.weathertowear.Weather.Calculations;
import edu.rosehulman.schaffll.weathertowear.Weather.JSONWeatherParser;
import edu.rosehulman.schaffll.weathertowear.Weather.Weather;
import edu.rosehulman.schaffll.weathertowear.Weather.WeatherHttpClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    public static final String FIREBASE_PATH = "FIREBASE_PATH";
    public static final String FIREBASE_USER_ID = "FIREBASE_USER_ID";
    private String weatherData;
    private String mUser;
    private DatabaseReference mUserRef;
    private DatabaseReference mOutfitsRef;
    private OnStartPressedListener mListener;
    private Toolbar mToolbar;
    private TextView locationText;
    private TextView tempText;
    private TextView conditionDesciption;
    private ImageView weatherImage;
    private ImageButton buttonOutfit1;
    private ImageButton buttonOutfit2;
    public static int tempF;
    public static int weatherID;
    public static String weatherCond;
    public List<OutfitItem> mSavedOutfitsItems;
    public TextView welcomeText;

    String zipcode;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        mUser = getArguments().getString(FIREBASE_USER_ID);
        mUserRef = FirebaseDatabase.getInstance().getReference().child(firebasePath);

        mOutfitsRef = mUserRef.child("newOutfits");
        mOutfitsRef.addChildEventListener(new NewOutfitsChildEventListener());

        mSavedOutfitsItems = new ArrayList<>();

        String time = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        //Log.d("timeforme", formattedDate);
        int intTime = Integer.parseInt(time);



        locationText = (TextView) view.findViewById(R.id.locationTextView);
        tempText = (TextView) view.findViewById(R.id.tempTextView);
        conditionDesciption = (TextView) view.findViewById(R.id.descriptionTextView);
        weatherImage = (ImageView) view.findViewById(R.id.weatherImageView);

        welcomeText = (TextView) view.findViewById(R.id.welcomeTextView);
        if (5 < intTime && intTime <= 12){
            welcomeText.setText("Good Morning!");
        }
        else if (12 < intTime && intTime <= 17){
            welcomeText.setText("Good Afternoon!");
        }
        else if (17 < intTime && intTime <= 21){
            welcomeText.setText("Good Evening!");
        }
        else{
            welcomeText.setText("Good Night!");
        }

        buttonOutfit1 = (ImageButton) view.findViewById(R.id.outfitChoiceOne);
        buttonOutfit2 = (ImageButton) view.findViewById(R.id.outfitChoiceTwo);


        mUserRef.child("zipcode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                zipcode = dataSnapshot.getValue().toString();
                JSONWeatherTask task = new JSONWeatherTask();
                task.execute(new String[]{zipcode});
                Log.d("ZC", zipcode);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        buttonOutfit1.setOnClickListener(this);
        buttonOutfit2.setOnClickListener(this);

        if (tempF < 32){
            view.setBackgroundColor(getResources().getColor(R.color.colorCold));
        }
        else if (tempF < 60){
            view.setBackgroundColor(getResources().getColor(R.color.colorMiddle));
        }
        else{
            view.setBackgroundColor(getResources().getColor(R.color.colorWarm));
        }


        return view;
    }


    class NewOutfitsChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            OutfitItem outfit = dataSnapshot.getValue(OutfitItem.class);
            mSavedOutfitsItems.add(outfit);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            Log.d("PK", "on child changed called");
//            String key = dataSnapshot.getKey();
//            OutfitItem updateOutfit = dataSnapshot.getValue(OutfitItem.class);
////            Log.d("PK", updatePic.getCaption());
//            for (OutfitItem o : mOutfitItems){
//                if (o.getKey().equals(key)){
//                    o.setmType1(updateOutfit.getmType1());
//                    o.setmType2(updateOutfit.getmType2());
//                    o.setmType3(updateOutfit.getmType3());
//                    o.setmType4(updateOutfit.getmType4());
//                    o.setmType5(updateOutfit.getmType5());
//                    o.setmType6(updateOutfit.getmType6());
//                    o.setOutfitName(updateOutfit.getOutfitName());
////                    p.setUrl(updatePic.getUrl());
////                    p.setValues(updatePic);
//                    notifyDataSetChanged();
//                    return;
//               }
//            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
//            String keyToRemove = dataSnapshot.getKey();
//            for(int i = 0; i < mOutfitItems.size();i++){
//                if(keyToRemove.equals(mOutfitItems.get(i).getKey())){
//                    mOutfitItems.remove(i);
//                    notifyDataSetChanged();
//                    return;
//               }
//            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("P", databaseError.getMessage());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartPressedListener) {
            mListener = (OnStartPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStartPressedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.outfitChoiceOne:
                OutfitItem temp = mSavedOutfitsItems.get(0);
                mListener.onStartPressed(temp);
                Log.d("click", "outfit one has been clicked");
                break;
            case R.id.outfitChoiceTwo:
                OutfitItem temp2 = mSavedOutfitsItems.get(1);
                mListener.onStartPressed(temp2);
                Log.d("click", "outfit two has been clicked");
        }
    }

    public interface OnStartPressedListener {
        void onStartPressed(OutfitItem o);
        void onLogout();
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            weatherData = ((new WeatherHttpClient()).getWeatherData(params[0]));

            if (weatherData != null) {
                try {
                    // Get Weather Data
                    weather = JSONWeatherParser.getWeather(weatherData);
                    // Get Weather Icon
                    weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap image = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                weatherImage.setImageBitmap(image);
            }

  if (weatherData != null) {
                locationText.setText(weather.location.getCity() + ", " + weather.location.getCountry());
                float tempC = Math.round((weather.temperature.getTemp() - 273.15));
                tempF = (int) (tempC * 1.8 + 32);
                tempText.setText("" + tempF + " *F");
                weatherID = weather.currentCondition.getWeatherId();
                weatherCond = weather.currentCondition.getCondition();
                Log.d("rain?", weatherCond);
                conditionDesciption.setText(weather.currentCondition.getCondition() + " (" + weather.currentCondition.getDescription() + ")");
            }
        }

    }

}
