package org.styleru.hseday.RecyclerViewAdapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import org.styleru.hseday.ActivityComments;
import org.styleru.hseday.ApiClasses.ApiEvents;
import org.styleru.hseday.ItemClickListener;

import java.util.ArrayList;

public class RecyclerViewAdapterLections extends RecyclerView.Adapter<RecyclerViewAdapterLections.ViewHolder> {
    Context mContext;
    private ArrayList<ApiEvents> dataEvents;

    public RecyclerViewAdapterLections(Context contexts, ArrayList<ApiEvents> dataEvents) {
        this.mContext = contexts;
        this.dataEvents = dataEvents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday.R.layout.cardview_lection, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.LectionTitle.setText(dataEvents.get(position).getName());
        holder.LectionTime.setText(dataEvents.get(position).getStarttime() + " - " + dataEvents.get(position).getEndtime());
        holder.LectionPerson.setText(dataEvents.get(position).getDescription());
        //holder.LectionContacts.setText(mContext.getResources().getText(org.styleru.hseday.R.string.cardview_contacts_string).toString().concat(parts[3].toString()).concat(parts[4].toString()));

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Intent intent = new Intent(mContext, ActivityComments.class);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, ActivityComments.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataEvents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView LectionTitle;
        private TextView LectionTime;
        private TextView LectionContacts;
        private TextView LectionPerson;
        private Button OpenComments;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            LectionContacts = (TextView) itemView.findViewById(org.styleru.hseday.R.id.lection_contacts);
            LectionTime = (TextView) itemView.findViewById(org.styleru.hseday.R.id.lection_time);
            LectionTitle = (TextView) itemView.findViewById(org.styleru.hseday.R.id.lection_title);
            LectionPerson = (TextView) itemView.findViewById(org.styleru.hseday.R.id.lection_person);
            OpenComments = (Button) itemView.findViewById(org.styleru.hseday.R.id.button_open_comments);


            itemView.setTag(itemView);
            OpenComments.setOnClickListener(this);

            //itemView.setOnClickListener(this);
            //itemView.setOnLongClickListener(this);
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
