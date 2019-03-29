package com.example.a40769.myapplication123;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

/**
 * Created by 40769 on 2018/12/7.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "login";
    Button loginBtn = null;
    EditText useridEt = null;
    EditText passEt = null;
    TextView promptText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        useridEt = (EditText) findViewById(R.id.userId);
        passEt = (EditText) findViewById(R.id.pass);
        promptText = (TextView) findViewById(R.id.promptText);

    }


    @Override
    public void onClick(View v) {
        String userid = useridEt.getText().toString().trim();
        String pass = passEt.getText().toString().trim();
        if(userid.equals("")){
            promptText.setText(R.string.userIdError);
            return ;
        }
        if(pass.equals("")){
            promptText.setText(R.string.passError);
            return ;
        }
        if (userid.equals("1") && pass.equals("1")) {
            Toast.makeText(this, R.string.loginSuccess, Toast.LENGTH_LONG).show();
            Intent intent_hello = new Intent(LoginActivity.this, Location.class);
            startActivity(intent_hello);
            finish();
        } else {
            Toast.makeText(this, R.string.loginError, Toast.LENGTH_LONG).show();
        }

    }

}