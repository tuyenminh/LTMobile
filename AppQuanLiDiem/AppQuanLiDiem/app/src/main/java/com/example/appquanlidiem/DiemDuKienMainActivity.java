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

public class DiemDuKienMainActivity extends AppCompatActivity implements ChoiceDialog.SingleChoiceListenner{
    private Button btn2;
    private TextView slmh;

    private int mon1 = 7;
    private int mon2 = 7;
    private int mon3 = 7;
    private int mon4 = 7;
    private int mon5 = 7;

    private int tcmon1 = 3;
    private int tcmon2 = 3;
    private int tcmon3 = 3;
    private int tcmon4 = 3;
    private int tcmon5 = 3;
    private int tongtc = 0;
    EditText diem1, kttbc, kttbm;
    Button kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemdukien);
        diem1 = (EditText) findViewById(R.id.diemdukien);
        kq = (Button) findViewById(R.id.mt_kq1);
        kttbc = (EditText) findViewById(R.id.mt_kqtbc);
        kttbm = (EditText) findViewById(R.id.mt_kqtbm);
        slmh = (TextView) findViewById(R.id.mt_chonmh);
        btn2 = (Button) findViewById(R.id.mt_buttondct);
        kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mdiem1;
                mdiem1 = diem1.getText().toString();
                Double d1, tb1, tb2;
                d1 = Double.parseDouble(mdiem1);
                tongtc = 3 + tcmon1 + tcmon2 + tcmon3 + tcmon4;
                tb2= ((d1*3)+ (mon1*tcmon1) + (mon2*tcmon2) + (mon3*tcmon3) + (mon4*tcmon4))/tongtc;
                if(tb2 < 4) {
                    kttbc.setText("F " + tb2);
                }else if ( tb2 > 4 && tb2 < 4.9) {
                    kttbc.setText("D " + tb2);
                }else if ( tb2 > 4.8 && tb2 < 5.6) {
                    kttbc.setText("D+ " + tb2);
                }else if (tb2 > 5.4 && tb2 < 6.5){
                    kttbc.setText("C " + tb2);
                }else if (tb2 > 6.4 && tb2 < 7.0){
                    kttbc.setText("C+ " + tb2);
                }else if (tb2 > 6.9 && tb2 < 8.0){
                    kttbc.setText("B " + tb2);
                }else if (tb2 > 7.9 && tb2 < 9.0){
                    kttbc.setText("B+ " + tb2);
                }else if (tb2 > 8.9 && tb2 < 11){
                    kttbc.setText("A " + tb2);
                }
                tb1 = d1;
//                kttbc.setText(String.valueOf(tb2));
                kttbm.setText(String.valueOf(tb1));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiemDuKienMainActivity.this, DiemCaiThienMainActivity.class);
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
    }

    @Override
    public void onPostiveButtonClicked(String[] list, int position) {
        slmh.setText("Chọn môn học  " + list[position]);



    }

    @Override
    public void oNegativeButtonClicked() {

    }

}