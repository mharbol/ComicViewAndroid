package edu.citadel.android.dailycomic;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.HashMap;


public class ComicGetter implements Serializable {

    // storing Bitmaps in static field allows ComicGetter objects
    // to be much smaller and allows more to be added to a Bundle
    // or Parcel
    private static HashMap<String, Bitmap> bitmapHashMap = new HashMap<>();

    private static final long serialVersionUID = 5474901498011L;
    private String comicTitle, urlExtension;

    // Constructor for a ComicGetter for current
    // date's edition of the input comic
    public ComicGetter(String comicTitle){
        this.comicTitle = comicTitle;
        this.urlExtension = makeUrlExtension(comicTitle);
    }

    // Constructor for a ComicGetter for input
    // date's edition of the input comic
    public ComicGetter(String comicTitle, int month, int day, int year){
        this.comicTitle = comicTitle;
        this.urlExtension = makeUrlExtension(comicTitle)
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
        return bitmapHashMap.get(urlExtension);
    }

    public void setComicBitmap(Bitmap comicBitmap){
        bitmapHashMap.put(urlExtension, comicBitmap);
    }

    public boolean hasComicBitmap(){
        return bitmapHashMap.get(urlExtension) != null;
    }

    // used to make URL extension from comic title
    // used in ComicFinder class as well
    public static String makeUrlExtension(String comicTitle){
        return comicTitle
                // change special characters to String
                .replaceAll("@", "at")
                // strip non-alphanumeric
                .replaceAll("[^A-Za-z0-9]", "")
                .toLowerCase();
    }
}