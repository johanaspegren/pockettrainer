package com.aspegrenide.pockettrainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aspegrenide.pockettrainer.data.Exercise;

import java.util.ArrayList;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    /*
     Simple adapter to create exercize cards for the session view
     */

    private ArrayList<Exercise> exerciseArrayList; //keeps all exercises connected to the session
    private Context mContext;

    public SessionAdapter(Context mContext, ArrayList<Exercise> exerciseArrayList) {
        this.mContext = mContext;
        this.exerciseArrayList = exerciseArrayList;
    }

    public class SessionViewHolder extends RecyclerView.ViewHolder {
        public TextView nameOfExercise;
        public TextView shortDescriptionExercise;

        public SessionViewHolder(View view) {
            super(view);
            nameOfExercise = (TextView) view.findViewById(R.id.sxl_card_name_of_exercise);
            shortDescriptionExercise = (TextView) view.findViewById(R.id.sxl_card_descr_of_exercise);
        }
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_exercise_list_card_layout, parent, false);

        return new SessionViewHolder(itemView);
    }

    public void onBindViewHolder(SessionViewHolder holder, int position) {
        holder.nameOfExercise.setText(exerciseArrayList.get(position).getName());
        holder.shortDescriptionExercise.setText("ShortDescription");
    }

    public void setArray(ArrayList<Exercise> eal) {
        exerciseArrayList = eal;
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }
}