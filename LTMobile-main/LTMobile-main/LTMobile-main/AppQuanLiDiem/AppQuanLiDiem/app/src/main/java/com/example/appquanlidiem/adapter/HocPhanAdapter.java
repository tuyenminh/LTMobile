package com.example.appquanlidiem.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlidiem.R;
import com.example.appquanlidiem.dao.HocPhanDao;
import com.example.appquanlidiem.model.HocPhan;
import com.example.appquanlidiem.R;
import com.example.appquanlidiem.dao.HocPhanDao;
import com.example.appquanlidiem.model.HocPhan;

import java.util.ArrayList;



public class HocPhanAdapter extends BaseAdapter implements Filterable {
    Activity context;
    int layout;
    ArrayList<HocPhan> dsHocPhan;
    ArrayList<HocPhan> dsSortHocPhan;
    private Filter lopFilter;
    HocPhanDao hocPhanDao;
    // ArrayList<SinhVien>dssv=new ArrayList<>();
    Animation animation;


    public HocPhanAdapter(Activity context, int layout, ArrayList<HocPhan> dsHocPhan) {
        this.context = context;
        this.layout = layout;
        this.dsHocPhan = dsHocPhan;
        this.dsSortHocPhan = dsHocPhan;
    }


    @Override
    public int getCount() {
        return dsHocPhan.size();

    }

    @Override
    public Object getItem(int position) {
        return dsHocPhan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(ArrayList<HocPhan> dsl) {
        this.dsHocPhan = dsl;
        notifyDataSetChanged();
    }

    public void resetData() {
        this.dsHocPhan = dsSortHocPhan;
    }

    @Override
    public Filter getFilter() {
        if (lopFilter == null) {
            lopFilter = new CustomFilter();
        }
        return lopFilter;
    }

    public class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = dsSortHocPhan;
                results.count = dsSortHocPhan.size();
            } else {
                ArrayList<HocPhan> dshocphanmoi = new ArrayList<HocPhan>();
                for (HocPhan hocPhan : dsHocPhan) {
                    if (hocPhan.getTenHocPhan().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        dshocphanmoi.add(hocPhan);
                    }
                }
                results.values = dshocphanmoi;
                results.count = dsHocPhan.size();
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                dsHocPhan = (ArrayList<HocPhan>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    private class ViewHolder {
        TextView txtMaHocPhan, txtTenHocPhan,txtTinChi, txtMucTieu;
        CircleImageView imageViewhinh;
        ImageView ivDelete, ivEdit;
        LinearLayout linearLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.linearLayout = convertView.findViewById(R.id.linearLayoutSuaHocPhan);
            holder.txtMaHocPhan = convertView.findViewById(R.id.txtMaHocPhan);
            holder.txtTenHocPhan = convertView.findViewById(R.id.txtTenHocPhan);
            holder.txtTinChi = convertView.findViewById(R.id.txtTinChi);
            holder.txtMucTieu = convertView.findViewById(R.id.txtMucTieu);
            holder.ivDelete = convertView.findViewById(R.id.imageViewdeletelop);
            holder.ivEdit = convertView.findViewById(R.id.imageeditlop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HocPhan lop = dsHocPhan.get(position);


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hocPhanDao = new HocPhanDao(context);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_hocphan);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                final EditText edtMaHocPhan = dialog.findViewById(R.id.edteditMaHocPhan);
                final EditText edtTenHocPhan = dialog.findViewById(R.id.edteditTenHocPhan);
                final EditText edtTinChi = dialog.findViewById(R.id.txtTinChi);
                final EditText edtMucTieu = dialog.findViewById(R.id.txtMucTieu);

                Button btnSua = dialog.findViewById(R.id.btnCapNhat);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                //Đổ dữ liệu
                edtMaHocPhan.setText(lop.getMaHocPhan());
                edtTenHocPhan.setText(lop.getTenHocPhan());
                edtTinChi.setText(lop.getTinChi());
                edtMucTieu.setText(lop.getMucTieu());
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String MaHocPhan = edtMaHocPhan.getText().toString();
                            String TenHocPhan = edtTenHocPhan.getText().toString();
                            String TinChi = edtTinChi.getText().toString();
                            String MucTieu = edtMucTieu.getText().toString();
                            HocPhan lop1 = new HocPhan(MaHocPhan, TenHocPhan,TinChi,MucTieu);
                            //Update vào sql
                            if (hocPhanDao.update(lop1)) {
                                Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                hocPhanDao.clear();
                                hocPhanDao.addAll(hocPhanDao.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hocPhanDao = new HocPhanDao(context);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_xoa);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                final TextView txt_Massage = dialog.findViewById(R.id.txt_Titleconfirm);
                Button btnXoa = dialog.findViewById(R.id.btn_yes);
                Button btnHuy = dialog.findViewById(R.id.btn_no);
                final ProgressBar progressBar = dialog.findViewById(R.id.progress_loadconfirm);
                progressBar.setVisibility(View.INVISIBLE);
                txt_Massage.setText("Bạn có chắc chắn xóa lớp " + lop.getMaHocPhan() + " không ? \nCảnh báo nếu bạn xóa học phần " + lop.getMaHocPhan() + " thì hệ thống sẽ xóa toàn bộ sinh viên trong lớp  " + lop.getMaHocPhan());
                final HocPhan hocPhan = dsHocPhan.get(position);
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hocPhanDao.delete(hocPhan.getMaHocPhan()) == true) {
                            txt_Massage.setText("");
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF478B, android.graphics.PorterDuff.Mode.MULTIPLY);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dsHocPhan.clear();
                                    dsHocPhan.addAll(hocPhanDao.getAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }, 2000);
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        holder.txtMaHocPhan.setText(lop.getMaHocPhan());
        holder.txtTenHocPhan.setText(lop.getTenHocPhan());
        holder.txtTinChi.setText(lop.getTinChi());
        holder.txtMucTieu.setText(lop.getMucTieu());


        return convertView;
    }
}
