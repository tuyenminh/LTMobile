package com.example.appquanlidiem.util_tkb;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;

import androidx.core.app.ActivityCompat;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarReminderUtils {
    private static final Uri CALENDER_URI = CalendarContract.Calendars.CONTENT_URI;
    private static final Uri CALENDER_EVENT_URI = CalendarContract.Events.CONTENT_URI;
    private static final Uri CALENDER_REMINDER_URI = CalendarContract.Reminders.CONTENT_URI;

    private static final String CALENDARS_NAME = "LightTimetable";
    private static final String CALENDARS_ACCOUNT_NAME = "LightTimetable@potato.com";
    private static final String CALENDARS_ACCOUNT_TYPE = "com.potato.timetable";

    private static final String CALENDARS_DISPLAY_NAME = "lịch học";

    public static final String DESCRIPTION = "Thời khóa biểu";




    public static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }


    private static int checkCalendarAccount(Context context) {
        try (Cursor userCursor = context.getContentResolver().query(CALENDER_URI, null,
                CalendarContract.Calendars.ACCOUNT_NAME + " = ? AND " +
                        CalendarContract.Calendars.ACCOUNT_TYPE + " = ?",
                new String[]{CALENDARS_ACCOUNT_NAME, CALENDARS_ACCOUNT_TYPE},
                null)) {
            if (userCursor == null) {
                return -1;
            }
            int count = userCursor.getCount();
            if (count > 0) {
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        }
    }


    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();

        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);

        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);

        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);

        value.put(CalendarContract.Calendars.VISIBLE, 1);

        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);

        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);

        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);

        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());

        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = CALENDER_URI.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        return result == null ? -1 : ContentUris.parseId(result);
    }


    public static Uri addCalendarEvent(Context context,
                                       String title,
                                       String description,
                                       String location,
                                       long eventStartTime,
                                       long eventLength) {
        if (context == null) {
            return null;
        }
        int calId = checkAndAddCalendarAccount(context);
        if (calId < 0) {
            return null;
        }

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(eventStartTime);
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + eventLength);
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        event.put(CalendarContract.Events.TITLE, title);
        event.put(CalendarContract.Events.DESCRIPTION, description);
        event.put(CalendarContract.Events.CALENDAR_ID, calId);

        event.put(CalendarContract.Events.EVENT_LOCATION, location);
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        return context.getContentResolver().insert(CALENDER_EVENT_URI, event);

    }


    public static Uri addCalendarAlarm(Context context, Uri event, int previousMinute) {
        if (event == null)
            return null;

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(event));
        values.put(CalendarContract.Reminders.MINUTES, previousMinute);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        return context.getContentResolver().insert(CALENDER_REMINDER_URI, values);

    }


    public static int findCalendarEvent(Context context, String description, long start, long end) {
        if (context == null) {
            return -1;
        }
        Cursor eventCursor = context.getContentResolver()
                .query(CALENDER_EVENT_URI, new String[]{CalendarContract.Events._ID},
                        CalendarContract.Events.DESCRIPTION + "=? and " +
                                CalendarContract.Events.DTSTART + ">? and " +
                                CalendarContract.Events.DTEND + "<?",
                        new String[]{description, String.valueOf(start), String.valueOf(end)}, null);

        try {
            if (eventCursor != null) {
                return eventCursor.getCount();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
        return -1;
    }


    public static boolean deleteCalendarEvent(Context context, String description) {
        int rows = context.getContentResolver().
                delete(CALENDER_EVENT_URI,
                        CalendarContract.Events.DESCRIPTION + "=?",
                        new String[]{description});
        return rows != -1;
    }


    public static boolean checkPermission(Activity activity) {
        try {
            int checkPermission = ActivityCompat.checkSelfPermission(
                    activity, Manifest.permission.WRITE_CALENDAR);
            return checkPermission == PackageManager.PERMISSION_GRANTED;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void fetchPermission(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CALENDAR}, requestCode);
    }
}
