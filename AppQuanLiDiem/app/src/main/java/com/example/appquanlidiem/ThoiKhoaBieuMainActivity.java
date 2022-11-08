package com.example.appquanlidiem;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.reflect.TypeToken;
import com.example.appquanlidiem.phat_tkb_database.Course;
import com.example.appquanlidiem.phat_tkb_database.Time;
import com.example.appquanlidiem.phat_tkb_database.OneClickListener;
import com.example.appquanlidiem.phat_tkb.coursedetails.CourseDetailsActivity;
import com.example.appquanlidiem.phat_tkb.editcourse.EditActivity;
import com.example.appquanlidiem.phat_tkb.settime.SetTimeActivity;
import com.example.appquanlidiem.phat_tkb_database.Config;
import com.example.appquanlidiem.phat_tkb_database.Database_tkb;
import com.example.appquanlidiem.phat_tkb_database.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ThoiKhoaBieuMainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    private TextView mWeekOfTermTextView;
    private ImageButton mAddImgBtn;
    private LinearLayout headerClassNumLl;
    private boolean flagUpdateCalendar = false;
    public static List<Course> sCourseList;
    public static Time[] sTimes;
    private final List<TextView> mClassTableTvList = new ArrayList<>();
    private TextView[] mClassNumHeaders = null;
    private static final int REQUEST_CODE_COURSE_DETAILS = 0;
    private static final int REQUEST_CODE_COURSE_EDIT = 1;
    private static final int REQUEST_CODE_FILE_CHOOSE = 2;
    private static final int REQUEST_CODE_CONFIG = 3;
    private static final int REQUEST_CODE_LOGIN = 4;
    private static final int REQUEST_CODE_SCAN = 5;
    private static final int REQUEST_CODE_SET_TIME = 6;
    private static final int REQ_PER_CALENDAR = 0x11;
    private OptionsPickerView<String> mOptionsPv;
    public static float VALUE_1DP;
    private static float sCellWidthPx;
    private static float sCellHeightPx;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phat_activity_thoikhoabieu);
        getWritePermission();
        mWeekOfTermTextView = findViewById(R.id.mp_tv_week_of_term);
        mAddImgBtn = findViewById(R.id.mp_img_btn_add);
        mFrameLayout = findViewById(R.id.mp_fl_timetable);
        headerClassNumLl = findViewById(R.id.mp_ll_header_class_num);
        Config.readFormSharedPreferences(this);
        Utils.setPATH(getExternalFilesDir(null).getAbsolutePath() + File.separator + "pictures");
        VALUE_1DP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                getResources().getDisplayMetrics());
        float headerClassNumWidth = getResources().getDimension(R.dimen.table_header_class_width);
        setTableCellDimens(headerClassNumWidth);
        updateDayOfWeekHeader();
        initToolbar();
        initTimetable();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.mp_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void updateDayOfWeekHeader() {
        int[] weekTextView = new int[]{
                R.id.mp_tv_sun,
                R.id.mp_tv_mon,
                R.id.mp_tv_tues,
                R.id.mp_tv_wed,
                R.id.mp_tv_thur,
                R.id.mp_tv_fri,
                R.id.mp_tv_sat
        };

        int week = Utils.getWeekOfDay();
        TextView weekTv = findViewById(weekTextView[week - 1]);
        weekTv.setBackground(ContextCompat.getDrawable(this, R.color.day_of_week_color));
    }

    private void updateCalendarEvent() {

        if (sTimes != null) {
            addClassCalendarEvent(getCoursesNeedToTake());
        }
    }

    private void setCalendarEvent() {
        if (sTimes == null)
            return;

    }

    private List<Course> getCoursesNeedToTake() {
        int currentWeek = Config.getCurrentWeek();
        if (Utils.getWeekOfDay() == 1) {
            currentWeek++;
        }

        List<Course> tempList = new LinkedList<>();

        for (Course course : sCourseList) {
            if (courseIsThisWeek(course, currentWeek)) {
                tempList.add(course);
            }
        }
        return tempList;
    }

    private void addClassCalendarEvent(List<Course> courses) {
        Calendar calendar = initCalendar();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DATE, -dayOfWeek);
        final long start = calendar.getTimeInMillis();

    }

    private Calendar initCalendar() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private void setTableCellDimens(float headerWidth) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        Resources resources = getResources();
        int toolbarHeight = resources.getDimensionPixelSize(R.dimen.toolbar_height);
        int headerWeekHeight = resources.getDimensionPixelSize(R.dimen.header_week_height);
        sCellWidthPx = (displayWidth - headerWidth) / 7.0f;
        sCellHeightPx = Math.max(sCellWidthPx,
                (displayHeight - toolbarHeight - headerWeekHeight) / (float) Config.getMaxClassNum());
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initFrameLayout() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFrameLayout.getLayoutParams();
        layoutParams.height = (int) sCellHeightPx * Config.getMaxClassNum();
        layoutParams.width = (int) sCellWidthPx * 7;
        mAddImgBtn.getLayoutParams().height = (int) sCellHeightPx;
        mFrameLayout.performClick();
        mFrameLayout.setOnTouchListener((view, motionEvent) -> {
            int event = motionEvent.getAction();
            if (event == MotionEvent.ACTION_UP) {
                if (mAddImgBtn.getVisibility() == View.VISIBLE) {
                    mAddImgBtn.setVisibility(View.GONE);
                } else {
                    int x = (int) (motionEvent.getX() / sCellWidthPx);
                    int y = (int) (motionEvent.getY() / sCellHeightPx);
                    x = (int) (x * sCellWidthPx);
                    y = (int) (y * sCellHeightPx);
                    setAddImgBtn(x, y);
                }
            }
            return true;
        });
    }

    private void initAddBtn() {
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mAddImgBtn.getLayoutParams();
        layoutParams.width = (int) sCellWidthPx;
        layoutParams.height = (int) sCellHeightPx;
        mAddImgBtn.setOnClickListener(view -> {
            mAddImgBtn.setVisibility(View.GONE);
            Intent intent = new Intent(ThoiKhoaBieuMainActivity.this, EditActivity.class);
            int dayOfWeek = layoutParams.leftMargin / (int) sCellWidthPx;
            int classStart = layoutParams.topMargin / (int) sCellHeightPx;
            mAddImgBtn.setVisibility(View.INVISIBLE);
            intent.putExtra(EditActivity.EXTRA_Day_OF_WEEK, dayOfWeek + 1);
            intent.putExtra(EditActivity.EXTRA_CLASS_START, classStart + 1);
            startActivityForResult(intent, REQUEST_CODE_COURSE_EDIT);
        });
    }

    private void setAddImgBtn(int left, int top) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mAddImgBtn.getLayoutParams();
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;
        mAddImgBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phat_menumain_tkb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
      if (id == R.id.mp_menu_set_week) {
            showSelectCurrentWeekDialog();
        }else if (id == R.id.mp_menu_append_class) {
            intent = new Intent(this, EditActivity.class);
            startActivityForResult(intent, REQUEST_CODE_COURSE_EDIT);
        }  else if (id == R.id.mp_menu_set_time) {
            startActivityForResult(
                    new Intent(this, SetTimeActivity.class),
                    REQUEST_CODE_SET_TIME);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSelectCurrentWeekDialog() {
        final int currentWeek = Config.getCurrentWeek();
        final String str = "Tuần hiện tại là : ";
        final List<String> items = new ArrayList<>();
        for (int i = 1; i <= Config.getMaxWeekNum(); i++) {
            items.add("Tuần " + i);
        }
        mOptionsPv = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            int selectCurrentWeek = options1 + 1;
            if (selectCurrentWeek != currentWeek) {
                Config.setCurrentWeek(selectCurrentWeek);
                updateTimetable();
                Config.saveCurrentWeek(ThoiKhoaBieuMainActivity.this);
            }
        }).setOptionsSelectChangeListener(
                (options1, options2, options3) -> mOptionsPv.setTitleText(str + items.get(options1))).build();
        mOptionsPv.setTitleText("Tuần hiện tại là : " + items.get(currentWeek - 1));
        mOptionsPv.setNPicker(items, null, null);
        mOptionsPv.setSelectOptions(currentWeek - 1);
        mOptionsPv.show();
    }
    private boolean hasAllPermissions() {
        for (String item : PERMISSIONS_STORAGE) {
            int permission = ActivityCompat.checkSelfPermission(this, item);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    private void getWritePermission() {

        if (hasAllPermissions()) {
            setCalendarEvent();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void initTimetable()
    {
        initAddBtn();
        mWeekOfTermTextView.setText(String.format(getString(R.string.day_of_week), Config.getCurrentWeek()));
        initFrameLayout();
        sTimes = new Database_tkb<Time[]>().readFromJson(this, Database_tkb.TIME_FILE_NAME, Time[].class);
        sCourseList = new Database_tkb<ArrayList<Course>>().readFromJson(
                this,
                Database_tkb.TIMETABLE_FILE_NAME,
                new TypeToken<ArrayList<Course>>() {
                }.getType());
        updateClassNumHeader();
        if (sCourseList == null) {
            sCourseList = new ArrayList<>();
            return;
        }
        int size = sCourseList.size();
        if (size != 0) {
            updateTimetable();
        }
        flagUpdateCalendar = false;
    }

    private List<Course> selectNeedToShowCourse() {
        LinkedList<Course> courseList = new LinkedList<>();
        boolean[] flag = new boolean[12];
        int weekOfDay = 0;
        int size = sCourseList.size();
        for (int index = 0; index < size; index++)
        {
            Course course = sCourseList.get(index);
            if (!isThisWeekCourseNeedToShow(course.getWeekOfTerm())) {
                continue;
            }
            if (course.getDayOfWeek() != weekOfDay) {
                Arrays.fill(flag, false);
                weekOfDay = course.getDayOfWeek();
            }
            int class_start = course.getClassStart();
            int class_num = course.getClassLength();
            int i;
            for (i = 0; i < class_num; i++) {
                if (flag[class_start + i - 1]) {
                    if (!courseIsThisWeek(course)) {
                        break;
                    } else {
                        courseList.removeLast();
                        courseList.add(course);
                        for (int j = 0; j < class_num; j++) {
                            flag[class_start + j - 1] = true;
                        }
                        break;
                    }
                }
            }
            if (i == class_num) {
                courseList.add(course);
                for (int j = 0; j < class_num; j++) {
                    flag[class_start + j - 1] = true;
                }
            }
        }
        return courseList;
    }

    private boolean isThisWeekCourseNeedToShow(int weekOfTerm) {
        int offset = Config.getMaxWeekNum() - Config.getCurrentWeek();
        if ((1 << offset) > weekOfTerm) {
            return false;
        }
        return (((1 << (offset + 1)) - 1) & weekOfTerm) > 0;
    }

    private void updateClassNumHeader() {
        headerClassNumLl.getLayoutParams().height = (int) sCellHeightPx * Config.getMaxClassNum();
        if (mClassNumHeaders == null) {
            mClassNumHeaders = new TextView[Config.getMaxClassNum()];
            for (int i = 0, len = mClassNumHeaders.length; i < len; i++) {
                mClassNumHeaders[i] = null;
            }
            headerClassNumLl.removeAllViews();
        }

        int height = (int) sCellHeightPx;
        float textSize = getResources().getDimensionPixelSize(R.dimen.class_num_header_text_size);
        StringBuilder stringBuilder = new StringBuilder("12\n22:00\n23:00".length());
        for (int i = 0; i < Config.getMaxClassNum(); i++) {
            TextView textView;
            if (mClassNumHeaders[i] == null) {
                textView = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, height);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(getResources().getColor(R.color.colorBlack));
                textView.setLayoutParams(layoutParams);
                mClassNumHeaders[i] = textView;
                headerClassNumLl.addView(textView);
            } else {
                textView = mClassNumHeaders[i];
            }
            stringBuilder.append(i + 1);
            textView.getLayoutParams().height = height;
            if (sTimes != null && i < sTimes.length) {
                stringBuilder.append('\n');
                stringBuilder.append(sTimes[i].getStart());
                stringBuilder.append('\n');
                stringBuilder.append(sTimes[i].getEnd());
            }
            textView.setText(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        for (int i = Config.getMaxClassNum(); i < mClassNumHeaders.length; i++) {
            headerClassNumLl.removeViewAt(i);
        }
        flagUpdateCalendar = true;
    }

    private void updateTimetable() {
        mWeekOfTermTextView.setText(String.format(getString(R.string.day_of_week), Config.getCurrentWeek()));
        List<Course> courseList = selectNeedToShowCourse();
        int size = courseList.size();
        StringBuilder stringBuilder = new StringBuilder();
        int[] color = new int[]{
                ContextCompat.getColor(this, R.color.item_1),
                ContextCompat.getColor(this, R.color.item_2),
                ContextCompat.getColor(this, R.color.item_3),
                ContextCompat.getColor(this, R.color.item_4),
                ContextCompat.getColor(this, R.color.item_5),
                ContextCompat.getColor(this, R.color.item_6),
                ContextCompat.getColor(this, R.color.item_7)
        };
        int mClassTableListSize = mClassTableTvList.size();
        for (int i = 0; i < size; i++) {
            Course course = courseList.get(i);
            int class_num = course.getClassLength();
            int week = course.getDayOfWeek() - 1;
            int class_start = course.getClassStart() - 1;
            TextView textView;
            if (i < mClassTableListSize) {
                textView = mClassTableTvList.get(i);
            } else {
                textView = new TextView(this);
                mClassTableTvList.add(textView);
                mFrameLayout.addView(textView);
            }
            setTableCellTextView(textView,
                    class_num, week,
                    class_start);
            setTableClickListener(textView, sCourseList.indexOf(course));
            String name = course.getName();
            int maxLength = 8;
            if (name.length() > maxLength) {
                name = name.substring(0, maxLength) + "...";
            }
            stringBuilder.append(name);
            stringBuilder.append("\n");
            stringBuilder.append(course.getClassRoom());
            GradientDrawable myGrad = new GradientDrawable();
            myGrad.setCornerRadius(5 * VALUE_1DP);
            if (courseIsThisWeek(course))
            {
                myGrad.setColor(color[ i % color.length ]);
            } else {
                myGrad.setColor(getResources().getColor(R.color.item_gray));
                stringBuilder.insert(0, "Không phải tuần này \n");
            }
            textView.setText(stringBuilder.toString());
            textView.setBackground(myGrad);
            stringBuilder.delete(0, stringBuilder.length());
        }
        for (int i = size, len = mClassTableTvList.size(); i < len; i++) {
            mFrameLayout.removeView(mClassTableTvList.get(i));
        }
        if (mClassTableTvList.size() > size) {
            mClassTableTvList.subList(size, mClassTableTvList.size()).clear();
        }
        flagUpdateCalendar = true;
    }

    private void setTableClickListener(TextView textView, final int index)
    {
        textView.setOnClickListener(new OneClickListener(view -> {
            Intent intent = new Intent(ThoiKhoaBieuMainActivity.this, CourseDetailsActivity.class);
            intent.putExtra(CourseDetailsActivity.KEY_COURSE_INDEX, index);
            startActivityForResult(intent, REQUEST_CODE_COURSE_DETAILS);
        }));
    }

    private void setTableCellTextView(TextView textView, int class_num, final int left,
                                      final int top) {
        float leftMargin = left * sCellWidthPx;
        float topMargin = top * sCellHeightPx;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                (int) (sCellWidthPx - 6 * VALUE_1DP),
                (int) (class_num * sCellHeightPx - 6 * VALUE_1DP));
        layoutParams.topMargin = (int) (topMargin + 3 * VALUE_1DP);
        layoutParams.leftMargin = (int) (leftMargin + 3 * VALUE_1DP);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.timetable_cell_text_size));
        textView.setLayoutParams(layoutParams);
    }

    private boolean courseIsThisWeek(Course course) {
        return courseIsThisWeek(course, Config.getCurrentWeek());
    }

    private boolean courseIsThisWeek(Course course, int currentWeek) {
        return (course.getWeekOfTerm() >> (Config.getMaxWeekNum() - currentWeek) & 0x01) == 1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PER_CALENDAR) {
            for (int r : grantResults) {
                if (r != PackageManager.PERMISSION_DENIED) {
                    finish();
                    return;
                }
            }
            setCalendarEvent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FILE_CHOOSE:
                    if (data == null) {
                        return;
                    }
                    Uri uri = data.getData();
                    String name = Database_tkb.getNameFromUri(this, uri);

                    String path = getExternalCacheDir().getAbsolutePath() + File.separator + name;
                    if (TextUtils.isEmpty(path)) {
                        Utils.showToast("Không lấy được đường dẫn tệp");
                        return;
                    }
                    try {
                        if (!Database_tkb.fileCopy(getContentResolver().openInputStream(uri), path)) {
                            return;
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                    saveCurrentTimetable();
                    updateTimetable();
                    break;
                case REQUEST_CODE_COURSE_EDIT:
                case REQUEST_CODE_COURSE_DETAILS:
                    if (data == null)
                        return;
                    boolean update = data.getBooleanExtra(EditActivity.EXTRA_UPDATE_TIMETABLE, false);
                    if (update) {
                        updateTimetable();
                    }
                    break;
                case REQUEST_CODE_CONFIG:
                    if (data == null)
                        return;
                case REQUEST_CODE_LOGIN:
                    if (data == null)
                        return;
                    saveCurrentTimetable();
                    break;
                case REQUEST_CODE_SCAN:
                case REQUEST_CODE_SET_TIME:
                    if (data != null && data.getBooleanExtra(SetTimeActivity.EXTRA_UPDATE_Time, false)) {
                        updateClassNumHeader();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void saveCurrentTimetable() {
        new Database_tkb<List<Course>>().saveToJson(this, sCourseList, Database_tkb.TIMETABLE_FILE_NAME);
    }

    @Override
    protected void onDestroy() {
        if (flagUpdateCalendar) {
            updateCalendarEvent();
        }
        super.onDestroy();
    }
}
