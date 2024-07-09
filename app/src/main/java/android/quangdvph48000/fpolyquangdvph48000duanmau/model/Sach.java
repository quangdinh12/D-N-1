package android.quangdvph48000.fpolyquangdvph48000duanmau.model;

public class Sach {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
    private  int soLuongdamuon;
    private String tenLoai;

    public Sach(int masach, String tensach, int giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public int getSoLuongdamuon() {
        return soLuongdamuon;
    }

    public void setSoLuongdamuon(int soLuongdamuon) {
        this.soLuongdamuon = soLuongdamuon;
    }

    public Sach(int masach, String tensach, int soLuongdamuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.soLuongdamuon = soLuongdamuon;
    }

    public Sach(int masach, String tensach, int giathue, int maloai, String tenLoai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenLoai = tenLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
