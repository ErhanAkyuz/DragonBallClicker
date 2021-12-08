package com.example.dragonballclicker;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {
/*    private static String storageFileName = "UserData.bin";

    private int nbPlus;
    private long cookie;
    private long cookiePs;
    private List<Upgrade> upgradeList;

    public void update() {
        this.saveToFile();
    }

    public UserData(int nbPlus, long cookie, long cookiePs, List<Upgrade> upgradeList) {
        this.nbPlus = nbPlus;
        this.cookie = cookie;
        this.cookiePs = cookiePs;
        this.upgradeList = upgradeList;
    }

    private void loadListFromFile() {
        try {
            File myObj = new File(App.getAppContext().getFilesDir(), storageFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File " + myObj.getName() + " already exists.");
            }

            FileInputStream inputStream = new FileInputStream(myObj.getAbsoluteFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            UserData loadedUserData = (UserData) objectInputStream.readObject();
            this.userGameList = loadedUserData.userGameList;
            this.userGameList = new UserGameList(this, loadedUserData.userGameList.getList());
            this.userTagList = new UserTagList(this, loadedUserData.userTagList.getList());

            Log.i("MICHTOS", "Loaded UserGameList with " + userGameList.getGameList().size() + " games inside");
            Log.i("MICHTOS", "Loaded UserTagList with " + userTagList.getList().size() + " tags inside");
            for (com.jv.theque.TagsImplementation.Tag tag : userTagList.getList()) {
                Log.i("MICHTOS", tag.getName() + " " + tag.getGames().size());
            }

        } catch (Exception e) {
            this.userGameList = new UserGameList(this);
            this.userTagList = new UserTagList(this);
        }
        return;
    }

    public void saveToFile() {

        try {
            //modifiée récemment pour essayer d'enlever le App, peut ne pas marcher
            File myObj = new File(MainActivity.getContext().getFilesDir(), storageFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileOutputStream outputStream = new FileOutputStream(myObj.getAbsoluteFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
