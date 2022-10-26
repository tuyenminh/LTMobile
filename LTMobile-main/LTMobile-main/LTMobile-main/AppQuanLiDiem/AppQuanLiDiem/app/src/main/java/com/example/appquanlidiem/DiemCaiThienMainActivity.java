package com.example.appquanlidiem;

import static java.lang.Float.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DiemCaiThienMainActivity extends AppCompatActivity implements ChoiceDialog.SingleChoiceListenner,ChoiceResult.SingleChoiceListenner{

    private Button btn1;
    private TextView slmh;
    private EditText slkq, tbhk, tbmh;
    private Button kq;
    private float mon1 = 7.5F;
    private float mon2 = 7.3F;
    private float mon3 = 9.0F;
    private float mon4 = 7.1F;
    private float mon5 = 7;

    private int tcmon1 = 3;
    private int tcmon2 = 3;
    private int tcmon3 = 3;
    private int tcmon4 = 3;
    private int tcmon5 = 3;
    private int tongtc = 0;
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
                String mslkq;
                String a = " A";
                String b = " B+";
                String b1 = " B";
                String c = " C+";
                String c1 = " C";
                String d = " D+";
                String d1 = " D";
                String f = " F";
                Float diem1;
                Float tb1;
                Float tb2;
                mslkq = slkq.getText().toString();
                tongtc = 3 + tcmon1 + tcmon2 + tcmon3 + tcmon4;
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
                };
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
                };
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

                if (mslkq.equals(a)){
                    mslkq = "4.0";
//                    diem1 = Float.parseFloat(mslkq);
//                    tb1 = ((diem1*3) + (mon1*tcmon1) + (mon2*tcmon2) + (mon3*tcmon3) + (mon4*tcmon4))/tongtc;
//                    tbmh.setText(String.format("%.2f",tb1));
                }
                else if (mslkq.equals(b)){
                    mslkq = "3.5";
//                    diem1 = Float.parseFloat(mslkq);
//                    tb1 = ((diem1*3) + (mon1*tcmon1) + (mon2*tcmon2) + (mon3*tcmon3) + (mon4*tcmon4))/tongtc;
//                    tbmh.setText(String.format("%.2f",tb1));
                }

                else if (mslkq.equals(b1)){
                    mslkq = "3.0";
                }
                else if (mslkq.equals(c)){
                    mslkq = "2.5";
                }
                else if (mslkq.equals(c1)){
                    mslkq = "2.0";
                }
                else if (mslkq.equals(d)){
                    mslkq = "1.5";
                }
                else if (mslkq.equals(d1)){
                    mslkq = "1.0";
                }
                else if (mslkq.equals(d1)){
                    mslkq = "0.0";
                };
                diem1 = Float.parseFloat(mslkq);
                tb1 = ((diem1*3) + (mon1*tcmon1) + (mon2*tcmon2) + (mon3*tcmon3) + (mon4*tcmon4))/tongtc;
                tbmh.setText(String.format("%.2f",tb1));
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