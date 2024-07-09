package android.quangdvph48000.fpolyquangdvph48000duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.quangdvph48000.fpolyquangdvph48000duanmau.adapter.Top10Adapter;
import android.quangdvph48000.fpolyquangdvph48000duanmau.dao.ThongKeDAO;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.Sach;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.quangdvph48000.fpolyquangdvph48000duanmau.R;

import java.util.ArrayList;


public class ThongKeTop10Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_top10, container, false);

        RecyclerView recyclerViewtop10 = view.findViewById(R.id.recyclerTop10);

        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewtop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter= new Top10Adapter(getContext(), list);
        recyclerViewtop10.setAdapter(adapter);


        return view;
    }
}