package com.maktab.taskmanager.repository;

import com.maktab.taskmanager.model.StateEnum;
import com.maktab.taskmanager.model.Task;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    public int TASK_SIZE = 10;
    private static TaskRepository sInstance;
    private List<Task> mTasks;


    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();
        return sInstance;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public List<Task> getTodoTasks() {
        ArrayList<Task> mtemp = new ArrayList<Task>();
        StateEnum temp = StateEnum.Todo;
        for (Task item : mTasks) {
            if (item.getStateEnum() == StateEnum.Todo) {
                mtemp.add(item);
            }
        }

        return mtemp;

    }


    public void setTasks(List<Task> mTasks) {
        this.mTasks = mTasks;
    }

    private TaskRepository() {
        int rand;
        StateEnum temp = StateEnum.Todo;
        mTasks = new ArrayList<>();
        for (int i = 0; i < TASK_SIZE; i++) {
            Task task = new Task();
            task.setname("task" + i);
            task.setDes("description" + i);
            rand = makeRandom();
            if (rand == 1)
                task.setStateEnum(temp.Todo);
            if (rand == 2)
                task.setStateEnum(temp.Done);
            if (rand == 3)
                task.setStateEnum(temp.Doing);

            mTasks.add(task);
        }
    }

    private int makeRandom() {
        int min, max, randOrder;
        max = 3;
        min = 1;
        randOrder = (int) (Math.random() * ((max - min) + 1)) + min;
        return randOrder;
    }


    public Task getTask(UUID id) {
        for (Task task: mTasks) {
            if (task.getId().equals(id))
                return task;
        }
        return null;
    }

    public void insertTask(Task task) {
        mTasks.add(task);
    }


    public void deleteTask(Task task) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId())) {
                mTasks.remove(i);
                return;
            }
        }
    }


    public void updateTask(Task task) {
        Task findTask = getTask(task.getId());
        findTask.setname(task.getname());
        findTask.setDes(task.getDes());
        findTask.setDate(task.getDate());
    }


    public int getPosition(UUID crimeId) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(crimeId))
                return i;
        }
        return 0;
    }




}
