package android.quangdvph48000.fpolyquangdvph48000duanmau;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.quangdvph48000.fpolyquangdvph48000duanmau.dao.SachDAO;
import android.quangdvph48000.fpolyquangdvph48000duanmau.dao.ThuThuDAO;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.QLLoaiSachFragment;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.QLPhieuMuonFragment;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.QuanLySachFragment;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.QuanLyThanhVienFragment;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.ThongKeDoanhThuFragment;
import android.quangdvph48000.fpolyquangdvph48000duanmau.fragment.ThongKeTop10Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
               if (menuItem.getItemId() == R.id.mQLPhieuMuon){
                   fragment = new QLPhieuMuonFragment();
               } else if (menuItem.getItemId() == R.id.mQLLoaiSach) {
                   fragment = new QLLoaiSachFragment();
               } else if (menuItem.getItemId() == R.id.mTop10) {
                   fragment = new ThongKeTop10Fragment();
               } else if (menuItem.getItemId() == R.id.mDoanhThu) {
                   fragment = new ThongKeDoanhThuFragment();
               }else if (menuItem.getItemId() == R.id.mQLThanhVien) {
                   fragment = new QuanLyThanhVienFragment();
               }else if (menuItem.getItemId() == R.id.mQLSach) {
                   fragment = new QuanLySachFragment();
               }
               else if (menuItem.getItemId() == R.id.mThoat) {
                   Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
                   return true;
               } else if (menuItem.getItemId() == R.id.mDoiMatKhau) {
                   showDialogDoiMatKhau();
                   return true;
               }else {
                   fragment = new QLPhieuMuonFragment();
               }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();

                    toolbar.setTitle(menuItem.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
           drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setNegativeButton("Cập Nhật", null).setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText edtOldPass = view.findViewById(R.id.edtPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtRePass = view.findViewById(R.id.edtRePass);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewpass = edtRePass.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || reNewpass.equals("")){
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(reNewpass)){
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String maTT = sharedPreferences.getString("maTT","");
                        // cap nhat
                        ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                        int check = thuThuDAO.capNhatMatKhau(maTT, oldPass, newPass);
                        if (check == 1){
                            Toast.makeText(MainActivity.this, "Cập Nhật Mật Khẩu thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if(check == 0){
                            Toast.makeText(MainActivity.this, "Mật Khẩu Cũ Không Đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Nhập mật khẩu không trung với nhau", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}