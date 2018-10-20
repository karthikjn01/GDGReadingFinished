package apps.evotech.com.gdgreadingfinished;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.EventListener;
import java.util.HashMap;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {


    String TAG = "MainActivity.Java";
    TextView status;
    FloatingActionButton send;
    EditText name;
    HashMap<String, Object> data;
    FirebaseFirestore db;
    DocumentReference document;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameEditText);
        send = findViewById(R.id.sendFloatingActionButton);
        status = findViewById(R.id.statusTextView);

        //make a connection to the DB

        db = FirebaseFirestore.getInstance();
        data = new HashMap<>();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add the data and send to firebase

                status.setText("Sending!");
                data.put("nameOfApp",name.getText().toString());
                data.put("status","Server Recieved");
                data.put("time",System.currentTimeMillis());

                db.collection("loc").add(data).addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(final DocumentReference documentReference) {
                                document = documentReference;
//                                status.setText("Server Received");


                                documentReference.addSnapshotListener(new com.google.firebase.firestore.EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                                        String statusRecieved = documentSnapshot.get("status").toString();
                                        status.setText(statusRecieved);

                                        if(statusRecieved.equalsIgnoreCase("opened!")){
                                            Toast.makeText(MainActivity.this, "Opened on your computer!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                );

            }
        });




    }

}
