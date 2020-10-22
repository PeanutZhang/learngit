package com.example.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;

import java.util.Calendar;

/**
 * @Package: com.example.demo.utils
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/10/22
 * @company: 上海若美科技有限公司
 */
public class CalendarUtils {


    public static void addEvent(Context context,long eventId){
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2020, 9, 22, 12, 55);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, 9, 22, 13, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Yoga")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        context.startActivity(intent);

    }

}
