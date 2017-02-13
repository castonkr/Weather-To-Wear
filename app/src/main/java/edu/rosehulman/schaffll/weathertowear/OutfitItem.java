package edu.rosehulman.schaffll.weathertowear;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Kiana on 2/9/17.
 */

public class OutfitItem implements Parcelable{

    private ClothingItem mType1;
    private ClothingItem mType2;
    private ClothingItem mType3;
    private ClothingItem mType4;
    private ClothingItem mType5;
    private ClothingItem mType6;
    //private String key;
    private String mOutfitName;


    public ClothingItem getmType1() {
        if (mType1 == null){
            mType1 = new ClothingItem("null", 0, 0, 0);
        }

        return mType1;
    }

    public void setmType1(ClothingItem mType1) {
        this.mType1 = mType1;
    }

    public ClothingItem getmType2() {
        return mType2;
    }

    public void setmType2(ClothingItem mType2) {
        this.mType2 = mType2;
    }

    public ClothingItem getmType3() {
        return mType3;
    }

    public void setmType3(ClothingItem mType3) {
        this.mType3 = mType3;
    }

    public ClothingItem getmType4() {
        return mType4;
    }

    public void setmType4(ClothingItem mType4) {
        this.mType4 = mType4;
    }

    public ClothingItem getmType5() {
        return mType5;
    }

    public void setmType5(ClothingItem mType5) {
        this.mType5 = mType5;
    }

    public ClothingItem getmType6() {
        return mType6;
    }

    public void setmType6(ClothingItem mType6) {
        this.mType6 = mType6;
    }

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
    public OutfitItem(ClothingItem type1, ClothingItem type2, ClothingItem type3, ClothingItem type4, ClothingItem type5,
                  ClothingItem type6) {
        if (type1 == null){
            mType1 = new ClothingItem("null", 0, 0, 0);
            //Log.d("tag", "shouldn't be null");
        }
        else {
            mType1 = type1;
            //Log.d("tag", "is null..shoudln't be");
        }

        if (type2 == null){
            mType2 = new ClothingItem("null", 0, 0, 0);
        }
        else{
            mType2 = type2;
        }

        if (type3 == null){
            mType3 = new ClothingItem("null", 0, 0, 0);
        }
        else {
            mType3 = type3;
        }

        if (type4 == null){
            mType4 = new ClothingItem("null", 0, 0, 0);
        }
        else {
            mType4 = type4;
        }

        if (type5 == null){
            mType5 = new ClothingItem("null", 0, 0, 0);
        }
        else {
            mType5 = type5;
        }

        if (type6 == null){
            mType6 = new ClothingItem("null", 0, 0, 0);
        }
        else {
            mType6 = type6;
        }

    }

    public OutfitItem(ClothingItem topLayer1) {

    }

    public OutfitItem(){
        //leave empty
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

    public void setOutfitName(String outfitName){
        mOutfitName = outfitName;
    }

    public String getOutfitName(){
        return mOutfitName;
    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getKey(){
//        return this.getKey();
//    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }


}
