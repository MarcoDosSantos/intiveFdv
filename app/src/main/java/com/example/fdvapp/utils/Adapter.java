package com.example.fdvapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fdvapp.R;
import com.example.fdvapp.model.User;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {
    private Context mContext;
    private ArrayList<User> mUsers;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public Adapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User user = mUsers.get(i);

        String username = user.getUsername();
        String thumbnailUrl = user.getThumbnail();

        userViewHolder.textView.setText(username);
        Glide.with(mContext)
                .load(thumbnailUrl)
                .centerCrop()
                .into(userViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addList(ArrayList<User> users) {
        mUsers.addAll(users);
        notifyDataSetChanged();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_item);
            textView = itemView.findViewById(R.id.text_view_username_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);

                        }

                    }
                }
            });
        }
    }
}
