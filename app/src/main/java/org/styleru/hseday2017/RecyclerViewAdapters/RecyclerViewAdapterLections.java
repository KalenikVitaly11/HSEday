package org.styleru.hseday2017.RecyclerViewAdapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.styleru.hseday2017.ActivityComments;
import org.styleru.hseday2017.ApiClasses.ApiEvents;
import org.styleru.hseday2017.ItemClickListener;
import org.styleru.hseday2017.R;

import java.util.ArrayList;

public class RecyclerViewAdapterLections extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    private ArrayList<ApiEvents> dataEvents;

    public RecyclerViewAdapterLections(Context contexts, ArrayList<ApiEvents> dataEvents) {
        this.mContext = contexts;
        this.dataEvents = dataEvents;
        if(this.dataEvents.get(0).getDescription().equals("")){  // Самая первая запись списка - описание самое метки. Если описание отсутствует, то запись удаляется
            this.dataEvents.remove(0);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lecture_title, viewGroup, false);
            ViewHolderTitle cvh = new ViewHolderTitle(v);
            return cvh;
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lection, viewGroup, false);
            ViewHolderMain cvh = new ViewHolderMain(v);
            return cvh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 1:
                ViewHolderMain viewHolder = (ViewHolderMain)holder;
                viewHolder.LectionTitle.setText(dataEvents.get(position).getName());
                viewHolder.LectionTime.setText(dataEvents.get(position).getStarttime() + " - " + dataEvents.get(position).getEndtime());
                viewHolder.LectionInfo.setText(dataEvents.get(position).getDescription());

                viewHolder.setClickListener(new ItemClickListener() {
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
                break;
            case 2:
                ViewHolderTitle viewHolderTitle = (ViewHolderTitle)holder;
                if(dataEvents.get(position).getDescription().equals("")){
                    dataEvents.remove(position);
                    //try{
                    //    notifyItemRemoved(position);
                    //} catch (IndexOutOfBoundsException e){
                    //    notifyDataSetChanged();
                    //    e.printStackTrace();
                    //}
                    //
                } else {
                    viewHolderTitle.markerInfo.setText(dataEvents.get(position).getDescription());
                    viewHolderTitle.markerInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        return dataEvents.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && dataEvents.get(position).getName().equals("marker")) {
            // Это проверка, удалена ли запись с описанием метки. Так как самая первая запись (та, что для описания метки) имеет имя marker,
            // проверяем именно этот параметр, если им не равно marker, то элемент удален
            return 2;  // Если самая первая карточка - то ViewHolder типа ViewHolderTitle
        } else {
            return 1;
        }
    }

    public static class ViewHolderMain extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener { // holder 1
        private TextView LectionTitle;
        private TextView LectionTime;
        private TextView LectionInfo;
        private Button OpenComments;

        private ItemClickListener clickListener;

        public ViewHolderMain(View itemView) {
            super(itemView);
            LectionTime = (TextView) itemView.findViewById(R.id.lection_time);
            LectionTitle = (TextView) itemView.findViewById(R.id.lection_title);
            LectionInfo = (TextView) itemView.findViewById(R.id.lection_info);
            OpenComments = (Button) itemView.findViewById(R.id.button_open_comments);

            itemView.setTag(itemView);
            //OpenComments.setOnClickListener(this);
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

    public static class ViewHolderTitle extends RecyclerView.ViewHolder { // holder 2
        private TextView markerInfo;


        public ViewHolderTitle(View itemView) {
            super(itemView);
            markerInfo = (TextView) itemView.findViewById(R.id.marker_title);

            itemView.setTag(itemView);
        }
    }
}
