package org.styleru.hseday.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.styleru.hseday.AboutOrganisationActivity;
import org.styleru.hseday.ItemClickListener;

/**
 * Created by Виталий on 31.01.2017.
 */

public class RecyclerViewAdapterOrganisations extends RecyclerView.Adapter<RecyclerViewAdapterOrganisations.ViewHolder> {
    Context mContext;
    private String[] mList;

    public RecyclerViewAdapterOrganisations(Context contexts, String[] list) {
        this.mContext = contexts;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday.R.layout.cardview_organisation, viewGroup, false);
        ViewHolder cvh = new ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.OrganisationName.setText(mList[position].toString());
        switch (position){
            case 0:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_1);
                break;
            case 1:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_2);
                break;
            case 2:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_3);
                break;
            case 3:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_4);
                break;
            case 4:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_5);
                break;
            case 5:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_6);
                break;
            case 6:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_7);
                break;
            case 7:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_8);
                break;
            case 8:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_9);
                break;
            case 9:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_10);
                break;
            case 10:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_11);
                break;
            case 11:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_12);
                break;
            case 12:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_13);
                break;
            case 13:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_14);
                break;
            case 14:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_15);
                break;
            case 15:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_16);
                break;
            case 16:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_17);
                break;
            case 17:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_18);
                break;
            case 18:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_19);
                break;
            case 19:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_20);
                break;
            case 20:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_21);
                break;
            case 21:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_22);
                break;
            case 22:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_23);
                break;
            case 23:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_24);
                break;
            case 24:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_25);
                break;
            case 25:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_26);
                break;
            case 26:holder.OrganisationIcon.setImageResource(org.styleru.hseday.R.drawable.organisation_image_27);
                break;

        }
        //holder.OrganisationIcon.setImageResource();

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Intent intent = new Intent(mContext, AboutOrganisationActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, AboutOrganisationActivity.class);

                    switch(position){
                        case 0:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_1);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_1);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_1);
                            intent.putExtra("OrganisationImage", 0);
                            break;
                        case 1:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_2);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_2);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_2);
                            intent.putExtra("OrganisationImage", 1);
                            break;
                        case 2:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_3);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_3);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_3);
                            intent.putExtra("OrganisationImage", 2);
                            break;
                        case 3:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_4);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_4);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_4);
                            intent.putExtra("OrganisationImage", 3);
                            break;
                        case 4:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_5);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_5);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_5);
                            intent.putExtra("OrganisationImage", 4);
                            break;
                        case 5:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_6);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_6);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_6);
                            intent.putExtra("OrganisationImage", 5);
                            break;
                        case 6:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_7);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_7);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_7);
                            intent.putExtra("OrganisationImage", 6);
                            break;
                        case 7:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_8);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_8);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_8);
                            intent.putExtra("OrganisationImage", 7);
                            break;
                        case 8:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_9);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_9);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_9);
                            intent.putExtra("OrganisationImage", 8);
                            break;
                        case 9:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_10);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_10);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_10);
                            intent.putExtra("OrganisationImage", 9);
                            break;
                        case 10:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_11);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_11);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_11);
                            intent.putExtra("OrganisationImage", 10);
                            break;
                        case 11:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_12);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_12);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_12);
                            intent.putExtra("OrganisationImage", 11);
                            break;
                        case 12:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_13);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_13);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_13);
                            intent.putExtra("OrganisationImage", 12);
                            break;
                        case 13:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_14);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_14);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_14);
                            intent.putExtra("OrganisationImage", 13);
                            break;
                        case 14:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_15);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_15);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_15);
                            intent.putExtra("OrganisationImage", 14);
                            break;
                        case 15:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_16);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_16);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_16);
                            intent.putExtra("OrganisationImage", 15);
                            break;
                        case 16:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_17);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_17);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_17);
                            intent.putExtra("OrganisationImage", 16);
                            break;
                        case 17:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_18);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_18);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_18);
                            intent.putExtra("OrganisationImage", 17);
                            break;
                        case 18:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_19);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_19);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_19);
                            intent.putExtra("OrganisationImage", 18);
                            break;
                        case 19:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_20);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_20);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_20);
                            intent.putExtra("OrganisationImage", 19);
                            break;
                        case 20:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_21);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_21);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_21);
                            intent.putExtra("OrganisationImage", 20);
                            break;
                        case 21:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_22);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_22);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_22);
                            intent.putExtra("OrganisationImage", 21);
                            break;
                        case 22:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_23);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_23);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_23);
                            intent.putExtra("OrganisationImage", 22);
                            break;
                        case 23:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_24);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_24);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_24);
                            intent.putExtra("OrganisationImage", 23);
                            break;
                        case 24:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_25);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_25);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_25);
                            intent.putExtra("OrganisationImage", 24);
                            break;
                        case 25:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_26);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_26);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_26);
                            intent.putExtra("OrganisationImage", 25);
                            break;
                        case 26:
                            intent.putExtra("OrganisationName", org.styleru.hseday.R.string.organisation_name_27);
                            intent.putExtra("OrganisationInformation", org.styleru.hseday.R.string.organisation_information_27);
                            intent.putExtra("OrganisationContacts", org.styleru.hseday.R.string.organisation_contacts_27);
                            intent.putExtra("OrganisationImage", 26);
                            break;
                    }
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.length;
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
