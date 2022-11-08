package com.example.appquanlidiem.phat_tkb.coursedetails;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.appquanlidiem.R;
import com.example.appquanlidiem.phat_tkb_database.Course;
import com.example.appquanlidiem.phat_tkb.editcourse.EditActivity;
import com.example.appquanlidiem.ThoiKhoaBieuMainActivity;
import com.example.appquanlidiem.phat_tkb_database.Database_tkb;
import com.example.appquanlidiem.phat_tkb_database.Utils;

import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final String KEY_COURSE_INDEX = "course_index";
    private static final String[] aStrWeek = new String[]{
            "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "CN"
    };
    public static final int EDIT_ID = 0;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phat_layout_xem_tkb);
        Button button = findViewById(R.id.mp_btn_edit);
        setActionBar();
        mIndex = getIntent().getIntExtra(KEY_COURSE_INDEX, 0);
        setCourseTextView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetailsActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.EXTRA_COURSE_INDEX, mIndex);
                startActivityForResult(intent, EDIT_ID);
            }
        });

        setCardViewAlpha();
    }

    private void setCardViewAlpha() {
        CardView cardView = findViewById(R.id.mp_cv_course_details);
        Utils.setCardViewAlpha(cardView);
    }

    private void setUpdateResult() {
        Intent intent = new Intent();
        intent.putExtra(EditActivity.EXTRA_UPDATE_TIMETABLE, true);
        setResult(RESULT_OK, intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phat_menuxem_tkb, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ID) {
            if (data != null) {
                if (data.getBooleanExtra(EditActivity.EXTRA_UPDATE_TIMETABLE, false)) {
                    setCourseTextView();
                }
                setResult(RESULT_OK, data);
            }
        }
    }

    private void setCourseTextView() {
        Course course = ThoiKhoaBieuMainActivity.sCourseList.get(mIndex);
        TextView textView = findViewById(R.id.mp_tv_class_name);
        textView.setText(course.getName());
        textView = findViewById(R.id.mp_tv_class_room);
        textView.setText(course.getClassRoom());
        textView = findViewById(R.id.mp_tv_class_num);
        int class_start = course.getClassStart();
        int class_num = course.getClassLength();
        textView.setText(String.format(getString(R.string.schedule_section),
                aStrWeek[course.getDayOfWeek() - 1], class_start, (class_start + class_num - 1)));
        textView = findViewById(R.id.mp_tv_week_of_term);
        textView.setText(Utils.getFormatStringFromWeekOfTerm(course.getWeekOfTerm()));
        textView = findViewById(R.id.mp_tv_ghi_chu);
        textView.setText(course.getTeacher());
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.course_details);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.mp_menu_delete:
                final AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn xóa lịch này không?")
                        .create();
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ThoiKhoaBieuMainActivity.sCourseList.remove(mIndex);
                        new Database_tkb<List<Course>>().saveToJson(CourseDetailsActivity.this,
                                ThoiKhoaBieuMainActivity.sCourseList,
                               Database_tkb.TIMETABLE_FILE_NAME);
                        Toast.makeText(CourseDetailsActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                        setUpdateResult();
                        alertDialog.dismiss();
                        finish();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
