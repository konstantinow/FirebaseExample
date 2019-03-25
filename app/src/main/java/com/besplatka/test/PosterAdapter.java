package com.besplatka.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {

    private List<Poster> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    PosterAdapter(Context context, List<Poster> data) {
        this.mInflater = LayoutInflater.from(context);
        if (data != null) {
            this.mData = data;
        } else {
            this.mData = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poster p = mData.get(position);
        holder.tvTitle.setText(p.getTitle());
        holder.tvDescription.setText(p.getDescription());
        holder.tvCost.setText(String.valueOf(p.getCost()));
        holder.tvContacts.setText(p.getName() + ": " + p.getPhone());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvCity;
        TextView tvCost;
        TextView tvContacts;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvCost = itemView.findViewById(R.id.tv_cost);
            tvContacts = itemView.findViewById(R.id.tv_contacts);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Poster getItem(int id) {
        return mData.get(id);
    }

    void updateData(List<Poster> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
