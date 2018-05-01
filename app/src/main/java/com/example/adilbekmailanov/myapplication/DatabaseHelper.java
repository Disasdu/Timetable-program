package com.example.adilbekmailanov.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MY_DATABASE";
    public static final String DATABASE_TABLE = "MY_TABLE";

    public static final String ID = "_ID";
    public static final String NAME = "_NAME";
    public static final String START_TIME = "_STIME";
    public static final String FINISH_TIME = "_FTIME";
    public static final String ROOM = "_ROOM";
    public static final String DATE = "_DATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                START_TIME + " TEXT," +
                FINISH_TIME + " TEXT," +
                ROOM + " TEXT," +
                DATE + " INTEGER" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.delete(DATABASE_TABLE, null, null);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<LessonItemModel> getLessons () {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<LessonItemModel> lessonItemModels = new ArrayList<>();

        Cursor c = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex(ID);
            int nameColIndex = c.getColumnIndex(NAME);
            int sTimeColIndex = c.getColumnIndex(START_TIME);
            int fTimeColIndex = c.getColumnIndex(FINISH_TIME);
            int roomColIndex = c.getColumnIndex(ROOM);
            int dateColIndex = c.getColumnIndex(DATE);

            do {

                int id = c.getInt(idColIndex);
                String name = c.getString(nameColIndex);
                String sTime = c.getString(sTimeColIndex);
                String fTime = c.getString(fTimeColIndex);
                String room = c.getString(roomColIndex);
                int date = c.getInt(dateColIndex);

                LessonItemModel lessonItemModel = new LessonItemModel(id, name, sTime, fTime, room, date);
                lessonItemModels.add(lessonItemModel);

            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return lessonItemModels;
    }

    public void addNewLesson (LessonItemModel model) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, model.getName());
        cv.put(START_TIME, model.getSTime());
        cv.put(FINISH_TIME, model.getFTime());
        cv.put(ROOM, model.getRoom());
        cv.put(DATE, model.getDate());

        db.insert(DATABASE_TABLE, null, cv);
        db.close();
    }

    public void removeLesson (LessonItemModel model) {

        SQLiteDatabase db = getWritableDatabase();

        db.delete(DATABASE_TABLE,ID+"="+model.getId(),null);

        db.close();
    }

    public void changeLesson (LessonItemModel oldModel, LessonItemModel newModel) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, newModel.getName());
        cv.put(START_TIME, newModel.getSTime());
        cv.put(FINISH_TIME, newModel.getFTime());
        cv.put(ROOM, newModel.getRoom());
        cv.put(DATE, newModel.getDate());

        db.update(DATABASE_TABLE, cv,ID+"="+oldModel.getId(), null);
        db.close();
    }
}
