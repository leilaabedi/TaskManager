package com.maktab.taskmanager.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.maktab.taskmanager.R;

import static android.widget.Toast.LENGTH_LONG;

public class login extends AppCompatActivity {

    Toolbar mToolbar;
    TextView txt_name, txt_pass;
    Button btn_login, btn_signup;
    String name, pass;
    static String[] member = new String[20];
    static int index = 0;
    LinearLayout layoutSnack;

    public static final String EXTRA_SIGNUP_NAME = "com.maktab.loginprojeh.signupname";
    public static final String EXTRA_SIGNUP_PASS = "com.maktab.loginprojeh.signuppass";

    public static final int REQUEST_CODE_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        this.setTitle("Login");
        findview();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                intent.putExtra("name", txt_name.getText().toString());
                intent.putExtra("pass", txt_pass.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_SIGNUP);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                boolean flag = false;
                for (i = 0; i < index; i += 2) {
                    if ((member[i].equals(txt_name.getText().toString())) && (member[i + 1].equals(txt_pass.getText().toString()))) {
                      //  Snackbar snkbr = Snackbar.make(layoutSnack, "You have successfully logged in ",
                        //        Snackbar.LENGTH_LONG);
                        //snkbr.show();

                        Intent intent = new Intent(login.this, TaskListActivity.class);
                       // intent.putExtra("name", txt_name.getText().toString());
                        //intent.putExtra("pass", txt_pass.getText().toString());
                        startActivity(intent);




                        flag = true;
                        break;
                    }
                }

                if (flag == false) {
                    Toast.makeText(login.this, "Invalid username or password", LENGTH_LONG).show();
                }

            }
        });

    }


    private void findview() {
        txt_name = findViewById(R.id.txt_name);
        txt_pass = findViewById(R.id.txt_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_sign);
        layoutSnack = findViewById(R.id.layout1);
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGNUP) {
            name = data.getStringExtra("namesignup");
            pass = data.getStringExtra("passlogin");
            member[index] = name;
            member[index + 1] = pass;
            index += 2;
            txt_name.setText(name);
            txt_pass.setText(pass);
        }
    }


}