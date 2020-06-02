package com.example.tmaadminapp.AppModules.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.tmaadminapp.AppModules.Administration.AdminHome.AdminHome;
import com.example.tmaadminapp.AppModules.InfrastructureHead.InfraHome.InfraHome;
import com.example.tmaadminapp.AppModules.Regulation.RegulationHome;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome.SanitationMain;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.UnionCouncil;
import com.example.tmaadminapp.Models.LoginPresenterImplementer;
import com.example.tmaadminapp.Presenters.LoginPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.LoginView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements LoginView
{

    private TextInputLayout userEmail , userPassword;
    private CheckBox rememberMe;
    private LoginPresenter loginPresenter;
    private ProgressDialog progressBar;
    private String saveUserEmail , saveUserPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        loginPresenter.getUserNameAndPasswordFromSharedPref();

    }

    private void initViews()
    {
        loginPresenter = new LoginPresenterImplementer(this , this);
        userEmail  = findViewById(R.id.emailTextInputLayoutLogin);
        userPassword  = findViewById(R.id.passwordTextInputLayoutLogin);
        rememberMe   = findViewById(R.id.remember_me);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Workers Head");

        progressBar  = new ProgressDialog(this , R.style.MyAlertDialogStyle);

    }


    // login button click
    public void loginBtnClick(View view)
    {
        String email = userEmail.getEditText().getText().toString();
        String password = userPassword.getEditText().getText().toString();

        if(rememberMe.isChecked())
        {
            loginPresenter.savePassword(email , password);
            loginPresenter.login(databaseReference ,mAuth , email , password);
        }
        else
        {
            loginPresenter.login(databaseReference ,mAuth , email , password);
        }


    }


    // login view callbacks methods
    @Override
    public void showProgressBar()
    {
      progressBar.setMessage("Please wait");
      progressBar.setCanceledOnTouchOutside(false);
      progressBar.setCancelable(false);
      progressBar.show();

    }

    @Override
    public void hideProgressBar()
    {
         progressBar.dismiss();
    }

    @Override
    public void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void moveToMainPage()
    {
        Intent intent = new Intent(this,AdminHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);    }

    @Override
    public void goToSanitationHomePage() {

        Intent intent = new Intent(this,SanitationMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToInfraHomePage() {
        Intent intent = new Intent(this,InfraHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToRegulationHomePage() {

        Intent intent = new Intent(this,RegulationHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);    }

    @Override
    public void goToUnionCouncilHomePage() {

        Intent intent = new Intent(this,UnionCouncil.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToFinanceHomePage() {
        Toast.makeText(this, "Not you work", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSaveUserEmailAndPassword(String userEmail, String userPassword)
    {
          saveUserEmail = userEmail;
          saveUserPassword = userPassword;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!saveUserEmail.isEmpty() && !saveUserPassword.isEmpty())
        {
            userEmail.getEditText().setText(saveUserEmail);
            userPassword.getEditText().setText(saveUserPassword);
        }

    }
}
