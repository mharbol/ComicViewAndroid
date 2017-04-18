package edu.citadel.android.comicviewtester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ComicView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = (ComicView) findViewById(R.id.activity_main);

        ArrayList<String> comics = new ArrayList<>();
        comics.add("Calvin and Hobbes");
        comics.add("Pearls Before Swine");
        comics.add("Wizard of Id");
        comics.add("B.C.");
        comics.add("Garfield");
        comics.add("Peanuts");
        comics.add("Non Sequitur");
        comics.add("Andy Capp");

        cv.addComics(comics, 12, 6, 2013);

    }
}