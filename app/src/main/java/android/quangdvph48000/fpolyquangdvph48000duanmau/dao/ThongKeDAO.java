package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;
import android.quangdvph48000.fpolyquangdvph48000duanmau.model.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    Dbhelper dbhelper;
    public ThongKeDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select pm.maSach, sc.tenSach, count(pm.maSach) from PHIEUMUON pm, SACH sc where pm.maSach = sc.maSach group by pm.maSach, sc.tenSach order by count(pm.maSach) desc limit 10", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau=ngaybatdau.replace("/","");
        ngayketthuc=ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase=dbhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(tienThue) FROM PHIEUMUON WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) BETWEEN ? AND ? ",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }


}
