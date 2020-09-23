package com.maktab.taskmanager.utils;

public class Sortchar {
    public char[] charFarsi;


    public static int convert(char ch){
        char mychar[]={'ا','آ','ب','پ','ت','ث','ج','چ','ح','خ','د','ذ','ر','ز','ژ','س','ش','ص','ض','ط','ظ','ع','غ','ف','ق','ك','گ','ل','م','ن','و','ه','ي'};
        for(int i=0;i<mychar.length;i++)
            if (mychar[i]==ch)
                return i;
        return -1;
    }



}
