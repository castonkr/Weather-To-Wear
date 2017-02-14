
package edu.rosehulman.schaffll.weathertowear.Weather;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.rosehulman.schaffll.weathertowear.ClothingItem;
import edu.rosehulman.schaffll.weathertowear.ClothingItemList;
import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.fragments.ClosetFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.HomeFragment;

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

    public List<OutfitItem> createNewOutfits() {
        ClothingItemList mClothes = new ClothingItemList(mBoolList);
        userClothingItems = mClothes.getReturnClothingItemList();
        List<ClothingItem> availableClothesTemp = new ArrayList<>();
        List<ClothingItem> type1Clothes = new ArrayList<>();
        List<ClothingItem> type2Clothes = new ArrayList<>();
        List<ClothingItem> type3Clothes = new ArrayList<>();
        List<ClothingItem> type4Clothes = new ArrayList<>();
        List<ClothingItem> type5Clothes = new ArrayList<>();
        List<ClothingItem> type6Clothes = new ArrayList<>();
        List<OutfitItem> outfitOptions = new ArrayList<>();


        //gets the clothing appropriate to the weather
        for(int i = 0; i < userClothingItems.size();i++){
            if ((userClothingItems.get(i).getClothingTemp() == (weatherNum)) ||
                    (userClothingItems.get(i).getClothingTemp() == (weatherNum + 1)) ||
                    (userClothingItems.get(i).getClothingTemp() == (weatherNum - 1))){
                availableClothesTemp.add(userClothingItems.get(i));
            }
        }
        for (int i = 0; i < availableClothesTemp.size(); i++){
            int type = availableClothesTemp.get(i).getClothingType();
            if (type == 1){
                type1Clothes.add(availableClothesTemp.get(i));
            }
            else if (type == 2){
                type2Clothes.add(availableClothesTemp.get(i));
            }
            else if (type == 3){
                type3Clothes.add(availableClothesTemp.get(i));
            }
            else if (type == 4){
                type4Clothes.add(availableClothesTemp.get(i));
            }
            else if (type == 5){
                type5Clothes.add(availableClothesTemp.get(i));
            }
            else {
                type6Clothes.add(availableClothesTemp.get(i));
            }
        }
        //put size of lists in order
        //outmost loop = list with the greatest size
        //outmost2 loop = list wih the second greatest size
        //...
        //innermost loop = list with the least size
        int iter1 = 1;
        int iter2 = 1;
        int iter3 = 1;
        int iter4 = 1;
        int iter5 = 1;
        int iter6 = 1;

        if (type1Clothes.size() > 0){
            iter1 = type1Clothes.size();
            Log.d("tag", "correct size set");
        }
        else{
            type1Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        if (type2Clothes.size() > 0){
            iter2 = type2Clothes.size();
        }
        else{
            type2Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        if (type3Clothes.size() > 0){
            iter3 = type3Clothes.size();
        }
        else{
            type3Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        if (type4Clothes.size() > 0){
            iter4 = type4Clothes.size();
        }
        else{
            type4Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        if (type5Clothes.size() > 0){
            iter5 = type5Clothes.size();
        }
        else{
            type5Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        if (type6Clothes.size() > 0){
            iter6 = type6Clothes.size();
        }
        else{
            type6Clothes.add(new ClothingItem("null", 0, 0, 0));
        }
        for (int i = 0; i < iter1; i++){
            for (int j = 0; j < iter2; j++){
                for (int k = 0; k < iter3;k++){
                    for (int l = 0; l < iter4;l++){
                        for (int m = 0; m < iter5;m++){
                            for (int n = 0; n < iter6; n++){
                                OutfitItem newOutfit = new OutfitItem(type1Clothes.get(i), type2Clothes.get(j), type3Clothes.get(k), type4Clothes.get(l), type5Clothes.get(m), type6Clothes.get(n));
                                outfitOptions.add(newOutfit);
                            }
                        }
                    }
                }
            }
        }


        ArrayList<Integer> random = new ArrayList<>();
        ArrayList<OutfitItem> fiveOutfitOptions = new ArrayList<>();

        ClothingItem umbrella = new ClothingItem("Umbrella", 6, 0, 0);

        Log.d("rain", "before checking weather cond");

        if (HomeFragment.weatherCond.equals("Rain")){
            for (int i = 0; i < outfitOptions.size();i++){
                if (outfitOptions.get(i).getmType6() != umbrella) {
                    Log.d("rain", "umbrella doesn't exist in the outfit");
                    outfitOptions.get(i).setmType6(umbrella);
                }
            }
        }



        if (outfitOptions.size() > 5){
            for (int i = 0; i < outfitOptions.size(); i++){
                random.add(i);
            }
            Collections.shuffle(random);
            fiveOutfitOptions.add(outfitOptions.get(random.get(0)));
            fiveOutfitOptions.add(outfitOptions.get(random.get(1)));
            fiveOutfitOptions.add(outfitOptions.get(random.get(2)));
            fiveOutfitOptions.add(outfitOptions.get(random.get(3)));
            fiveOutfitOptions.add(outfitOptions.get(random.get(4)));

        }
        else{
            for (int i = 0; i < outfitOptions.size(); i++){
                fiveOutfitOptions.add(outfitOptions.get(i));
            }
        }

        for(int i = 0; i < fiveOutfitOptions.size(); i++){
            fiveOutfitOptions.get(i).setOutfitName("Outfit: " + (i+1));
            fiveOutfitOptions.get(i).setKey(""+i);
        }


        return fiveOutfitOptions;





    }


    }






