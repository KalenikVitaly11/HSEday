package com.example.hseday.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hseday.ItemClickListener;
import com.example.hseday.R;

/**
 * Created by Виталий on 12.03.2017.
 */

public class RecyclerViewAdapterComments extends RecyclerView.Adapter<RecyclerViewAdapterLections.ViewHolder> {
    Context mContext;
    private String[] mList;

    public RecyclerViewAdapterComments(Context contexts, String[] list) {
        this.mContext = contexts;
        this.mList = list;
    }

    @Override
    public RecyclerViewAdapterLections.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lection, viewGroup, false);
        RecyclerViewAdapterLections.ViewHolder cvh = new RecyclerViewAdapterLections.ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterLections.ViewHolder holder, int position) {
        String[] parts = mList[position].split("#");
        //holder.LectionTitle.setText(mContext.getResources().getText(R.string.cardview_title_string).toString().concat(parts[1].toString()));
        //holder.LectionTitle.setText(parts[1].toString());
        //holder.LectionTime.setText(parts[0].toString());
        //holder.LectionPerson.setText(mContext.getResources().getText(R.string.cardview_person_string).toString().concat(parts[2].toString()));
        //holder.LectionContacts.setText(mContext.getResources().getText(R.string.cardview_contacts_string).toString().concat(parts[3].toString()).concat(parts[4].toString()));

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {

                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView LectionTitle;
        private TextView LectionTime;
        private TextView LectionContacts;
        private TextView LectionPerson;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            LectionContacts = (TextView) itemView.findViewById(R.id.lection_contacts);
            LectionTime = (TextView) itemView.findViewById(R.id.lection_time);
            LectionTitle = (TextView) itemView.findViewById(R.id.lection_title);
            LectionPerson = (TextView) itemView.findViewById(R.id.lection_person);


            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
