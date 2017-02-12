
package edu.rosehulman.schaffll.weathertowear.Weather;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import edu.rosehulman.schaffll.weathertowear.ClothingItem;
import edu.rosehulman.schaffll.weathertowear.ClothingItemList;
import edu.rosehulman.schaffll.weathertowear.fragments.ClosetFragment;

import static edu.rosehulman.schaffll.weathertowear.ClothingItemList.getReturnClothingItemList;

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
    private List<Boolean> mFinalBool = new ArrayList<>();
    private ClothingItemList cil;

    private DatabaseReference mUserRef;

    public Calculations(int hotTemp, int coldTemp, float currentTemp, int weatherID, boolean boolList[]) {
        mHotTemp = hotTemp;
        mColdTemp = coldTemp;
        mCurrentTemp = currentTemp;
        mWeatherID = weatherID;
        mBoolList = boolList;

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

        ClothingItemList mClothingItems = new ClothingItemList(mBoolList);
        userClothingItems = mClothingItems.getReturnClothingItemList();

        userClothingItems = getReturnClothingItemList();
        //getReturnClothingItemList();
        int i = userClothingItems.size();




    }

    public void setBooleanList(boolean t, int position){
        if (t != false){
            mFinalBool.add(position, true);
            Log.d("boolean", "BOOLEAN DOESN't EQUAL FALSE");
        }
        else{
            mFinalBool.add(position, true);
        }
        boolean te = mFinalBool.get(position);
        if (te){
            Log.d("t", "set to true");
        }
    }
//
//    public boolean[] getBooleanList(){
//        return mFinalBool;
//    }


    public void createNewOutfits() {
        userClothingItems = getReturnClothingItemList();
        int i = userClothingItems.size();


        Log.d("tst", "idk: "+ i);
//        for(int i = 0; i < mFinalBool.size(); i++){
//            if (mFinalBool.get(i) == true){
//                Log.d("yes", "true!");
//            }
//        }
//
//        ClothingItemList mClothingItems = new ClothingItemList(mBoolList);
        //userClothingItems = mClothingItems.getReturnClothingItemList();
        //use the available clothing items list
        Log.d("create outfit", "create outfit is used");
    }


    }






