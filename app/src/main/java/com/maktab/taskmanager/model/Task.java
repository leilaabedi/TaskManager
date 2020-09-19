package com.maktab.taskmanager.model;


import com.maktab.taskmanager.utils.DateUtils;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mname;
    private Date mDate;
    private Time mTime;
    private String mDes;
    private String icon;

    public UUID getId() {
        return mId;
    }

    public Task() {
        mId = UUID.randomUUID();
        mDate = DateUtils.randomDate();
        mTime=DateUtils.randomTime();

    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time Time) {
        this.mTime = Time;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String mDes) {
        this.mDes = mDes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private StateEnum stateEnum;

    public String getname() {
        return mname;
    }

    public void setname(String name) {
        this.mname = name;
    }

    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(StateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }
}
