package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage;

public class ModelForWorkerLIstInAdmin
{
    private String workerName  , field;
    private float ratingGrade;
   // private int image;

    public ModelForWorkerLIstInAdmin(String workerName, String field, float ratingGrade) //int image)
     {
        this.workerName = workerName;
        this.field = field;
        this.ratingGrade = ratingGrade;
      //  this.image = image;
    }


    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public float getRatingGrade() {
        return ratingGrade;
    }

    public void setRatingGrade(float ratingGrade) {
        this.ratingGrade = ratingGrade;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

  /*  public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }*/
}
