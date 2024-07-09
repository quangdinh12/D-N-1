package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    Dbhelper dbhelper;
    public ThanhVienDAO(Context context){
        dbhelper = new Dbhelper(context);
    }
    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from THANHVIEN",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themThanhVien(String hoten, String namsinh, int sotaikhoan){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", hoten);
        contentValues.put("namSinh", namsinh);
        contentValues.put("sotaikhoan", sotaikhoan);
        long check = sqLiteDatabase.insert("THANHVIEN", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongtinTV(int matv, String hoten, String namsinh, int sotaikhoan){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", hoten);
        contentValues.put("namSinh", namsinh);
        contentValues.put("sotaikhoan", sotaikhoan);
        long check = sqLiteDatabase.update("THANHVIEN", contentValues, "maTV = ?", new String[]{String.valueOf(matv)});
        if (check == -1)
            return false;
        return true;
    }

    public int xoaThongTinTV(int matv){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from PHIEUMUON where maTV = ?", new String[]{String.valueOf(matv)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("THANHVIEN", "maTV = ?", new String[]{String.valueOf(matv)});
        if (check == -1)
            return 0;
            return 1;
    }
}
