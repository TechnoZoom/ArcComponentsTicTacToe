package com.kapil.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by kapilbakshi on 06/06/18.
 */

public class TicTacRecyclerAdapter  extends RecyclerView.Adapter<TicTacRecyclerAdapter.TileImageViewHolder>{

    private List<TicTacItem> ticTacItemList;

    private TileCallBacks tileCallBacks;

    public TicTacRecyclerAdapter(List<TicTacItem> ticTacItemList, TileCallBacks tileCallBacks) {
        this.ticTacItemList = ticTacItemList;
        this.tileCallBacks = tileCallBacks;
    }


    public interface TileCallBacks {

        void onTileClicked(TicTacItem ticTacItem, int position);
    }

    @Override
    public TileImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_layout, parent, false);
        return new TileImageViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(TileImageViewHolder holder, final int position) {
        final TicTacItem ticTacItem = getItem(position);
        if(ticTacItem.getStatus() != null) {
            if (ticTacItem.getStatus()) {
                holder.getTileImageView().setImageResource(R.drawable.circle);
            } else if (!ticTacItem.getStatus()) {
                holder.getTileImageView().setImageResource(R.drawable.cross);
            }
        }

        holder.getTileImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tileCallBacks != null) {
                    tileCallBacks.onTileClicked(ticTacItem, position);
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return ticTacItemList.size();
    }

    public TicTacItem getItem(int position)
    {
        return ticTacItemList.get(position);
    }

    class TileImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView tileImageView;
        TileImageViewHolder(View rowView) {
            super(rowView);
            tileImageView = (ImageView) rowView;
        }

        public ImageView getTileImageView() {
            return tileImageView;
        }
    }

    public void setItem(TicTacItem ticTacItem, int position)
    {
        ticTacItemList.set(position,ticTacItem);
        notifyItemChanged(position);
    }



}