package edu.citadel.android.comicviewtester;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ComicGetter implements Serializable {

    private static final long serialVersionUID = 5474901498011L;
    private String comicTitle, urlExtension;
    private Bitmap comicBitmap = null;

    // Constructor for a ComicGetter for current
    // date's edition of the input comic
    public ComicGetter(String comicTitle){
        this.comicTitle = comicTitle;
        this.urlExtension = comicTitle
                // strip non-alphanumeric
                .replaceAll("[^A-Za-z0-9]", "")
                .toLowerCase();
    }

    // Constructor for a ComicGetter for input
    // date's edition of the input comic
    public ComicGetter(String comicTitle, int month, int day, int year){
        this.comicTitle = comicTitle;
        this.urlExtension = comicTitle
                // strip non-alphanumeric
                .replaceAll("[^A-Za-z0-9]", "")
                .toLowerCase()
                // add date to URL extension
                + "/" + year + "/"
                + (month > 10 ? month : "0" + month)
                + "/" + (day > 10 ? day : "0" + day);
    }



    // getters and setters for private fields
    public String getComicTitle(){
        return comicTitle;
    }

    public String getUrlExtension(){
        return urlExtension;
    }

    public Bitmap getComicBitmap(){
        return comicBitmap;
    }

    public void setComicBitmap(Bitmap comicBitmap){
        this.comicBitmap = comicBitmap;
    }

    public boolean hasComicBitmap(){
        return comicBitmap != null;
    }

}