package edu.citadel.android.comicviewtester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<ComicGetter> cgal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // originally made with array but ArrayList will be more practical
        ComicGetter[] cg = {new ComicGetter("Calvin and Hobbes", "calvinandhobbes"),
                new ComicGetter("Pearls Before Swine", "pearlsbeforeswine"),
                new ComicGetter("Wizard of Id", "wizardofid"),
                new ComicGetter("B.C.", "bc"),
                new ComicGetter("Garfield", "garfield"),
                new ComicGetter("Peanuts", "peanuts"),
                new ComicGetter("Non Sequitur", "nonsequitur"),
                new ComicGetter("Andy Capp", "andycapp")};

        cgal = new ArrayList<>();
        for (ComicGetter c : cg)
            cgal.add(c);

        // setting the adapter for the ListView.  In the future this should
        // be its own ComicView object that does this all automatically.
        lv = (ListView) findViewById(R.id.activity_main);

        lv.setAdapter(new ComicAdapter(this, cgal));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putSerializable("cgal", cgal);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        cgal = (ArrayList<ComicGetter>) inState.getSerializable("cgal");
        lv.setAdapter(new ComicAdapter(this, cgal));
    }

}