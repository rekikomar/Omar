package com.example.win.omar;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by WIN on 08/03/2015.
 */
public class add extends Activity {

    EditText num,nom,adresse,postal,Contact,Tel,mobile,fax,email;
    ImageView entrez,entrez1;
    Spinner fromVille;
    private CommentsDataSource datasource;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forniseur);

        num = (EditText) findViewById(R.id.editText4);
        nom = (EditText) findViewById(R.id.editText5);
        adresse = (EditText) findViewById(R.id.editText6);
        postal = (EditText) findViewById(R.id.editText7);
        Contact = (EditText) findViewById(R.id.editText8);
        Tel = (EditText) findViewById(R.id.editText9);
        mobile = (EditText) findViewById(R.id.editText10);
        fax = (EditText) findViewById(R.id.editText11);
        email = (EditText) findViewById(R.id.editText12);
        fromVille = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter0 = ArrayAdapter.createFromResource(this,
                R.array.ville, android.R.layout.simple_spinner_item);
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromVille.setAdapter(adapter0);

        datasource = new CommentsDataSource(this);
        datasource.open();
        String piece=datasource.selection_Forniseur();
        Log.i("piece", piece);
        entrez = (ImageView) findViewById(R.id.button3);
        entrez.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)

            {


                String Ville= fromVille.getSelectedItem().toString();
                datasource.insert_forniseur(num.getText().toString(),nom.getText().toString(),adresse.getText().toString(),postal.getText().toString(),Ville,Contact.getText().toString(),Integer.parseInt(Tel.getText().toString()),Integer.parseInt(mobile.getText().toString()),Integer.parseInt(fax.getText().toString()),email.getText().toString());
                Toast.makeText(getApplicationContext(), "insertion effectuée avec succée", Toast.LENGTH_SHORT).show();
                datasource.close();
                Intent i = new Intent(add.this, entree.class);
                startActivity(i);
            }
        });
        entrez1 = (ImageView) findViewById(R.id.button4);
        entrez1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)

            {

                Intent i = new Intent(add.this, list.class);
                startActivity(i);
            }
        });

    }
}