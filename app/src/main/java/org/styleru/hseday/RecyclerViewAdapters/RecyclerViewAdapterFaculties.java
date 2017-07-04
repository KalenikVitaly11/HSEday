package org.styleru.hseday.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.hseday.AboutFacultyActivity;
import org.styleru.hseday.ItemClickListener;

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(org.styleru.hseday.R.layout.cardview_faculty, viewGroup, false);
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
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);
                    switch(position){
                        case 0:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_1);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_1);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_1);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_1);
                            intent.putExtra("FacultyImage", 1);
                            break;
                        case 1:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_2);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_2);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_2);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_2);
                            intent.putExtra("FacultyImage", 2);
                            break;
                        case 2:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_3);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_3);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_3);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_3);
                            break;
                        case 3:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_4);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_4);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_4);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_4);
                            break;
                        case 4:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_5);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_5);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_5);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_5);
                            break;
                        case 5:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_6);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_6);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_6);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_6);
                            break;
                        case 6:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_7);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_7);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_7);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_7);
                            break;
                        case 7:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_8);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_8);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_8);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_8);
                            break;
                        case 8:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_9);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_9);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_9);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_9);
                            break;
                        case 9:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_10);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_10);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_10);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_10);
                            break;
                        case 10:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_11);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_11);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_11);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_11);
                            break;
                        case 11:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_12);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_12);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_12);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_12);
                            break;
                        case 12:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_13);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_13);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_13);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_13);
                            break;
                        case 13:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_14);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_14);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_14);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_14);
                            break;
                        case 14:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_15);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_15);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_15);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_15);
                            break;
                    }
                    mContext.startActivity(intent);

                } else {
                    Intent intent = new Intent(mContext, AboutFacultyActivity.class);

                    switch(position){
                        case 0:
                            //String name = R.string.faculty_name_1;
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_1);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_1);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_1);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_1);
                            intent.putExtra("FacultyImage", 1);
                            break;
                        case 1:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_2);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_2);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_2);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_2);
                            intent.putExtra("FacultyImage", 2);
                            break;
                        case 2:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_3);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_3);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_3);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_3);
                            intent.putExtra("FacultyImage", 3);
                            break;
                        case 3:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_4);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_4);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_4);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_4);
                            intent.putExtra("FacultyImage", 4);
                            break;
                        case 4:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_5);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_5);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_5);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_5);
                            intent.putExtra("FacultyImage", 5);
                            break;
                        case 5:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_6);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_6);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_6);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_6);
                            intent.putExtra("FacultyImage", 6);
                            break;
                        case 6:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_7);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_7);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_7);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_7);
                            intent.putExtra("FacultyImage", 7);
                            break;
                        case 7:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_8);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_8);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_8);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_8);
                            intent.putExtra("FacultyImage", 8);
                            break;
                        case 8:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_9);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_9);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_9);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_9);
                            intent.putExtra("FacultyImage", 9);
                            break;
                        case 9:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_10);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_10);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_10);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_10);
                            intent.putExtra("FacultyImage", 10);
                            break;
                        case 10:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_11);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_11);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_11);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_11);
                            intent.putExtra("FacultyImage", 11);
                            break;
                        case 11:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_12);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_12);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_12);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_12);
                            intent.putExtra("FacultyImage", 12);
                            break;
                        case 12:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_13);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_13);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_13);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_13);
                            intent.putExtra("FacultyImage", 13);
                            break;
                        case 13:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_14);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_14);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_14);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_14);
                            intent.putExtra("FacultyImage", 14);
                            break;
                        case 14:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_15);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_15);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_15);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_15);
                            intent.putExtra("FacultyImage", 15);
                            break;
                        case 15:
                            intent.putExtra("FacultyName", org.styleru.hseday.R.string.faculty_name_16);
                            intent.putExtra("FacultyInformation", org.styleru.hseday.R.string.faculty_information_16);
                            intent.putExtra("FacultyDepartments", org.styleru.hseday.R.string.faculty_departments_16);
                            intent.putExtra("FacultyContacts", org.styleru.hseday.R.string.faculty_contacts_16);
                            intent.putExtra("FacultyImage", 16);
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
