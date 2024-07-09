package android.quangdvph48000.fpolyquangdvph48000duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.quangdvph48000.fpolyquangdvph48000duanmau.R;
import android.quangdvph48000.fpolyquangdvph48000duanmau.dao.ThanhVienDAO;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.ThanhVien;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txtMaThanhVien.setText("Mã TV :"+list.get(position).getMatv());
       holder.txtHoTen.setText("Họ tên :"+list.get(position).getHoten());
       holder.txtNamSinh.setText("Năm Sinh :"+list.get(position).getNamsinh());

        int stk = list.get(position).getSotaikhoan();
        if (stk % 5 == 0) {
            holder.txtsotaikhoan.setTypeface(null, Typeface.BOLD);
        } else {
            holder.txtsotaikhoan.setTypeface(null, Typeface.NORMAL);
        }

       holder.txtsotaikhoan.setText("Số tài Khoản: "+list.get(position).getSotaikhoan());

       holder.ivEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            showDiaLogCapNhatTT(list.get(holder.getAdapterPosition()));
           }
       });

       holder.ivDel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int check = thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
               switch (check){
                   case 1:
                       Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                       loadData();
                       break;
                   case 0:
                       Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                       break;
                   case -1:
                       Toast.makeText(context, "Thành viên tồn tại phiếu mượn, không được phép xóa", Toast.LENGTH_SHORT).show();
                   default:
                       break;
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMaThanhVien, txtHoTen, txtNamSinh, txtsotaikhoan;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaThanhVien = itemView.findViewById(R.id.txtMaThanhVien);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            txtsotaikhoan = itemView.findViewById(R.id.txtSotaikhoan);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDelete);
        }
    }

    private void showDiaLogCapNhatTT(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.diaglog_chinhsua_thanhvien,null);
        builder.setView(view);

        TextView txtMaTv = view.findViewById(R.id.txtMaTv1);
        EditText edtHoten = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);
        EditText edtsotaikhoan = view.findViewById(R.id.edtsotaiKhoan);

        txtMaTv.setText("Mã TV : "+ thanhVien.getMatv());
        edtHoten.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());
        edtsotaikhoan.setText(""+thanhVien.getSotaikhoan());

        builder.setNegativeButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             String hoten = edtHoten.getText().toString();
             String namsinh = edtNamSinh.getText().toString();
             int sotaikhoan = Integer.parseInt(edtsotaikhoan.getText().toString());
             int id = thanhVien.getMatv();
             
             boolean check = thanhVienDAO.capNhatThongtinTV(id, hoten, namsinh, sotaikhoan);
             if (check){
                 Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                 loadData();
             }else {
                 Toast.makeText(context, "Cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
             }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
