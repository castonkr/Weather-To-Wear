package edu.rosehulman.schaffll.weathertowear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiana on 2/8/17.
 */

public class ClothingItemList {

    public List clothingItemList = new ArrayList<>();


    {
        ClothingItem blouse = new ClothingItem("blouse", 0, 0, 0);
        ClothingItem boots = new ClothingItem("boots", 6, -2, 1);


        clothingItemList.add(blouse);
        clothingItemList.add(boots);
    }


}
