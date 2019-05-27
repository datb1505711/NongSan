package thanhdat.example.nongsan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagingActivity extends AppCompatActivity {
    private String username,date,name,namee;
    private EditText editText;
    private Button btnSend;
    private ListView listView;
    private DatabaseReference databaseReference;
    private TextView txtNamee;
    private ImageButton imgbtnback;
    private int i=0;
    private int[] resource;
    private MessageAdapter messageAdapter;
    private List<Message> listMess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        addcontrols();
        addData();


        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
               v.setFocusableInTouchMode(true);
                return false;
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date().toString();
                if(editText.getText().toString().equals("")){
                }
                else{
                Message message = new Message(editText.getText().toString(),username);
                databaseReference.child("Chat").child(username).child(name).child(date).setValue(message);
                databaseReference.child("Chat").child(name).child(username).child(date).setValue(message);
                Log.d("1111a", username + name);
                editText.setText("");

            }}
        });
        txtNamee.setText(namee);
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        databaseReference.child("Chat").child(username).child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMess = new ArrayList<>();
                resource = new int[9999];
                i=0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Message mess = snapshot.getValue(Message.class);
                    if(mess.getName().equals(username)) {
                        resource[i] = 1;
                    }
                    else{
                        resource[i] = 0;
                    }
                    listMess.add(mess);
                    i++;
                }
                messageAdapter = new MessageAdapter(MessagingActivity.this,resource, listMess);
                listView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addData() {

    }

    private void addcontrols() {
        Intent intent =getIntent();
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        namee = intent.getStringExtra("namee");
        btnSend = findViewById(R.id.btnSend);
        listView = findViewById(R.id.lstmess);
        editText = findViewById(R.id.edtmessage);
        imgbtnback = findViewById(R.id.imgbtnBack);
        txtNamee = findViewById(R.id.txtNamee);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
