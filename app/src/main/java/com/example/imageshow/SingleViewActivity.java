package com.example.imageshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class SingleViewActivity extends AppCompatActivity {
    LinksReader linksReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        // Get intent data
        Intent i = getIntent();

        // Selected image id
        try {
            linksReader = new LinksReader(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int position = i.getExtras().getInt("id");
        int[] arr = new int[linksReader.sumValu.size()];
        ArrayList<Integer> comming = linksReader.sumValu;
        for (int j=0;j<arr.length;j++) {
            arr[j] = comming.get(j);
        }
        MainActivity.CustomGrid imageAdapter = new MainActivity.CustomGrid(this, arr);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageURI(Uri.parse(imageAdapter.path+"/"+imageAdapter.files[position].getName().toString()));
    }
}