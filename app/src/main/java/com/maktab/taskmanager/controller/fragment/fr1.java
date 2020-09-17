package com.maktab.taskmanager.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maktab.taskmanager.R;
import com.maktab.taskmanager.controller.activity.TaskPagerActivity;
import com.maktab.taskmanager.model.StateEnum;
import com.maktab.taskmanager.model.Task;
import com.maktab.taskmanager.repository.TaskRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr1#newInstance} factory method to
 * create an instance of this fragment.
 */


public class fr1 extends Fragment {
    StateEnum mState;

    private RecyclerView mRecyclerView;
    private TaskRepository mRepository;


    public static fr1 newInstance() {

        Bundle args = new Bundle();

        fr1 fragment = new fr1();
        fragment.setArguments(args);
        return fragment;
    }


    public fr1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = TaskRepository.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr1, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Task> tasks = mRepository.getTodoTasks();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(taskAdapter);
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.rec1);
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
            mRoot = itemView.findViewById(R.id.relative_item_row);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = TaskPagerActivity.newIntent(getActivity(), mTask.getId());
                    startActivity(intent);

                }
            });
        }

        public void bindTask(Task task) {

            mTask = task;
            mTextViewTitle.setText(task.getMname());

            mTextViewdes.setText(task.getDate().toString());


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

