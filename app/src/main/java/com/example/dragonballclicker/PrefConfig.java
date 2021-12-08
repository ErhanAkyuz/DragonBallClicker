package com.example.dragonballclicker;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefConfig {

    private static final String LIST_KEY = "list_key";
    private static final String PATH = Environment.getExternalStorageDirectory() + "/DragonBallCliker/save.json";

    public static void writeListInPref(List<Upgrade> upgradeList) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(PATH);
        gson.toJson(upgradeList.toArray(), fileWriter);
        System.out.println(PATH);
    }

    public static ArrayList readListInPref() throws FileNotFoundException {
        Gson gson = new Gson();
        Upgrade[] list = gson.fromJson(new FileReader(PATH), Upgrade[].class);
        return (ArrayList) Arrays.asList(list);
    }
}
