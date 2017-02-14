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
    public List<ClothingItem> returnClothingItemList = new ArrayList<>();

    {
        ClothingItem blouse = new ClothingItem("Blouse", 1, 0, 0);
        ClothingItem boots = new ClothingItem("Boots", 3, -1, 1);
        ClothingItem cardigan = new ClothingItem("Cardigan", 4, 0, 0);
        ClothingItem gloves = new ClothingItem("Gloves", 5, -1, 0);
        ClothingItem hat = new ClothingItem("Hat", 5, -1, 0);
        ClothingItem hoodie = new ClothingItem("Hoodie", 4, 0, 0);
        ClothingItem jacket = new ClothingItem("Jacket", 4, -1, 0);
        ClothingItem jeans = new ClothingItem("Jeans", 2, 0, 0);
        ClothingItem polo = new ClothingItem("Polo", 1, 0, 0);
        ClothingItem sandals = new ClothingItem("Sandals", 3, 2, 0);
        ClothingItem scarf = new ClothingItem("Scarf", 5, -1, 0);
        ClothingItem shorts = new ClothingItem("Shorts", 2, 1, 0);
        ClothingItem skirt = new ClothingItem("Skirt", 2, 0, 0);
        ClothingItem sneakers = new ClothingItem("Sneakers", 3, 0, 0);
        ClothingItem sweater = new ClothingItem("Sweater", 1, -1, 0);
        ClothingItem sweatpants = new ClothingItem("Sweatpants", 2, 0, 0);
        ClothingItem sweatshirt = new ClothingItem("Sweatshirt", 1, -1, 0);
        ClothingItem tshirt = new ClothingItem("T-Shirt", 1, 0, 0);
        ClothingItem tanktop = new ClothingItem("Tanktop", 1, 1, 0);
        ClothingItem umbrella = new ClothingItem("Umbrella", 6, 0, 0);
        ClothingItem wintercoat = new ClothingItem("Winter Coat", 4, -2, 0);


        allClothingItemList.add(blouse);
        allClothingItemList.add(boots);
        allClothingItemList.add(cardigan);
        allClothingItemList.add(gloves);
        allClothingItemList.add(hat);
        allClothingItemList.add(hoodie);
        allClothingItemList.add(jacket);
        allClothingItemList.add(jeans);
        allClothingItemList.add(polo);
        allClothingItemList.add(sandals);
        allClothingItemList.add(scarf);
        allClothingItemList.add(shorts);
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
            if (mBooleanClothingItems[i]) {
                Log.d("test", "clothing item has been added at " + i + " pos");
                returnClothingItemList.add(allClothingItemList.get(i));
            }
        }
    }


    public List<ClothingItem> getReturnClothingItemList(){
        int k = returnClothingItemList.size();
        return returnClothingItemList;
    }

    public void add(int pos) {
        mClothingItemList.add(allClothingItemList.get(pos));
    }

    public void remove(int pos) {
        mClothingItemList.remove(allClothingItemList.get(pos));
    }

//    public List getAllClothingItems(){
//        return allClothingItemList;
//    }





}
