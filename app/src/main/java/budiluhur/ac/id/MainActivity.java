package budiluhur.ac.id;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private TextView method,status,role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.txtUsername);
        password = (EditText)findViewById(R.id.txtPassword);
        method = (TextView)findViewById(R.id.method);
        status = (TextView)findViewById(R.id.status);
        role = (TextView)findViewById(R.id.role);
    }

    public void LoginPost(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        method.setText("Post Method");
        new SigninActivity(this,status,role,0).execute(user,pass);
    }

    public void LoginGet(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        method.setText("Get Method");
        new SigninActivity(this,status,role,0).execute(user,pass);
    }
}
