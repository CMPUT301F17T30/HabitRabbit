package com.example.cmput301f17t30.habitrabbit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * arankin on 26/10/17.
 */

public class HabitLayoutAdapter extends RecyclerView.Adapter<HabitLayoutAdapter.ViewHolder>  {

    private ArrayList<Habit> habitList;
    private Context mainContext;


    public HabitLayoutAdapter(ArrayList<Habit> habitList, Context context) {
        this.habitList = habitList;
        this.mainContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView habitName;
        private TextView habitReason;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            habitName = itemView.findViewById(R.id.habitNameTextView);
            habitReason = itemView.findViewById(R.id.habitEventCommentTextView);
        }

        @Override
        public void onClick(View view) {
            //this is for clicking on the habit item itself as oppsed to any particular button
            Toast.makeText(mainContext, "clicking here will bring up new activity",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .habit_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HabitLayoutAdapter.ViewHolder holder, final int position) {
        final Habit habit = habitList.get(position);
        String title = habit.getTitle();
        String reason = habit.getReason();

        holder.habitName.setText(title);
        holder.habitReason.setText(reason);



        /**
        Button delete = holder.itemView.findViewById(R.id.deleteHabitButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do stuff
                notifyItemChanged(position);

            }
        });
        **/

    }


    @Override
    public int getItemCount() {

        return habitList.size();
    }
}
