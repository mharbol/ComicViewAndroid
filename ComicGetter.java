package edu.citadel.android.comicviewtester;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ComicGetter implements Serializable {

    private static final long serialVersionUID = 5474901498011L;
    private String comicTitle, urlExtension;
    private Bitmap comicBitmap = null;

    public ComicGetter(String comicTitle, String urlExtension){
        this.comicTitle = comicTitle;
        this.urlExtension = urlExtension;
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