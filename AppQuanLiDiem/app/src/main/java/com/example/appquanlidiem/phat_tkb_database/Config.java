package com.example.appquanlidiem.phat_tkb_database;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
    private static int currentWeek = 1;
    private static float cardViewAlpha = 1.0f;
    private static int MAX_WEEK_NUM = 25;
    private static int MAX_CLASS_NUM = 12;
    private static final String KEY_WEEK_OF_TERM = "long_current_week";
    private static final String KEY_CARD_VIEW_ALPHA = "card_view_alpha";
    public static float getCardViewAlpha() {
        return cardViewAlpha;
    }
    public static int getMaxWeekNum() {
        return MAX_WEEK_NUM;
    }
    public static int getMaxClassNum() {
        return MAX_CLASS_NUM;
    }
    public static int getCurrentWeek() {
        return currentWeek;
    }
    public static void setCurrentWeek(int currentWeek) {
        Config.currentWeek = currentWeek;
    }
    public static void saveCurrentWeek(final Context context) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(KEY_WEEK_OF_TERM, Utils.getWeekNum() - currentWeek);
        editor.apply();
    }

    public static void readFormSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        long weekNum = Utils.getWeekNum();
        currentWeek = (int) (weekNum - sharedPreferences.getLong(KEY_WEEK_OF_TERM, weekNum - 1));
        currentWeek = currentWeek > 0 ? currentWeek : 1;
        cardViewAlpha = sharedPreferences.getFloat(KEY_CARD_VIEW_ALPHA, 1.0f);
    }
}
