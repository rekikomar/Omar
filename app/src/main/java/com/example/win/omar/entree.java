package com.example.win.omar;

/**
 * Created by WIN on 07/03/2015.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;


public class entree extends Activity {

    private ImageAdapter mAdapter;
    private ArrayList<String> listCountry;
    private ArrayList<Integer> listFlag;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    private GridView gridView;
    private CommentsDataSource datasource;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entree);

        if(lireFichier("test.txt").equals("Bonjour")){
            Log.i("ouverture", "ouverture de fichier avec success");
        }else{
            ecrireFicher("test.txt","Bonjour");
            datasource = new CommentsDataSource(entree.this);
            Log.i("ecriture", "création de fichier avec success");

        }
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 48);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.PM);
        Intent myIntent = new Intent(entree.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(entree.this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        prepareList();
        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.famille);
        Bitmap appel = BitmapFactory.decodeResource(this.getResources(), R.drawable.forniseur);
        Bitmap plan = BitmapFactory.decodeResource(this.getResources(), R.drawable.article);
        Bitmap garage = BitmapFactory.decodeResource(this.getResources(), R.drawable.entree);
        Bitmap refernce1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.commande);
        Bitmap refernce = BitmapFactory.decodeResource(this.getResources(), R.drawable.stock);

        gridArray.add(new Item(homeIcon));
        gridArray.add(new Item(appel));
        gridArray.add(new Item(plan));
        gridArray.add(new Item(garage));
        gridArray.add(new Item(refernce1));
        gridArray.add(new Item(refernce));


        mAdapter = new ImageAdapter(this,listCountry,listFlag,gridArray);

        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if(position==0){
                    Intent i = new Intent(getApplicationContext(), Fam.class);
                    startActivity(i);
                }
                if(position==1){
                    Intent i2 = new Intent(getApplicationContext(), add.class);
                    startActivity(i2);
                }
                if(position==2){
                     Intent i3 = new Intent(getApplicationContext(), article.class);
                    startActivity(i3);

                }
                if(position==3){
                     Intent i4 = new Intent(getApplicationContext(), com.class);
                     startActivity(i4);

                }
                if(position==4){
                    Intent i5 = new Intent(getApplicationContext(), f.class);
                    startActivity(i5);

                }
                if(position==5){
                    Intent i6 = new Intent(getApplicationContext(), stock.class);
                    startActivity(i6);

                }

            }
        });

    }

    public void prepareList()
    {
        listCountry = new ArrayList<String>();

        listCountry.add("Gestion des Famille");
        listCountry.add("Ajouter des Forniseur ");
        listCountry.add("Gestion des Articles");
        listCountry.add("Commande");
        listCountry.add("facture");
        listCountry.add("Stock");

        listFlag = new ArrayList<Integer>();
        listFlag.add(R.drawable.carreau_royge);
        listFlag.add(R.drawable.carreau_orange);
        listFlag.add(R.drawable.carreau_vert);
        listFlag.add(R.drawable.carreau_bleu_ciel);
        listFlag.add(R.drawable.carreau_bleu_marine_c);
        listFlag.add(R.drawable.carreau_bleu_marine);



    }

    private void ecrireFicher(String nomFichier,String monText) {

        BufferedWriter writer = null;

        try {

            File dir = getDir("ToutMesFichiers",MODE_PRIVATE);

            File newfile = new File(dir.getAbsolutePath() + File.separator + nomFichier);

            newfile.createNewFile();

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile)));

            writer.write(monText);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (writer != null) {

                try {

                    writer.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }

    private String lireFichier(String nomFichier) {

        File dir = getDir("ToutMesFichiers",MODE_PRIVATE);

        File newfile = new File(dir.getAbsolutePath() + File.separator + nomFichier);

        String monText="";

        BufferedReader input = null;

        try {

            input = new BufferedReader(new InputStreamReader( new FileInputStream(newfile)));

            String line;

            StringBuffer buffer = new StringBuffer();

            while ((line = input.readLine()) != null) {

                buffer.append(line);

            }

            monText = buffer.toString();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (input != null) {

                try {

                    input.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

        return monText;

    }


}
