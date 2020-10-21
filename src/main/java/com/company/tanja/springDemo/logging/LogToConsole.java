package com.company.tanja.springDemo.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogToConsole {
    public void log(String message){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date) + " : " + message); //2016/11/16 12:08:43
    }

}
