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

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

public class SessionFragment extends Fragment{
    /*
     Fragment for the session tab (middle)
     Fragment is basically a sub-activity
     */

    private RecyclerView recyclerViewSessionMain;
    private SessionAdapter mSessionAdapter;

    private ArrayList<Exercise> selectedMainExercises = new ArrayList<>();
    private Session currentSession;

    public static SessionFragment newInstance() {
        return new SessionFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.session_fragment_layout, container, false);

//        selectedMainExercises = Resources.getExercises();


        recyclerViewSessionMain = (RecyclerView) rootView.findViewById(R.id.session_fragment_recycler_view);
        mSessionAdapter = new SessionAdapter(this.getContext(), selectedMainExercises);

        RecyclerView.LayoutManager mLayoutManagerMain = new LinearLayoutManager(getContext());
        recyclerViewSessionMain.setLayoutManager(mLayoutManagerMain);
        recyclerViewSessionMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSessionMain.setAdapter(mSessionAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("SessionFragment", "enter");
            // fetch
            currentSession = MainActivity.getCurrentSession();
            if (currentSession != null) {
                selectedMainExercises = currentSession.getExercisesMain();
            }
            mSessionAdapter.setArray(selectedMainExercises);
            mSessionAdapter.notifyDataSetChanged();
        }
        else {
            Log.i("SessionFragment", "leaving");
            // fetch
            if(currentSession != null) {
               // MainActivity.setCurrentSession(currentSession);
            }
        }
    }
}
