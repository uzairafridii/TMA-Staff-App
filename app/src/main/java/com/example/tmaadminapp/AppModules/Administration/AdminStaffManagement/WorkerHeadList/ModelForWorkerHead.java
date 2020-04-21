package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;

public class ModelForWorkerHead
{
    private String name_worker_head , department , pushKey ;

    public ModelForWorkerHead(String name_worker_head, String department, String pushKey) {
        this.name_worker_head = name_worker_head;
        this.department = department;
        this.pushKey = pushKey;

    }

    public ModelForWorkerHead() {}

    public String getName_worker_head() {
        return name_worker_head;
    }

    public String getDepartment() {
        return department;
    }

    public String getPushKey() {
        return pushKey;
    }
}
