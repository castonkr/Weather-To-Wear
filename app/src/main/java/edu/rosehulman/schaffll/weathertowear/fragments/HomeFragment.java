package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.rosehulman.schaffll.weathertowear.MyNewOutfits;
import edu.rosehulman.schaffll.weathertowear.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private OnStartPressedListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((Button)view.findViewById(R.id.outfitChoiceOne)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStartPressed();
            }

        });

        ((Button)view.findViewById(R.id.outfitChoiceTwo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStartPressed();
            }

        });

       // return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartPressedListener) {
            mListener = (OnStartPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStartPressedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnStartPressedListener {
        public void onStartPressed();
    }

}
