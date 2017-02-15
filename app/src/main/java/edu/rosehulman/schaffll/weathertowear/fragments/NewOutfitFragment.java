package edu.rosehulman.schaffll.weathertowear.fragments;


import android.content.Context;
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
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.rosehulman.schaffll.weathertowear.ClothingItemList;
import edu.rosehulman.schaffll.weathertowear.OutfitAdapter;
import edu.rosehulman.schaffll.weathertowear.OutfitItem;
import edu.rosehulman.schaffll.weathertowear.R;

import static edu.rosehulman.schaffll.weathertowear.Constants.FIREBASE_PATH;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewOutfitFragment extends Fragment {

    //private MenuItem save;
    private OutfitAdapter mAdapter;
    private String[] mClothingItems;
    private DatabaseReference mBooleanRef;
    private DatabaseReference mNewOutfitRef;
    private boolean mBoolList[];
    private ClothingItemList mClothingItemList;
    private Callback mCallback;

    // Make set list of ClothingItems

    public NewOutfitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String firebasePath = getArguments().getString(FIREBASE_PATH);
        mBooleanRef = FirebaseDatabase.getInstance().getReference().child(firebasePath).child("booleanArray");
       // mNewOutfitRef = FirebaseDatabase.getInstance().getReference().child(firebasePath).child("newOutfits");
        mNewOutfitRef = FirebaseDatabase.getInstance().getReference().child(firebasePath);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_outfit_list, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new OutfitAdapter(getContext(), mCallback, mNewOutfitRef);
        view.setAdapter(mAdapter);
        mClothingItemList = new ClothingItemList();
        mClothingItems = getResources().getStringArray(R.array.clothing_list);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NewOutfitFragment.Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface Callback {
        void onOutfitSelected(OutfitItem outfitItem);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog();
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.save_outfit);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_save_outfit, null);
        final EditText editOutfitName = (EditText) view.findViewById(R.id.saveOutfitEditText);
        builder.setView(view);

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int k) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }
}
