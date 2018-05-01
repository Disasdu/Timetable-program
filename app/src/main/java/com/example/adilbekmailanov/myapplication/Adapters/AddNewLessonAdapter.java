package com.example.adilbekmailanov.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;
import com.example.adilbekmailanov.myapplication.R;

import java.util.ArrayList;

public class AddNewLessonAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<LessonItemModel> lessons;

    public AddNewLessonAdapter(@NonNull Context context, ArrayList<LessonItemModel> lessons) {
        this.context = context;
        this.lessons = lessons;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int i) {
        return lessons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        v = viewGroup;

        LessonItemModel lesson = getLesson(i);

        if (lesson.isSectionHeader()) {
            v = inflater.inflate(R.layout.add_new_lesson_dialog, null);
            ((TextView) v.findViewById(R.id.weekName)).setText(lesson.getDate()+"");
        }
        else {
            v = inflater.inflate(R.layout.cell_lessons, null);
            ((TextView) v.findViewById(R.id.lessonName)).setText(lesson.getName());
            ((TextView) v.findViewById(R.id.lessonTime)).setText(lesson.getSTime() + "\n" + lesson.getFTime());
            ((TextView) v.findViewById(R.id.lessonRoom)).setText(lesson.getRoom());
        }
        return v;
    }

    private LessonItemModel getLesson(int i) {
        return (LessonItemModel) getItem(i);
    }
}
