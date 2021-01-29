package com.training.timekeeping.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
//    public static String convertLocalDateTimeToString(LocalDateTime) {
//
//    }

    public static String convertTime(long seconds) {
        String hourStr;
        String minuteStr;
        String secondsStr;
        int hour = (int) seconds/3600;
        int minute = (int) (seconds - hour * 3600) / 60;
        seconds = (int) seconds - hour * 3600 - minute * 60;

        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = hour + "";
        }

        if (minute < 10) {
            minuteStr = "0" + minute;
        } else {
            minuteStr = "" + minute;
        }

        if (seconds < 10) {
            secondsStr = "0" + seconds;
        } else {
            secondsStr = "" + seconds;
        }


        return hourStr + ":" + minuteStr + ":" + secondsStr;
    }
}
