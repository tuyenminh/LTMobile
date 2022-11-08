package com.example.appquanlidiem.phat_tkb.editcourse;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.appquanlidiem.R;
import com.example.appquanlidiem.phat_tkb_database.Course;
import com.example.appquanlidiem.ThoiKhoaBieuMainActivity;
import com.example.appquanlidiem.phat_tkb_database.Database_tkb;
import com.example.appquanlidiem.phat_tkb_database.Utils;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    public static final String EXTRA_UPDATE_TIMETABLE = "update_timetable";
    private static List<String> sWeekItems;
    private List<String> sStartItems;
    private List<String> sEndItems;
    private TextView mClassNumTextView;
    private EditText mNameEditText;
    private EditText mClassRoomEditText;
    private TextView mWeekOfTermTextView;
    private EditText mTeacherEditText;
    private OptionsPickerView<String> pvOptions;
    private Course mCourse;
    public static final String EXTRA_COURSE_INDEX = "course_index";
    public static final String EXTRA_Day_OF_WEEK = "day_of_week";
    public static final String EXTRA_CLASS_START = "class_start";
    private int mIndex;
    private int mClassStart;
    private int mClassEnd;
    private int mDayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phat_layout_edit_tkb);
        mClassNumTextView = findViewById(R.id.mp_tv_class_num);
        mNameEditText = findViewById(R.id.mp_name_editText);
        mClassRoomEditText = findViewById(R.id.mp_et_class_room);
        mWeekOfTermTextView = findViewById(R.id.mp_tv_week_of_term);
        mTeacherEditText = findViewById(R.id.mp_ghi_chu);
        setData();
        setActionBar();
        Intent intent = getIntent();
        if (intent != null) {
            mIndex = intent.getIntExtra(EXTRA_COURSE_INDEX, -1);
            mDayOfWeek = intent.getIntExtra(EXTRA_Day_OF_WEEK, 0);
            mClassStart = intent.getIntExtra(EXTRA_CLASS_START, 0);
        }
        if (mIndex != -1) {
            try {
                mCourse = (Course) ThoiKhoaBieuMainActivity.sCourseList.get(mIndex).clone();
                mClassStart = mCourse.getClassStart();
                mClassEnd = mClassStart + mCourse.getClassLength() - 1;
                mDayOfWeek = mCourse.getDayOfWeek();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            setDefaultValue();
        } else {
            if (mDayOfWeek != 0) {
                mClassEnd = mClassStart + 1;
                mClassNumTextView.setText(
                        String.format(getString(R.string.schedule_section),
                                sWeekItems.get(mDayOfWeek - 1), mClassStart, mClassEnd));
            } else {
                mDayOfWeek = 1;
                mClassStart = 1;
                mClassEnd = 1;
            }
            mCourse = new Course();
        }
        setCardViewAlpha();
        ImageView imageView = findViewById(R.id.mp_iv_bg_edit);
        mClassNumTextView.setOnClickListener(view -> {
            hideInput();
            initOptionsPicker();
        });
        mWeekOfTermTextView.setOnClickListener(view -> {
            final WeekOfTermSelectDialog dialog = new WeekOfTermSelectDialog(EditActivity.this, mCourse.getWeekOfTerm());
            dialog.setPositiveBtn(view1 -> {
                mCourse.setWeekOfTerm(dialog.getWeekOfTerm());
                mWeekOfTermTextView.setText(Utils.getFormatStringFromWeekOfTerm(mCourse.getWeekOfTerm()));
                dialog.dismiss();
            });
            dialog.setNativeBtn(view2 -> dialog.dismiss());
            dialog.show();
        });
    }

    private void setCardViewAlpha() {
        CardView cardView = findViewById(R.id.mp_cv_edit_1);
        Utils.setCardViewAlpha(cardView);
        cardView = findViewById(R.id.mp_cv_edit_2);
        Utils.setCardViewAlpha(cardView);
    }

    private void setUpdateResult() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_UPDATE_TIMETABLE, true);
        setResult(RESULT_OK, intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phat_menuedit_tkb, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean setCourseFromView() {
        String name = mNameEditText.getText().toString();
        String classroom = mClassRoomEditText.getText().toString();
        String teacher = mTeacherEditText.getText().toString();
        if (name.isEmpty() || classroom.isEmpty() || teacher.isEmpty() ||
                mDayOfWeek == 0 || mClassStart == 0 || mClassEnd == 0) {
            Toast.makeText(this, "Nội dung không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        mCourse.setName(name);
        mCourse.setClassRoom(classroom);
        mCourse.setClassStart(mClassStart);
        mCourse.setDayOfWeek(mDayOfWeek);
        mCourse.setClassLength(mClassEnd - mClassStart + 1);

        mCourse.setTeacher(teacher);
        return true;
    }

    private void saveCourse() {
        if (setCourseFromView()) {
            if (!Utils.isWeekOfTermValid(mCourse.getWeekOfTerm())) {
                Utils.showToast("Vui lòng chọn một tuần！");
                return;
            }
            if (mIndex == -1) {
                ThoiKhoaBieuMainActivity.sCourseList.add(getInsertIndex(), mCourse);
            } else {
                ThoiKhoaBieuMainActivity.sCourseList.set(mIndex, mCourse);
            }
            new Database_tkb<List<Course>>().saveToJson(this, ThoiKhoaBieuMainActivity.sCourseList, Database_tkb.TIMETABLE_FILE_NAME);
            setUpdateResult();
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private int getInsertIndex() {
        List<Course> courseList = ThoiKhoaBieuMainActivity.sCourseList;
        int dayOfWeek = mCourse.getDayOfWeek();
        int class_start = mCourse.getClassStart();
        int size = courseList.size();
        int i;
        for (i = 0; i < size; i++) {
            Course course = courseList.get(i);
            if (dayOfWeek == course.getDayOfWeek() && class_start < course.getClassStart())
                break;
        }
        return i;
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.course_edit);
    }

    private void setDefaultValue() {
        mTeacherEditText.setText(mCourse.getTeacher());
        mNameEditText.setText(mCourse.getName());

        mWeekOfTermTextView.setText(Utils.getFormatStringFromWeekOfTerm(mCourse.getWeekOfTerm()));

        mClassRoomEditText.setText(mCourse.getClassRoom());

        int class_start = mCourse.getClassStart();
        int class_end = class_start + mCourse.getClassLength() - 1;
        String week = sWeekItems.get(mCourse.getDayOfWeek() - 1);

        mClassNumTextView.setText(
                String.format(getString(R.string.schedule_section), week, class_start, class_end));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Thông báo")
                    .setMessage("Bạn có chắc bạn muốn thoát khỏi?")
                    .create();

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có",
                    (dialogInterface, i) -> {

                        alertDialog.dismiss();
                        finish();

                    });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Hủy bỏ",
                    (dialogInterface, i) -> alertDialog.dismiss());

            alertDialog.show();

        } else if (id == R.id.mp_menu_save) {
            if (setCourseFromView()) {
                final AlertDialog alertDialog = initAlertDialog("Thông báo", "Có lưu nội dung không?", "Hủy bỏ");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có",
                        (dialogInterface, i) -> {
                            saveCourse();
                            finish();
                        });
                alertDialog.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog initAlertDialog(String title, String message, String cancleBtnText) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create();

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancleBtnText,
                (dialogInterface, i) -> alertDialog.dismiss());
        return alertDialog;
    }
    private void setData() {
        sWeekItems = new ArrayList<>();
        sWeekItems.add ("Thứ Hai");
        sWeekItems.add ("Thứ Ba");
        sWeekItems.add ("Thứ Tư");
        sWeekItems.add ("Thứ Năm");
        sWeekItems.add ("Thứ Sáu");
        sWeekItems.add ("Thứ Bảy");
        sWeekItems.add ("CN");
        sStartItems = new ArrayList<>();
        sEndItems = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        for (int i = 1; i <= 12; i++) {
            stringBuilder.append(i);
            sStartItems.add(String.valueOf(i));
            sEndItems.add(stringBuilder.toString());
            stringBuilder.delete(1, stringBuilder.length());
        }
    }

    private void initOptionsPicker() {
        int defaultOptions1 = mDayOfWeek - 1;
        int defaultOptions2 = mClassStart - 1;
        int defaultOptions3 = mClassEnd - 1;
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String str12 = sWeekItems.get(options1) + " ( tiết " + (options2 + 1) + " đến tiết " + (options3 + 1) + " )";
            mClassNumTextView.setText(str12);
            mDayOfWeek = options1 + 1;
            mClassStart = options2 + 1;
            mClassEnd = options3 + 1;

        }).setOptionsSelectChangeListener((options1, options2, options3) -> {
            String str1 = sWeekItems.get(options1) + " ( tiết " + (options2 + 1) + " đến tiết " + (options3 + 1) + " )";

            pvOptions.setTitleText(str1);
            if (options3 < options2) {
                pvOptions.setSelectOptions(options1, options2, options2 + 1);
            }
        }).build();

        if (pvOptions != null) {
            pvOptions.setNPicker(sWeekItems, sStartItems, sEndItems);
            pvOptions.setSelectOptions(defaultOptions1, defaultOptions2, defaultOptions3);
            pvOptions.setTitleText(" Chọn tiết học ");
            pvOptions.show();
        }

    }
}
