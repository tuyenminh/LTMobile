package com.example.appquanlidiem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appquanlidiem.dao.HocPhanDao;
import com.example.appquanlidiem.model.HocPhan;
import com.example.appquanlidiem.dao.HocPhanDao;
import com.example.appquanlidiem.model.HocPhan;

import java.util.ArrayList;

public class ThemHocPhanActivity extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout2;
    Animation animation;
    EditText edtMaHocPhan, edtTenHocPhan, edtTinChi, edtMucTieu;
    Button btnLuu, btnXemHocPhan;
    HocPhanDao hocPhanDao;
    ArrayList<HocPhan> dsHocPhan = new ArrayList<>();





    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themhocphan);
        linearLayout = findViewById(R.id.linearLayoutThemHocPhan);
        linearLayout2=findViewById(R.id.linearLayout);
        btnLuu = findViewById(R.id.btnThemHocPhan);
        edtMaHocPhan = findViewById(R.id.edtMaHocPhan);
        edtTenHocPhan = findViewById(R.id.edtTenHocPhan);
        btnXemHocPhan = findViewById(R.id.btnXemHocPhan);
        if(MainActivity.isDark==true) {
            // dark theme is on
            linearLayout2.setBackgroundColor(getResources().getColor(R.color.black));
        }
        else
        {
            // light theme is on
            linearLayout2.setBackgroundDrawable(getResources().getDrawable(R.drawable.backdound_app));
        }
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        linearLayout.setAnimation(animation);
        hocPhanDao = new HocPhanDao(ThemHocPhanActivity.this);
        btnXemHocPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemHocPhanActivity.this, HocPhanMainActivity.class));
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahocphan = edtMaHocPhan.getText().toString();
                String tenhocphan = edtTenHocPhan.getText().toString();
                String tinchi = edtTinChi.getText().toString();
                String muctieu = edtMucTieu.getText().toString();
                if (mahocphan.equals("")) {
                    Toast.makeText(ThemHocPhanActivity.this, "Mã học phần không được để trống", Toast.LENGTH_SHORT).show();
                }else if(tenhocphan.equals("")){
                    Toast.makeText(ThemHocPhanActivity.this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                }else if(tinchi.equals("")){
                    Toast.makeText(ThemHocPhanActivity.this, "Tin chi không được để trống",Toast.LENGTH_SHORT).show();
                }
                else if(muctieu.equals("")){

                    Toast.makeText(ThemHocPhanActivity.this,"Mục tiêu không được để trống",Toast.LENGTH_SHORT).show();
                }
                else {
                    HocPhan hocPhan = new HocPhan(mahocphan, tenhocphan, tinchi, muctieu);
                    if (hocPhanDao.insert(hocPhan)) {
                        Toast.makeText(ThemHocPhanActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ThemHocPhanActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
