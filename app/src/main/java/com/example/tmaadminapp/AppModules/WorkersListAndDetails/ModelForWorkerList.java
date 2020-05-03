package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

public class ModelForWorkerList
{
    private String nameOfWorker ,phone , cnic, field ,pushKey;
    private String average_rating;

    public ModelForWorkerList(String nameOfWorker, String phone,
                              String cnic, String average_rating,
                              String field, String pushKey)
    {
        this.nameOfWorker = nameOfWorker;
        this.phone = phone;
        this.cnic = cnic;
        this.average_rating = average_rating;
        this.field = field;
        this.pushKey = pushKey;
    }

    public ModelForWorkerList() {}


    public String getNameOfWorker() { return nameOfWorker; }

    public String getPhone() { return phone; }

    public String getCnic() { return cnic; }

    public String getAverage_rating() { return average_rating; }

    public String getField() { return field; }

    public String getPushKey() { return pushKey; }
}
