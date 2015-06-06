package com.example.win.omar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by WIN on 04/03/2015.
 */
public class article extends Activity {

    private CommentsDataSource datasource;
    String str[],str1[],str2[],str3[],str4[],numfor[],strf[],numfam[],num,num1,piece;
    EditText Ref,Refc,des,prix,quantite;
    Spinner spin;
    ImageView entrez,entrez1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        datasource = new CommentsDataSource(this);
        datasource.open();
        Ref = (EditText) findViewById(R.id.editText4);
        Refc = (EditText) findViewById(R.id.editText5);
        des = (EditText) findViewById(R.id.editText6);
        prix = (EditText) findViewById(R.id.editText10);
        quantite = (EditText) findViewById(R.id.editText11);

        piece = datasource.selection_Famille();

        if (piece.equals("")) {
            Toast.makeText(getApplicationContext(), "Inserer au moin 1 famille pour les articles", Toast.LENGTH_LONG).show();

        } else {
            str = piece.split("//");
            num1 = "";
            ArrayList<String> years = new ArrayList<String>();
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                num1 = num1 + str1[0] + "/" + str1[1] + "//";
                years.add(str1[0]);
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, years);
            final Spinner spinYear = (Spinner) findViewById(R.id.spinner);
            spinYear.setAdapter(adapter1);

            spin = (Spinner) findViewById(R.id.spinner3);

            entrez = (ImageView) findViewById(R.id.button3);
            entrez.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v)

                {


                    int famille = spinYear.getSelectedItemPosition();
                    strf = num1.split("//");
                    numfam = strf[famille].split("/");
                    Log.i("numfam", numfam[1]);


                    datasource.insert_Article(Ref.getText().toString(), Refc.getText().toString(), des.getText().toString(), Integer.parseInt(numfam[1]), spin.getSelectedItem().toString(), Integer.parseInt(prix.getText().toString()), Integer.parseInt(quantite.getText().toString()));
                    Toast.makeText(getApplicationContext(), "insertion effectuée avec succée", Toast.LENGTH_SHORT).show();
                    datasource.close();
                    Intent i = new Intent(article.this, entree.class);
                    startActivity(i);
                }
            });
            entrez1 = (ImageView) findViewById(R.id.button4);
            entrez1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v)

                {

                    Intent i = new Intent(article.this, updateA.class);
                    startActivity(i);
                }
            });

        }
    }


    }



