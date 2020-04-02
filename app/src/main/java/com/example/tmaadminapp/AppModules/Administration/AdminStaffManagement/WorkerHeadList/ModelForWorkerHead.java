package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;

public class ModelForWorkerHead
{
    private String nameOfWorkerHead , fieldOfWorkerHead;
    //private int image;


    public ModelForWorkerHead(String nameOfWorkerHead, String fieldOfWorkerHead) {
        this.nameOfWorkerHead = nameOfWorkerHead;
        this.fieldOfWorkerHead = fieldOfWorkerHead;
    }

    public String getNameOfWorkerHead() {
        return nameOfWorkerHead;
    }

    public void setNameOfWorkerHead(String nameOfWorkerHead) {
        this.nameOfWorkerHead = nameOfWorkerHead;
    }

    public String getFieldOfWorkerHead() {
        return fieldOfWorkerHead;
    }

    public void setFieldOfWorkerHead(String fieldOfWorkerHead) {
        this.fieldOfWorkerHead = fieldOfWorkerHead;
    }
}
