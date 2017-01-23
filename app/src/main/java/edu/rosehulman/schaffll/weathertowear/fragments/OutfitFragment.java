package edu.rosehulman.schaffll.weathertowear.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import edu.rosehulman.schaffll.weathertowear.MySavedOutfits;
import edu.rosehulman.schaffll.weathertowear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutfitFragment extends Fragment {

    MenuItem save;
    MenuItem remove;


    public OutfitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outfit, container, false);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        save = menu.add("Save");
        save.setIcon(android.R.drawable.ic_menu_add);
        // TODO: Show Save only if NewOutfits
        save.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_WITH_TEXT);


        remove = menu.add("Remove");
        remove.setIcon(android.R.drawable.ic_delete);
        // TODO: Show Remove only if SavedOutfits
        remove.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        super.onCreateOptionsMenu(menu, inflater);
    }

}
