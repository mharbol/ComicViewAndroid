package edu.citadel.android.comicviewtester;

import java.net.URL;
import java.util.Scanner;

public class ComicFinder {

    private static final String COMICS_URL = "http://www.gocomics.com/";
    private static final String OBVIOUS_IDENTIFIER = "img-fluid item-comic-image";
    private static final String SRC_TAG = "src=\"";
    private static final int    TAG_LEN = SRC_TAG.length();

    // returns today's comic image source URL as a String from gocomics.com
    // returns null if cannot find the source
    public static String getComicSource(String comicName) {

        // initialize Strings
        String comicPage = COMICS_URL + comicName;

        try {

            // read lines from the comic page source
            URL url = new URL(comicPage);
            Scanner comicSourceStream = new Scanner(url.openStream());

            while (comicSourceStream.hasNext()) {
                // find line that has OBVIOUS_IDENTIFIER since source for
                // the comic comes after that
                String line = comicSourceStream.nextLine();
                if (line.contains(OBVIOUS_IDENTIFIER)) {

                    // loop through lines until find SRC_TAG
                    do {
                        if (line.contains(SRC_TAG)) {

                            // find open quote after SRC_TAG
                            int start = line.indexOf(SRC_TAG) + TAG_LEN;
                            int end = line.indexOf("\"", start);
                            comicSourceStream.close();
                            return line.substring(start, end);
                        }

                        line = comicSourceStream.nextLine();

                    } while (comicSourceStream.hasNext());

                }
            }
            comicSourceStream.close();
        }

        catch (Exception e) {
            // System.out.println("URL " + comicPage + " not found");

        }

        // if not find, return null
        return null;
    }

    // returns the specified date's comic image source URL as a String from
    // gocomics.com. Returns null if cannot find the source
    public static String getComicSource(String comicName, int month, int day, int year) {
        return getComicSource(comicName + "/" + year + "/"
                + (month > 10 ? month : "0" + month)
                + "/" + (day > 10 ? day : "0" + day));
    }
}
