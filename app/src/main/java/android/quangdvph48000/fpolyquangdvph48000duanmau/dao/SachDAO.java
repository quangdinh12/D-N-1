package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    static Dbhelper dbhelper;
    public  SachDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    //lay toan bo dau sach co trong thu vien
     public static ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
         SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
         Cursor cursor = sqLiteDatabase.rawQuery("select sc.maSach, sc.tenSach, sc.giaThue, sc.maLoai, lo.hoten from SACH sc, LOAI lo where sc.maLoai = lo.maLoai",null);
         if (cursor.getCount() != 0){
             cursor.moveToFirst();
             do {
                 list.add(new Sach(cursor.getInt(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4)));
             }while (cursor.moveToNext());
         }

        return list;
     }

     public boolean themSachMoi(String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("tenSach", tensach);
         contentValues.put("giaThue", giatien);
         contentValues.put("maLoai", maloai);
         long check = sqLiteDatabase.insert("SACH", null, contentValues);
         if (check == -1)
             return false;
         return true;
     }

     public boolean capNhatThongTin(int masach, String tensach, int giathue, int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", tensach);
        contentValues.put("giaThue", giathue);
        contentValues.put("maLoai", maloai);
        long check = sqLiteDatabase.update("SACH", contentValues, "maSach = ?", new String[]{String.valueOf(masach)});
        if (check == -1)
            return false;
        return true;
     }

     public int xoaSach(int masach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from PHIEUMUON where maSach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount()!= 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("SACH", "maSach = ?", new String[]{String.valueOf(masach)});
        if (check == -1)
        return 0;
        return 1;
     }
}
