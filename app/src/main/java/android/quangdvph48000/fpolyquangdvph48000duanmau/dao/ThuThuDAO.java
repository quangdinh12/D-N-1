package android.quangdvph48000.fpolyquangdvph48000duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.quangdvph48000.fpolyquangdvph48000duanmau.database.Dbhelper;

public class ThuThuDAO {
    Dbhelper dbhelper;
    public ThuThuDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    //dang nhap
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from THUTHU where maTT = ? and matKhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0){
            return true;
        }else {
                return false;
        }
    }

    public int capNhatMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from THUTHU where maTT = ? and matKhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matKhau", newPass);
            long check = sqLiteDatabase.update("THUTHU",contentValues,"maTT = ?", new String[]{username});
            if (check == -1)
                return -1;
            return 1;
        }
        return 0;
    }
}
