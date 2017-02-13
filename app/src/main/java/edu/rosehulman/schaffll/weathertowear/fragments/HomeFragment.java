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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import edu.rosehulman.schaffll.weathertowear.R;
import edu.rosehulman.schaffll.weathertowear.Weather.Calculations;
import edu.rosehulman.schaffll.weathertowear.Weather.JSONWeatherParser;
import edu.rosehulman.schaffll.weathertowear.Weather.Weather;
import edu.rosehulman.schaffll.weathertowear.Weather.WeatherHttpClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String FIREBASE_PATH = "FIREBASE_PATH";
    public static final String FIREBASE_USER_ID = "FIREBASE_USER_ID";
    private String weatherData;
    private String mUser;
    private DatabaseReference mUserRef;
    private OnStartPressedListener mListener;
    private Toolbar mToolbar;
    private TextView locationText;
    private TextView tempText;
    private TextView conditionDesciption;
    private ImageView weatherImage;
    public static float tempF;
    public static int weatherID;

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

        locationText = (TextView) view.findViewById(R.id.locationTextView);
        tempText = (TextView) view.findViewById(R.id.tempTextView);
        conditionDesciption = (TextView) view.findViewById(R.id.descriptionTextView);
        weatherImage = (ImageView) view.findViewById(R.id.weatherImageView);

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

        ((Button)view.findViewById(R.id.outfitChoiceOne)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStartPressed();
            }

        });

        ((Button)view.findViewById(R.id.outfitChoiceTwo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStartPressed();
            }

        });
        return view;
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

    public interface OnStartPressedListener {
        public void onStartPressed();
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
                tempF = Math.round(tempC * 1.8) + 32;
                tempText.setText("" + tempF + " *F");
                weatherID = weather.currentCondition.getWeatherId();
                conditionDesciption.setText(weather.currentCondition.getCondition() + " (" + weather.currentCondition.getDescription() + ")");
            }
        }

    }

}
