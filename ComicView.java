package edu.citadel.android.comicviewtester;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ComicView extends ListView {

    // constructors to make compiler and IDE happy
    // allows XML to stylize ComicView
    public ComicView(Context context){
        super(context);
    }
    public ComicView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public ComicView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }
}