package com.maktab.taskmanager.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maktab.taskmanager.R;
import com.maktab.taskmanager.model.StateEnum;
import com.maktab.taskmanager.model.Task;
import com.maktab.taskmanager.repository.TaskRepository;
import com.maktab.taskmanager.utils.Sortchar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr1#newInstance} factory method to
 * create an instance of this fragment.
 */


public class fr3 extends Fragment {
    StateEnum mState;
    private TaskAdapter mTaskAdapter;
    private RecyclerView mRecyclerView;
    private TaskRepository mRepository;
    public static final String FRAGMENT_TAG_TASK_MAN2 = "TaskManager2";
    public static final int REQUEST_CODE_TASK_MAN2 = 2;
    private ImageView img1;



    public static fr3 newInstance() {
        Bundle args = new Bundle();
        fr3 fragment = new fr3();
        fragment.setArguments(args);
        return fragment;
    }


    public fr3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = TaskRepository.getInstance();
    }

    public void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr3, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Task> tasks = mRepository.getdoneTasks();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(taskAdapter);
    }


    private void updateUI() {
        List<Task> tasks = mRepository.getdoneTasks();
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.notifyDataSetChanged();
        }
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.rec3);
    }


    private class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;
        private RelativeLayout mRoot;
        private TextView mTextViewdes;

        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.row_item_task_title);
            mTextViewdes = itemView.findViewById(R.id.row_item_task_des);
            img1 = itemView.findViewById(R.id.icon_id);

            mRoot = itemView.findViewById(R.id.relative_item_row);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TaskDetailFragment taskDetailFragment = TaskDetailFragment.newInstance(mTask.getId());
                    taskDetailFragment.setTargetFragment(fr3.this, REQUEST_CODE_TASK_MAN2);


                    taskDetailFragment.show(
                            getActivity().getSupportFragmentManager(),
                            FRAGMENT_TAG_TASK_MAN2);

                }
            });
        }

        public void bindTask(Task task) {
            mTask = task;
            mTextViewTitle.setText(task.getname());

            Date temp = task.getDate();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(temp);
            /*
            pattern = "HH:mm:ss";
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
            String time = simpleDateFormat1.format(temp);
*/
            String time = task.getTime().toString();
            mTextViewdes.setText(date + "  " + time);

            String str=task.getname();
            char ch=str.charAt(0);
            int position=Sortchar.convert(ch);
            Sortchar.setImage(position,img1);




            //  img1.setImageResource(R.mipmap.btn_1);


        }


    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> Tasks) {
            this.mTasks = Tasks;
        }

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> Tasks) {
            this.mTasks = Tasks;
        }


        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }


}

