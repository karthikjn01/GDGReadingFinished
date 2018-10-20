package apps.evotech.com.gdgreading;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    String TAG = "MainActivity.Java";
    TextView status;
    FloatingActionButton send;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameEditText);
        send = findViewById(R.id.sendFloatingActionButton);
        status = findViewById(R.id.statusTextView);





    }

}
