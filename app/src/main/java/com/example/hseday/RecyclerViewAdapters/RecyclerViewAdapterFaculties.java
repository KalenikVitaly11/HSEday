package com.example.hseday.RecyclerViewAdapters;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hseday.AboutFacultyActivity;
import com.example.hseday.ItemClickListener;
import com.example.hseday.R;

/**
 * Created by Виталий on 31.01.2017.
 */

public class RecyclerViewAdapterFaculties extends RecyclerView.Adapter<RecyclerViewAdapterFaculties.ViewHolder> {
    Context mContext;
    private String[] mList;

    public RecyclerViewAdapterFaculties(Context contexts, String[] list) {
        this.mContext = contexts;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_faculty, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.FacultyName.setText(mList[position].toString());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //FragmentTransaction ftransaction = getFragmentManager().beginTransaction();
                    //ftransaction.replace(R.id.content_map, FragmentFaculties);
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);
                    mContext.startActivity(intent);
                    //FragmentTransaction ftransaction = getFragmentManager().beginTransaction();
                    //ftransaction.replace(R.id.content_map, FragmentFaculties);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView FacultyName;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            FacultyName = (TextView)itemView.findViewById(R.id.faculty_name);
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
