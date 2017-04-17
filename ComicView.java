package edu.citadel.android.comicviewtester;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ComicView extends ListView {



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