package edu.rosehulman.schaffll.weathertowear;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;




/**
 * Created by Matt Boutell on 12/1/2015.
 */
public class MyClosetAdapter extends RecyclerView.Adapter<MyClosetAdapter.ViewHolder> {

    private ArrayList<String> mClosetItems;

//    private DocListFragment.Callback mCallback;


    public MyClosetAdapter(Context context) {
        mClosetItems = new ArrayList<>();
//        mClosetItems.add("T-shirt");
//        mClosetItems.add("Shorts");
//        mClosetItems.add("Umbrella");
//        mClosetItems = DocUtils.loadDocs(context);
//        mCallback = callback;
    }

    @Override
    public MyClosetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_closet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClosetAdapter.ViewHolder holder, int position) {
//        final Doc doc = mClosetItems.get(position);
//        holder.mTitleTextView.setText(doc.getTitle());
//        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCallback.onDocSelected(doc);
//            }
//        });
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
