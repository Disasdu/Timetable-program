package com.example.adilbekmailanov.myapplication;

        import android.app.Dialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;

        import com.example.adilbekmailanov.myapplication.Model.LessonItemModel;

public class AddNewLessonDialog extends Dialog {

    private ArrayAdapter<String> adapter;
    private DatabaseHelper databaseHelper;
    private HomeFragment rootFragment;
    private String[] weekNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public AddNewLessonDialog(@NonNull Context context, HomeFragment rootFragment) {
        super(context);
        databaseHelper = new DatabaseHelper(context);
        adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, weekNames);

        this.rootFragment = rootFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_lesson_dialog);

        final TextView name = findViewById(R.id.nameEditText);
        final TextView room = findViewById(R.id.roomEditText);
        final TextView endTime = findViewById(R.id.endTimeEditText);
        final TextView startTime = findViewById(R.id.startTimeEditText);

        final Spinner weekNameSpinner = findViewById(R.id.weekNameSpinner);

        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

        weekNameSpinner.setAdapter (adapter);
        weekNameSpinner.setPrompt ("Week");
        weekNameSpinner.setSelection (0);

        (findViewById(R.id.saveButton)).setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonItemModel newLesson = new LessonItemModel(0, name.getText().toString(), startTime.getText().toString(), endTime.getText().toString(), room.getText().toString(), weekNameSpinner.getSelectedItemPosition());
                databaseHelper.addNewLesson(newLesson);
                rootFragment.reloadData();
                dismiss();
            }
        });

        (findViewById(R.id.cancelButton)).setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
