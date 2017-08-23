package org.styleru.hseday.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.hseday.AboutFacultyActivity;
import org.styleru.hseday.ApiClasses.ApiFaculties;
import org.styleru.hseday.ItemClickListener;

import java.util.ArrayList;

/**
 * Created by Виталий on 31.01.2017.
 */

public class RecyclerViewAdapterFaculties extends RecyclerView.Adapter<RecyclerViewAdapterFaculties.ViewHolder> {
    Context mContext;
    private ArrayList<ApiFaculties> facultiesList;

    public RecyclerViewAdapterFaculties(Context contexts, ArrayList<ApiFaculties> facultiesList) {
        this.mContext = contexts;
        this.facultiesList = facultiesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday.R.layout.cardview_faculty, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.FacultyName.setText(facultiesList.get(position).getName());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);
                    intent.putExtra("facultyName", facultiesList.get(position).getName());
                    mContext.startActivity(intent);

                } else {
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);
                    intent.putExtra("facultyName", facultiesList.get(position).getName());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultiesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView FacultyName;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            FacultyName = (TextView)itemView.findViewById(org.styleru.hseday.R.id.faculty_name);
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
