package com.example.adilbekmailanov.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.adilbekmailanov.myapplication.Adapters.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionPageAdapter sectionPageAdapter;
    private ViewPager viewPager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("111", true)) {
            sharedPreferences.edit().putBoolean("111", false).commit();

            Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
        }
    }



    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addNewPage(new PageModel(new HomeFragment(), "Home"));
        sectionPageAdapter.addNewPage(new PageModel(new SettingFragment(), "Settings"));
        viewPager.setAdapter(sectionPageAdapter);
    }
}
