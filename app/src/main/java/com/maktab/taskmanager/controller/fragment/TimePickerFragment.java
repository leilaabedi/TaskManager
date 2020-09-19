package com.maktab.taskmanager.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.maktab.taskmanager.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimePickerFragment extends DialogFragment {

    public static final String ARGS_TASK_TIME = "taskTime";
    public static final String EXTRA_USER_SELECTED_TIME = "com.maktab.taskmanager.userSelectedTime";
    private Time mTaskTime;

    private TimePicker mTimePicker;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    public static TimePickerFragment newInstance(Time taskTime) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_TASK_TIME, taskTime);
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskTime = (Time) getArguments().getSerializable(ARGS_TASK_TIME);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_time_picker, null);

        findViews(view);
        initViews();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("hello")
                .setIcon(R.mipmap.ic_launcher)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Time userSelectedTime = extractTimeFromTimePicker();
                        sendResult(userSelectedTime);

                    }
                })
                .setNegativeButton(android.R.string.cancel, null);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void findViews(View view) {
        mTimePicker = view.findViewById(R.id.time_picker_task);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews() {
        initTimePicker();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTimePicker() {
        // i have a date and i want to set it in date picker.
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(mTaskTime);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private Time extractTimeFromTimePicker() {
        int hour = mTimePicker.getHour();
        int minute = mTimePicker.getMinute();

        Time time = new Time(hour, minute, 0);

        return time;

    }

    private void sendResult(Time userSelectedTime) {
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_SELECTED_TIME, userSelectedTime);
        fragment.onActivityResult(requestCode, resultCode, intent);
    }


}