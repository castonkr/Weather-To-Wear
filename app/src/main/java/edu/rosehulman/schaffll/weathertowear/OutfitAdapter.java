package edu.rosehulman.schaffll.weathertowear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import edu.rosehulman.schaffll.weathertowear.fragments.NewOutfitFragment;


public class OutfitAdapter extends RecyclerView.Adapter<OutfitAdapter.ViewHolder> {

    private ArrayList<OutfitItem> mOutfitItems;
    private NewOutfitFragment.Callback mCallback;

    public OutfitAdapter(Context context, NewOutfitFragment.Callback callback) {
        mOutfitItems = new ArrayList<>();
        mOutfitItems.add(0, new OutfitItem(new ClothingItem("top", 2, 30, 2)));
        mCallback = callback;
    }

    @Override
    public OutfitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_new_outfit_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OutfitAdapter.ViewHolder holder, int position) {
        final OutfitItem outfitItem = mOutfitItems.get(position);
        holder.mTitleTextView.setText(outfitItem.getOutfitItemName(position));
        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("WTW", "outfit has been clicked on");
                mCallback.onOutfitSelected(outfitItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOutfitItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.outfitTypeTextView);
        }
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
