package com.example.burning.DatabaseV2;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burning.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView startTime;
        public TextView endTime;
        public TextView duration;
        public TextView distance;
        public TextView calorie;
        public TextView aktywnosc;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            startTime = itemView.findViewById(R.id.tv_startTime);
            endTime = itemView.findViewById(R.id.tv_endTime);
            distance = itemView.findViewById(R.id.tv_dystans);
            duration = itemView.findViewById(R.id.tv_chronometer);
            aktywnosc = itemView.findViewById(R.id.tv_aktywnosc);
            calorie = itemView.findViewById(R.id.tv_kalorie);
        }
    }

    private Activity activity;
    private List<HistoryData> history = new ArrayList<>();

    public ItemAdapter(List<HistoryData> historyList, Activity activity) {
        history.addAll(historyList);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_results,viewGroup,false);
        ItemViewHolder vh = new ItemViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {

        final HistoryData data = history.get(position);

        holder.startTime.setText(data.startTime);
        holder.endTime.setText(data.endTime);
        holder.duration.setText(data.duration);
        holder.distance.setText(String.format("%.3f", data.distance));
        holder.calorie.setText(data.calorie + "");
        holder.aktywnosc.setText(data.aktywnosc);
    }

    @Override
    public int getItemCount() {
        return history.size();
    }


}
