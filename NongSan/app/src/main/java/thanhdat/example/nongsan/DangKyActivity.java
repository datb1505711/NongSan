package thanhdat.example.nongsan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtusername,edtpassword,edthoten,edtsdt,edtngaysinh,
        edtdiachi,edttencongty,
            edtsdtcongty,edtdiachicty,edtwebcty;
    private String username,password,hoten,sdt,ngaysinh,
            gioitinh,diachi,tencongty,
            sdtcongty,diachicty,webcty,avatar;
    private DatabaseReference databaseReference;
    private Button btnDangky,btnTrove,btnchooseAvt;
    private ImageView imageViewavt;
    private CheckBox chkShowRelative;
    private RadioButton radNam,radNu;
    private RelativeLayout relCty;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();

    private void checkRadio(){
        if(radNam.isChecked()==true){
            gioitinh = "Nam";
        }
        else{
            gioitinh= "Nu";
        }
    }

    private void addControls() {
        edtusername = (EditText) findViewById(R.id.edtUsername);
        edtpassword = (EditText) findViewById(R.id.edtPassword);
        edthoten = (EditText) findViewById(R.id.edtHoten);
        edtsdt = (EditText) findViewById(R.id.edtSdt);
        edtngaysinh = (EditText) findViewById(R.id.edtNgaysinh);
        radNam = (RadioButton) findViewById(R.id.radNam);
        radNam.setChecked(true);
        radNu = (RadioButton) findViewById(R.id.radNu);
        checkRadio();
        edtdiachi = (EditText) findViewById(R.id.edtDiachi);
        edttencongty = (EditText) findViewById(R.id.edtTencty);
        edtdiachicty = (EditText) findViewById(R.id.edtDiachicty);
        edtsdtcongty = (EditText) findViewById(R.id.edtSdtCty);
        edtwebcty = (EditText) findViewById(R.id.edtWebsitecty);
        btnDangky = (Button) findViewById(R.id.btnDangkytaikhoan);
        btnTrove = (Button) findViewById(R.id.btnTrove);
        relCty = (RelativeLayout) findViewById(R.id.Cty);
        chkShowRelative = (CheckBox) findViewById(R.id.chkCty);
        btnchooseAvt = findViewById(R.id.btnChooseAvatar);
        imageViewavt = findViewById(R.id.imageAvatar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
    private void getValue(){
        username = edtusername.getText().toString();
        password =edtpassword.getText().toString();
        hoten =edthoten.getText().toString();
        sdt = edtsdt.getText().toString();
        ngaysinh = edtngaysinh.getText().toString();
        diachi = edtdiachi.getText().toString();
        tencongty = edttencongty.getText().toString();
        sdtcongty = edtsdtcongty.getText().toString();
        diachicty = edtdiachicty.getText().toString();
        webcty = edtwebcty.getText().toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        btnchooseAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),113);
            }
        });
        chkShowRelative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    relCty.setVisibility(View.VISIBLE);
                }
                else {
                    relCty.setVisibility(View.GONE);
                }
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis()+".PNG");
                imageViewavt.setDrawingCacheEnabled(true);
                imageViewavt.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageViewavt.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(DangKyActivity.this, "Lỗi upload! ", Toast.LENGTH_SHORT).show();
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
                                avatar = uri.toString();
                                Log.d("aas",uri.toString());
                                getValue();
                                User user = new User(password,hoten,sdt,ngaysinh,diachi,gioitinh,tencongty,diachicty,sdtcongty,webcty,avatar);
                                databaseReference.child("User").child(username).setValue(user);
                                Toast.makeText(DangKyActivity.this, "Đăng ký thành công" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                                startActivity(intent);


                            }
                        });


                        //Toast.makeText(DangBaiVietActivity.this, "Thành heo " + downloadURL, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode ==113 && resultCode == RESULT_OK && data.getData() != null){
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
            imageViewavt.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
