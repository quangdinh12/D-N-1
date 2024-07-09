package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    Dbhelper dbhelper;
    public LoaiSachDAO(Context context){
        dbhelper = new Dbhelper(context);
    }


    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from LOAI", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }


    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", tenloai);
        long check = sqLiteDatabase.insert("LOAI", null, contentValues);
        if (check == -1)
            return false;
        return true;
        }

        //xoa loai sach
    //1 : xoa thanh cong - 0 : xoa that bai - -1 : co loai scah ton tai

    public  int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SACH where maLoai = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("LOAI", "maLoai = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }

    public  boolean thayDoiLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", loaiSach.getTenLoai());
        long check = sqLiteDatabase.update("LOAI", contentValues, "maLoai = ?", new String[]{String.valueOf(loaiSach.getId())});
        if (check == -1)
            return false;
        return true;
    }
}
