package com.example.gurjeet.booklisting;

/**
 * Created by Gurjeet on 11-Feb-17.
 */

public class Book {
    private String mcountry;
    private String mtitle;
    private String mauthor;
    private String murl;
    public Book(String country, String title, String author, String url)
    {   mcountry=country;
        mtitle=title;
        mauthor=author;
        murl=url;
    }

    public String getUrl() {    return murl; }

    public String getCountry() {
        return mcountry;
    }

    public String getTitle() {
        return mtitle;
    }

    public String getAuthor() {
        return mauthor;
    }

}
