package com.aspegrenide.pockettrainer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.aspegrenide.pockettrainer.data.Session;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<Session> sessionList;
    private Context mContext;
    private int mExpandedPosition;
    private RecyclerView recyclerView;

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;


    public HistoryAdapter(Context mContext, List<Session> sessionList, RecyclerView recyclerView) {
        this.sessionList = sessionList;
        this.mContext = mContext;
        this.recyclerView = recyclerView; // this is provided to control collaps/expand the
        // item of the parent recyclerview

    }


    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_card_linear, parent, false);

        return new HistoryViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        final boolean isExpanded = position == mExpandedPosition;
        holder.vecka.setText(String.valueOf(sessionList.get(position).getWeekNr()));
        holder.grenar.setText(sessionList.get(position).toString());
        holder.expandableLayout.collapse();


        // below is code for the recyclerView for exercises
        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL,
                false);
        holder.recList.setLayoutManager(mLayoutManager);
        holder.recList.setItemAnimator(new DefaultItemAnimator());

        ArrayList<String> pelle = new ArrayList<>();
        pelle.add("hej");
        pelle.add("dej");
        pelle.add("Arnes");

        //Date send to Adapter / Constructor call
        holder.historyExerciseAdapter = new HistoryExerciseAdapter(mContext, sessionList);
        holder.recList.setAdapter(holder.historyExerciseAdapter);

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {

        public TextView vecka;
        public TextView grenar;
        public RecyclerView recList;
        public TextView expText;
        public ExpandableLayout expandableLayout; //https://github.com/cachapa/ExpandableLayout
        public HistoryExerciseAdapter historyExerciseAdapter;


        public HistoryViewHolder(View view) {
            super(view);
            vecka = (TextView) view.findViewById(R.id.textViewVeckonummer);
            grenar = (TextView) view.findViewById(R.id.textviewGrenar);
            recList = (RecyclerView)view.findViewById(R.id.exerciseListView);
            expText = (TextView) view.findViewById(R.id.textview_exp);

            vecka.setOnClickListener(this);

            expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
            expandableLayout.setOnExpansionUpdateListener(this);

        }

        public void bind() {
            int position = getAdapterPosition();
            boolean isSelected = position == selectedItem;

            vecka.setSelected(isSelected);
            expandableLayout.setExpanded(isSelected, false);
        }

        @Override
        public void onClick(View view) {

            HistoryAdapter.HistoryViewHolder holder =
                    (HistoryAdapter.HistoryViewHolder)
                            recyclerView.findViewHolderForAdapterPosition(selectedItem);

            if (holder != null) {
                holder.vecka.setSelected(false);
                holder.expandableLayout.collapse();
            }

            int position = getAdapterPosition();

            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                vecka.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }

}
