package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.ModelForWorkerHead;

import java.util.List;

public interface WorkerHeadView {

    void onShowProgressBar();

    void onHideProgressBar();

    void showMessage(String message);

    void onGetAllWorkerHeadDetails(List<ModelForWorkerHead> workerHeadList);

    void onShowSignUpDialog();

    void onClearAllFields();

}
