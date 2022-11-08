package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appquanlidiem.duc_adapter_HP.LopAdapter;
import com.example.appquanlidiem.duc_dao_HP.LopDao;
import com.example.appquanlidiem.duc_model_HP.Lop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class DanhSachHocPhanActivity extends AppCompatActivity {
    FloatingActionButton fbadd;
    FloatingActionButton fab;
    FloatingActionButton fbHome;
    FloatingActionButton fabDangXuat;
    TextView tvanhien;
    EditText edtSearch;

    ArrayList<Lop> dsLop = new ArrayList<>();
    ArrayList<Lop> timKiem = new ArrayList<>();

    public static boolean xetList = true;

    ListView listView;
    LopAdapter lopAdapter;

    LopDao lopDao;


    Boolean isOpen = false;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duc_layout_danhsach_hp);
        relativeLayout=findViewById(R.id.relativel_layout);
        listView = findViewById(R.id.listviewLop);
        fbadd = findViewById(R.id.fbThemLop);
        tvanhien = findViewById(R.id.tvAnHien);
        fbHome = findViewById(R.id.fbHomeLop);
        fab = findViewById(R.id.fab1);
        fabDangXuat = findViewById(R.id.fbDangXuatLop);
        edtSearch = findViewById(R.id.edtserchLop);

        fbAction();
        lopDao = new LopDao(DanhSachHocPhanActivity.this);

        dsLop = lopDao.getAll();
        timKiem = lopDao.getAll();

        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachHocPhanActivity.this, ThemHocPhanActivity.class));
            }
        });

        lopAdapter = new LopAdapter(DanhSachHocPhanActivity.this, R.layout.duc_layout_dong_hp, dsLop);
        listView.setAdapter(lopAdapter);

        if (dsLop.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            tvanhien.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            tvanhien.setVisibility(View.INVISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maLop = dsLop.get(position).toString();


                int dem = 0;


                if (dem > 0) {
                    Intent i = new Intent(DanhSachHocPhanActivity.this, MainActivity.class);
                    xetList = true;
                    startActivity(i);
                } else {
                    Toast.makeText(DanhSachHocPhanActivity.this, "Không có sinh viên nào thuộc mã lớp " + dsLop.get(position), Toast.LENGTH_LONG).show();
                }
            }
        });


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Search or Filter

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    lopAdapter.resetData();

                } else {
                    lopAdapter.getFilter().filter(s);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void fbAction() {


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }
        });
    }

    private void openMenu() {
        isOpen = true;
        fbHome.animate().translationY(-getResources().getDimension(R.dimen.stan_60));
        fbadd.animate().translationY(-getResources().getDimension(R.dimen.stan_105));
        fabDangXuat.animate().translationY(-getResources().getDimension(R.dimen.stan_155));
    }

    private void closeMenu() {
        isOpen = false;
        fbHome.animate().translationY(0);
        fbadd.animate().translationY(0);
        fabDangXuat.animate().translationY(0);
    }
}

