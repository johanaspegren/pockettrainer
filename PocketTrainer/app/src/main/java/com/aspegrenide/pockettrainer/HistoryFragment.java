package com.aspegrenide.pockettrainer;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspegrenide.pockettrainer.data.Exercise;
import com.aspegrenide.pockettrainer.data.Gren;
import com.aspegrenide.pockettrainer.data.Resources;
import com.aspegrenide.pockettrainer.data.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerViewHistory;
    private HistoryAdapter mHistoryAdapter;

    private ArrayList<Session> allSessions;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("sessionFragment", "onCreateView");
        allSessions = createHistory();

        View rootView = inflater.inflate(R.layout.history_fragment_layout, container, false);

        recyclerViewHistory = (RecyclerView) rootView.findViewById(R.id.recycler_view_history);

        mHistoryAdapter = new HistoryAdapter(this.getContext(),allSessions,recyclerViewHistory);

        RecyclerView.LayoutManager mLayoutManagerMain = new LinearLayoutManager(getContext());
        recyclerViewHistory.setLayoutManager(mLayoutManagerMain);
        recyclerViewHistory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHistory.setAdapter(mHistoryAdapter);

        // done in setUserVisibleHint
//        findSelectedExercises(allExercises);
//        mExerciseAdapterSessionMain.notifyDataSetChanged();


        // Inflate the layout for this fragment
        return rootView;
    }


    public ArrayList<Session> createHistory(){
        // convenience method, create a seasnons worth of training sessions
        ArrayList<Session> history = new ArrayList<Session>();
        ArrayList<Gren> allGrenar = Resources.getGrenar();
        ArrayList<Exercise> allExercises = Resources.getExercises();

        Random rand = new Random();

        for (int i = 4; i < 26; i++) {
            ArrayList<Gren> gg = new ArrayList<Gren>();
            gg.add(allGrenar.get(rand.nextInt(allGrenar.size())));
            gg.add(allGrenar.get(rand.nextInt(allGrenar.size())));

            ArrayList<Exercise> ee = new ArrayList<Exercise>();
            ee.add(allExercises.get(rand.nextInt(allExercises.size())));
            ee.add(allExercises.get(rand.nextInt(allExercises.size())));
            ee.add(allExercises.get(rand.nextInt(allExercises.size())));
            ee.add(allExercises.get(rand.nextInt(allExercises.size())));

            Date date = new GregorianCalendar(2019, 8, 29, 17, 30).getTime();

            history.add(new Session(date, gg, ee, ee));
        }
        return history;
    }
}
