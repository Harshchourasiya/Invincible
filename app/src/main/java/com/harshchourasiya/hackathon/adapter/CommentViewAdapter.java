package com.harshchourasiya.hackathon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.schema.Comment;

import java.util.List;

public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewAdapter.CommentViewHolder> {

    private Context context;
    private List<Comment> comments;


    public CommentViewAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        View levelView
                = inflater
                .inflate(R.layout.comment_view,
                        parent, false);

        CommentViewHolder viewHolder = new CommentViewHolder(levelView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.avatarTextView.setText(comments.get(position).getUserName().substring(0, 1));

        holder.userLevelTextView.setText(comments.get(position).getUserLevel());
        holder.userNameTextView.setText(comments.get(position).getUserName());

        String detail = comments.get(position).getComment();
        if (detail.length() > 100) {
            detail = detail.substring(0, 100) + "...";
        }

        holder.commentDetail.setText(detail);
        int p = position;
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.commentDetail.getText().toString().endsWith("...")) {
                    holder.commentDetail.setText(comments.get(p).getComment());
                }  else {
                    if (holder.commentDetail.getText().length() > 100) {
                        holder.commentDetail.setText(comments.get(p).getComment().substring(0, 100));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView avatarTextView, userNameTextView, userLevelTextView, commentDetail;
        private LinearLayout layout;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarTextView = itemView.findViewById(R.id.avatarText);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userLevelTextView = itemView.findViewById(R.id.userLevelTextView);
            commentDetail = itemView.findViewById(R.id.commentDetail);
            layout = itemView.findViewById(R.id.showMoreLinearLayout);
        }
    }
}
