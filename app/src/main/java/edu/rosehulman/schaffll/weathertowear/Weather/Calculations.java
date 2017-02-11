
package edu.rosehulman.schaffll.weathertowear.Weather;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import edu.rosehulman.schaffll.weathertowear.ClothingItem;
import edu.rosehulman.schaffll.weathertowear.ClothingItemList;

/**
 * Created by Kiana on 2/11/17.
 */

public class Calculations {

    private int mHotTemp;
    private int mColdTemp;
    private float mCurrentTemp;
    private int mWeatherID;
    private int weatherNum;
    private List alLClothingItemList;
    private List mClothingItems;
    private boolean mBoolList[] = new boolean[21];
    private List<ClothingItem> userClothingItems;

    private DatabaseReference mUserRef;

    public Calculations(int hotTemp, int coldTemp, float currentTemp, int weatherID, DatabaseReference userRef) {
        mHotTemp = hotTemp;
        mColdTemp = coldTemp;
        mCurrentTemp = currentTemp;
        mWeatherID = weatherID;
        mUserRef = userRef;
//        mBoolList = new boolean[21];

        int medianTemp = (coldTemp + hotTemp) / 2;

        if (currentTemp < coldTemp + 5) {
            weatherNum = -2;
        }

        if ((currentTemp > coldTemp + 5) && (currentTemp < medianTemp - 5)) {
            weatherNum = -1;
        }

        if ((currentTemp < medianTemp + 5) && (currentTemp > medianTemp - 5)) {
            weatherNum = 0;
        }

        if ((currentTemp < hotTemp - 5) && (currentTemp > medianTemp + 5)) {
            weatherNum = 1;
        }

        if(currentTemp > hotTemp - 5) {
            weatherNum = 2;
        }

        //have weather num

        //this will get us to the reference https://weather-to-wear.firebaseio.com/users/QFFVDeWJR4Xs84FpdetUCk1dovG3/booleanArray
        //from there, we need to go through all of the clothing items and see if they are true and false
        //put those into a boolean array
        //

       DatabaseReference booleanArray = mUserRef.child("booleanArray");
        Log.d("firebare", ""+booleanArray);
        //booleanArray.orderByChild("booleanArray").equalTo("true");
        //String k = booleanArray.child("0").getKey();
       // booleanArray.
        //Query booleanArray = mUserRef.orderByChild("booleanArray").equalTo("true");
        //Query trueClothing = booleanArray.orderByKey().equalTo("true");
        booleanArray.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int position = Integer.parseInt(dataSnapshot.getKey());
                mBoolList[position] = (boolean) dataSnapshot.getValue();
                boolean temp = (boolean) dataSnapshot.getValue();
                //        boolean b = mBoolList[0];
        String k;
        if (temp){
            k = "true";
        }
        if (temp == false){
            k = "faslse";
        }
        else{
            k = "null";
        }
                Log.d("test2", k);
//                if (mBoolList[position]) {
//                    //mAdapter.addItem(mClothingItems[position]);
//                    //mClothingItemList.add(position);
//
//                }
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

//        boolean b = mBoolList[0];
//        String k;
//        if (b == true){
//            k = "true";
//        }
//        if (b == false){
//            k = "faslse";
//        }
//        else{
//            k = "null";
//        }

       // Log.d("test", k);
        ClothingItemList mClothingItems = new ClothingItemList(mBoolList);
        userClothingItems = mClothingItems.getReturnClothingItemList();

      //  Log.d("clothing item 1 test", userClothingItems.get(0).getClothingName());


        //Log.d("boolean", ""+booleanArray);
        //booleanArray.addChildEventListener(new )
        //pass this list into a clothing item list and obtain a clothing list with the appropriate clothes from it

    }

    public void createNewOutfits() {
        //use the available clothing items list
        Log.d("create outfit", "create outfit is used");
    }


    }






