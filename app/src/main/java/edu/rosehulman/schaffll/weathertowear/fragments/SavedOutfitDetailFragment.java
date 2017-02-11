package edu.rosehulman.schaffll.weathertowear.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_outfit_detail, container, false);

        TextView outfitTitleView = (TextView) view.findViewById(R.id.textOutfitTitle);
        outfitTitleView.setText(mOutfitItem.getOutfitItemName(0));

        TextView jacketView = (TextView) view.findViewById(R.id.textJacket);
        jacketView.setText("test jacket");
        //jacketView.setText(mOutfitItem.getJacket());

        TextView topView = (TextView) view.findViewById(R.id.textTops);
        topView.setText("test tops");
        //topView.setText(mOutfitItem.getTop());


        TextView accessoryOneView = (TextView) view.findViewById(R.id.textAccessory1);
        accessoryOneView.setText("test accessory 1");
        //accessoryOneView.setText(mOutfitItem.getAccessoryOne();

        TextView bottomView = (TextView) view.findViewById(R.id.textBottoms);
        bottomView.setText("test bottom");
        //bottomView.setText(mOutfitItem.getBottom());

        TextView accessoryTwoView = (TextView) view.findViewById(R.id.textAccessory2);
        accessoryTwoView.setText("test accessory 2");
        //accessoryTwoView.setText(mOutfitItem.getAccessoryTwo());

        TextView shoesView = (TextView) view.findViewById(R.id.textShoes);
        shoesView.setText("test shoes");
        //shoesView.setText(mOutfitItem.getShoes());

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_new_outfit_detail, container, false);
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
}