package thanhdat.example.nongsan;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class XemBaiVietActivity extends AppCompatActivity {
    private ListView listviewUser;
    private UserAdapter userAdapter;
    private List<User> listUser;
    private DatabaseReference databaseReference;
    private String username;
    private int i =0;
    private String[] ID,namee,name;
    private EditText edtSearch;
    private GridView gridviewban;
    private List<Baiban> listbaiban;
    private AdapterBaiban gridarrayadapter;
    private Button btnchangeStatus;
    private Button btnChatwithadmin;
    private ImageButton imgbtnCreate,imgbtnSearch;
    private RelativeLayout rel1,rel2,rel3,rel4;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    rel1.setVisibility(View.VISIBLE);
                    rel2.setVisibility(View.GONE);
                    btnChatwithadmin.setVisibility(View.GONE);
                    imgbtnCreate.setVisibility(View.VISIBLE);
                    Home();
                    return true;
                case R.id.navigation_contact:
                    rel1.setVisibility(View.GONE);
                    rel2.setVisibility(View.VISIBLE);
                    btnChatwithadmin.setVisibility(View.GONE);
                    imgbtnCreate.setVisibility(View.GONE);
                    Contact();
                    return true;
                case R.id.navigation_notifier:
                    return true;
                case R.id.navigation_setting:
                    return true;
            }
            return false;
        }
    };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_xem_bai_viet);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            addControls();
            addData();
            Home();
            rel1.setVisibility(View.VISIBLE);
            rel2.setVisibility(View.GONE);
            imgbtnCreate.setVisibility(View.VISIBLE);
            btnchangeStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("aaasad",btnchangeStatus.getText().toString() + "asd1");
                    if(btnchangeStatus.getText().toString()==("BÀI MUA")){
                        btnchangeStatus.setText("BÀI BÁN");
                        Log.d("aaasad",btnchangeStatus.getText().toString() + "asd2");
                    }
                    else{
                        btnchangeStatus.setText("BÀI MUA");
                        Log.d("aaasad",btnchangeStatus.getText().toString() + "asd3");
                    }
                }
            });
        }

    private void Contact() {
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUser = new ArrayList<>();
                i=0;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    // Log.e("asddd",snapshot.getKey() );
                    // Log.e("aaa",username);
                    if (snapshot.getKey().equals(username)){
                        continue;
                    }
                    else {
                        User user = snapshot.getValue(User.class);
                        name[i] = snapshot.getKey();
                        namee[i] = user.getHoten();
                        Log.e("adss",namee[i] + " "+name[i]);
                        listUser.add(user);
                        i++;
                    }
                }
                userAdapter = new UserAdapter(XemBaiVietActivity.this,R.layout.layout_user,listUser);
                listviewUser.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listviewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact();
                Intent intent = new Intent(XemBaiVietActivity.this,MessagingActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("name",name[position]);
                intent.putExtra("namee",namee[position]);
              // Log.d( "datt", namee[position] + name[position]);
                startActivity(intent);
            }
        });
    }

    private void Home() {

        imgbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemBaiVietActivity.this,DangBaiVietActivity.class);
                intent.putExtra("username",username);
                //Toast.makeText(XemBaiVietActivity.this, username, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        gridviewban.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XemBaiVietActivity.this,DetailBaiVietActitivy.class);
                intent.putExtra("idbaiban",ID[position]);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
            }
        });
        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void addData(){

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Ban").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listbaiban= new ArrayList<>();
                    i=0;
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                        ID[i] = snapshot.getKey();
                        Baiban baiban = snapshot.getValue(Baiban.class);

                        listbaiban.add(baiban);
                        i++;
                        //  Log.e("aaa",listbaiban.getClass().toString());
                    }
                    gridarrayadapter = new AdapterBaiban(XemBaiVietActivity.this,R.layout.layout_gridviewxembaiviet,listbaiban);

                    gridviewban.setAdapter(gridarrayadapter);
                    Log.e("aaa",gridviewban.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        private void addControls() {
            ID = new String[9999];
            namee = new String[9999];
            name = new String[9999];
            imgbtnSearch = findViewById(R.id.btnsearch);
            edtSearch = (EditText) findViewById(R.id.edtSearch);
            gridviewban = (GridView) findViewById(R.id.GridviewBan);
            btnchangeStatus = (Button) findViewById(R.id.btnchangeStatus);
            btnchangeStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btnchangeStatus.getText()=="BÀI MUA"){
                        btnchangeStatus.setText("BÀI BÁN");
                    }
                    else{
                        btnchangeStatus.setText("BÀI MUA");
                    }
                }
            });
            imgbtnCreate = findViewById(R.id.imgbtnDangbaiviet);

            btnChatwithadmin = findViewById(R.id.btnChatwithAdmin);
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
            // Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show();
            btnchangeStatus = findViewById(R.id.btnChatwithAdmin);
            rel1 = findViewById(R.id.TopRelative1);
            rel2 = findViewById(R.id.TopRelative2);

            listviewUser = findViewById(R.id.ListUser);


        }
    }


