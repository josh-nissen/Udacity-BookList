package com.android.nissen.udacity_booklist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Josh Nissen on 4/5/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bookSearch = (Button) findViewById(R.id.booksearch);
        final int REQUEST_INTERNET_PERMISSION = 1;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_PERMISSION);
        } else {

            bookSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText searchText = (EditText) findViewById(R.id.search_text);
                    String searchString = searchText.getText().toString();
                    FetchData fetch = new FetchData();
                    AsyncTask<String, Void, ArrayList<ResultStrings>> getJsonData = fetch.execute(searchString);
                    try {
                        ListView lv = (ListView) findViewById(R.id.list);
                        lv.setEmptyView(findViewById(R.id.empty_text));
                        String[] authors = new String[getJsonData.get().size()];
                        for (int i = 0; i < getJsonData.get().size(); i++) {
                            authors[i] = "Name: " + String.valueOf(getJsonData.get().get(i).getTitle()) + "\n" +
                                    "Author: " + String.valueOf(getJsonData.get().get(i).getAuthor()) + "\n" +
                                    "Publisher: " + String.valueOf(getJsonData.get().get(i).getPublisher());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                MainActivity.this,
                                R.layout.result_list_item,
                                R.id.text_1,
                                authors);
                        lv.setAdapter(arrayAdapter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
