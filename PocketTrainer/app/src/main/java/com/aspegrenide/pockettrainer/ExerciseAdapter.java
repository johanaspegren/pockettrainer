package com.aspegrenide.pockettrainer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aspegrenide.pockettrainer.data.Exercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    /*
     Simple adapter to create exercice cards for the grenar freagment

     Contains ExerciseViewHolder
     impleements
     onCreateViewholder returns exerciseViewHolder)
     onBindViewHolder (void)
     */

    private List<Exercise> exerciseList;
    private Context mContext;

    public ExerciseAdapter(Context mContext, List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        this.mContext = mContext;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView nameOfExercise;
        public TextView shortDescriptionExercise;
        public ImageView pic;
        public CheckBox checkBoxExercise;

        public ExerciseViewHolder(View view) {
            super(view);
            nameOfExercise = (TextView) view.findViewById(R.id.gxl_card_name_of_exercise);
            shortDescriptionExercise = (TextView) view.findViewById(R.id.gxl_card_descr_of_exercise);
            pic = (ImageView) view.findViewById(R.id.imageView);
            checkBoxExercise = (CheckBox) view.findViewById(R.id.checkBoxExercise);

            checkBoxExercise.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        checkBoxChanged(compoundButton.getText().toString(), b);
                    }
                }
            );
        }
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grenar_exercise_list_card_layout, parent, false);

        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        holder.nameOfExercise.setText(exerciseList.get(position).getName());
        holder.shortDescriptionExercise.setText(exerciseList.get(position).getShortDescription());
        holder.pic.setImageResource(R.drawable.simringmedhandtag);
        holder.checkBoxExercise.setText(exerciseList.get(position).getName());
        holder.checkBoxExercise.setChecked(exerciseList.get(position).isSelected());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


    public void checkBoxChanged(String exerciseName, boolean b) {
        // This exercise is selected
        Log.i("EXERCISE ADAPTER", " is chaecked " + b);
//        mAdapterCallback.onMethodCallback(exerciseName,b);
    }
}