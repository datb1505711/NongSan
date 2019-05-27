package thanhdat.example.nongsan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ListView listviewUser;
    private UserAdapter userAdapter;
    private List<User> listUser;
    private DatabaseReference databaseReference;
    private String username;
    private int i =0;
    private String[] ID,namee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        addControls();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                   // Log.e("asddd",snapshot.getKey() );
                   // Log.e("aaa",username);
                    if (snapshot.getKey().equals(username)){
                        continue;
                    }
                    else {
                        User user = snapshot.getValue(User.class);
                        ID[i] = snapshot.getKey();
                        namee[i] = user.getHoten();
//                        Log.e("adss",namee[i] + " "+ID[i]);
                        listUser.add(user);
                        i++;
                    }
                }
                userAdapter = new UserAdapter(UserActivity.this,R.layout.layout_user,listUser);
                listviewUser.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listviewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserActivity.this,MessagingActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("name",ID[position]);
                intent.putExtra("namee",namee[position]);
                startActivity(intent);
            }
        });
    }


    private void addControls() {
        ID = new String[9999];
        namee = new String[9999];
        databaseReference = FirebaseDatabase.getInstance().getReference();
        listviewUser = findViewById(R.id.ListUser);
        listUser = new ArrayList<>();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }
}
