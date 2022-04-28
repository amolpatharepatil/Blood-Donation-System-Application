package com.example.bloodrequisitionsystem.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodrequisitionsystem.R;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    List<ModelHistory> list;
    Context ctx;

    public AdapterHistory(Context ctx, List<ModelHistory> list){
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.item_history,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_date.setText("Date : "+list.get(position).getDate());
        holder.tv_task.setText("Task : "+list.get(position).getTask());
        holder.tv_blood.setText("Blood : "+list.get(position).getBlood());
        holder.tv_status.setText("Status : "+list.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date, tv_task, tv_blood, tv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_date = itemView.findViewById(R.id.tv_date);
            tv_task = itemView.findViewById(R.id.tv_task);
            tv_blood = itemView.findViewById(R.id.tv_blood);
            tv_status = itemView.findViewById(R.id.tv_status);

        }
    }
}
