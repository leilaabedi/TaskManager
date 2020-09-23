package com.maktab.taskmanager.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.maktab.taskmanager.R;
import com.maktab.taskmanager.controller.fragment.TaskDetailFragment;
import com.maktab.taskmanager.model.Task;
import com.maktab.taskmanager.repository.TaskRepository;
import com.maktab.taskmanager.taskmanager.controller.fragment.ViewPagerAdapter;
import com.maktab.taskmanager.controller.fragment.fr1;
import com.maktab.taskmanager.controller.fragment.fr2;
import com.maktab.taskmanager.controller.fragment.fr3;

public class TaskListActivity extends AppCompatActivity {
    Toolbar mToolbar;
    TabLayout tbLayout;
    ViewPager vPager;
    ImageView userIcon;
    FloatingActionButton add_btn;
    // public static final int REQUEST_CODE_TASK_MAN = 0;
    //public static final String FRAGMENT_TAG_TASK_MAN = "TaskManager";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TaskListActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = findViewById(R.id.m_toolbar);
        setSupportActionBar(mToolbar);


        vPager = findViewById(R.id.view_pager);
        tbLayout = findViewById(R.id.tab_layout);
        userIcon = findViewById(R.id.userpic);
        add_btn = findViewById(R.id.add_Task);

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TaskListActivity.this, login.class);
                startActivity(intent);

            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task task = new Task();
                TaskRepository.getInstance().insertTask(task);

                FragmentManager fm = getSupportFragmentManager();
                TaskDetailFragment alertDialog = TaskDetailFragment.newInstance(task.getId());

                alertDialog.show(fm, "fragment_alert");


            }
        });


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrg(new fr1(), "TODO");
        adapter.addFrg(new fr2(), "DOING");
        adapter.addFrg(new fr3(), "DONE");
        vPager.setAdapter(adapter);
        tbLayout.setupWithViewPager(vPager);


    }


}