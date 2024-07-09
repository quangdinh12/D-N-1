package android.quangdvph48000.fpolyquangdvph48000duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.quangdvph48000.fpolyquangdvph48000duanmau.R;
import android.quangdvph48000.fpolyquangdvph48000duanmau.dao.SachDAO;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.Sach;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list, ArrayList<HashMap<String, Object>> listHM, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_sach,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txtMaSach.setText("Mã Sách : "+ list.get(position).getMasach());
       holder.txtTenSach.setText("Tên Sách : "+ list.get(position).getTensach());
       holder.txtGiaThue.setText("Giá Thuê : "+ list.get(position).getGiathue());
       holder.txtMaloai.setText("Mã Loại : "+ list.get(position).getMaloai());
       holder.txtTenLoai.setText("Tên Loại: "+ list.get(position).getTenLoai());

    holder.ivEdit1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog(list.get(holder.getAdapterPosition()));
        }
    });

    holder.ivDel1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMasach());
            switch (check){
                case 1:
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    break;
                case 0:
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(context, "Không xóa đươợc sách này vì sách này có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                    break;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSach, txtTenSach, txtGiaThue, txtMaloai, txtTenLoai;
        ImageView ivEdit1, ivDel1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSach2);
            txtTenSach = itemView.findViewById(R.id.txtTenSach2);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue2);
            txtMaloai = itemView.findViewById(R.id.txtMaLoai2);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai2);
            ivDel1 = itemView.findViewById(R.id.ivDel1);
            ivEdit1 = itemView.findViewById(R.id.ivEdit1);
        }
    }

    private void showDialog(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chinhsuasach, null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtCSSach1);
        EditText edtTien = view.findViewById(R.id.edtCSTien1);
        TextView txtMaSach = view.findViewById(R.id.txtCSMaSach1);
        Spinner spnLoaiSach = view.findViewById(R.id.spnCSSach);

        txtMaSach.setText("Mã sách :" + sach.getMasach());
        edtTenSach.setText(sach.getTensach());
        edtTien.setText(String.valueOf(sach.getGiathue()));


        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);


         int index = 0;
         int position = -1;
         for (HashMap<String, Object> item : listHM){
             if ((int)item.get("maLoai") == sach.getMaloai()){
                 position = index;
             }
             index++;
         }
         spnLoaiSach.setSelection(position);

         builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 String tensach = edtTenSach.getText().toString();
                 int tien =Integer.parseInt(edtTien.getText().toString());
                 HashMap<String, Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                 int maloai = (int) hs.get("maLoai");


                 boolean check = sachDAO.capNhatThongTin(sach.getMasach(),tensach, tien, maloai);
                 if (check){
                     Toast.makeText(context, "Sửa sách thành công", Toast.LENGTH_SHORT).show();
                     loadData();
                 }else {
                     Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                 }
             }
         });

         builder.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

             }
         });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        list.clear();
        list = sachDAO.getDSDauSach();
        notifyDataSetChanged();

    }
}
