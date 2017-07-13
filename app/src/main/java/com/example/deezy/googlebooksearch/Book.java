package com.example.deezy.googlebooksearch;

import java.util.List;

/**
 * Created by DEEZY on 11/07/2017.
 */

public class Book {
    private String mTitle;
    private String mSubtitle;
    private String mAuthors;
    private String mPublisher;
    private String mDate;
    private int mPages;
    private String mRating;
    private String mDescription;


    public Book(String title, String subtitle, String authors, String publisher,
                String date, int pages, String rating, String description){
        mTitle = title;
        mSubtitle = subtitle;
        mAuthors = authors;
        mPublisher = publisher;
        mDate = date;
        mPages = pages;
        mRating = rating;
        mDescription = description;

    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public String getmAuthors() {
        return mAuthors;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmDate() {
        return mDate;
    }

    public int getmPages() {
        return mPages;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public void setmAuthors(String mAuthors) {
        this.mAuthors = mAuthors;
    }

    public void setmPublisher(String mPublisher) {
        this.mPublisher = mPublisher;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmPages(int mPages) {
        this.mPages = mPages;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}

