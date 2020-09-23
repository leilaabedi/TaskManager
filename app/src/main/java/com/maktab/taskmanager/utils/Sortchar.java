package com.maktab.taskmanager.utils;

import android.widget.ImageView;

import com.maktab.taskmanager.R;

public class Sortchar {
    public char[] charFarsi;


    public static int convert(char ch) {
        char mychar[] = {'ا', 'آ', 'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز', 'ژ', 'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'گ', 'ل', 'م', 'ن', 'و', 'ه', 'ي'};
        for (int i = 0; i < mychar.length; i++)
            if (mychar[i] == ch)
                return i;
        return -1;
    }

    public static void setImage(int pos, ImageView img1) {


        switch (pos) {
            case 0:
                img1.setImageResource(R.mipmap.btn_1);
                break;
            case 1:
                img1.setImageResource(R.mipmap.btn_1);
                break;
            case 2:
                img1.setImageResource(R.mipmap.btn_2);
                break;

            case 3:
                img1.setImageResource(R.mipmap.btn_3);
                break;

            case 4:
                img1.setImageResource(R.mipmap.btn_4);
                break;
            case 5:
                img1.setImageResource(R.mipmap.btn_5);
                break;

            case 6:
                img1.setImageResource(R.mipmap.btn_6);
                break;

            case 7:
                img1.setImageResource(R.mipmap.btn_7);
                break;

            case 8:
                img1.setImageResource(R.mipmap.btn_8);
                break;
            case 9:
                img1.setImageResource(R.mipmap.btn_9);
                break;

            case 10:
                img1.setImageResource(R.mipmap.btn_10);
                break;


            case 11:
                img1.setImageResource(R.mipmap.btn_11);
                break;

            case 12:
                img1.setImageResource(R.mipmap.btn_12);
                break;
            case 13:
                img1.setImageResource(R.mipmap.btn_13);
                break;

            case 14:
                img1.setImageResource(R.mipmap.btn_14);
                break;


            case 15:
                img1.setImageResource(R.mipmap.btn_15);
                break;
            case 16:
                img1.setImageResource(R.mipmap.btn_16);
                break;

            case 17:
                img1.setImageResource(R.mipmap.btn_17);
                break;

            case 18:
                img1.setImageResource(R.mipmap.btn_18);
                break;
            case 19:
                img1.setImageResource(R.mipmap.btn_19);
                break;

            case 20:
                img1.setImageResource(R.mipmap.btn_20);
                break;

            case 21:
                img1.setImageResource(R.mipmap.btn_21);
                break;

            case 22:
                img1.setImageResource(R.mipmap.btn_22);
                break;
            case 23:
                img1.setImageResource(R.mipmap.btn_23);
                break;

            case 24:
                img1.setImageResource(R.mipmap.btn_24);
                break;


            case 25:
                img1.setImageResource(R.mipmap.btn_25);
                break;

            case 26:
                img1.setImageResource(R.mipmap.btn_26);
                break;
            case 27:
                img1.setImageResource(R.mipmap.btn_27);
                break;

            case 28:
                img1.setImageResource(R.mipmap.btn_28);
                break;

            case 29:
                img1.setImageResource(R.mipmap.btn_29);
                break;

            case 30:
                img1.setImageResource(R.mipmap.btn_30);
                break;

            case 31:
                img1.setImageResource(R.mipmap.btn_31);
                break;

            case 32:
                img1.setImageResource(R.mipmap.btn_32);
                break;


        }


    }


}
