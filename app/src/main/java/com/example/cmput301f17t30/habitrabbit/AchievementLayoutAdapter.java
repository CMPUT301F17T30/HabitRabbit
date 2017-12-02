/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.achievementController;

/**
 * Adapter for displaying achievements in user profile
 * @See UserProfileActivity
 */

public class AchievementLayoutAdapter extends RecyclerView.Adapter<AchievementLayoutAdapter.ViewHolder> {

    private ArrayList<Achievement>  achievements;
    private Context profileContext;



    public AchievementLayoutAdapter(ArrayList<Achievement> habitList, Context context) {
        this.profileContext = context;

        achievements = achievementController.getAchievements();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView achievementName;
        private TextView achievementDescription;
        private ImageView achievementImage;
        private TextView achievementProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            achievementName = itemView.findViewById(R.id.achievement_name);
            achievementDescription = itemView.findViewById(R.id.achievement_description);
            achievementImage = itemView.findViewById(R.id.achievement_image);
            achievementProgress = itemView.findViewById(R.id.achievement_progess);
        }

        @Override
        public void onClick(View view) {
            //say something maybe
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .achievement_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }
    @Override
    public void onBindViewHolder(AchievementLayoutAdapter.ViewHolder holder, final int position) {
        final Achievement achievement = achievements.get(position);
        String name = achievement.getName();
        String description = achievement.getDescription();
        String progressDisplay;


        if (achievement.getCompleted()){
            progressDisplay = "";
        }
        else {
            String total = achievement.getProgressRequired().toString();
            String done = achievement.getProgress().toString();
            progressDisplay = ("(" + done + "/" + total + ")");
            /*
            if (achievement.getProgressRequired() == 1){
                progressDisplay = "Not completed";
            }
            else {
                String total = achievement.getProgressRequired().toString();
                String done = achievement.getProgress().toString();
                progressDisplay = ("(" + done + "/" + total + ")");
            }
            */
        }



        holder.achievementName.setText(name);
        holder.achievementDescription.setText(description);
        holder.achievementProgress.setText(progressDisplay);
        if (achievement.getCompleted() == Boolean.TRUE){
            Bitmap successImage = BitmapFactory.decodeResource(profileContext.getResources(), R.drawable.unlocked);

            holder.achievementImage.setBackground(null);
            holder.achievementImage.setImageBitmap(successImage);
        }


    }


    @Override
    public int getItemCount() {

        return achievements.size();
    }
}
