package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class ThongKeMainActivity extends AppCompatActivity {
    Spinner dsbieudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        dsbieudo = (Spinner) findViewById(R.id.mp_spinner);
        final ArrayList<String> arrayHocKi = new ArrayList<String>();
        arrayHocKi.add("Học kì 1 (Năm 1)");
        arrayHocKi.add("Học kì 2 (Năm 1)");
        arrayHocKi.add("Học kì 3 (Năm 1)");
        arrayHocKi.add("Học kì 1 (Năm 2)");
        arrayHocKi.add("Học kì 2 (Năm 2)");
        arrayHocKi.add("Học kì 3 (Năm 2)");
        arrayHocKi.add("Học kì 1 (Năm 3)");
        arrayHocKi.add("Học kì 2 (Năm 3)");
        arrayHocKi.add("Học kì 3 (Năm 3)");
        arrayHocKi.add("Học kì 1 (Năm 4)");
        arrayHocKi.add("Học kì 2 (Năm 4)");
        arrayHocKi.add("Học kì 3 (Năm 4)");
        arrayHocKi.add("Học kì 1 (Năm 5)");
        arrayHocKi.add("Học kì 2 (Năm 5)");
        arrayHocKi.add("Học kì 3 (Năm 5)");


        ArrayAdapter arrAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayHocKi);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dsbieudo.setAdapter(arrAdapter);
        dsbieudo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ThongKeMainActivity.this, arrayHocKi.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2014, 420));
        visitors.add(new BarEntry(2015, 450));
        visitors.add(new BarEntry(2016, 520));
        visitors.add(new BarEntry(2017, 620));
        visitors.add(new BarEntry(2018, 540));
        visitors.add(new BarEntry(2019, 720));
        visitors.add(new BarEntry(2020, 920));


        BarDataSet barDataSet = new BarDataSet(visitors, "Định danh");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("NMP");
        barChart.animateY(3000);

    }
}