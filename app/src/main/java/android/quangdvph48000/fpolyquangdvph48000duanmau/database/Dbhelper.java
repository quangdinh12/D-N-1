package android.quangdvph48000.fpolyquangdvph48000duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(@Nullable Context context) {
        super(context, "DANGKYMONHOC", null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TableThanhVien = "create table THANHVIEN (" +
                "maTV integer primary key autoincrement," +
                "hoTen text," +
                "namSinh text," +
                "sotaikhoan integer)";
        db.execSQL(TableThanhVien);

        String TableThuThu = "create table THUTHU (" +
                "maTT text primary key," +
                "hoTen text," +
                "matKhau text)";
        db.execSQL(TableThuThu);

        String TableLoaiSach = "create table LOAI(" +
                "maLoai integer primary key autoincrement," +
                "hoten text)";
        db.execSQL(TableLoaiSach);

        String TableSach = "create table SACH(" +
                "maSach integer primary key autoincrement," +
                "tenSach text," +
                "giaThue integer," +
                "maLoai integer references LOAI(maLoai))";
        db.execSQL(TableSach);


        String TablePhieuMuon = "create table PHIEUMUON(" +
                "maPM integer primary key autoincrement," +
                "maTV integer references THANHVIEN(maTV)," +
                "maTT text references THUTHU(maTT)," +
                "maSach integer references SACH(maSach)," +
                "ngay text," +
                "traSach integer," +
                "tienThue integer)";
        db.execSQL(TablePhieuMuon);

        // data mau
        db.execSQL("INSERT INTO LOAI VALUES (1, 'Truyện ma'),(2,'Loves'),(3, 'Giáo khoa')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Căn nhà số 1', 2500, 1), (2, 'Hành lang', 1000, 1), (3, 'Techno', 2000, 3)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Đinh Việt Quang','abc123'),('thuthu02','Vũ thị nở','123abc')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Nguyễn Văn Cao','2000',20000),(2,'Hoàng Công Tấn','2000',7000)");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '19/03/2024', 0, 2500),(2,1,'thuthu01', 3, '19/03/2024', 0, 2000),(3,2,'thuthu02', 1, '19/05/2024', 0, 2000)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists THUTHU");
        db.execSQL("drop table if exists THANHVIEN");
        db.execSQL("drop table if exists LOAI");
        db.execSQL("drop table if exists SACH");
        db.execSQL("drop table if exists PHIEUMUON");

        onCreate(db);
    }
}
