package edu.citadel.android.comicviewtester;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class ComicAdapter extends ArrayAdapter<ComicGetter> {

    // constructor that inherits superclass constructor
    public ComicAdapter(Context context, ArrayList<ComicGetter> resource){
        super(context, R.layout.comic_view_layout, resource);
    }

    // overridden method that places comics in the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ComicGetter getter = getItem(position);

        // checking if ComicView is reused, if not them match the
        // comic_view_layout format
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.comic_view_layout, parent, false);
        }

        // get widgets for each component of ComicView item
        TextView comicTitle = (TextView) convertView.findViewById(R.id.comicTitle);
        ImageView comicImage = (ImageView) convertView.findViewById(R.id.comicImage);

        comicTitle.setText(getter.getComicTitle());

        // if the ComicGetter is reused, it has the comicBitmap already
        // saved and will use that
        if (getter.hasComicBitmap()){
            comicImage.setImageBitmap(getter.getComicBitmap());
        }
        // otherwise it uses the AsyncTask DownloadComic to get the image, set
        // the ImageView to the image, and save the Bitmap to the ComicGetter
        else {
            DownloadComic dc = new DownloadComic(comicImage, getter);
            dc.execute(getter.getUrlExtension());
        }

        return convertView;
    }

    // used to get image from the internet and save the Bitmap of the comic
    // to the ComicGetter object for future use.
    private class DownloadComic extends AsyncTask<String, Void, Bitmap> {
        private ImageView iv;
        private ComicGetter getter;

        public DownloadComic(ImageView comic, ComicGetter getter){
            iv = comic;
            this.getter = getter;
        }

        @Override
        protected Bitmap doInBackground(String... urls){
            String url = ComicFinder.getComicSource(urls[0]);
            Bitmap comic = null;
            try{
                InputStream in = new java.net.URL(url).openStream();
                comic = BitmapFactory.decodeStream(in);
            }
            catch (Exception e){}
            return comic;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            iv.setImageBitmap(result);
            getter.setComicBitmap(result);
        }
    }

}
