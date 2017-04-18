package edu.citadel.android.comicviewtester;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.ArrayList;


public class ComicView extends ListView {

    private ArrayList<ComicGetter> comicGetters;


    // Takes an ArrayList<String> of comic titles and adds them
    // to the ComicView
    public void addComics(ArrayList<String> comics){

        // make ArrayList of ComicGetters to add to ListView
        comicGetters = new ArrayList<>();
        for (String comicName : comics)
            comicGetters.add(new ComicGetter(comicName));

        addComicGetters();
    }

    // Takes an ArrayList<String> of comic titles and dates and adds
    // them to the ComicView
    public void addComics(ArrayList<String> comics, int month, int day, int year){
        // make ArrayList of ComicGetters to add to ListView
        comicGetters = new ArrayList<>();
        for (String comicName : comics)
            comicGetters.add(new ComicGetter(comicName, month, day, year));

        addComicGetters();
    }

    private void addComicGetters(){
        this.setAdapter(new ComicAdapter(this.getContext(), comicGetters));
    }

    // Custom View version of saving and restoring instance state
    @Override
    public Parcelable onSaveInstanceState(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putSerializable("comicGetters", comicGetters);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable inState){
        if (inState instanceof Bundle){
            Bundle bundle = (Bundle) inState;
            comicGetters = (ArrayList<ComicGetter>) bundle.getSerializable("comicGetters");
            addComicGetters();
            inState = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(inState);
    }


    // constructors to make compiler and IDE happy
    // allows XML to stylize ComicView
    public ComicView(Context context){
        this(context, null, 0);
    }
    public ComicView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public ComicView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }
}