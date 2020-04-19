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
import android.widget.CompoundButton;
import android.widget.Switch;

import com.aspegrenide.pockettrainer.data.Exercise;
import com.aspegrenide.pockettrainer.data.Gren;
import com.aspegrenide.pockettrainer.data.Resources;
import com.aspegrenide.pockettrainer.data.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GrenarFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    // Uppvärmning
    private RecyclerView recyclerViewWarmup;
    private ExerciseAdapter mExerciseAdapterWarmup;

    // Huvudpass
    private RecyclerView recyclerViewMain;
    private ExerciseAdapter mExerciseAdapterMain;

    private ArrayList<Gren> allGrenar;
    private ArrayList<Exercise> allExersices  = new ArrayList<>();

    private ArrayList<Gren> selectedGrenar = new ArrayList<>();

    private ArrayList<Exercise> proposedExersicesWarmup = new ArrayList<>();
    private ArrayList<Exercise> proposedExersicesMain = new ArrayList<>();

    private Session currentSession;

    public static GrenarFragment newInstance() {
        return new GrenarFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.grenar_fragment, container, false);

        // fetch data about available grenar and exercises
        allGrenar = Resources.getGrenar();
        allExersices = Resources.getExercises();

        // connect the switches to grenar and init them
        connectAllSwitches();
        initAllSwitches(rootView);

        // Huvudpass
        recyclerViewMain = (RecyclerView) rootView.findViewById(R.id.recycler_view_main);
        mExerciseAdapterMain = new ExerciseAdapter(this.getContext(), proposedExersicesMain);
        RecyclerView.LayoutManager mLayoutManagerMain = new LinearLayoutManager(getContext());
        recyclerViewMain.setLayoutManager(mLayoutManagerMain);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMain.setAdapter(mExerciseAdapterMain);

        // Uppvärmning
        recyclerViewWarmup = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mExerciseAdapterWarmup = new ExerciseAdapter(this.getContext(), proposedExersicesWarmup);
        RecyclerView.LayoutManager mLayoutManagerWarmup = new LinearLayoutManager(getContext());
        recyclerViewWarmup.setLayoutManager(mLayoutManagerWarmup);
        recyclerViewWarmup.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWarmup.setAdapter(mExerciseAdapterWarmup);


        return rootView;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (selectedGrenar == null) {return;}
        if (mExerciseAdapterWarmup == null) {return;}
        if (isVisibleToUser) {
            Log.i("GrenarFragment", "Enter");
//            updateExerciseCandidates(Exercise.WARMUP, selectedGrenar);
//            mExerciseAdapterWarmup.notifyDataSetChanged();
//            mExerciseAdapterMain.notifyDataSetChanged();
        }
        else {

            Log.i("GrenarFragment", "Leaving");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    // duplicate in MainActivity
    private Gren getGren(View s) {
        for(Gren g: allGrenar) {
            if(g.getId() == s.getId())
            { return g; }
        }
        return null;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        boolean checked = compoundButton.isChecked();
        Log.i("MAIN FRAGMENT", "onChecked " + checked);
        compoundButton.getId();
        switchChanged(compoundButton, compoundButton.isChecked());
    }

    /** Called when a switch is switched */
    public void switchChanged(View view, boolean checked ) {
        Gren g = getGren(view);
        if (checked) {
            selectedGrenar.add(g);
        } else {
            selectedGrenar.remove(g);
        }
        updateExerciseCandidates(Exercise.WARMUP, selectedGrenar);
        mExerciseAdapterWarmup.notifyDataSetChanged();
        mExerciseAdapterMain.notifyDataSetChanged();

    }


    private boolean updateExerciseCandidates(int type, ArrayList<Gren> mSelectedGrenar) {
        proposedExersicesWarmup.clear();
        proposedExersicesMain.clear();

        for(Gren g: mSelectedGrenar) {
            for(Exercise e: allExersices) {
                Log.i("Main", "good for " + e.getGoodFor());
                if(e.getGoodFor().contains(g) &&
                        type == e.getType() &&
                        !proposedExersicesWarmup.contains(e)){
                    // match!
                    Log.i("Main", "selected gren " + g.getName());
                    Log.i("Main", "selected exercise name " + e.getName());
                    Log.i("Main", "selected exercise short desc " + e.getShortDescription());
                    proposedExersicesWarmup.add(e);
                    proposedExersicesMain.add(e);
                }
            }
        }

        // UPDATE THE MAIN CURRENTSESSION A
        // Init the currentSession
        Date currentTime = Calendar.getInstance().getTime();
        currentSession = new Session(currentTime);
        currentSession.setExercisesMain(proposedExersicesMain);
        currentSession.setExercisesWarmup(proposedExersicesWarmup);
        currentSession.setGrenar(selectedGrenar);
        MainActivity.setCurrentSession(currentSession);

        return true;
    }



    private void connectSwitchToGren(String grenName, int switchId) {
        for (Gren g: allGrenar) {
            if(g.getName().equalsIgnoreCase(grenName)){
                g.setId(switchId);
            }
        }
    }

    private void initSwitchar(View mView, int mId ) {
        Switch mSwitch = (Switch) mView.findViewById(mId);
        mSwitch.setOnCheckedChangeListener(this);
    }

    // LAZY STUFF BELOW

    private void initAllSwitches(View rootView) {

        // init switchar
        initSwitchar(rootView, R.id.switch_sprint);
        initSwitchar(rootView, R.id.switch_hack);
        initSwitchar(rootView, R.id.switch_medeldistans);
        initSwitchar(rootView, R.id.switch_stafett);
        initSwitchar(rootView, R.id.switch_langdistans);

        initSwitchar(rootView, R.id.switch_langd);
        initSwitchar(rootView, R.id.switch_hojd);
        initSwitchar(rootView, R.id.switch_stav);
        initSwitchar(rootView, R.id.switch_tresteg);

        initSwitchar(rootView, R.id.switch_kula);
        initSwitchar(rootView, R.id.switch_diskuss);
        initSwitchar(rootView, R.id.switch_slagga);
        initSwitchar(rootView, R.id.switch_spjut);

        initSwitchar(rootView, R.id.switch_koordination);
        initSwitchar(rootView, R.id.switch_kondition);
        initSwitchar(rootView, R.id.switch_styrka);
        initSwitchar(rootView, R.id.switch_snabbhet);
    }

    private void connectAllSwitches() {
        connectSwitchToGren("Sprint", R.id.switch_sprint);
        connectSwitchToGren("Häck", R.id.switch_hack);
        connectSwitchToGren("Medeldistans", R.id.switch_medeldistans);
        connectSwitchToGren("Stafett", R.id.switch_stafett);
        connectSwitchToGren("Långdistans", R.id.switch_langdistans);

        connectSwitchToGren("Längd", R.id.switch_langd);
        connectSwitchToGren("Höjd", R.id.switch_hojd);
        connectSwitchToGren("Stav", R.id.switch_stav);
        connectSwitchToGren("Tresteg", R.id.switch_tresteg);

        connectSwitchToGren("Kula", R.id.switch_langd);
        connectSwitchToGren("Diskuss", R.id.switch_diskuss);
        connectSwitchToGren("Slägga", R.id.switch_slagga);
        connectSwitchToGren("Spjut", R.id.switch_spjut);

        connectSwitchToGren("Koordination", R.id.switch_koordination);
        connectSwitchToGren("Kondition", R.id.switch_kondition);
        connectSwitchToGren("Styrka", R.id.switch_styrka);
        connectSwitchToGren("Snabbhet", R.id.switch_snabbhet);

    }

}
