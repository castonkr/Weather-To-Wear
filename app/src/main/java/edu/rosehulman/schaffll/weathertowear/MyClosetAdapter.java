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

    private ArrayList<String> mClosetItems;
    private DatabaseReference mClosetItemsRef;

    public MyClosetAdapter(Context context) {

        mClosetItems = new ArrayList<>();
        mClosetItemsRef = FirebaseDatabase.getInstance().getReference().child("clothingItems");
        //mClosetItemsRef.addChildEventListener(new ClothesChildEventListener());
    }

//    class ClothesChildEventListener implements ChildEventListener{
//
//        @Override
//        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            String str = dataSnapshot.getValue(String.class);
//            str.setKey(dataSnapshot.getKey());
//            mClosetItems.add(s);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//        }
//
//        @Override
//        public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//        }
//
//        @Override
//        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    }

    @Override
    public MyClosetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_closet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClosetAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mClosetItems.get(position));

    }

    @Override
    public int getItemCount() {
        return mClosetItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.clothesTypeTextView);
        }
    }

    public void addItem(String item) {
        if (!mClosetItems.contains(item)) {
            mClosetItems.add(0, item);
            notifyItemInserted(0);
            notifyItemRangeChanged(0, mClosetItems.size());
        }
    }

    public void removeItem(String item) {
        int index = mClosetItems.indexOf(item);
        mClosetItems.remove(item);
        notifyItemRemoved(index);
        notifyItemRangeChanged(0, mClosetItems.size());

    }
}
