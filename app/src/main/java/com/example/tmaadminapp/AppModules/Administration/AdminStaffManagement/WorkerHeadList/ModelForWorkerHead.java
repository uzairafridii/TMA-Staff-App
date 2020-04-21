package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;

public class ModelForWorkerHead
{
    private String name_worker_head , worker_head_field;



    public ModelForWorkerHead(String nameOfWorkerHead, String fieldOfWorkerHead) {
        this.name_worker_head = nameOfWorkerHead;
        this.worker_head_field = fieldOfWorkerHead;
    }

    public String getNameOfWorkerHead() {
        return name_worker_head;
    }

    public void setNameOfWorkerHead(String nameOfWorkerHead) {
        this.name_worker_head = nameOfWorkerHead;
    }

    public String getFieldOfWorkerHead() {
        return worker_head_field;
    }

    public void setFieldOfWorkerHead(String fieldOfWorkerHead) {
        this.worker_head_field = fieldOfWorkerHead;
    }
}
