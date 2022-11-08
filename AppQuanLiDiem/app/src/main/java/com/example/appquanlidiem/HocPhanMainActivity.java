
package com.example.appquanlidiem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
public class HocPhanMainActivity extends AppCompatActivity {
    ListView listView;
    EditText edtSearch;



    FloatingActionButton fbadd;
    FloatingActionButton fab;


    Boolean isOpen = false;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duc_activity_hocphan);

        relativeLayout=findViewById(R.id.relativel_layout);
        edtSearch = findViewById(R.id.edtsearch);

        fbadd = findViewById(R.id.fbsearch);
        fab = findViewById(R.id.fab);




        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });
        listView.setTextFilterEnabled(true);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
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

        fbadd.animate().translationY(-getResources().getDimension(R.dimen.stan_105));

    }

    private void closeMenu() {
        isOpen = false;

        fbadd.animate().translationY(0);

    }

    @Override
    protected void onResume() {

        super.onResume();


    }
}