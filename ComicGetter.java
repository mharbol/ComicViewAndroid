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
    private String comicTitle;
    private String[] urlExt;

    private int month, day, year = -1;

    // Constructor for a ComicGetter for current
    // date's edition of the input comic
    public ComicGetter(String comicTitle){
        this(comicTitle, 0, 0, -1);
    }

    // Constructor for a ComicGetter for input
    // date's edition of the input comic
    public ComicGetter(String comicTitle, int month, int day, int year){
        this.comicTitle = comicTitle;
        this.month = month;
        this.day = day;
        this.year = year;
        if (year == -1)
            urlExt = makeUrlExtension(comicTitle);
        else {
            String[] ext = makeUrlExtension(comicTitle);
            for (int x = 0; x < ext.length; x++) {
                ext[x] = ext[x] + ("/" + year + "/"
                        + (month > 9 ? month : "0" + month)
                        + "/" + (day > 9 ? day : "0" + day));
            }
            urlExt = ext;
        }
    }



    // getters and setters for private fields
    public String getComicTitle(){
        return comicTitle;
    }

    public String[] getUrlExtension(){
        return urlExt;
    }

    public Bitmap getComicBitmap(){
        return bitmapHashMap.get(urlExt[0]);
    }

    public void setComicBitmap(Bitmap comicBitmap){
        bitmapHashMap.put(urlExt[0], comicBitmap);
    }

    public boolean hasComicBitmap(){
        return bitmapHashMap.get(urlExt[0]) != null;
    }

    // used to make URL extension from comic title
    // used in ComicFinder class as well
    public static String[] makeUrlExtension(String comicTitle){
        return new String[] {
                comicTitle
                        // change special characters to String
                        .replaceAll("@", "at")
                        // strip non-alphanumeric
                        .replaceAll("[^A-Za-z0-9]", "")
                        .toLowerCase(),
                comicTitle
                        // change special characters to String
                        .replaceAll("@", "at")
                        // replace spaces with dashes
                        .replaceAll(" ", "-")
                        // strip non-alphanumeric or dashes
                        .replaceAll("[^A-Za-z0-9-]", "")
                        .toLowerCase()
        };
    }
}