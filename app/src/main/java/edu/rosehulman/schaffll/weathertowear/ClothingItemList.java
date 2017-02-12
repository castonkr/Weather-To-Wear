package edu.rosehulman.schaffll.weathertowear;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiana on 2/8/17.
 */

public class ClothingItemList {

    public List<ClothingItem> allClothingItemList = new ArrayList<>();
    private List mClothingItemList = new ArrayList<>();
    private boolean [] mBooleanClothingItems;
    public static List<ClothingItem> returnClothingItemList = new ArrayList<>();

    {
        ClothingItem blouse = new ClothingItem("Blouse", 0, 0, 0);
        ClothingItem boots = new ClothingItem("Boots", 6, -2, 1);
        ClothingItem cardigan = new ClothingItem("Cardigan", 0, 0, 0);
        ClothingItem dress = new ClothingItem("Dress", 0, 0, 0);
        ClothingItem gloves = new ClothingItem("Gloves", 0, 0, 0);
        ClothingItem hat = new ClothingItem("Hat", 0, 0, 0);
        ClothingItem hoody = new ClothingItem("Hoody", 0, 0, 0);
        ClothingItem jacket = new ClothingItem("Jacket", 0, 0, 0);
        ClothingItem jeans = new ClothingItem("Jeans", 0, 0, 0);
        ClothingItem sandals = new ClothingItem("Sandals", 0, 0, 0);
        ClothingItem scarf = new ClothingItem("Scarf", 0, 0, 0);
        ClothingItem shirt = new ClothingItem("Shirt", 0, 0, 0);
        ClothingItem skirt = new ClothingItem("Skirt", 0, 0, 0);
        ClothingItem sneakers = new ClothingItem("Sneakers", 0, 0, 0);
        ClothingItem sweater = new ClothingItem("Sweater", 0, 0, 0);
        ClothingItem sweatpants = new ClothingItem("Sweatpants", 0, 0, 0);
        ClothingItem sweatshirt = new ClothingItem("Sweatshirt", 0, 0, 0);
        ClothingItem tshirt = new ClothingItem("T-Shirt", 0, 0, 0);
        ClothingItem tanktop = new ClothingItem("Tanktop", 0, 0, 0);
        ClothingItem umbrella = new ClothingItem("Umbrella", 0, 0, 0);
        ClothingItem wintercoat = new ClothingItem("Winter Coat", 0, 0, 0);


        allClothingItemList.add(blouse);
        allClothingItemList.add(boots);
        allClothingItemList.add(cardigan);
        allClothingItemList.add(dress);
        allClothingItemList.add(gloves);
        allClothingItemList.add(hat);
        allClothingItemList.add(hoody);
        allClothingItemList.add(jacket);
        allClothingItemList.add(jeans);
        allClothingItemList.add(sandals);
        allClothingItemList.add(scarf);
        allClothingItemList.add(shirt);
        allClothingItemList.add(skirt);
        allClothingItemList.add(sneakers);
        allClothingItemList.add(sweater);
        allClothingItemList.add(sweatpants);
        allClothingItemList.add(sweatshirt);
        allClothingItemList.add(tshirt);
        allClothingItemList.add(tanktop);
        allClothingItemList.add(umbrella);
        allClothingItemList.add(wintercoat);
    }

    public ClothingItemList() {
        //
    }

    public ClothingItemList(boolean[] clothingItemsinFirebase) {
        Log.d("test", "in the constructor");
        mBooleanClothingItems = clothingItemsinFirebase;

        for (int i = 0; i < allClothingItemList.size(); i++) {
            Log.d("LIST", String.valueOf(clothingItemsinFirebase[i]));
            if (mBooleanClothingItems[i]) {
                Log.d("test", "clothing item has been added at " + i + " pos");
                returnClothingItemList.add(allClothingItemList.get(i));
            }
        }
    }


    public void addBoolean(boolean b, int position){
        if (b != false){
            //mBooleanClothingItems[position] = true;
            Log.d("work", "true");
            returnClothingItemList.add(allClothingItemList.get(position));
            ClothingItem c = returnClothingItemList.get(returnClothingItemList.size()-1);
            String r = c.getClothingName();
            Log.d("name", r);

        }
        else{
            Log.d("work", "false");
            //mBooleanClothingItems[position] = false;

        }
    }

    public static List<ClothingItem> getReturnClothingItemList(){
//        for (int i = 0; i < allClothingItemList.size(); i++) {
        int k = returnClothingItemList.size();
           Log.d("help!", ""+k);
//            if (mBooleanClothingItems[i] != false) {
//                Log.d("test", "clothing item has been added at " + i + " pos");
//                returnClothingItemList.add(allClothingItemList.get(i));
//            }
//        }
        return returnClothingItemList;
    }

    public void add(int pos) {
        mClothingItemList.add(allClothingItemList.get(pos));
    }

    public void remove(int pos) {
        mClothingItemList.remove(allClothingItemList.get(pos));
    }

    public List getAllClothingItems(){
        return allClothingItemList;
    }





}
