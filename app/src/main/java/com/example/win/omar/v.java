package com.example.win.omar;

import android.app.Activity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by WIN on 27/03/2015.
 */
public class v extends Activity {
    Bundle bundle;
    TextView nom,date;
    String a,b,resultat,str[],str1[],str2[],str3[];
    private CommentsDataSource datasource;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v);
        nom = (TextView) findViewById(R.id.textView13);
        date = (TextView) findViewById(R.id.textView14);

        bundle = getIntent().getExtras();
        a = bundle.getString("a");
        b = bundle.getString("b");
        Log.i("a", a);
        Log.i("b", b);
        datasource = new CommentsDataSource(this);
        datasource.open();
        resultat = datasource.selection_comm();
        str = resultat.split("//");
        str1 = str[Integer.parseInt(a)].split("/");
        Log.i("qn", str1[3]);
        str2 = b.split("/");
        Log.i("time", str2[Integer.parseInt(a)]);
        Log.i("time1", str1[14]);

        if (str2[Integer.parseInt(a)].equals(str1[14])) {

            date.setText(str1[1]);
            nom.setText("FACTURE "+str1[0]);
            String[] columns = new String[]{"_id", "Article", "quantite", "PHT", "PTTC","Total"};

            MatrixCursor matrixCursor = new MatrixCursor(columns);
            startManagingCursor(matrixCursor);
            matrixCursor.addRow(new Object[]{1, "Article", "quantite", "PHT", "PTTC","Total"});
            matrixCursor.addRow(new Object[]{2, str1[12], str1[3], str1[7], str1[10],Integer.parseInt(str1[10])*Integer.parseInt(str1[3])});

            String[] from = new String[]{"Article", "quantite", "PHT", "PTTC","Total"};

            int[] to = new int[]{R.id.textViewCol1, R.id.textViewCol2, R.id.textViewCol3, R.id.textViewCol4,R.id.textViewCol5};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor, from, to, 0);

            ListView lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(adapter);
        }
    }
}
