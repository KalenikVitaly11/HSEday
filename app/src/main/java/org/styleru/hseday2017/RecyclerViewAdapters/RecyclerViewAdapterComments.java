package org.styleru.hseday2017.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.styleru.hseday2017.ItemClickListener;
import org.styleru.hseday2017.R;

/**
 * Created by Виталий on 12.03.2017.
 */

public class RecyclerViewAdapterComments extends RecyclerView.Adapter<RecyclerViewAdapterComments.ViewHolder> {
    Context mContext;
    private String[] commentsList;
    private String[] namesList;

    public RecyclerViewAdapterComments(Context contexts, String[] commentList, String[] nameList) {
        this.mContext = contexts;
        this.commentsList = commentList;
        this.namesList = nameList;
    }

    @Override
    public RecyclerViewAdapterComments.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_comments, viewGroup, false);
        RecyclerViewAdapterComments.ViewHolder cvh = new RecyclerViewAdapterComments.ViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterComments.ViewHolder holder, int position) {
        //holder.LectionTitle.setText(mContext.getResources().getText(R.string.cardview_title_string).toString().concat(parts[1].toString()));
        //holder.LectionTitle.setText(parts[1].toString());
        //holder.LectionTime.setText(parts[0].toString());
        //holder.LectionPerson.setText(mContext.getResources().getText(R.string.cardview_person_string).toString().concat(parts[2].toString()));
        //holder.LectionContacts.setText(mContext.getResources().getText(R.string.cardview_contacts_string).toString().concat(parts[3].toString()).concat(parts[4].toString()));
        holder.UserName.setText(namesList[position]);
        holder.UserComment.setText(commentsList[position]);
        holder.UserAvatar.setImageResource(R.drawable.pushkin);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {

                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView UserComment;
        private  TextView UserName;
        private ImageView UserAvatar;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            UserAvatar = (ImageView) itemView.findViewById(R.id.comments_user_avatar);
            UserComment = (TextView) itemView.findViewById(R.id.comments_user_comment);
            UserName = (TextView) itemView.findViewById(R.id.comments_user_name);

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
