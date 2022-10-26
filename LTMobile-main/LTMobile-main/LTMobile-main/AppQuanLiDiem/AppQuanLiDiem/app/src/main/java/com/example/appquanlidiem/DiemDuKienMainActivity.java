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
    private EditText diem1, kttbc, kttbm;
    private Button kq;
    private float mon1 = 7.5F;
    private float mon2 = 7.3F;
    private float mon3 = 9.0F;
    private float mon4 = 7.1F;
    private float mon5 = 7;
//    private float tb2 = 0.0F;


    private int tcmon1 = 3;
    private int tcmon2 = 3;
    private int tcmon3 = 3;
    private int tcmon4 = 3;
    private int tcmon5 = 3;
    private int tongtc = 0;

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
                Float mmon1,mmon2,mmon3,mmon4;
                mdiem1 = diem1.getText().toString();
                Float d1, tb1, tb2;
                tongtc = 3 + tcmon1 + tcmon2 + tcmon3 + tcmon4;
                d1 = Float.parseFloat(mdiem1);
                tb1 = d1;
                if(tb1 < 4) {
                    kttbm.setText(String.format("F %.1f",tb1));
                }else if ( tb1 > 4 && tb1 < 4.9) {
                    kttbm.setText(String.format("D %.1f",tb1));
                }else if ( tb1 > 4.8 && tb1 < 5.6) {
                    kttbm.setText(String.format("D+ %.1f",tb1));
                }else if (tb1 > 5.4 && tb1 < 6.5){
                    kttbm.setText(String.format("C %.1f",tb1));
                }else if (tb1 > 6.4 && tb1 < 7.0){
                    kttbm.setText(String.format("C+ %.1f",tb1));
                }else if (tb1 > 6.9 && tb1 < 8.0){
                    kttbm.setText(String.format("B %.1f",tb1));
                }else if (tb1 > 7.9 && tb1 < 9.0){
                    kttbm.setText(String.format("B+ %.1f",tb1));
                }else if (tb1 > 8.9 && tb1 < 11){
                    kttbm.setText(String.format("A %.1f",tb1));
                };

                if (mon1 < 4){
                    mon1 = 0.0F;
                }
                else if (mon1 > 4 && mon1 <4.9 ){
                    mon1 = 1.0F;
                }
                else if (mon1 > 4.8 && mon1 < 5.6 ){
                    mon1 = 1.5F;
                }
                else if (mon1 > 5.4 && mon1 < 6.5 ){
                    mon1 = 2.0F;
                }
                else if (mon1 > 6.4 && mon1 < 7.0 ){
                    mon1 = 2.5F;
                }
                else if (mon1 > 6.9 && mon1 < 8.0 ){
                    mon1 = 3.0F;
                }
                else if (mon1 > 7.9 && mon1 < 9.0 ){
                    mon1 = 3.5F;
                }
                else if (mon1 > 8.9 && mon1 < 11 ){
                    mon1 =4.0F;
                };
                if (mon2 < 4){
                    mon2 = 0.0F;
                }
                else if (mon2 > 4 && mon2 <4.9 ){
                    mon2 = 1.0F;
                }
                else if (mon2 > 4.8 && mon2 < 5.6 ){
                    mon2 = 1.5F;
                }
                else if (mon2 > 5.4 && mon2 < 6.5 ){
                    mon2 = 2.0F;
                }
                else if (mon2 > 6.4 && mon2 < 7.0 ){
                    mon2 = 2.5F;
                }
                else if (mon2 > 6.9 && mon2 < 8.0 ){
                    mon2 = 3.0F;
                }
                else if (mon2 > 7.9 && mon2 < 9.0 ){
                    mon2 = 3.5F;
                }
                else if (mon2 > 8.9 && mon2 < 11 ){
                    mon2 =4.0F;
                }
                if (mon3 < 4){
                    mon3 = 0.0F;
                }
                else if (mon3 > 4 && mon3 <4.9 ){
                    mon3 = 1.0F;
                }
                else if (mon3 > 4.8 && mon3 < 5.6 ){
                    mon3 = 1.5F;
                }
                else if (mon3 > 5.4 && mon3 < 6.5 ){
                    mon3 = 2.0F;
                }
                else if (mon3 > 6.4 && mon3 < 7.0 ){
                    mon3 = 2.5F;
                }
                else if (mon3 > 6.9 && mon3 < 8.0 ){
                    mon3 = 3.0F;
                }
                else if (mon3 > 7.9 && mon3 < 9.0 ){
                    mon3 = 3.5F;
                }
                else if (mon3 > 8.9 && mon3 < 11 ){
                    mon3 =4.0F;
                }
                if (mon4 < 4){
                    mon4 = 0.0F;
                }
                else if (mon4 > 4 && mon4 <4.9 ){
                    mon4 = 1.0F;
                }
                else if (mon4 > 4.8 && mon4 < 5.6 ){
                    mon4 = 1.5F;
                }
                else if (mon4 > 5.4 && mon4 < 6.5 ){
                    mon4 = 2.0F;
                }
                else if (mon4 > 6.4 && mon4 < 7.0 ){
                    mon4 = 2.5F;
                }
                else if (mon4 > 6.9 && mon4 < 8.0 ){
                    mon4 = 3.0F;
                }
                else if (mon4 > 7.9 && mon4 < 9.0 ){
                    mon4 = 3.5F;
                }
                else if (mon4 > 8.9 && mon4 < 11 ){
                    mon4 =4.0F;
                };
                if (d1 < 4){
                    d1 = 0.0F;
                }
                else if (d1 > 4 && d1 <4.9 ){
                    d1 = 1.0F;
                }
                else if (d1 > 4.8 && d1 < 5.6 ){
                    d1 = 1.5F;
                }
                else if (d1 > 5.4 && d1 < 6.5 ){
                    d1 = 2.0F;
                }
                else if (d1 > 6.4 && d1 < 7.0 ){
                    d1 = 2.5F;
                }
                else if (d1 > 6.9 && d1 < 8.0 ){
                    d1 = 3.0F;
                }
                else if (d1 > 7.9 && d1 < 9.0 ){
                    d1 = 3.5F;
                }
                else if (d1 > 8.9 && d1 < 10.1 ){
                    d1 =4.0F;
                };
                tb2 = ((d1*3)+ (mon1*tcmon1) + (mon2*tcmon2) + (mon3*tcmon3) + (mon4*tcmon4))/tongtc;
                kttbc.setText(String.format("%.2f",tb2));



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