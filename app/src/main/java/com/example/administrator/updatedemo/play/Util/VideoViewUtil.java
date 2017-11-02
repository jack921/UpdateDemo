package com.example.administrator.updatedemo.play.Util;

import android.content.Context;
import android.widget.Toast;

import java.util.Formatter;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/1.
 */

public class VideoViewUtil {

    public static String formatTime(long milliseconds){
        if(milliseconds<=0||milliseconds>=24*60*60*1000){
            return "00:00";
        }
        long totalSeconds = milliseconds / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public static void showToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }


}
