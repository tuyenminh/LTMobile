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
import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.EditText;
import com.example.appquanlidiem.bieudo_database.database;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.text.SimpleDateFormat;


public class ThongKeMainActivity extends AppCompatActivity {

    private BarChart barChart;
    private Button button;
    private EditText editText;
    private database db;
    long date = System.currentTimeMillis();
    Spinner dsbieudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        barChart = (BarChart) findViewById(R.id.barchart);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
         addDataToGraph();
        barChart.invalidate();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveToDatabase();
            }
        });

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
    }
    public void SaveToDatabase(){
        db = new database(this);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        String xvalue = sdf.format(date);
        String yvalue = editText.getText().toString();

        db.saveData(xvalue,yvalue);
        addDataToGraph();
        barChart.invalidate();

        db.close();
    }

    public void addDataToGraph(){
       db = new database(this);
        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        final ArrayList<String> ydata = db.queryYData();


       for(int i = 0; i < db.queryYData().size(); i++){
            BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(db.queryYData().get(i)));
            yVals.add(newBarEntry);
        }

        final ArrayList<String> xVals = new ArrayList<String>();
       final ArrayList<String> xdata = db.queryXData();

      for (int i = 0; i < db.queryXData().size(); i++){
            xVals.add(xdata.get(i));

          yVals.add(new BarEntry(5, 8));
          yVals.add(new BarEntry(6, 10));
      }

        BarDataSet dataSet = new BarDataSet(yVals, "           CT190       CT175        CT177        CT188       CT299        CT274");
       dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
      dataSet.setValueTextSize(16f);
      //  BarData barData = new BarData(dataSet);
       barChart.setFitBars(true);
        //barChart.setData(barData);
        barChart.getDescription().setText("NMP");
       ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(dataSet);
       BarData data = new BarData(dataSets1);
     //   barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
        barChart.setData(data);
        barChart.animateY(3000);
      //    XAxis xAxis = barChart.getXAxis();
      //   xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
         // xAxis.setAvoidFirstLastClipping(true);
         // xAxis.setDrawLabels(true);
        //  xAxis.isCenterAxisLabelsEnabled();
         // xAxis.setGranularityEnabled(true);
         //  YAxis righAxis = barChart.getAxisRight();
         // righAxis.setEnabled(false);
        // barChart.setMaxVisibleValueCount(5);
    }
}