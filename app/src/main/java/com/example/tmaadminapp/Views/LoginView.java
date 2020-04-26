package com.example.tmaadminapp.Views;

public interface LoginView
{
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void moveToMainPage();

    void goToSanitationHomePage();

    void goToInfraHomePage();

    void goToRegulationHomePage();

    void goToUnionCouncilHomePage();

    void goToFinanceHomePage();

    void getSaveUserEmailAndPassword(String userEmail, String userPassword);


}
