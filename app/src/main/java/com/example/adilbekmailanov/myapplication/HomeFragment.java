package com.example.adilbekmailanov.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adilbekmailanov.myapplication.Adapters.LessonsAdapter;
import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private ListView daysListView;
    private DatabaseHelper databaseHelper;
    private ArrayList<LessonItemModel> lessons;
    private LessonsAdapter daysAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        databaseHelper = new DatabaseHelper(getContext());

        lessons = databaseHelper.getLessons();

        daysAdapter = new LessonsAdapter(getActivity(), lessons);

        daysListView = view.findViewById(R.id.lessonsListView);

        daysListView.setAdapter(daysAdapter);

        view.findViewById(R.id.floatingActionButton).setOnClickListener(this);

        return view;
    }

    public void reloadData () {
        lessons = databaseHelper.getLessons();
        daysAdapter = new LessonsAdapter(getActivity(), lessons);
        daysListView.setAdapter(daysAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.floatingActionButton:

                AddNewLessonDialog addNewLessonDialog = new AddNewLessonDialog(getContext(), this);
                addNewLessonDialog.show();

                break;

        }

    }
}
