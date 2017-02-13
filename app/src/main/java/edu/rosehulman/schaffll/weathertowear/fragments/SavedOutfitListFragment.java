package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import edu.rosehulman.schaffll.weathertowear.ClothingItemList;
import edu.rosehulman.schaffll.weathertowear.OutfitAdapter;
import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.R;
import edu.rosehulman.schaffll.weathertowear.SavedOutfitAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedOutfitListFragment extends Fragment{

    public static final String FIREBASE_PATH = "FIREBASE_PATH";

    private SavedOutfitAdapter mAdapter;
    private String[] mClothingItems;
    private DatabaseReference mBooleanRef;
    private DatabaseReference mSavedOutfitsRef;
    private boolean mBoolList[];
    private ClothingItemList mClothingItemList;
    private Callback mCallback;

    // Make set list of ClothingItems

    public SavedOutfitListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        mBooleanRef = FirebaseDatabase.getInstance().getReference().child(firebasePath).child("booleanArray");
        mSavedOutfitsRef = FirebaseDatabase.getInstance().getReference().child("savedOutfits");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_new_outfit, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SavedOutfitAdapter(getContext(), mCallback, mSavedOutfitsRef);

        view.setAdapter(mAdapter);
        mClothingItemList = new ClothingItemList();
        mClothingItems = getResources().getStringArray(R.array.clothing_list);
        //mBoolList = new boolean[mClothingItems.length];


//        mBooleanRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                // Empty
//                int position = Integer.parseInt(dataSnapshot.getKey());
//                mBoolList[position] = (boolean) dataSnapshot.getValue();
//                if (mBoolList[position]) {
//                    mAdapter.addItem(mClothingItems[position]);
//                    mClothingItemList.add(position);
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                // Empty
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                // Empty
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                // Empty
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SavedOutfitFragment.Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface Callback {
        void onSavedOutfitSelected(OutfitItem outfitItem);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        save = menu.add("save");
//        save.setIcon(android.R.drawable.ic_menu_save);
//        save.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog();
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete_item);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_delete_outfit, null);
        final TextView deleteOutfit = (TextView) view.findViewById(R.id.deleteOutfitTextView);
        builder.setView(view);


//        builder.setMultiChoiceItems(mClothingItems, mBoolList, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
//                if (isChecked) {
//                    mBoolList[indexSelected] = true;
//                    mBooleanRef.child(""+indexSelected).setValue(true);
//
//                } else {
//                    mBoolList[indexSelected] = false;
//                    mBooleanRef.child(""+indexSelected).setValue(false);
//                }
//            }
//        });

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
//                for (int i = 0; i < mBoolList.length; i++) {
//                    // Not add everytime
//                    if (mBoolList[i] == true) {
//                        mAdapter.addItem(mClothingItems[i]);
//                        mClothingItemList.add(i);
//                    } else {
//                        mAdapter.removeItem(mClothingItems[i]);
//                        mClothingItemList.remove(i);
//                    }
//                }
                dialog.dismiss();
            }
        });

        builder.create().show();

    }
}
