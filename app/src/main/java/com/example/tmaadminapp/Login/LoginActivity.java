package com.example.tmaadminapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.Administration.AdminHome.AdminHome;
import com.example.tmaadminapp.InfrastructureHead.InfraHome.InfraHome;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.SanitationHome.SanitationMain;

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
