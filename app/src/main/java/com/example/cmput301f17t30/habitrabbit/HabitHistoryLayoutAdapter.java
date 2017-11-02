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
 * Created by arankin on 11/2/17.
 */

public class HabitHistoryLayoutAdapter extends RecyclerView.Adapter<HabitHistoryLayoutAdapter.ViewHolder>{

    private ArrayList<HabitEvent> habitHistoryList;
    private Context historyContext;

    public HabitHistoryLayoutAdapter(ArrayList<HabitEvent> habitHistoryList, Context context) {
        this.habitHistoryList = habitHistoryList;
        this.historyContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //this is for clicking on the history item itself as oppsed to any particular button
            Toast.makeText(historyContext, "clicking here will bring up new activity",
                    Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public HabitHistoryLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .habit_history_row_layout, parent, false);
        return new HabitHistoryLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HabitHistoryLayoutAdapter.ViewHolder holder, final int position) {
        final HabitEvent habitEvent = habitHistoryList.get(position);






        /**
         Button delete = holder.itemView.findViewById(R.id.deleteHabitEventButton);

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

        return habitHistoryList.size();
    }
}
