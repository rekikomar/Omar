package com.example.win.omar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.SimpleTimeZone;

/**
 * Created by WIN on 26/03/2015.
 */
public class f extends Activity {
    ListView listView;
    private CommentsDataSource datasource;
    String str[], str1[], str2, piece, date1, time;
    String date_actuel1;
    int i;
    SimpleDateFormat formater = null;

    ArrayList<HashMap<String, String>> legendList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f);
        listView = (ListView) findViewById(R.id.mainList);
        datasource = new CommentsDataSource(this);
        datasource.open();
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat("yyyy-M-dd");
        date_actuel1 = formater.format(aujourdhui);
        piece = datasource.selection_comm();
        Log.i("date_actuel1", date_actuel1);
        Log.i("piece", piece);
        if (piece.equals("")) {
            Toast.makeText(getApplicationContext(), "il n'y a pas des factures à ce date: " + date_actuel1, Toast.LENGTH_SHORT).show();
        } else {
            str = piece.split("//");
            str2 = "";
            legendList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                date1 = str1[1];
                time = str1[14];
                if (date1.equals(date_actuel1)) {
                    str2 = str2 + str1[0] + "/" + str1[1] + "/" + str1[2] + "/" + str1[3] + "/" + str1[4] + "/" + str1[5] + "/" + str1[6] + "/" + str1[7] + "/" + str1[8] + "/" + str1[9] + "/" + str1[10] + "/" + str1[11] + "/" + str1[12] + "/" + str1[13] + "/" + str1[14] + "//";
                    map = new HashMap<String, String>();
                    map.put("img1", String.valueOf(R.drawable.f));
                    map.put("titre", str1[4]);
                    map.put("titre1", str1[14]);
                    legendList.add(map);

                }
            }
            SimpleAdapter adapter = new SimpleAdapter(this, legendList, R.layout.legendf,
                    new String[]{"img1", "titre", "titre1"}, new int[]{R.id.img, R.id.titre, R.id.titre1});
            listView.setAdapter(adapter);
            final AlertDialog.Builder boite2;
            boite2 = new AlertDialog.Builder(this);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("position", String.valueOf(position));

                    i = position;
                    boite2.setTitle("Voir facture");
                    boite2.setIcon(R.drawable.numbers);
                    boite2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(f.this, v.class);
                                    intent.putExtra("a", String.valueOf(i));
                                    intent.putExtra("b", str2);
                                    startActivity(intent);

                                }
                            }
                    );
                    boite2.show();

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

