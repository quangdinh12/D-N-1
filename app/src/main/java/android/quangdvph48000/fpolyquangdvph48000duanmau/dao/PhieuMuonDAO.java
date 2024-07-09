package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    Dbhelper dbhelper;

    public PhieuMuonDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon (){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.maSach, sc.tenSach, pm.ngay, pm.traSach, pm.tienThue from PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.maTV = tv.maTV AND pm.maTT = tt.maTT and pm.maSach = sc.maSach ORDER BY pm.maPM DESC", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
            }while (cursor.moveToNext());
        }

        return list;
    }


    public boolean thayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traSach", 1);
        long check = sqLiteDatabase.update("PHIEUMUON", contentValues, "maPM = ?", new String[]{String.valueOf(mapm)});
        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean themPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("maPM", phieuMuon.getMapm());
        contentValues.put("maTV", phieuMuon.getMatv());
        contentValues.put("maTT", phieuMuon.getMatt());
        contentValues.put("maSach", phieuMuon.getMasach());
        contentValues.put("ngay", phieuMuon.getNgay());
        contentValues.put("traSach", phieuMuon.getTrasach());
        contentValues.put("tienThue", phieuMuon.getTienthue());

        long check = sqLiteDatabase.insert("PHIEUMUON", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
}
