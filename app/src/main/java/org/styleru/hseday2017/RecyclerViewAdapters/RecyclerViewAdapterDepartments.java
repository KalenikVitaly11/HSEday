package org.styleru.hseday2017.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.hseday2017.R;

import java.util.ArrayList;

/**
 * Created by Виталий on 30.08.2017.
 */

public class RecyclerViewAdapterDepartments extends RecyclerView.Adapter<RecyclerViewAdapterDepartments.ViewHolder> {
    Context mContext;
    private ArrayList<String> departmentsList;

    public RecyclerViewAdapterDepartments(Context context, ArrayList<String> departmentsList){
        this.mContext = context;
        this.departmentsList = departmentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_department, parent, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.departmentName.setText(departmentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView departmentName;
        public ViewHolder(View itemView){
            super(itemView);
            departmentName = (TextView) itemView.findViewById(R.id.department_name);
        }
    }
}
