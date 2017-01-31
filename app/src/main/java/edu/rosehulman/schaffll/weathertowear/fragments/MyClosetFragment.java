package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.rosehulman.schaffll.weathertowear.ClothingItem;
import edu.rosehulman.schaffll.weathertowear.MyClosetAdapter;
import edu.rosehulman.schaffll.weathertowear.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyClosetFragment extends Fragment {

    private MenuItem add;
    private MyClosetAdapter mAdapter;
    private String[] mClothingItems;
//    private ClothingItem[] mClothingItems;
    private boolean mBoolList[];

    public MyClosetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_my_closet, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyClosetAdapter(getContext());
        view.setAdapter(mAdapter);
//        mClothingItems = mAdapter.
        mClothingItems = getResources().getStringArray(R.array.clothing_list);
        mBoolList = new boolean[mClothingItems.length];

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        add = menu.add("add");
        add.setIcon(android.R.drawable.ic_menu_add);
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog();
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_clothing_title);


        builder.setMultiChoiceItems(mClothingItems, mBoolList, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                if (isChecked) {
                    mBoolList[indexSelected] = true;

                } else {
                    mBoolList[indexSelected] = false;
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int k) {
                // Show selected clothing items
                for (int i = 0; i < mBoolList.length; i++) {
                    // Not add everytime
                    if (mBoolList[i] == true) {
//                        mAdapter.addClothingItem();
//                        mAdapter.addItem(mClothingItems[i]);
                    } else {
//                        mAdapter.removeItem(mClothingItems[i]);
                    }
                }
                dialog.dismiss();
            }
        });

        builder.create().show();

    }

}
