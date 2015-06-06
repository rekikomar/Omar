package com.example.win.omar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WIN on 24/03/2015.
 */
public class list extends Activity {
    ListView listView;
    private CommentsDataSource datasource;
    String str[],str1[],piece;
    ArrayList<HashMap<String, String>> legendList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView = (ListView) findViewById(R.id.mainList);
        datasource = new CommentsDataSource(this);
        datasource.open();
        piece = datasource.selection_Forniseur();
        if (piece.equals("")) {
            Toast.makeText(getApplicationContext(), "il y a aucun forniseur disponible", Toast.LENGTH_SHORT).show();

        } else {
            Log.i("piece", piece);
            str = piece.split("//");
            legendList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                map = new HashMap<String, String>();
                map.put("img1", String.valueOf(R.drawable.forniseur));
                map.put("titre", str1[2]);
                map.put("description", str1[7]);
                map.put("description1", str1[10]);
                legendList.add(map);
            }


            SimpleAdapter adapter = new SimpleAdapter(this, legendList, R.layout.legend_row_item,
                    new String[]{"img1", "titre", "description", "description1"}, new int[]{R.id.img, R.id.titre, R.id.description, R.id.description1});
            listView.setAdapter(adapter);
        }
    }
}
