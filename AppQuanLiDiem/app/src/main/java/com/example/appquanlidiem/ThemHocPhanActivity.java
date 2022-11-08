package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.appquanlidiem.duc_dao_HP.LopDao;
import com.example.appquanlidiem.duc_model_HP.Lop;
import java.util.ArrayList;

public class ThemHocPhanActivity extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout2;
    Animation animation;
    EditText edtMalop, edtTenLop,edtTinChi, edtMucTieu;
    Button btnLuu, btnXemLop;
    LopDao lopDao;
    ArrayList<Lop> dsLop = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duc_layout_them_hp);
        linearLayout = findViewById(R.id.linearLayoutThemLop);
        linearLayout2=findViewById(R.id.linearLayout);
        btnLuu = findViewById(R.id.btnThemLop);
        edtMalop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtTinChi = findViewById(R.id.edtTinChi);
        edtMucTieu = findViewById(R.id.edtMucTieu);
        btnXemLop = findViewById(R.id.btnXemlop);

        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        linearLayout.setAnimation(animation);
        lopDao = new LopDao(ThemHocPhanActivity.this);
        btnXemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemHocPhanActivity.this, DanhSachHocPhanActivity.class));
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMalop.getText().toString();
                String tenlop = edtTenLop.getText().toString();
                String tinchi = edtTinChi.getText().toString();
                String muctieu = edtMucTieu.getText().toString();
                if (malop.equals("")) {
                    Toast.makeText(ThemHocPhanActivity.this, "Mã lớp không được để trống", Toast.LENGTH_SHORT).show();
                }else if(tenlop.equals("")){
                    Toast.makeText(ThemHocPhanActivity.this, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                }else if(tinchi.equals("")){
                    Toast.makeText(ThemHocPhanActivity.this, "Tín chỉ không được để trống", Toast.LENGTH_SHORT).show();
                }else if(muctieu.equals("")){
                    Toast.makeText(ThemHocPhanActivity.this, "Mục tiêu không được để trống", Toast.LENGTH_SHORT).show();
                }
                else {
                    Lop lop = new Lop(malop, tenlop, tinchi, muctieu);
                    if (lopDao.insert(lop)) {
                        Toast.makeText(ThemHocPhanActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ThemHocPhanActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
