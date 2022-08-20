package com.harshchourasiya.hackathon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.harshchourasiya.hackathon.R;
import com.harshchourasiya.hackathon.activities.HomeActivity;
import com.harshchourasiya.hackathon.helper.DatabaseUtility;
import com.harshchourasiya.hackathon.schema.Level;
import com.harshchourasiya.hackathon.schema.User;

import java.util.List;


public class LevelViewAdapter extends RecyclerView.Adapter<LevelViewAdapter.LevelViewHolder> {

    private Context context;
    private List<Level> levels;
    private User user;

    public LevelViewAdapter(Context context, List<Level> levels, User user) {
        this.context = context;
        this.levels = levels;
        this.user = user;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        View levelView
                = inflater
                .inflate(R.layout.level_view,
                        parent, false);

        LevelViewHolder viewHolder = new LevelViewHolder(levelView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.avatarTextView.setText(String.valueOf(position+1));
        holder.levelNameTextView.setText(levels.get(position).getLevelName());
        if (position == user.getLevel()-1 && !user.isComplete()) {
            holder.levelButton.setText("Complete");
        } else {
            holder.levelButton.setText("Experience");
        }

        // to Show All the Task Detail when User click on it
        String levelData = levels.get(position).getLevel();
        if (levelData.length() > 50) {
            levelData = levelData.substring(0, 50);
        }

        holder.levelTaskTextView.setText(levelData);
        int p = position;
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTaskViewData(p, holder);
            }
        });


        // On Level Button Click

        holder.levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.levelButton.getText() == "Complete") {
                    user.setLevel(user.getLevel()+1);
                    taskComplete(user);
                } else {
                    openCommentActivity();
                }
            }
        });

    }

    private void setTaskViewData(int p, LevelViewHolder holder) {
        if (holder.levelTaskTextView.getText().length() == 50) {
            holder.levelTaskTextView.setText(levels.get(p).getLevel());
        } else {
            holder.levelTaskTextView.setText(levels.get(p).getLevel().substring(0, 50));
        }
    }

    private void taskComplete(User user) {
       new DatabaseUtility().completeTask(user);
    }

    private void openCommentActivity() {
//        openActivity(Comment.class);
    }

    private void openActivity(Class c) {
        Intent intent = new Intent(context, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder{
        private TextView avatarTextView, levelTaskTextView, levelNameTextView;
        private Button levelButton;
        private LinearLayout layout;
        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarTextView = itemView.findViewById(R.id.avatarText);
            levelNameTextView = itemView.findViewById(R.id.levelNameTextView);
            levelTaskTextView = itemView.findViewById(R.id.levelTaskTextView);
            levelButton = itemView.findViewById(R.id.levelButton);
            layout = itemView.findViewById(R.id.showMoreLinearLayout);
        }
    }
}