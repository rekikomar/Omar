package com.example.win.omar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by WIN on 24/03/2015.
 */
public class stock extends Activity {

    private CommentsDataSource datasource;
    String str[],str1[],resultat,resultat1,str2[],f,num,str3[],nomf,str4[];
    ArrayList<HashMap<String, String>> legendList;
    ListView listView;
    SimpleAdapter adapter;
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock);
        listView = (ListView) findViewById(R.id.mainList);

        datasource = new CommentsDataSource(this);
        datasource.open();
        resultat = datasource.selection_comm();
        Log.i("r", resultat);
        if (resultat.equals("")) {
            Toast.makeText(getApplicationContext(), "votre stock est vide", Toast.LENGTH_SHORT).show();

        } else {
            str = resultat.split("//");
            legendList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            for (int k= 0; k < str.length; k++) {
                str1 = str[k].split("/");
                nomf=datasource.selection_F(Integer.parseInt(str1[13]));
                map = new HashMap<String, String>();
                map.put("img1", String.valueOf(R.drawable.stock));
                map.put("titre", str1[6]);
                map.put("titre1", str1[12]);
                map.put("titre2",  str1[11]);//gteStock
                map.put("titre3", str1[3]);
                map.put("titre4", str1[7]);
                map.put("titre5", nomf);
                map.put("titre6", str1[1]);
                map.put("titre7", str1[4]);
                legendList.add(map);
            }


            adapter = new SimpleAdapter(this, legendList, R.layout.legend,
                    new String[]{"img1", "titre", "titre1", "titre2", "titre3", "titre4", "titre5", "titre6", "titre7"}, new int[]{R.id.img, R.id.titre, R.id.titre1, R.id.titre2, R.id.titre3, R.id.titre4, R.id.titre5, R.id.titre6, R.id.titre7});
            listView.setAdapter(adapter);

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

