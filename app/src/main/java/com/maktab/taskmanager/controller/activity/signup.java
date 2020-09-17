package com.maktab.taskmanager.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.maktab.taskmanager.R;

import static android.widget.Toast.LENGTH_LONG;


public class signup extends AppCompatActivity {
    public static final String EXTRA_LOGIN_PASS = "com.maktab.loginprojeh.loginpass";
    Toolbar mToolbar;
    String name, pass, sUsername, uPassword;
    TextView txt_name, txt_pass;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        this.setTitle("SignUp");
        findview();
        name = getIntent().getStringExtra("name");
        pass = getIntent().getStringExtra("pass");
        txt_name.setText(name);
        txt_pass.setText(pass);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sUsername = txt_name.getText().toString();
                uPassword = txt_pass.getText().toString();
                if (sUsername.matches("") || uPassword.matches("")) {
                    Toast.makeText(signup.this, "You did not enter a username or password", LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(signup.this, login.class);
                intent.putExtra("namesignup", txt_name.getText().toString());
                intent.putExtra("passlogin", txt_pass.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    private void findview() {
        txt_name = findViewById(R.id.txt_name);
        txt_pass = findViewById(R.id.txt_pass);
        btn_signup = findViewById(R.id.btn_login);
    }
}




