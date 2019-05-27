package thanhdat.example.nongsan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edtTaiKhoan,edtMatKhau;
    private TextView tvQuenMatKhau,tvDangKy;
    private Button btnDangnhap;
    private DatabaseReference dataR;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtTaiKhoan.getText().toString();
                password = edtMatKhau.getText().toString();
                dataR.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(username).equals("")) {
                            Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu không thể để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dataSnapshot.child(username).exists()) {
                                //  Toast.makeText(DangNhapActivity.this,""+ dataSnapshot.child(username).child("password").getValue(), Toast.LENGTH_SHORT).show();
                                try{ if(dataSnapshot.child(username).child("password").getValue().equals(password)){
                                    Intent intent = new Intent(DangNhapActivity.this, XemBaiVietActivity.class);
                                    intent.putExtra("username",username);
                                    startActivity(intent);
                                }
                                }
                                catch(Exception e){
                                    Toast.makeText(DangNhapActivity.this, "Bạn nhập vào mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(DangNhapActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });














        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(DangNhapActivity.this,QuenMatKhauActivity.class);
                //  startActivity(intent);
            }
        });
    }

    private void addControls() {
        dataR = FirebaseDatabase.getInstance().getReference();
        edtTaiKhoan = findViewById(R.id.edtusrname);
        edtMatKhau = findViewById(R.id.edtpwd);
        tvQuenMatKhau = findViewById(R.id.txtLinkquenmatkhau);
        tvDangKy = findViewById(R.id.txtLinkdangky);
        btnDangnhap = findViewById(R.id.btnDangnhap);
    }


}
