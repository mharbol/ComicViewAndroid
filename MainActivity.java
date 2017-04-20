package edu.citadel.android.dailycomic;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ComicView cv;
    private ArrayList<String> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = (ComicView) findViewById(R.id.activity_main);

        comics = new ArrayList<>();
        comics.add("Calvin and Hobbes");
        comics.add("Pearls Before Swine");
        comics.add("Wizard of Id");
        comics.add("B.C.");
        comics.add("Garfield");
        comics.add("Peanuts");
        comics.add("Non Sequitur");
        comics.add("Andy Capp");

        cv.addComics(comics);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.select_date_item){
            updateComicViewDate();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    private void updateComicViewDate(){
        Date currentDate = new Date();
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cv.addComics(comics, view.getMonth() + 1, view.getDayOfMonth(), view.getYear());
            }
        },currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDate());
        dpd.show();
    }
}