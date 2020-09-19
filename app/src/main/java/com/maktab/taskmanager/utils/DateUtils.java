package com.maktab.taskmanager.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtils {
    public static final int YEAR_START = 2000;
    public static final int YEAR_END = 2020;

    public static Date randomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(YEAR_START, YEAR_END);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);



        return gc.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


    public static Time randomTime(){
        Random random = new Random();
          Time time = new Time(random.nextLong());
          return time;

    }




}
