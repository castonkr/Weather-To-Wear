package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.rosehulman.schaffll.weathertowear.ClosetAdapter;
import edu.rosehulman.schaffll.weathertowear.ClothingItemList;
import edu.rosehulman.schaffll.weathertowear.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClosetFragment extends Fragment {

    public static final String FIREBASE_PATH = "FIREBASE_PATH";

    private MenuItem add;
    private ClosetAdapter mAdapter;
    private String[] mClothingItems;
    private DatabaseReference mBooleanRef;
    private boolean mBoolList[];
    private ClothingItemList mClothingItemList;

    // Make set list of ClothingItems

    public ClosetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        mBooleanRef = FirebaseDatabase.getInstance().getReference().child(firebasePath).child("booleanArray");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_closet, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ClosetAdapter(getContext());
        view.setAdapter(mAdapter);
        mClothingItemList = new ClothingItemList();
        mClothingItems = getResources().getStringArray(R.array.clothing_list);
        mBoolList = new boolean[mClothingItems.length];


        mBooleanRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Empty
                int position = Integer.parseInt(dataSnapshot.getKey());
                mBoolList[position] = (boolean) dataSnapshot.getValue();
                if (mBoolList[position]) {
                    mAdapter.addItem(mClothingItems[position]);
                    //mClothingItemList.add(position);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // Empty
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // Empty
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                // Empty
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return view;
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
                    mBooleanRef.child(""+indexSelected).setValue(true);

                } else {
                    mBoolList[indexSelected] = false;
                    mBooleanRef.child(""+indexSelected).setValue(false);
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
                        mAdapter.addItem(mClothingItems[i]);
                        //mClothingItemList.add(i);
                    } else {
                        mAdapter.removeItem(mClothingItems[i]);
                        //mClothingItemList.remove(i);
                    }
                }
                dialog.dismiss();
            }
        });

        builder.create().show();

    }
}
