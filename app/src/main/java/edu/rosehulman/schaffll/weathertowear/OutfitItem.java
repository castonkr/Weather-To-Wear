package edu.rosehulman.schaffll.weathertowear;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kiana on 2/9/17.
 */

public class OutfitItem implements Parcelable{

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

//    public OutfitItem(ClothingItem topLayer1, ClothingItem topLayer2, ClothingItem topLayer3, ClothingItem bottomShort, ClothingItem bottomLong,
//                        ClothingItem dress, ClothingItem shoes, ClothingItem hat, ClothingItem scarf, ClothingItem accessory ) {
//
//    }

    public OutfitItem(ClothingItem topLayer1) {

    }

    protected OutfitItem(Parcel in) {
    }

    public static final Creator<OutfitItem> CREATOR = new Creator<OutfitItem>() {
        @Override
        public OutfitItem createFromParcel(Parcel in) {
            return new OutfitItem(in);
        }

        @Override
        public OutfitItem[] newArray(int size) {
            return new OutfitItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getOutfitItemName(int position){
        return "outfit 1";
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
