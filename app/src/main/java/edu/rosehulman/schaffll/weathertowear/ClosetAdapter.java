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


public class ClosetAdapter extends RecyclerView.Adapter<ClosetAdapter.ViewHolder> {

    private ArrayList<String> mClothingItems;

    public ClosetAdapter(Context context) {
        mClothingItems = new ArrayList<>();
    }

    @Override
    public ClosetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_closet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClosetAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mClothingItems.get(position));

    }

    @Override
    public int getItemCount() {
        return mClothingItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.clothesTypeTextView);
        }
    }

    public void addItem(String item) {
        if(!mClothingItems.contains(item)) {
            mClothingItems.add(item);
            notifyItemInserted(0);
            notifyItemRangeChanged(0, mClothingItems.size());
        }

    }

    public void removeItem(String item) {
        int index = mClothingItems.indexOf(item);
        mClothingItems.remove(item);
        notifyItemRemoved(index);
        notifyItemRangeChanged(0, mClothingItems.size());

    }
}

