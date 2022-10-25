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

import com.example.appquanlidiem.adapter.HocPhanAdapter;
import com.example.appquanlidiem.dao.HocPhanDao;

import com.example.appquanlidiem.model.HocPhan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HocPhanMainActivity extends AppCompatActivity {
    FloatingActionButton fbadd;
    FloatingActionButton fab;
    FloatingActionButton fbHome;
    FloatingActionButton fabDangXuat;
    TextView tvanhien;
    EditText edtSearch;

    ArrayList<HocPhan> dsHocPhan = new ArrayList<>();
    ArrayList<HocPhan> timKiem = new ArrayList<>();

    ArrayList<HocPhan> svlist;
    static ArrayList<HocPhan> svlistDuocLoc;
    public static boolean xetList = true;

    ListView listView;
    HocPhanAdapter hocPhanAdapter;

    HocPhanDao hocPhanDao;


    Boolean isOpen = false;
    RelativeLayout relativeLayout;
    private Object HocPhanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocphan);
        relativeLayout=findViewById(R.id.relativel_layout);
        listView = findViewById(R.id.listviewHocPhan);
        fbadd = findViewById(R.id.fbThemHocPhan);
        tvanhien = findViewById(R.id.tvAnHien);
        fbHome = findViewById(R.id.fbHomeHocPhan);
        fab = findViewById(R.id.fab1);
        edtSearch = findViewById(R.id.edtserchHocPhan);
        if(MainActivity.isDark==true) {
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
        fbAction();
        hocPhanDao = new HocPhanDao(HocPhanMainActivity.this);

        // dsHocPhan = hocPhanDao.getAll();
        // timKiem = hocPhanDao.getAll();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocPhanMainActivity.this, ThemHocPhanActivity.class));
            }
        });

        hocPhanAdapter = new HocPhanAdapter(HocPhanMainActivity.this, R.layout.dong_hocphan, dsHocPhan);
        listView.setAdapter(hocPhanAdapter);

        if (dsHocPhan.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            tvanhien.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            tvanhien.setVisibility(View.INVISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maHocPhan = dsHocPhan.get(position).toString();
                HocPhanDao = new HocPhanDao(HocPhanMainActivity.this);
                hocPhanDao.getALL();
                int dem = 0;
                svlistDuocLoc = new ArrayList<HocPhan>();
                for (int i = 0; i < svlist.size(); i++) {

                    HocPhan sv = svlist.get(i);
                    if (maHocPhan.matches(sv.getMaHocPhan())) {
                        svlistDuocLoc.add(svlist.get(i));
                        dem++;
                    }
                }
                if (dem > 0) {
                    Intent i = new Intent(HocPhanMainActivity.this, MainActivity.class);
                    xetList = true;
                    startActivity(i);
                } else {
                    Toast.makeText(HocPhanMainActivity.this, "Không có học phần nào thuộc mã lớp " + dsHocPhan.get(position), Toast.LENGTH_LONG).show();
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
                    hocPhanAdapter.resetData();

                } else {
                    hocPhanAdapter.getFilter().filter(s);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void fbAction() {
        fbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HocPhanMainActivity.this, ThemHocPhanActivity.class));
            }
        });
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
