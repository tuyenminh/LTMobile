package com.example.appquanlidiem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static boolean isDark;
    private CardView  t2, t3, t4, t6;
    Spinner spDanhSach;
    TextView tendialog;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t2 = (CardView) findViewById(R.id.d2);
        t3 = (CardView) findViewById(R.id.d3);
        t4 = (CardView) findViewById(R.id.d4);
        t6 = (CardView) findViewById(R.id.d6);

        t2.setOnClickListener((View.OnClickListener) this);
        t3.setOnClickListener((View.OnClickListener) this);
        t4.setOnClickListener((View.OnClickListener) this);
        t6.setOnClickListener((View.OnClickListener) this);

        spDanhSach = (Spinner) findViewById(R.id.mt_spinner1);
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
        spDanhSach.setAdapter(arrAdapter);
        spDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayHocKi.get(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tendialog = findViewById(R.id.mt_ten);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập tên của bạn");
        View view = getLayoutInflater().inflate(R.layout.dialogtt_layout, null);
        EditText tennhap;
        tennhap = view.findViewById(R.id.tennhapdialog);
        Button submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tendialog.setText(tennhap.getText().toString());
                dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();

        tendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.d2 : i = new Intent(this,HocPhanMainActivity.class); startActivity(i); break;
            case R.id.d3 : i = new Intent(this,DiemDuKienMainActivity.class); startActivity(i); break;
            case R.id.d4 : i = new Intent(this,ThoiKhoaBieuMainActivity.class); startActivity(i); break;
            case R.id.d6 : i = new Intent(this,ThongKeMainActivity.class); startActivity(i); break;

        }
    }
}