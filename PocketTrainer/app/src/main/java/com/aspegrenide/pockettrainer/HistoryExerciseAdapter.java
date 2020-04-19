package com.aspegrenide.pockettrainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspegrenide.pockettrainer.data.Session;

import java.util.ArrayList;
import java.util.List;

public class HistoryExerciseAdapter extends RecyclerView.Adapter<HistoryExerciseAdapter.MyViewHolder> {
    //ArrayList<String> list;
    List<Session> list;
    Context context;

    public HistoryExerciseAdapter(Context context, List<Session> listCal) {
        this.context = context;
        this.list = listCal;
    }

    @NonNull
    @Override
    public HistoryExerciseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.whatever , parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryExerciseAdapter.MyViewHolder holder, int position) {
        final Session listPotn = list.get(position);
        holder.day.setText(listPotn.toString() + " \n\n\nhhh\n\n\nhhh\n\n\nhhh");
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0 && list !=null)
            return list.size();
        else return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day, date, price;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.textView);
//            linearLayout = itemView.findViewById(R.id.lLayout);
        }
    }}
