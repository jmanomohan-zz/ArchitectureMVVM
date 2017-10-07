package com.jithin.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.TypedValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jithin on 07/10/17.
 */

public class Util {
    public static float dptopx(Context context, float dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static String parseDate(String s) {
        try {
            if (s.contains("\\+"))
                s = s.split("\\+")[0];
            Date date = null;
            SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); //"2016-11-30T10:23:54Z"
            date = parse.parse(s.replace("Z", ""));
            Calendar smsTime = Calendar.getInstance();
            smsTime.setTime(date);
            Calendar now = Calendar.getInstance();
            if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
                return "Today";
            } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
                return "Yesterday";
            } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
                return DateFormat.format("dd MMM", smsTime).toString();
            } else {
                return DateFormat.format("dd MMM yyyy", smsTime).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
