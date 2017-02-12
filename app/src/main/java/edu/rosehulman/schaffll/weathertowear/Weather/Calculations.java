
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

//import static edu.rosehulman.schaffll.weathertowear.ClothingItemList.getReturnClothingItemList;

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



    }



    public void createNewOutfits() {
        ClothingItemList mClothes = new ClothingItemList(mBoolList);
        userClothingItems = mClothes.getReturnClothingItemList();


        Log.d("OUTFIT_TEST", "0" + userClothingItems.get(0).getClothingName());
        Log.d("OUTFIT_TEST", "1" + userClothingItems.get(1).getClothingName());
        Log.d("OUTFIT_TEST", "2" + userClothingItems.get(2).getClothingName());
        Log.d("OUTFIT_TEST", "3" + userClothingItems.get(3).getClothingName());
        Log.d("OUTFIT_TEST", "4" + userClothingItems.get(4).getClothingName());
        Log.d("OUTFIT_TEST", "5" + userClothingItems.get(5).getClothingName());
        Log.d("OUTFIT_TEST", "6" + userClothingItems.get(6).getClothingName());
        Log.d("OUTFIT_TEST", "7" + userClothingItems.get(7).getClothingName());
        Log.d("OUTFIT_SIZE", String.valueOf(userClothingItems.size()));

        for(int i = 0; i < userClothingItems.size();i++){
            if (userClothingItems.get(i).getClothingTemp() != (weatherNum) ||
                    userClothingItems.get(i).getClothingTemp() != (weatherNum + 1) ||
                    userClothingItems.get(i).getClothingTemp() != (weatherNum - 1)){
                userClothingItems.remove(i);
            }
        }
        Log.d("OUTFIT_new outfit", ""+weatherNum);
        Log.d("OUTFIT_new outfit", String.valueOf(userClothingItems.size()));
        Log.d("OUTFIT_new outfit", "0" + userClothingItems.get(0).getClothingName());
        Log.d("OUTFIT_new outfit", "1" + userClothingItems.get(1).getClothingName());
        Log.d("OUTFIT_new outfit", "2" + userClothingItems.get(2).getClothingName());
        Log.d("OUTFIT_new outfit", "3" + userClothingItems.get(3).getClothingName());

    }


    }






