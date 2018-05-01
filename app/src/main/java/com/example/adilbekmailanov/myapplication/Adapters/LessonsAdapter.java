package com.example.adilbekmailanov.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adilbekmailanov.myapplication.DatabaseHelper;
import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;
import com.example.adilbekmailanov.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;


public class LessonsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] weekNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    ArrayList<LessonItemModel> lessons;
    private int lessonNumber;

    public LessonsAdapter(Context context, ArrayList<LessonItemModel> lessons) {
        this.context = context;
        this.lessons = sortAndAddSections(lessons);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lessonNumber = 0;
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
    public View getView(int i, View convertView, ViewGroup parentView) {
        View v;

        final LessonItemModel lesson = getLessonItemModel(i);

        if (lesson.isSectionHeader()) {
            v = inflater.inflate(R.layout.cell_head, null);
            ((TextView) v.findViewById(R.id.weekName)).setText(weekNames[lesson.getDate()]);
            lessonNumber = 0;
        }
        else {
            v = inflater.inflate(R.layout.cell_lessons, null);
            ((TextView) v.findViewById(R.id.lessonNumber)).setText(lessonNumber+"");
            ((TextView) v.findViewById(R.id.lessonName)).setText(lesson.getName());
            ((TextView) v.findViewById(R.id.lessonTime)).setText(lesson.getSTime() + "\n" + lesson.getFTime());
            ((TextView) v.findViewById(R.id.lessonRoom)).setText(lesson.getRoom());
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    databaseHelper.removeLesson(lesson);
                    lessons.remove(lesson);
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
        lessonNumber++;
        return v;
    }

    private ArrayList sortAndAddSections(ArrayList<LessonItemModel> itemList)
    {

        ArrayList<LessonItemModel> tempList = new ArrayList<>();
        Collections.sort(itemList);

        int header = -1;
        for(int i = 0; i < itemList.size(); i++)
        {
            if(header != itemList.get(i).getDate()) {
                LessonItemModel sectionCell = new LessonItemModel(0, null,null, null, null, itemList.get(i).getDate());
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = itemList.get(i).getDate();
            }
            tempList.add(itemList.get(i));
        }

        return tempList;
    }

    private LessonItemModel getLessonItemModel (int i) {
        return (LessonItemModel) getItem(i);
    }
}
