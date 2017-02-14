package edu.rosehulman.schaffll.weathertowear;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.rosehulman.schaffll.weathertowear.fragments.NewOutfitFragment;
import edu.rosehulman.schaffll.weathertowear.fragments.SavedOutfitListFragment;


public class SavedOutfitAdapter extends RecyclerView.Adapter<SavedOutfitAdapter.ViewHolder> {

    private ArrayList<OutfitItem> mOutfitItems;
    private SavedOutfitListFragment.Callback mCallback;
    LayoutInflater mInflater;
    private DatabaseReference mSavedOutfitsRef;

    public SavedOutfitAdapter(Context context, SavedOutfitListFragment.Callback callback, DatabaseReference firebaseDatabase) {
        mOutfitItems = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        //mOutfitItems.add(0, new OutfitItem(new ClothingItem("top", 2, 30, 2)));
        mCallback = callback;

        mSavedOutfitsRef = firebaseDatabase.child("savedOutfits");
        mSavedOutfitsRef.addChildEventListener(new SavedOutfitsChildEventListener());

    }

    @Override
    public SavedOutfitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_new_outfit_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavedOutfitAdapter.ViewHolder holder, int position) {
        final OutfitItem outfitItem = mOutfitItems.get(position);
        holder.mTitleTextView.setText(outfitItem.getOutfitName());
        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("WTW", "saved outfit has been clicked on");
                mCallback.onSavedOutfitSelected(outfitItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOutfitItems.size();
    }


    class SavedOutfitsChildEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            OutfitItem outfit = dataSnapshot.getValue(OutfitItem.class);

            outfit.setKey(dataSnapshot.getKey());
            Log.d("kiki", dataSnapshot.getKey());
            mOutfitItems.add(outfit);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d("PK", "on child changed called");
            String key = dataSnapshot.getKey();
            OutfitItem updateOutfit = dataSnapshot.getValue(OutfitItem.class);
            Log.d("PK", updateOutfit.getOutfitName());
            for (OutfitItem o : mOutfitItems){
                if (o.getKey().equals(key)){
                    o.setmType1(updateOutfit.getmType1());
                    o.setmType2(updateOutfit.getmType2());
                    o.setmType3(updateOutfit.getmType3());
                    o.setmType4(updateOutfit.getmType4());
                    o.setmType5(updateOutfit.getmType5());
                    o.setmType6(updateOutfit.getmType6());
                    o.setOutfitName(updateOutfit.getOutfitName());
//                    p.setUrl(updatePic.getUrl());
//                    p.setValues(updatePic);
                    notifyDataSetChanged();
                    return;
               }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
           String keyToRemove = dataSnapshot.getKey();
            for(int i = 0; i < mOutfitItems.size();i++){
                if(keyToRemove.equals(mOutfitItems.get(i).getKey())){
                    mOutfitItems.remove(i);
                    notifyDataSetChanged();
                    return;
               }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("P", databaseError.getMessage());
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.outfitTypeTextView);
            mTitleTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    Log.d("adapter", "item has been long clicked");
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(mInflater.getContext());
//                    builder.setTitle(R.string.save_outfit);
//                    LayoutInflater inflater = LayoutInflater.from(mInflater.getContext());
//                    final View contentView = inflater.inflate(R.layout.dialog_save_outfit, null, false);
//                    final EditText editOutfitName = (EditText) contentView.findViewById(R.id.saveOutfitEditText);
//                    builder.setView(view);
//
//                    builder.create().show();
                    final OutfitItem outfitItem = mOutfitItems.get(getAdapterPosition());

                    final View contentView = mInflater.inflate(R.layout.dialog_delete_outfit, null, false);
                    //final TextView editOutfitName = (EditText) contentView.findViewById(R.id.deleteOutfitTextView);

//                    final EditText serviceView = (EditText) contentView.findViewById(R.id.service);
//                    final EditText usernameView = (EditText) contentView.findViewById(R.id.username);
//                    final EditText passwordView = (EditText) contentView.findViewById(R.id.password);
                    //passwordView.setImeActionLabel("Save", EditorInfo.IME_NULL);
                    //serviceView.setText(password.getService());
                    //usernameView.setText(password.getUsername());
                    //passwordView.setText(password.getPassword());

                    final Dialog dialog = new AlertDialog.Builder(mInflater.getContext())
                            .setTitle("Delete Outfit")
                            .setView(contentView)
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(R.string.delete_simple, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    password.setService(serviceView.getText().toString());
//                                    password.setPassword(passwordView.getText().toString());
//                                    String username = usernameView.getText().toString();
//                                    password.setUsername(username.isEmpty() ? null : username);
//                                    firebaseUpdate(password);
                                    mOutfitItems.remove(getAdapterPosition());
                                    removeOutfitItem(outfitItem);
                                }
                            })
                            .create();
                    dialog.show();

                    return false;
                }
            });
            mTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("adapter", "item has been clicked");
                    mCallback.onSavedOutfitSelected(mOutfitItems.get(getAdapterPosition()));
                }
            });
        }
    }

    public void removeOutfitItem(OutfitItem outfitItem){
        mSavedOutfitsRef.child(outfitItem.getKey()).removeValue();
        //notifyDataSetChanged();
    }

//    public void addItem(String item) {
//        if(!mClothingItems.contains(item)) {
//            mClothingItems.add(item);
//            notifyItemInserted(0);
//            notifyItemRangeChanged(0, mClothingItems.size());
//        }
//
//    }

//    public void removeItem(String item) {
//        int index = mClothingItems.indexOf(item);
//        mClothingItems.remove(item);
//        notifyItemRemoved(index);
//        notifyItemRangeChanged(0, mClothingItems.size());
//
//    }
}
