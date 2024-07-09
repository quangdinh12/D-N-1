package android.quangdvph48000.fpolyquangdvph48000duanmau.model;

public class ThanhVien {
    private int matv;
    private String hoten;
    private String namsinh;
    private int sotaikhoan;

    public ThanhVien(int matv, String hoten, String namsinh, int sotaikhoan) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.sotaikhoan = sotaikhoan;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public int getSotaikhoan() {
        return sotaikhoan;
    }

    public void setSotaikhoan(int sotaikhoan) {
        this.sotaikhoan = sotaikhoan;
    }
}
