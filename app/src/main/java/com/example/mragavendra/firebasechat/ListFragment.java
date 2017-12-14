package com.example.mragavendra.firebasechat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.userexperior.UserExperior;

/**
 * Created by m.ragavendra on 20-07-2017.
 */

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        RecyclerView recyclerView=(RecyclerView)inflater.inflate(R.layout.recyclerview,container,false);

        ConAdapter conAdapter=new ConAdapter(recyclerView.getContext());
        recyclerView.setAdapter(conAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv1;
        public TextView head,desc;
        public ViewHolder(LayoutInflater inflater,ViewGroup parent)
        {
            super(inflater.inflate(R.layout.item_list,parent,false));
            iv1=(ImageView)itemView.findViewById(R.id.list_avatar1);
            head=(TextView)itemView.findViewById(R.id.list_title);
            desc=(TextView)itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION,getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
    public static class ConAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        private static final int LENGTH=18;
        private final String[] mplaces;
        private final String[] mplacesdesc;
        private final Drawable[] pics;

        public ConAdapter(Context context)
        {
            Resources resources=context.getResources();
            mplaces=resources.getStringArray(R.array.places_name);
            mplacesdesc=resources.getStringArray(R.array.place_desc);
            TypedArray typedArray = resources.obtainTypedArray(R.array.place_avator);
            pics=new Drawable[typedArray.length()];
            for(int i=0;i<typedArray.length();i++)
            {
                pics[i]=typedArray.getDrawable(i);
            }
            typedArray.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.iv1.setImageDrawable(pics[position%pics.length]);
            holder.head.setText(mplaces[position%mplaces.length]);
            holder.desc.setText(mplacesdesc[position%mplacesdesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
