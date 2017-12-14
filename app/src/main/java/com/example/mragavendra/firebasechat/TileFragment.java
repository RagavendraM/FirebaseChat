package com.example.mragavendra.firebasechat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by m.ragavendra on 20-07-2017.
 */

public class TileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView=(RecyclerView)inflater.inflate(R.layout.recyclerview,container,false);

        ContentAdapter contentAdapter=new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setHasFixedSize(true);
        //padding
        int tilepadding=getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilepadding,tilepadding,tilepadding,tilepadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        return recyclerView;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_tile, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
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
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
        private static final  int LENGTH=18;
        private final String[] mplaces;
        private final Drawable[] mpics;
        public ContentAdapter(Context context)
        {
            Resources resources=context.getResources();
            mplaces=resources.getStringArray(R.array.places_name);
            TypedArray a=resources.obtainTypedArray(R.array.places_picture);
            mpics=new Drawable[a.length()];
            for(int i=0;i<a.length();i++)
            {
                mpics[i]=a.getDrawable(i);
            }
            a.recycle();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

                holder.picture.setImageDrawable(mpics[position%mpics.length]);
            holder.name.setText(mplaces[position%mplaces.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
