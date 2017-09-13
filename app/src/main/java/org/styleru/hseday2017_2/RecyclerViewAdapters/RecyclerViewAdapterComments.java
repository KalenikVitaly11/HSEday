package org.styleru.hseday2017_2.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.hseday2017_2.ApiClasses.ApiComments;
import org.styleru.hseday2017_2.R;

import java.util.ArrayList;


public class RecyclerViewAdapterComments extends RecyclerView.Adapter<RecyclerViewAdapterComments.ViewHolder> {
    Context mContext;
    ArrayList<ApiComments> dataComments;

    public RecyclerViewAdapterComments(Context contexts, ArrayList<ApiComments> dataComments) {
        this.mContext = contexts;
        this.dataComments = dataComments;
    }

    @Override
    public RecyclerViewAdapterComments.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_comments, viewGroup, false);
        RecyclerViewAdapterComments.ViewHolder cvh = new RecyclerViewAdapterComments.ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterComments.ViewHolder holder, int position) {
        holder.userName.setText(dataComments.get(position).getAuthor());
        holder.userComment.setText(dataComments.get(position).getContent());
        holder.userTime.setText(dataComments.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return dataComments.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userComment;
        private TextView userName;
        private TextView userTime;

        public ViewHolder(View itemView) {
            super(itemView);
            userComment = (TextView) itemView.findViewById(R.id.comments_user_comment);
            userName = (TextView) itemView.findViewById(R.id.comments_user_name);
            userTime = (TextView) itemView.findViewById(R.id.comments_time);

            itemView.setTag(itemView);

        }
    }
}
