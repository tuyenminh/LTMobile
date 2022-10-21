package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiemCaiThienMainActivity extends AppCompatActivity implements ChoiceDialog.SingleChoiceListenner,ChoiceResult.SingleChoiceListenner{

    private Button btn1;
    private TextView slmh;
    private EditText slkq, tbhk, tbmh;
    private Button kq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemcaithien);
        kq = (Button) findViewById(R.id.mt_kq2);
        slmh = (TextView) findViewById(R.id.mt_chonmh2);
        tbhk = (EditText) findViewById(R.id.mt_tbtk);
        tbmh = (EditText) findViewById(R.id.mt_tbmon);
        slkq = (EditText) findViewById(R.id.diemdukien1);
        btn1 = (Button) findViewById(R.id.mt_buttonddk);
        kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float mslkq;
                String str = slkq.getText().toString();
                mslkq = Float.parseFloat(str);
                String slkq1, tbmon, tbhk;
                slkq1 = String.valueOf(slkq);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiemCaiThienMainActivity.this, DiemDuKienMainActivity.class);
                startActivity(intent);
            }
        });

        slmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment choiDialogFragment = new ChoiceDialog();
                choiDialogFragment.setCancelable(false);
                choiDialogFragment.show(getSupportFragmentManager(), "");
            }
        });
        slkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment choiDialogFragment = new ChoiceResult();
                choiDialogFragment.setCancelable(false);
                choiDialogFragment.show(getSupportFragmentManager(), "");
            }
        });

    }

    @Override
    public void onPostiveButtonClicked(String[] list, int position) {
        slmh.setText("Chọn môn học      " + list[position]);



    }

    @Override
    public void oNegativeButtonClicked() {

    }


    @Override
    public void onPostiveButtonClicked1(String[] list, int position1) {
        slkq.setText(" " + list[position1]);

    }

    @Override
    public void oNegativeButtonClicked1() {

    }
}