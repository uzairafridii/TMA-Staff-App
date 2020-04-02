package com.example.tmaadminapp.AppModules.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.Administration.AdminHome.AdminHome;
import com.example.tmaadminapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

    }

    private void initViews()
    {

    }

    public void loginBtnClick(View view)
    {
        startActivity(new Intent(this , AdminHome.class));
    }
}
