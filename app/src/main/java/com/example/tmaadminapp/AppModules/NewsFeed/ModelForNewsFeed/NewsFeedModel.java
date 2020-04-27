package com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed;

public class NewsFeedModel
{
    private String title , description , author , dateAndTime , pushKey;

    public NewsFeedModel(String title, String description, String author, String dateAndTime, String pushKey) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.dateAndTime = dateAndTime;
        this.pushKey = pushKey;
    }

    public NewsFeedModel() {}


    public String getPushKey() {
        return pushKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
}
