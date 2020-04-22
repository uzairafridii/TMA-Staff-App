package com.example.tmaadminapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tmaadminapp.Presenters.LoginPresenter;
import com.example.tmaadminapp.Views.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenterImplementer implements LoginPresenter {
    private LoginView loginView;

    public LoginPresenterImplementer(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(final DatabaseReference dbRef, final FirebaseAuth mAuth, final String email, String password) {
        if (mAuth != null && !email.isEmpty() && !password.isEmpty()) {
            loginView.showProgressBar();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        if (email.equals("admin@gmail.com"))
                        {
                            loginView.moveToMainPage();
                            loginView.hideProgressBar();
                            loginView.showMessage("Successfully login");
                        }
                        else
                            {
                            String currentUser = mAuth.getCurrentUser().getUid();
                            Query query = dbRef.child(currentUser);

                            query.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String role = dataSnapshot.child("department").getValue().toString();
                                    Log.d("roleOfUser", "onDataChange: " + role);


                                    if (role.equals("Sanitation")) {
                                        loginView.goToSanitationHomePage();
                                        loginView.hideProgressBar();
                                        loginView.showMessage("Successfully login");
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                    }

                }
            });


        } else {
            loginView.showMessage("Please enter email and password");
        }

    }

    @Override
    public void savePassword(String userEmail, String userPassword) {

    }
}
