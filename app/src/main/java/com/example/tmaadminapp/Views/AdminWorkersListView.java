package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage.ModelForWorkerLIstInAdmin;

import java.util.List;

public interface AdminWorkersListView
{
    void getWorkerList(List<ModelForWorkerLIstInAdmin> list);
}
