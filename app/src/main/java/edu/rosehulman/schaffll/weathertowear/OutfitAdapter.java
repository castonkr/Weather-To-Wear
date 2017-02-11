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
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.rosehulman.schaffll.weathertowear.fragments.NewOutfitFragment;


public class OutfitAdapter extends RecyclerView.Adapter<OutfitAdapter.ViewHolder> implements ChildEventListener{

    private ArrayList<OutfitItem> mOutfitItems;
    private NewOutfitFragment.Callback mCallback;
    private LayoutInflater mInflater;
    private DatabaseReference mOutfitsRef;

    public OutfitAdapter(Context context, NewOutfitFragment.Callback callback, DatabaseReference firebaseDatabase) {
        mOutfitItems = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mOutfitItems.add(0, new OutfitItem(new ClothingItem("top", 2, 30, 2)));
        mCallback = callback;
        mOutfitsRef = firebaseDatabase;
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
//        holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("WTW", "outfit has been clicked on");
//                mCallback.onOutfitSelected(outfitItem);
//            }
//        });
//        holder.mTitleTextView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.d("adapter", "outfit has been long clicked on");
//                //showDialog();
//                return false;
//            }
//        });

    }

//    public void showDialog(){
//
//    }

    @Override
    public int getItemCount() {
        return mOutfitItems.size();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

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

                    final View contentView = mInflater.inflate(R.layout.dialog_save_outfit, null, false);
                    final EditText editOutfitName = (EditText) contentView.findViewById(R.id.saveOutfitEditText);

//                    final EditText serviceView = (EditText) contentView.findViewById(R.id.service);
//                    final EditText usernameView = (EditText) contentView.findViewById(R.id.username);
//                    final EditText passwordView = (EditText) contentView.findViewById(R.id.password);
                    //passwordView.setImeActionLabel("Save", EditorInfo.IME_NULL);
                    //serviceView.setText(password.getService());
                    //usernameView.setText(password.getUsername());
                    //passwordView.setText(password.getPassword());

                    final Dialog dialog = new AlertDialog.Builder(mInflater.getContext())
                            .setTitle(R.string.save_outfit)
                            .setView(contentView)
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    password.setService(serviceView.getText().toString());
//                                    password.setPassword(passwordView.getText().toString());
//                                    String username = usernameView.getText().toString();
//                                    password.setUsername(username.isEmpty() ? null : username);
                                     firebasePush(outfitItem);
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
                    mCallback.onOutfitSelected(mOutfitItems.get(getAdapterPosition()));
                }
            });

        }
    }

    public void firebasePush(OutfitItem outfitItem) {
        mOutfitsRef.push().setValue(outfitItem);
    }

//    public void firebaseUpdate(Password pw) {
//        mPasswordRef.child(pw.getKey()).setValue(pw);
//    }
//
//    public void firebaseRemove(Password password) {
//        mPasswordRef.child(password.getKey()).removeValue();
//    }
//
//    public void insert(Password password, int position) {
//        mPasswords.add(position, password);
//        notifyItemInserted(position);
//    }

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
