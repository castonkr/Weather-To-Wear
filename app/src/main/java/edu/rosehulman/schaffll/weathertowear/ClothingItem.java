package edu.rosehulman.schaffll.weathertowear;

import com.google.firebase.database.Exclude;

/**
 * Created by Kiana on 1/30/17.
 */

public class ClothingItem {
    private String clothingName;
    private int clothingType;
    private String key;

    // For clothingType
    // 0 = top
    // 1 = bottom
    // 2 = shoes
    // 3 = accessory 1


    public ClothingItem() {
        // Empty Constructor
    }

    public ClothingItem(String name, int type) {
        this.clothingName = name;
        this.clothingType = type;
    }



    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public int getClothingType() {
        return clothingType;
    }

    public void setClothingType(int clothingType) {
        this.clothingType = clothingType;
    }

    @Exclude

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
