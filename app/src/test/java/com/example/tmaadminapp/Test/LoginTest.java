package com.example.tmaadminapp.Test;

import android.content.Context;

import com.example.tmaadminapp.Models.LoginPresenterImplementer;
import com.example.tmaadminapp.Presenters.LoginPresenter;
import com.example.tmaadminapp.Views.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest
{


    @Mock
    LoginView loginInView;
    @Mock
    LoginPresenter presenterImplementer;
    FirebaseAuth firebaseAuth;
    Context context;
    DatabaseReference dbRef;

    @Before
    public void setUp() throws Exception {
        presenterImplementer = new LoginPresenterImplementer(loginInView, context);
    }


    @Test
    public void shouldFailedToSignInIfPassIncompleteData() throws Exception {

        presenterImplementer.login(dbRef, firebaseAuth, "","");
        verify(loginInView).showMessage("Please enter email and password");
    }





}
