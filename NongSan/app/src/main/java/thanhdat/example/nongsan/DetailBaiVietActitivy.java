package thanhdat.example.nongsan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailBaiVietActitivy extends AppCompatActivity {
    private String idbaiban;
    private String tieude,ngaydang,tinhthanh,loai,gia,mota;
    private TextView tvtieude,tvngaidang,tvtinhthanh,tvloai,tvgia,tvmota;
    private ImageView imgsanpham;
    private ImageButton imgbtnback;
    private DatabaseReference databaseReference;
    private List<Baiban> listbaiban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bai_viet_actitivy);
        addControls();
        databaseReference.child("Ban").child(idbaiban).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listbaiban = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                   // Log.d("aab",snapshot.getValue().toString());
                    Log.d("aaa",dataSnapshot.getValue().toString());
                    Baiban bai = dataSnapshot.getValue(Baiban.class);
                    tvtieude.setText(bai.getTieude());
                    tvngaidang.setText("Ngày đăng: "+ bai.getNgaydangbaiviet());
                    tvtinhthanh.setText("Tỉnh thành: "+ bai.getTinhthanh());
                    tvloai.setText("Loại: " +bai.getDanhmucsanpham());
                    tvgia.setText("Giá : " +bai.getGiasanpham() + " Vnd");
                    tvmota.setText("Mô tả sản phẩm: \n\t" + bai.getMotasanpham());
                    Picasso.with(DetailBaiVietActitivy.this)
                            .load(bai.getIdhinhanhsanpham())
                            .fit()
                            .centerCrop()
                            .into(imgsanpham);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addControls() {
        imgbtnback = findViewById(R.id.imgbtnBackToXembaiviet);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        idbaiban = intent.getStringExtra("idbaiban");
        tvtieude = findViewById(R.id.txtTieudedetails);
        tvngaidang = findViewById(R.id.txtNgaydangdetail);
        tvtinhthanh = findViewById(R.id.txtTinhthanhdetail);
        tvloai = findViewById(R.id.txtLoaisoanphamdetail);
        tvgia = findViewById(R.id.txtGiasanphamdetail);
        tvmota = findViewById(R.id.txtmotadetals);
        imgsanpham = findViewById(R.id.imgSanphamdetails);

    }
}
