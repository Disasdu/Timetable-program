package com.example.adilbekmailanov.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SettingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        final SharedPreferences sharedPreferences = getContext().getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);

        final EditText editText = view.findViewById(R.id.editText2);
        editText.setText(""+sharedPreferences.getInt("222", 10));

        (view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putInt("222", Integer.parseInt(editText.getText().toString())).commit();
                editText.endBatchEdit();
            }
        });

        return view;
    }
}
