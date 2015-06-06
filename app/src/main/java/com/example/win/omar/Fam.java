package com.example.win.omar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WIN on 05/03/2015.
 */
public class Fam extends Activity {

    final Context context = this;
    EditText nom;
    ImageView entrez;
    ListView listView;
    int i;
    ArrayList<HashMap<String, String>> legendList;
    String piece, str[], str1[];
    ArrayList<String> arr_cars = new ArrayList<String>();
    AlertDialog.Builder alertDialogBuilder;

    private CommentsDataSource datasource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.famille);
        datasource = new CommentsDataSource(this);
        nom = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.mainList);
        datasource.open();
        piece = datasource.selection_Famille();

        entrez = (ImageView) findViewById(R.id.button2);
        entrez.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)

            {
                datasource.open();
                datasource.insertFamille(nom.getText().toString());

                Toast.makeText(getApplicationContext(), "insertion effectuée avec succée", Toast.LENGTH_SHORT).show();
                datasource.close();
                Intent i = new Intent(Fam.this, entree.class);
                startActivity(i);
            }
        });
        if (piece.equals("")) {
            listView.setVisibility(View.INVISIBLE);

        } else {
            listView.setVisibility(View.VISIBLE);

            str = piece.split("//");
            legendList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                map = new HashMap<String, String>();
                map.put("img1", String.valueOf(R.drawable.famille));
                map.put("txt", str1[0]);
                map.put("img", String.valueOf(R.drawable.update));
                legendList.add(map);
            }


            SimpleAdapter adapter = new SimpleAdapter(this, legendList, R.layout.list_item,
                    new String[]{"img1", "txt", "img"}, new int[]{R.id.imageView1, R.id.textView, R.id.button});
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("position", String.valueOf(position));

                    i = position;
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompts, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            // get user input and set it to result
                                            // edit text
                                            datasource.open();
                                            datasource.update_byID_fam(userInput.getText().toString(), i + 1);
                                            Intent i = new Intent(Fam.this, entree.class);
                                            startActivity(i);
                                            datasource.close();
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                }

                ;
       /* Alert Dialog Code End*/


                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });


        }
    }
}
