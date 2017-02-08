package edu.rosehulman.schaffll.weathertowear;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

/**
 * Created by Kiana on 1/30/17.
 */

public class ClothingItem implements Parcelable{
    private String clothingName;
    private int clothingType;
    private int clothingTemp;
    private int clothingCond;
    private int isChecked;
    private String key;

    // For clothingType
    // 0 = top layer 1
    // 1 = top layer 2
    // 2 = top layer 3
    // 3 = bottom short
    // 4 = bottom long
    // 5 = dress
    // 6 = shoes
    // 7 = hat
    // 8 = gloves
    // 9 = scarf
    // 10 = accessory


    // For clothingTemp
    // -3 = very cold
    // -2 = cold
    // -1 = cool
    // 0 = neutral
    // 1 = warm
    // 2 = hot
    // 3 = very hot

    // For clothingCond
    // 0 = clear
    // 1 = rain
    // 2 = snow


    public ClothingItem() {
        // Empty Constructor
    }

    public ClothingItem(String name, int type, int temp, int cond, int isChecked) {
        this.clothingName = name;
        this.clothingType = type;
        this.clothingTemp = temp;
        this.clothingCond = cond;
        this.isChecked = isChecked;
    }


    protected ClothingItem(Parcel in) {
        clothingName = in.readString();
        clothingType = in.readInt();
        clothingTemp = in.readInt();
        clothingCond = in.readInt();
        isChecked = in.readInt();
    }

    public static final Creator<ClothingItem> CREATOR = new Creator<ClothingItem>() {
        @Override
        public ClothingItem createFromParcel(Parcel in) {
            return new ClothingItem(in);
        }

        @Override
        public ClothingItem[] newArray(int size) {
            return new ClothingItem[size];
        }
    };

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

    public int getClothingTemp() {
        return clothingTemp;
    }

    public void setClothingTemp(int clothingTemp) {
        this.clothingTemp = clothingTemp;
    }

    public int getClothingCond() {
        return clothingCond;
    }

    public void setClothingCond(int clothingCond) {
        this.clothingCond = clothingCond;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(clothingName);
        parcel.writeInt(clothingType);
        parcel.writeInt(clothingTemp);
        parcel.writeInt(clothingCond);
        parcel.writeInt(isChecked);
    }

    @Exclude

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
