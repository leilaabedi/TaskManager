package com.maktab.taskmanager.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.maktab.taskmanager.R;
import com.maktab.taskmanager.controller.activity.TaskListActivity;
import com.maktab.taskmanager.model.StateEnum;
import com.maktab.taskmanager.model.Task;
import com.maktab.taskmanager.repository.TaskRepository;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TaskDetailFragment extends DialogFragment {

    public static final String TAG = "CDF";
    public static final String ARGS_CRIME_ID = "crimeId";
    public static final String FRAGMENT_TAG_DATE_PICKER = "DatePicker";
    public static final int REQUEST_CODE_DATE_PICKER = 0;
    public static final String FRAGMENT_TAG_TIME_PICKER = "TimePicker";
    public static final int REQUEST_CODE_TIME_PICKER = 1;


    private EditText mTitle, mDes;
    private Button mDate, mTime;
    private CheckBox mDone;

    private Task mTask;
    private TaskRepository mRepository;

    public static TaskDetailFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_ID, crimeId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TaskDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        mRepository = TaskRepository.getInstance();

        //this is storage of this fragment
        UUID crimeId = (UUID) getArguments().getSerializable(ARGS_CRIME_ID);
        mTask = mRepository.getTask(crimeId);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_task_detail, null);

        findViews(view);
        initViews();
        setListeners();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.task_title_label)
                .setView(view)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateTask();

                        Intent intent = TaskListActivity.newIntent(getActivity());
                        startActivity(intent);

                    }
                });


        AlertDialog dialog = builder.create();

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mTitle.requestFocus();
        return dialog;
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            Date userSelectedDate =
                    (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);

            updateTaskDate(userSelectedDate);
        }

        if (requestCode == REQUEST_CODE_TIME_PICKER) {
            Time userSelectedTime =
                    (Time) data.getSerializableExtra(TimePickerFragment.EXTRA_USER_SELECTED_TIME);

            updateTaskTime(userSelectedTime);
        }


    }


    private void findViews(View view) {
        mTitle = view.findViewById(R.id.task_title);
        mDes = view.findViewById(R.id.task_des);
        mDone = view.findViewById(R.id.task_done);
        mDate = view.findViewById(R.id.task_date);
        mTime = view.findViewById(R.id.task_time);
    }

    private void initViews() {
        mTitle.setText(mTask.getname());
        mDes.setText(mTask.getDes());
        changeDate();
        mTime.setText(mTask.getTime().toString());
        initDone();

    }


    private void initDone() {
        StateEnum temp = StateEnum.Done;
        if (mTask.getStateEnum() == StateEnum.Done)
            mDone.setChecked(true);

        if (mTask.getStateEnum() == StateEnum.Doing)
            mDone.setChecked(false);

        if (mTask.getStateEnum() == StateEnum.Todo)
            mDone.setChecked(false);

    }

    private void changeDate() {
        Date temp = mTask.getDate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(temp);
        mDate.setText(date);
    }
/*
    private void changeTime() {
        Date temp = mTask.getDate();
        String pattern = "HH:mm:ss";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
        String time = simpleDateFormat1.format(temp);
        mTime.setText(time);
    }




    @Override
    public void onPause() {
        super.onPause();
        updateCrime();

        Log.d(TAG, "onPause");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_DATE_PICKER) {
            Date userSelectedDate =
                    (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);

            updateCrimeDate(userSelectedDate);
        }
    }
*/

    private void setListeners() {
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s + ", " + start + ", " + before + ", " + count);
                mTask.setname(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                StateEnum temp = StateEnum.Done;

                if (b == true)
                    mTask.setStateEnum(temp);

            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerFragment datePickerFragment =
                        DatePickerFragment.newInstance(mTask.getDate());

                //create parent-child relations between CDF and DPF
                datePickerFragment.setTargetFragment(
                        TaskDetailFragment.this,
                        REQUEST_CODE_DATE_PICKER);

                datePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_DATE_PICKER);

            }
        });


        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment =
                        TimePickerFragment.newInstance(mTask.getTime());

                //create parent-child relations between CDF and DPF
                timePickerFragment.setTargetFragment(
                        TaskDetailFragment.this,
                        REQUEST_CODE_TIME_PICKER);

                timePickerFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_TIME_PICKER);

            }
        });


    }

    private void updateTask() {
        mRepository.updateTask(mTask);
    }

    private void deleteTask() {
        mRepository.deleteTask(mTask);

    }

    private void updateTaskDate(Date userSelectedDate) {
        mTask.setDate(userSelectedDate);
        updateTask();
        changeDate();
        //  mDate.setText(mTask.getDate().toString());
    }

    private void updateTaskTime(Time userSelectedTime) {
        mTask.setTime(userSelectedTime);
        updateTask();
        mTime.setText(userSelectedTime.toString());
    }


}

