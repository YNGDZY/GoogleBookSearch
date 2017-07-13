package com.example.deezy.googlebooksearch;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by DEEZY on 11/07/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Activity context, ArrayList<Book> bookList) {

        super(context, 0, bookList);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Book currentbook = getItem(position);

        TextView titleText = (TextView) listItemView.findViewById(R.id.title);
        String title = currentbook.getmTitle();
        titleText.setText(title);

        TextView subtitleText = (TextView) listItemView.findViewById(R.id.subtitle);
        String subtitle = currentbook.getmSubtitle();
        subtitleText.setText(subtitle);

        TextView authorText = (TextView) listItemView.findViewById(R.id.authors);
        String authors = currentbook.getmAuthors();
        authorText.setText(authors);

        TextView publisherText = (TextView) listItemView.findViewById(R.id.publisher);
        String publisher = currentbook.getmPublisher();
        publisherText.setText(publisher);

        TextView dateText = (TextView) listItemView.findViewById(R.id.publishDate);
        String date = currentbook.getmDate();

        dateText.setText(date);

        TextView pageText = (TextView) listItemView.findViewById(R.id.pages);
        int page = currentbook.getmPages();
        String pageStr = String.valueOf(page);
        pageText.setText(pageStr);

        TextView ratingText = (TextView) listItemView.findViewById(R.id.rating);
        String rating = currentbook.getmRating();

        ratingText.setText(rating);

        TextView descriptionText = (TextView) listItemView.findViewById(R.id.description);
        String description = currentbook.getmDescription();
        descriptionText.setText(description);

        return listItemView;
    }
}
