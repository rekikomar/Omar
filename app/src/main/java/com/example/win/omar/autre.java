package com.example.win.omar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by WIN on 21/03/2015.
 */
public class autre extends Activity {
    Bundle bundle;
    String a,b;
    private CommentsDataSource datasource;
    EditText qte;
    int i,r,qteS,r1,qteS1,rp1,rp2,qteS2;
    String str[],num1,str1[],nom,str2[],str4[],str5[],qte0,nomf,qtes,qtecomm,reste,str02[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autre);
        bundle = getIntent().getExtras();
        a = bundle.getString("a");
        Log.i("a", a);
        qte = (EditText) findViewById(R.id.editText11);
        datasource = new CommentsDataSource(this);
        datasource.open();
        String piece=datasource.selection_Article();
        Log.i("piece",piece);
        str=piece.split("//");
        num1="";
        ArrayList<String> years = new ArrayList<String>();
        for (int i = 0; i < str.length; i++)
        {
            str1=str[i].split("/");
            num1=num1 + str1[0]+"/"+str1[1]+"/"+str1[2]+"/"+str1[3]+"/"+str1[4]+"/"+str1[5]+"/"+str1[6]+"/"+str1[7]+"//";
            years.add(str1[3]);

        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, years);
        final Spinner spinYear = (Spinner)findViewById(R.id.spinnerA);
        spinYear.setAdapter(adapter1);
        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {

                i = position;
                nom = spinYear.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        ImageView suivant = (ImageView) findViewById(R.id.suivantrapport);
        suivant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
           str02=a.split("/");
           qte0=str02[1];
        if(str02[8].equals(nom)){

                int y = Integer.parseInt(qte.getText().toString()) + Integer.parseInt(str02[2]);
                if (y > Integer.parseInt(qte0)) {
                    r = y - Integer.parseInt(qte0);
                    qteS = 0;
                    Log.i("qteS", String.valueOf(qteS));
                    Log.i("r", String.valueOf(r));
                } else if (y == Integer.parseInt(qte0)) {
                    qteS = 0;
                    r = 0;
                    Log.i("r", String.valueOf(r));
                    Log.i("qteS", String.valueOf(qteS));


                } else {
                    qteS = Integer.parseInt(qte0) - y;
                    r = 0;
                    Log.i("qteS", String.valueOf(qteS));
                    Log.i("r", String.valueOf(r));
                }
                datasource.open();
                int prixttc = ((Integer.parseInt(str02[6]) * Integer.parseInt(str02[7])) / 100) + Integer.parseInt(str02[6]);//,str02[9]
                datasource.insert_Commande(str02[0], str02[1], String.valueOf(y), str02[3], str02[4], str02[5], str02[6], str02[7], String.valueOf(r), String.valueOf(prixttc), String.valueOf(qteS), str02[8], Integer.parseInt(str02[9]),str02[10]);
                datasource.update_byID_piece(i + 1, str02[2], str02[3], nom, Integer.parseInt(str02[4]), str02[7], Integer.parseInt(str02[6]), qteS);
                Intent i = new Intent(autre.this, entree.class);
                startActivity(i);

                datasource.close();

        }else {

            str2 = a.split("/");
            str4 = num1.split("//");
            str5 = str4[i].split("/");
            int y = Integer.parseInt(str5[7]);
            int qte0 = Integer.parseInt(qte.getText().toString());
            if (y > qte0) {
                rp1 = 0;
                qteS1 = y - qte0;
            } else if (y == qte0) {
                qteS1 = 0;
                rp1 = 0;
            } else {
                qteS1 = 0;
                rp1 = qte0 - y;
            }
            Log.i("r1", String.valueOf(r1));
            Log.i("qteS1", String.valueOf(qteS1));
            int y1 = Integer.parseInt(str2[1]);
            int qte01 = Integer.parseInt(str2[2]);
            if (y1 > qte01) {
                rp2 = 0;
                qteS2 = y1 - qte01;
            } else if (y1 == qte01) {
                qteS2 = 0;
                rp2 = 0;
            } else {
                qteS2 = 0;
                rp2 = qte01 - y1;
            }

            Log.i("rp2", String.valueOf(rp2));
            Log.i("qteS2", String.valueOf(qteS2));
            datasource.open();
            int prixttcp1 = ((Integer.parseInt(str5[6]) * Integer.parseInt(str5[5])) / 100) + Integer.parseInt(str5[6]);
            int prixttcp2 = ((Integer.parseInt(str2[6]) * Integer.parseInt(str2[7])) / 100) + Integer.parseInt(str2[6]);
            datasource.insert_Commande(str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], str2[6], str2[7], String.valueOf(rp2), String.valueOf(prixttcp2), String.valueOf(qteS2), str2[8], Integer.parseInt(str2[9]),str2[10]);
            datasource.insert_Commande(str2[0], str5[7], qte.getText().toString(), str2[3], str5[0], str5[1], str5[6], str5[5], String.valueOf(rp1), String.valueOf(prixttcp1), String.valueOf(qteS1), nom, Integer.parseInt(str5[4]),str2[10]);
            datasource.update_byID_piece(Integer.parseInt(str2[4]), str2[2], str2[3], str2[8], Integer.parseInt(str2[4]), str2[7], Integer.parseInt(str2[6]), qteS2);
            datasource.update_byID_piece(i + 1, str5[1], str5[2], nom, Integer.parseInt(str5[4]), str5[5], Integer.parseInt(str5[6]), qteS1);
            Intent i = new Intent(autre.this, entree.class);
            startActivity(i);
            datasource.close();

            }
        }

        //qte.getText().toString();

        });



    }
}
