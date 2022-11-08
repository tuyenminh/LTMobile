package com.example.appquanlidiem.duc_adapter_HP;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlidiem.R;
import com.example.appquanlidiem.duc_dao_HP.LopDao;
import com.example.appquanlidiem.duc_model_HP.Lop;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LopAdapter extends BaseAdapter implements Filterable {
    Activity context;
    int layout;
    ArrayList<Lop> dslop;
    ArrayList<Lop> dsSortLop;
    private Filter lopFilter;
    LopDao lopDao;
    // ArrayList<SinhVien>dssv=new ArrayList<>();
    Animation animation;


    public LopAdapter(Activity context, int layout, ArrayList<Lop> dslop) {
        this.context = context;
        this.layout = layout;
        this.dslop = dslop;
        this.dsSortLop = dslop;
    }


    @Override
    public int getCount() {
        return dslop.size();

    }

    @Override
    public Object getItem(int position) {
        return dslop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(ArrayList<Lop> dsl) {
        this.dslop = dsl;
        notifyDataSetChanged();
    }

    public void resetData() {
        this.dslop = dsSortLop;
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
                results.values = dsSortLop;
                results.count = dsSortLop.size();
            } else {
                ArrayList<Lop> dslopmoi = new ArrayList<Lop>();
                for (Lop lop : dslop) {
                    if (lop.getTenLop().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        dslopmoi.add(lop);
                    }
                }
                results.values = dslopmoi;
                results.count = dslop.size();
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                dslop = (ArrayList<Lop>) results.values;
                notifyDataSetChanged();
            }
        }
    }

    private class ViewHolder {
        TextView txtMalop, txtTenLop, txtTinChi, txtMucTieu;
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
            holder.linearLayout = convertView.findViewById(R.id.linearLayoutSuaLop);
            holder.txtMalop = convertView.findViewById(R.id.txtMaLophoc);
            holder.txtTenLop = convertView.findViewById(R.id.txtTenLophoc);
            holder.txtTinChi = convertView.findViewById(R.id.txtTinChihoc);
            holder.txtMucTieu = convertView.findViewById(R.id.txtMucTieuhoc);
            holder.ivDelete = convertView.findViewById(R.id.imageViewdeletelop);
            holder.imageViewhinh = convertView.findViewById(R.id.imageView);
            holder.ivEdit = convertView.findViewById(R.id.imageeditlop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Lop lop = dslop.get(position);


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopDao = new LopDao(context);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.duc_layout_edit_hp);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                final EditText edtmaLop = dialog.findViewById(R.id.edteditMaLop);
                final EditText edtTenLop = dialog.findViewById(R.id.edteditTenLop);
                final EditText edtTinChi = dialog.findViewById(R.id.edteditTinChi);
                final EditText edtMucTieu = dialog.findViewById(R.id.edteditMucTieu);
                Button btnSua = dialog.findViewById(R.id.btnCapNhat);
                Button btnHuy = dialog.findViewById(R.id.btnHuy);
                //Đổ dữ liệu
                edtmaLop.setText(lop.getMaLop());
                edtTenLop.setText(lop.getTenLop());
                edtTinChi.setText(lop.getTinChi());
                edtMucTieu.setText(lop.getMucTieu());
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String maLop = edtmaLop.getText().toString();
                            String tenLop = edtTenLop.getText().toString();
                            String tinChi = edtTinChi.getText().toString();
                            String mucTieu = edtMucTieu.getText().toString();
                            Lop lop1 = new Lop(maLop, tenLop,tinChi, mucTieu);
                            //Update vào sql
                            if (lopDao.update(lop1)) {
                                Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                dslop.clear();
                                dslop.addAll(lopDao.getAll());
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
                lopDao = new LopDao(context);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.duc_layout_xoa_hp);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                final TextView txt_Massage = dialog.findViewById(R.id.txt_Titleconfirm);
                Button btnXoa = dialog.findViewById(R.id.btn_yes);
                Button btnHuy = dialog.findViewById(R.id.btn_no);
                final ProgressBar progressBar = dialog.findViewById(R.id.progress_loadconfirm);
                progressBar.setVisibility(View.INVISIBLE);
                txt_Massage.setText("Bạn có chắc chắn xóa lớp " + lop.getMaLop() + " không ? \nCảnh báo nếu bạn xóa lớp " + lop.getMaLop() + " thì hệ thống sẽ xóa toàn bộ sinh viên trong lớp  " + lop.getMaLop());
                final Lop lop = dslop.get(position);
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lopDao.delete(lop.getMaLop()) == true) {
                            txt_Massage.setText("");
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF478B, android.graphics.PorterDuff.Mode.MULTIPLY);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dslop.clear();
                                    dslop.addAll(lopDao.getAll());
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


        holder.txtMalop.setText(lop.getMaLop());
        holder.txtTenLop.setText(lop.getTenLop());
        holder.txtTinChi.setText(lop.getTinChi());
        holder.txtMucTieu.setText(lop.getMucTieu());
        holder.imageViewhinh.setImageResource(R.drawable.iconlop);
        return convertView;
    }
}
