package com.example.tmaadminapp.Presenters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public interface LoginPresenter
{

    void login(DatabaseReference dbRef ,FirebaseAuth mAuth , String userEmail , String userPassword);

    void savePassword(String userEmail , String userPassword);

    void getUserNameAndPasswordFromSaveDb();


}
