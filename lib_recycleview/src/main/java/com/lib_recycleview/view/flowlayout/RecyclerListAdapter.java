package com.lib_recycleview.view.flowlayout;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib_recycleview.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author yangyakun
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<DemoViewHolder>  {
    List<String> items;
    private int maxLinesPerItem;
    private boolean showMeta = false;
    public RecyclerListAdapter() {
        this.items = new LinkedList<>();
        maxLinesPerItem = 1;
    }

    public RecyclerListAdapter(int maxLinesPerItem, List<String> items) {
        this();
        this.items.addAll(items);
        this.maxLinesPerItem = maxLinesPerItem;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_tag, parent, false)).setShowMeta(showMeta);
    }

    @Override
    public void onBindViewHolder(final DemoViewHolder holder, final int position) {
        holder.setTagText(items.get(position));
        holder.tagSize.setClickable(false);
        holder.tagText.setClickable(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                items.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                List<String> newItems = DemoUtil.generate(1, 3, 14, maxLinesPerItem, true);
                items.addAll(adapterPosition, newItems);
                notifyItemRangeInserted(adapterPosition, newItems.size());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void newItems(int maxLinesPerItem, List<String> newItems) {
        this.maxLinesPerItem = maxLinesPerItem;
        items.clear();
        items.addAll(newItems);
    }

    public int getMaxLinesPerItem() {
        return maxLinesPerItem;
    }

    public void setShowMeta(boolean showMeta) {
        this.showMeta = showMeta;
    }
}
