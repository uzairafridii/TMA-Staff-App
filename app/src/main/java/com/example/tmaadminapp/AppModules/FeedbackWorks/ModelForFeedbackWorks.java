package com.example.tmaadminapp.AppModules.FeedbackWorks;

import java.util.List;

public class ModelForFeedbackWorks
{
    private String completedDate, firstWorker , secondWorker , title , pushKey;
    private List<String> imageUrl;

    public ModelForFeedbackWorks(){}

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public String getFirstWorker() {
        return firstWorker;
    }

    public String getSecondWorker() {
        return secondWorker;
    }

    public String getTitle() {
        return title;
    }

    public String getPushKey() {
        return pushKey;
    }
}
