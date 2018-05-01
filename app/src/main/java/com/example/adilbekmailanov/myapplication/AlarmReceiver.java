package com.example.adilbekmailanov.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class AlarmReceiver extends BroadcastReceiver {

    private DatabaseHelper databaseHelper;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        databaseHelper = new DatabaseHelper(context);

        checkLesson(context);
    }

    private void checkLesson(Context context) {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-2;

        ArrayList<LessonItemModel> itemList = databaseHelper.getLessons();
        Collections.sort(itemList);

        int header = -1;
        for(int i = 0; i < itemList.size(); i++)
        {
            if (header == dayOfWeek) {

                LessonItemModel lessonItemModel = itemList.get(i);
                String time[] = lessonItemModel.getSTime().split(":");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));

                if (System.currentTimeMillis() < calendar.getTimeInMillis()) {

                    setNotification(context, calendar, lessonItemModel);
                }
            }
            if(header != itemList.get(i).getDate()) {
                header = itemList.get(i).getDate();
            }
        }
    }

    private void setNotification (Context context, Calendar calendar, LessonItemModel lessonItemModel) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        String time[] = lessonItemModel.getSTime().split(":");
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]) - sharedPreferences.getInt("222", 10));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context)
                .setContentTitle(lessonItemModel.getName())
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentText("")
                .setSound(alarmSound)
                .setWhen(calendar.getTimeInMillis())
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager.notify(0, mNotifyBuilder.build());
    }
}