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
    private int isChecked;
    private String key;

    // For clothingType
    // 0 = top
    // 1 = bottom
    // 2 = shoes
    // 3 = accessory 1


    public ClothingItem() {
        // Empty Constructor
    }

    public ClothingItem(String name, int type, int isChecked) {
        this.clothingName = name;
        this.clothingType = type;
        this.isChecked = isChecked;
    }


    protected ClothingItem(Parcel in) {
        clothingName = in.readString();
        clothingType = in.readInt();
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
