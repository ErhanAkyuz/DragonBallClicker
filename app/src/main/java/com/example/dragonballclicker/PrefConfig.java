package com.example.dragonballclicker;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class PrefConfig implements Serializable {

    private static String filename = "list.xml";
/*
    //éssais d'écriture des données en local
    public static void writeListInPref(List<Upgrade> upgradeList) throws IOException {
        Gson gson = new Gson();
        File file = new File(PATH);
        File filedb = new File(Environment.getExternalStorageDirectory() + "/DragonBallCliker");
        if (!filedb.exists()) {
            filedb.mkdirs();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(PATH);
        gson.toJson(upgradeList.toArray(), fileWriter);
        //System.out.println(PATH);
    }

    public static void writeListInPref(Context context, List<Upgrade> upgradeList) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(upgradeList);
        //Log.i("string", json);
        FileWriter fWriter;
        File sdCardFile = new File(context.getCacheDir()+"/filename.txt");
        //Log.d("TAG", sdCardFile.getPath()); //<-- check the log to make sure the path is correct.
        try{
            fWriter = new FileWriter(sdCardFile, true);
            fWriter.write(json);
            fWriter.flush();
            fWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(PATH);
    }

    //éssais de lecture des données en local
    public static ArrayList readListInPref() throws FileNotFoundException {
        Gson gson = new Gson();
        Upgrade[] list = gson.fromJson(new FileReader(PATH), Upgrade[].class);
        return (ArrayList) Arrays.asList(list);
    }

    /*
    public static boolean createNewFile(Context context) {
        File file = new File(context.getCacheDir(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File didn't exist, so I created it !");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static ArrayList readAncientList(Context context) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(context.getCacheDir() + "/" + filename);
        Upgrade[] list = gson.fromJson(new FileReader(PATH), Upgrade[].class);
        return (ArrayList) Arrays.asList(list);
    }*/

    public static void writer(List<Upgrade> upgradeList, Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput("filename.txt", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        System.out.println(upgradeList.get(0).getNbUp());
        os.writeObject(upgradeList);
        os.close();
        fos.close();
    }

    public static List<Upgrade> reader(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("filename.txt");
        ObjectInputStream is = new ObjectInputStream(fis);
        List<Upgrade> ls = (List<Upgrade>) is.readObject();
        is.close();
        fis.close();
        return ls;
    }
}
