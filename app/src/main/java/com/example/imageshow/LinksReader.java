package com.example.imageshow;

import android.content.Context;

import androidx.annotation.RawRes;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class LinksReader {
    ArrayList<String> arL;
    ArrayList<Integer> sumValu = new ArrayList<>();
    Context context;
    public LinksReader(Context c) throws Exception {
        this.context = c;
        String str = readRawResource(R.raw.dog_urls);

//        String str = readFileAsString("dog_urls.json");

        String[] list = str.split(",");
        List<String> dataList = Arrays.asList(list);
        arL = new ArrayList<>(dataList);
        arL.set(0, arL.get(0).split("\\[")[1]);
        arL.set(arL.size()-1, arL.get(arL.size()-1).split("\\]")[0]);
        for (int i = 0; i < arL.size(); i++) {
            arL.set(i, arL.get(i).trim().replaceAll("^\"|\"$", ""));
            String[] numArr = arL.get(i).split("/");
            String num = numArr[numArr.length - 1].split("_")[0];
            int digit = 0;
            for (int j = 1; j < num.length(); j++) {
                digit += Character.getNumericValue(num.charAt(j));
            }
            sumValu.add(digit);
        }
        String s = arL.get(0).trim().replaceAll("^\"|\"$", "");
//        URL url = new URL(s);
    }

    public String readRawResource(@RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    private String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}