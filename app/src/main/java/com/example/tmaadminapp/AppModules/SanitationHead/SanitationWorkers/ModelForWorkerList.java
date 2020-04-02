package com.example.tmaadminapp.AppModules.SanitationHead.SanitationWorkers;

public class ModelForWorkerList
{
    private String nameOfWorker;
   // private String imageUrl;
    private float rating;

    public ModelForWorkerList(String nameOfWorker , float rating) {
        this.nameOfWorker = nameOfWorker;
       // this.imageUrl = imageUrl;
        this.rating = rating;
    }

    public String getNameOfWorker() {
        return nameOfWorker;
    }

    public void setNameOfWorker(String nameOfWorker) {
        this.nameOfWorker = nameOfWorker;
    }



   /* public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }*/

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
