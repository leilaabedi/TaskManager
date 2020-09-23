package com.maktab.taskmanager.model;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;

import com.maktab.taskmanager.R;
import com.maktab.taskmanager.utils.DateUtils;
import com.maktab.taskmanager.utils.Sortchar;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mname;
    private Date mDate;
    private Time mTime;
    private String mDes;
    private int icon;

    public UUID getId() {
        return mId;
    }

    public Task() {
        mId = UUID.randomUUID();
        mDate = DateUtils.randomDate();
        mTime=DateUtils.randomTime();

    }


    public void setIcon(int icon) {
        //char ch=mname.charAt(0);
        //int temp;
        //Context context = null;
        //temp= Sortchar.convert(ch);
       // String str="btn_"+temp;

       // icon.setImageDrawable(getDrawable("btn_1"));

             //icon=getDrawable("btn_1");
        icon=getRes(1);
    }

    public int getIcon() {
        return icon;
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




    public static int getRes(int i){
        Context context = YourApplication.getContext();

        int imageKey = context.getResources().getIdentifier("btn_"+i, "drawable", context.getPackageName());
        return imageKey;

    }


    public static Drawable getDrawable(String name) {
        Context context = YourApplication.getContext();
        int resourceId = context.getResources().getIdentifier(name, "drawable", YourApplication.getContext().getPackageName());
        return context.getResources().getDrawable(resourceId);
    }



}

class YourApplication extends Application {

    private static YourApplication instance;

    public YourApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
