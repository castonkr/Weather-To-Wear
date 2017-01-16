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

    private ArrayList<String> mDocs;

//    private DocListFragment.Callback mCallback;


    public MyClosetAdapter(Context context) {
        mDocs = new ArrayList<>();
        mDocs.add("T-shirt");
        mDocs.add("Shorts");
        mDocs.add("Umbrella");
//        mDocs = DocUtils.loadDocs(context);
//        mCallback = callback;
    }

    @Override
    public MyClosetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_closet_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClosetAdapter.ViewHolder holder, int position) {
//        final Doc doc = mDocs.get(position);
//        holder.mTitleTextView.setText(doc.getTitle());
//        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCallback.onDocSelected(doc);
//            }
//        });
        holder.mTitleTextView.setText(mDocs.get(position));

    }

    @Override
    public int getItemCount() {
        return mDocs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
           mTitleTextView = (TextView) itemView.findViewById(R.id.clothesTypeTextView);
        }
    }
}
