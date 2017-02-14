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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link} interface
 * to handle interaction events.
 * Use the {@link SavedOutfitDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedOutfitDetailFragment extends Fragment {
    private static final String ARG_OUTFIT = "outfit";


    private OutfitItem mOutfitItem;
    private OnFlingListenerSavedOutfit mListener;
    private GestureDetectorCompat mGestureDetector;
    //private OnFragmentInteractionListener mListener;

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
        View view = inflater.inflate(R.layout.fragment_outfit_detail, container, false);

        TextView outfitTitleView = (TextView) view.findViewById(R.id.textOutfitTitle);
        outfitTitleView.setText(mOutfitItem.getOutfitName());

        TextView jacketView = (TextView) view.findViewById(R.id.textJacket);
        //jacketView.setText("test jacket");
        jacketView.setText(mOutfitItem.getmType1().getClothingName());

        TextView topView = (TextView) view.findViewById(R.id.textTops);
        //topView.setText("test tops");
        topView.setText(mOutfitItem.getmType2().getClothingName());


        TextView accessoryOneView = (TextView) view.findViewById(R.id.textAccessory1);
        //accessoryOneView.setText("test accessory 1");
        accessoryOneView.setText(mOutfitItem.getmType3().getClothingName());

        TextView bottomView = (TextView) view.findViewById(R.id.textBottoms);
        //bottomView.setText("test bottom");
        bottomView.setText(mOutfitItem.getmType4().getClothingName());

        TextView accessoryTwoView = (TextView) view.findViewById(R.id.textAccessory2);
        //accessoryTwoView.setText("test accessory 2");
        accessoryTwoView.setText(mOutfitItem.getmType5().getClothingName());

        TextView shoesView = (TextView) view.findViewById(R.id.textShoes);
        //shoesView.setText("test shoes");
        shoesView.setText(mOutfitItem.getmType6().getClothingName());

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_outfit_detail, container, false);
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d("wtw", "onFling: " + event1.toString() + event2.toString());
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    public interface OnFlingListenerSavedOutfit {
        void onSwipeSavedOutfit();
    }
}
