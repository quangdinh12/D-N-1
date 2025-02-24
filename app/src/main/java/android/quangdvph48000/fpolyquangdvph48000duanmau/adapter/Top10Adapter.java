package android.quangdvph48000.fpolyquangdvph48000duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.quangdvph48000.fpolyquangdvph48000duanmau.R;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.Sach;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Top10Adapter extends  RecyclerView.Adapter<Top10Adapter.ViewHolder>{

    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMaSach.setText("Mã Sách :"+String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText("Tên Sách :" +list.get(position).getTensach());
        holder.txtSoLuongMuon.setText("Số Lượng Mượn :" +String.valueOf(list.get(position).getSoLuongdamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach, txtSoLuongMuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuon);
        }
    }
}
