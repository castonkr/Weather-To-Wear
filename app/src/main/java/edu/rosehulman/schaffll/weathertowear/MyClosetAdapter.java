package edu.rosehulman.schaffll.weathertowear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;




/**
 * Created by Matt Boutell on 12/1/2015.
 */
public class MyClosetAdapter extends RecyclerView.Adapter<MyClosetAdapter.ViewHolder> {

    private ArrayList<String> mClothingItems;
//    private ArrayList<ClothingItem> mClothingItems;
    private DatabaseReference mClothingItemsRef;

    public MyClosetAdapter(Context context) {


        mClothingItems = new ArrayList<>();
        mClothingItemsRef = FirebaseDatabase.getInstance().getReference().child("clothingItems");
        mClothingItemsRef.addChildEventListener(new ClothesChildEventListener());
    }

    class ClothesChildEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            ClothingItem clothingItem = dataSnapshot.getValue(ClothingItem.class);
//            clothingItem.setKey(dataSnapshot.getKey());
//            mClothingItems.add(0, clothingItem);
            String str = (String) dataSnapshot.getValue();
            mClothingItems.add(0,str);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            // empty at the moment
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
//            for (ClothingItem c : mClothingItems) {
//
//            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            // empty
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // empty
        }
    }

    @Override
    public MyClosetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_closet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClosetAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mClothingItems.get(position));

    }

    @Override
    public int getItemCount() {
//        return mClosetItems.size();
        return mClothingItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.clothesTypeTextView);
        }
    }

    public void addClothingItem(ClothingItem item) {

    }

    public void removeClothingItem(ClothingItem item) {

    }


//    public void addItem(String item) {
//        if (!mClosetItems.contains(item)) {
//            mClothingItemsRef.push().setValue(item);
//            notifyItemInserted(0);
//            notifyItemRangeChanged(0, mClosetItems.size());
//        }
//    }

//    public void removeItem(String item) {

//        mClothingItemsRef.child()
//        int index = mClosetItems.indexOf(item);
//        mClosetItems.remove(item);
//        notifyItemRemoved(index);
//        notifyItemRangeChanged(0, mClosetItems.size());

//    }
}
