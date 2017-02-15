package edu.rosehulman.schaffll.weathertowear.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.R;

import static edu.rosehulman.schaffll.weathertowear.Constants.ARG_OUTFIT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link} interface
 * to handle interaction events.
 * Use the {@link SavedOutfitDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedOutfitDetailFragment extends Fragment {

    private OutfitItem mOutfitItem;
    private OnFlingListenerSavedOutfit mListener;
    private GestureDetectorCompat mGestureDetector;

    public SavedOutfitDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param
     * @param
     * @return A new instance of fragment NewOutfitDetailFragment.
     */
    public static SavedOutfitDetailFragment newInstance(OutfitItem outfitItem) {
        SavedOutfitDetailFragment fragment = new SavedOutfitDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_OUTFIT, outfitItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOutfitItem = getArguments().getParcelable(ARG_OUTFIT);
        }
        mGestureDetector  = new GestureDetectorCompat(getContext(), new MyGestureDetector());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_outfit_detail, container, false);

        TextView outfitTitleView = (TextView) view.findViewById(R.id.textOutfitTitle);
        outfitTitleView.setText(mOutfitItem.getOutfitName());

        // Tops Text View
        TextView topsView = (TextView) view.findViewById(R.id.textTops);
        if (!mOutfitItem.getmType1().getClothingName().equals("None")) {
            topsView.setText(mOutfitItem.getmType1().getClothingName());
        }
        else{
            topsView.setVisibility(View.INVISIBLE);
        }

        // Bottoms Text VIew
        TextView bottomsView = (TextView) view.findViewById(R.id.textBottoms);
        if (!mOutfitItem.getmType2().getClothingName().equals("None")) {
            bottomsView.setText(mOutfitItem.getmType2().getClothingName());
        }
        else{
            bottomsView.setVisibility(View.INVISIBLE);
        }

        // Shoes Text View
        TextView shoesView = (TextView) view.findViewById(R.id.textShoes);
        if (!mOutfitItem.getmType3().getClothingName().equals("None")) {
            shoesView.setText(mOutfitItem.getmType3().getClothingName());
        }
        else{
            shoesView.setVisibility(View.INVISIBLE);
        }

        // Jacket Text View
        TextView jacketView = (TextView) view.findViewById(R.id.textJacket);
        if (!mOutfitItem.getmType4().getClothingName().equals("None")) {
            jacketView.setText(mOutfitItem.getmType4().getClothingName());
        }
        else{
            jacketView.setVisibility(View.INVISIBLE);
        }

        // Accessory1 Text View
        TextView accessory1View = (TextView) view.findViewById(R.id.textAccessory1);
        if (!mOutfitItem.getmType5().getClothingName().equals("None")) {
            accessory1View.setText(mOutfitItem.getmType5().getClothingName());
        }
        else{
            accessory1View.setVisibility(View.INVISIBLE);
        }

        //Accessory2 Text View
        TextView accessory2View = (TextView) view.findViewById(R.id.textAccessory2);
        if (!mOutfitItem.getmType6().getClothingName().equals("None")) {
            accessory2View.setText(mOutfitItem.getmType6().getClothingName());
        }
        else{
            accessory2View.setVisibility(View.INVISIBLE);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        return view;
    }

class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        mListener.onSwipeSavedOutfit();
        return true;
    }
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFlingListenerSavedOutfit) {
            mListener = (OnFlingListenerSavedOutfit) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFlingListener");
        }
    }
    //
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFlingListenerSavedOutfit {
        void onSwipeSavedOutfit();
    }
}
