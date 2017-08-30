package org.styleru.hseday.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.hseday.AboutOrganisationActivity;
import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.ItemClickListener;

import java.util.ArrayList;

/**
 * Created by Виталий on 31.01.2017.
 */

public class RecyclerViewAdapterOrganisations extends RecyclerView.Adapter<RecyclerViewAdapterOrganisations.ViewHolder> {
    Context mContext;
    private ArrayList<ApiOrganisations> organisationsList;

    public RecyclerViewAdapterOrganisations(Context contexts, ArrayList<ApiOrganisations> organisationsList) {
        this.mContext = contexts;
        this.organisationsList = organisationsList;
        //Toast.makeText(mContext, "размер1111 = " + String.valueOf(organisationsList.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday.R.layout.cardview_organisation, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        //Toast.makeText(mContext, "123", Toast.LENGTH_LONG).show();
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.OrganisationName.setText(organisationsList.get(position).getName());
        Glide.with(mContext).load(organisationsList.get(position).getImageurl()).into(holder.OrganisationIcon);
        //Toast.makeText(mContext, organisationsList.get(position).toString(), Toast.LENGTH_LONG).show();
        //Toast.makeText(mContext, "123", Toast.LENGTH_LONG).show();


        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Intent intent = new Intent(mContext, AboutOrganisationActivity.class);
                    intent.putExtra("organisationName", organisationsList.get(position).getName());
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, AboutOrganisationActivity.class);
                    intent.putExtra("organisationName", organisationsList.get(position).getName());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return organisationsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView OrganisationName;
        private ImageView OrganisationIcon;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            OrganisationName = (TextView)itemView.findViewById(org.styleru.hseday.R.id.organisation_name);
            OrganisationIcon = (ImageView) itemView.findViewById(org.styleru.hseday.R.id.organisation_icon);
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
