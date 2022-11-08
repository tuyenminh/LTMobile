package com.example.appquanlidiem.phat_tkb.settime;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.appquanlidiem.R;
import com.example.appquanlidiem.phat_tkb_database.Time;
import com.example.appquanlidiem.ThoiKhoaBieuMainActivity;
import com.example.appquanlidiem.phat_tkb_database.Config;
import com.example.appquanlidiem.phat_tkb_database.Database_tkb;
import com.example.appquanlidiem.phat_tkb_database.Utils;
import java.util.ArrayList;
import java.util.List;

public class SetTimeActivity extends AppCompatActivity {
    private OptionsPickerView<String> mOptionsPv;
    private final TimeAdapter timeAdapter = new TimeAdapter();
    private final List<String> timeList = new ArrayList<>(18 * 12);
    private final Time[] times = new Time[Config.getMaxClassNum()];
    public static final String EXTRA_UPDATE_Time = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phat_layout_settime_tkb);
        setActionBar();
        RecyclerView recyclerView = findViewById(R.id.mp_rv_time_list);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timeAdapter);
        initData();
    }

    private void initData() {
        for (int i = 0, len = times.length; i < len; i++) {
            try {
                if (ThoiKhoaBieuMainActivity.sTimes == null || ThoiKhoaBieuMainActivity.sTimes.length == 0) {
                    times[i] = new Time();
                } else {
                    times[i] = (Time) ThoiKhoaBieuMainActivity.sTimes[i].clone();
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 6; i < 24; i++) {
            for (int j = 0; j < 12; j++) {
                timeList.add(Utils.formatTime(i) + ":" + Utils.formatTime(j * 5));
            }
        }
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.set_time);
    }

    private void setUpdateResult() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_UPDATE_Time, true);
        setResult(RESULT_OK, intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phat_menusettime_tkb, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.mp_menu_save) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Thông báo")
                    .setMessage("Có lưu thời gian và thoát ra không?")
                    .setPositiveButton("Có", (dialogInterface, i) -> {
                        new Database_tkb<Time[]>().saveToJson(
                                SetTimeActivity.this,
                                times,
                                Database_tkb.TIME_FILE_NAME);
                        ThoiKhoaBieuMainActivity.sTimes = times;
                        setUpdateResult();
                        finish();
                    })
                    .setNegativeButton("Hủy bỏ", null)
                    .create();
            alertDialog.show();
        } else if (id == R.id.mp_menu_delete) {
            for (int i = 0; i < times.length; i++) {
                times[i].setStart("");
                times[i].setEnd("");
            }
            timeAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showTimeSelectDialog(final TextView textView, final int index) {
        mOptionsPv = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String start = timeList.get(options1);
            String end = timeList.get(options2);
            times[index].setStart(start);
            times[index].setEnd(end);
            textView.setText(times[index].toString());
        }).setOptionsSelectChangeListener((options1, options2, options3) -> {
            if (options1 >= options2) {
                mOptionsPv.setSelectOptions(options1, options1 + 1);
            }
        }).build();

        mOptionsPv.setTitleText("Thời gian ");
        mOptionsPv.setNPicker(timeList, timeList, null);
        if (!times[index].getEnd().isEmpty()) {
            mOptionsPv.setSelectOptions(getOptionsIndex(times[index].getStart()),
                    getOptionsIndex(times[index].getEnd()));
        } else if (index > 0 && !times[index - 1].getEnd().isEmpty()) {
            int option = getOptionsIndex(times[index - 1].getEnd()) + 2;
            mOptionsPv.setSelectOptions(option, option + 9);
        } else {
            mOptionsPv.setSelectOptions(0, 1);
        }
        mOptionsPv.show();
    }

    private int getOptionsIndex(String time) {
        String[] strings = time.split(":");
        int hour = Integer.parseInt(strings[0]);
        int minute = Integer.parseInt(strings[1]);
        return (hour - 6) * 12 + minute / 5;
    }

    private class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phat_layout_itemtime_tkb, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.classTv.setText(" Thời gian " + (position + 1));
            holder.timeTv.setText(times[position].toString());
            if (!holder.linearLayout.hasOnClickListeners()) {
                holder.linearLayout.setOnClickListener(view -> showTimeSelectDialog(holder.timeTv, position));
            }
        }

        @Override
        public int getItemCount() {
            return times.length;
        }
        private class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView classTv;
            private final TextView timeTv;
            private final LinearLayout linearLayout;
            public ViewHolder(View view) {
                super(view);
                classTv = view.findViewById(R.id.mp_tv_class_num);
                timeTv = view.findViewById(R.id.mp_tv_time);
                linearLayout = view.findViewById(R.id.mp_ll_set_time);
            }

        }
    }
}
