package com.android.nissen.udacity_booklist;

/**
 * Created by Josh Nissen on 4/5/2017.
 */

public class ResultStrings {

    private String mTitle = "";
    private String mAuthor = "";
    private String mPublisher = "";

    public ResultStrings(String title, String author, String publisher) {
        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
    }
    // Getter Methods
    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublisher() {
        return mPublisher;
    }

}
