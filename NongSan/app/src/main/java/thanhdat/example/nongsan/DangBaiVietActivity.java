package thanhdat.example.nongsan;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DangBaiVietActivity extends AppCompatActivity {
    private EditText edttieude,edtmota,edtgia;
    private ImageView imgsanpham;
    private Button btnDangtin,btnTrove,btnImageadd;
    private String tieude,loaibai,tinhthanh,danhmuc,gia,loaigia,mota,hinhanh;
    private TextView tenhinh;
    private String ngaydangbaiviet,username;
    private ProgressBar prgUpload;
    private RadioButton radBan,radMua;
    private List<String> listdanhmuc = new ArrayList<String>();
    private List<String> listdonvi = new ArrayList<String>();
    private List<String> listtinhthanh = new ArrayList<String>();
    private DatabaseReference databaseReference;
    private Dialog dialog;
    private String ImageUrl;
    private Spinner spintinhthanh,spinDanhmuc,spinloaigia;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_bai_viet);
        addControls();
        load_combo();

        spintinhthanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tinhthanh = spintinhthanh.getSelectedItem().toString();
                //  Log.e("aaa",spintinhthanh.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinloaigia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaigia = spinloaigia.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinDanhmuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                danhmuc = spinDanhmuc.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDangtin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });
        btnImageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),255);

            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangBaiVietActivity.this,XemBaiVietActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
    private void load_combo(){
        databaseReference.child("DanhMuc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String tempt = snapshot.getValue(String.class);
                    listdanhmuc.add(tempt);
                }ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(DangBaiVietActivity.this,android.R.layout.simple_list_item_1,listdanhmuc);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinDanhmuc.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("TinhThanh").child("TinhThanh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String tempt= snapshot.getValue(String.class);
                    listtinhthanh.add(tempt);
                }
                ArrayAdapter<String>    arrayAdapter = new ArrayAdapter<String>(DangBaiVietActivity.this,android.R.layout.simple_list_item_1,listtinhthanh);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spintinhthanh.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("DonViTinh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String tempt = snapshot.getValue(String.class);
                    listdonvi.add(tempt);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DangBaiVietActivity.this,android.R.layout.simple_list_item_1,listdonvi);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinloaigia.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getValue(){
        tieude = edttieude.getText().toString();
        gia = edtgia.getText().toString();
        mota = edtmota.getText().toString();
        if(radMua.isChecked()){
            loaibai="Mua";
        }
        else{
            loaibai="Ban";
        }
        ngaydangbaiviet=new java.sql.Date(System.currentTimeMillis()).toString();
    }
    private void addControls() {
        edttieude = findViewById(R.id.edtTieude);
        edtgia = findViewById(R.id.edtGia);
        edtmota = findViewById(R.id.edtmotasanpham);
        btnDangtin = findViewById(R.id.btndangbaiviet);
        btnTrove = findViewById(R.id.btnDangbaivietTrove);
        btnImageadd = findViewById(R.id.btnImage);
        imgsanpham = findViewById(R.id.imgViewbaiviet);
        radBan = findViewById(R.id.radBan);
        radBan.setChecked(true);
        radMua = findViewById(R.id.radMua);
        spinDanhmuc = findViewById(R.id.spindanhmuc);
        spintinhthanh = findViewById(R.id.spintinhthanh);
        spinloaigia = findViewById(R.id.spingia);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        prgUpload = findViewById(R.id.prgUpload);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

    }

    private void uploadImage(){
        Calendar calendar = Calendar.getInstance();
        StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis()+".PNG");
        prgUpload.setVisibility(View.VISIBLE);
        imgsanpham.setDrawingCacheEnabled(true);
        imgsanpham.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imgsanpham.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(DangBaiVietActivity.this, "Lỗi upload! ", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Task<Uri> downloadURL =  taskSnapshot.getMetadata().getReference().getDownloadUrl();

                downloadURL.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageUrl = uri.toString();
                        Log.d("aas",uri.toString());
                        getValue();
                        Baiban baiban = new Baiban(tieude,tinhthanh,danhmuc,mota,gia,ImageUrl,loaigia,ngaydangbaiviet,username);
                        databaseReference.child(loaibai).push().setValue(baiban);
                        Toast.makeText(DangBaiVietActivity.this, "Đăng thành công" , Toast.LENGTH_SHORT).show();
                        prgUpload.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(DangBaiVietActivity.this,XemBaiVietActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);

                    }
                });


                //Toast.makeText(DangBaiVietActivity.this, "Thành heo " + downloadURL, Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==255 && resultCode == RESULT_OK && data.getData() != null){
           // Log.e("image",data.getData().toString());

            Uri uri = data.getData();
            Bitmap bitmap =null;
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgsanpham.setImageBitmap(bitmap);
        }
    }
}
