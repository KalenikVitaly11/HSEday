package org.styleru.hseday2017.RecyclerViewAdapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.styleru.hseday2017.ActivityComments;
import org.styleru.hseday2017.ApiClasses.ApiEvents;
import org.styleru.hseday2017.ItemClickListener;

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday2017.R.layout.cardview_lection, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.LectionTitle.setText(dataEvents.get(position).getName());
        if (!dataEvents.get(position).getStarttime().equals("-10")) {
            holder.LectionTime.setText(dataEvents.get(position).getStarttime() + " - " + dataEvents.get(position).getEndtime());
        } else {
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LayoutParams.setMargins(0, 0, 0, 0);
            ((ViewGroup) holder.OpenComments.getParent()).removeView(holder.OpenComments);
            ((ViewGroup) holder.LectionTime.getParent()).removeView(holder.LectionTime);
        }
        holder.LectionInfo.setText(dataEvents.get(position).getDescription());
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
        private TextView LectionInfo;
        private Button OpenComments;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            LectionTime = (TextView) itemView.findViewById(org.styleru.hseday2017.R.id.lection_time);
            LectionTitle = (TextView) itemView.findViewById(org.styleru.hseday2017.R.id.lection_title);
            LectionInfo = (TextView) itemView.findViewById(org.styleru.hseday2017.R.id.lection_info);
            OpenComments = (Button) itemView.findViewById(org.styleru.hseday2017.R.id.button_open_comments);


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
