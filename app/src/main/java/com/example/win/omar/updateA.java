package com.example.win.omar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WIN on 24/03/2015.
 */
public class updateA extends Activity {
    ListView listView;
    final Context context = this;
    int i;
    private CommentsDataSource datasource;
    String str[],str1[],piece;
    EditText inputSearch;
    SimpleAdapter adapter;
    ArrayList<HashMap<String, String>> legendList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatearticle);
        listView = (ListView) findViewById(R.id.mainList);
        datasource = new CommentsDataSource(this);
        datasource.open();
        piece = datasource.selection_Article();
        if (piece.equals("")) {
            Toast.makeText(getApplicationContext(), "il y a aucun article disponible", Toast.LENGTH_SHORT).show();

        } else {
            Log.i("piece", piece);
            str = piece.split("//");
            legendList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                map = new HashMap<String, String>();
                map.put("img1", String.valueOf(R.drawable.article));
                map.put("titre", str1[3]);
                map.put("description", str1[7]);
                map.put("img2", String.valueOf(R.drawable.update));
                legendList.add(map);
            }


            adapter = new SimpleAdapter(this, legendList, R.layout.list_item1,
                    new String[]{"img1", "titre", "description", "img2"}, new int[]{R.id.imageView1, R.id.titre, R.id.description, R.id.button});
            listView.setAdapter(adapter);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("position", String.valueOf(position));

                    i = position;
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.prompts1, null);

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
                                            str1 = str[i].split("/");
                                            int y = Integer.parseInt(str1[7]) + Integer.parseInt(userInput.getText().toString());
                                            datasource.update_byID_piece(i + 1, str1[1], str1[2], str1[3], Integer.parseInt(str1[4]), str1[5], Integer.parseInt(str1[6]), y);
                                            Intent i = new Intent(updateA.this, entree.class);
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


            inputSearch = (EditText) findViewById(R.id.inputSearch);
            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });

        }
    }
}
