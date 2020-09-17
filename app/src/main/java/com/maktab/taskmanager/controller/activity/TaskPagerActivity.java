package com.maktab.taskmanager.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.maktab.taskmanager.R;
import com.maktab.taskmanager.model.Task;
import com.maktab.taskmanager.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

public class TaskPagerActivity extends AppCompatActivity {
    public static final String EXTRA_Task_ID = "com.maktab.taskmanager.crimeId";
    public static final String TAG = "CPA";
    private TaskRepository mRepository;
    private UUID mCrimeId;

    private ViewPager2 mViewPagerTasks;

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, TaskPagerActivity.class);
        intent.putExtra(EXTRA_Task_ID, crimeId);
        return intent;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        mRepository = TaskRepository.getInstance();
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_Task_ID);

        findViews();
        initViews();
    }

    private void findViews() {
        mViewPagerTasks = findViewById(R.id.view_pager_tasks);
    }

    private void initViews() {
        List<Task> tasks = mRepository.getTasks();
        TaskPagerAdapter adapter = new TaskPagerAdapter(this, tasks);
        mViewPagerTasks.setAdapter(adapter);

        int currentIndex = mRepository.getPosition(mCrimeId);
        mViewPagerTasks.setCurrentItem(currentIndex);
    }

    private class TaskPagerAdapter extends FragmentStateAdapter {

        private List<Task> mTasks;

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks =tasks;
        }

        public TaskPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Task> tasks) {
            super(fragmentActivity);
            mTasks = tasks;
        }


        @Override
        public Fragment createFragment(int position) {
            Log.d(TAG, "position: " + (position + 1));

            Task task = mTasks.get(position);
            CrimeDetailFragment crimeDetailFragment =
                    CrimeDetailFragment.newInstance(crime.getId());

            return crimeDetailFragment;
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }









}