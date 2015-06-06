package com.example.win.omar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by WIN on 10/03/2015.
 */
public class com extends Activity {
    private CommentsDataSource datasource;
    private ImageView btnChangeDate;
    SimpleDateFormat sdf;
    private int year;
    private int month;
    private int day;
    TextView date;
    int i,j,qteS,r,y,qte0;
    String a="";
    static final int DATE_DIALOG_ID = 999;
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private ImageView btnChangeTime;

    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 9999;
    EditText qte;
    String str[],num1,str1[],str2[],num2,str3[],str4[],str5[],str6[],str7[],ref,reff,tva,prix,qte1,refc,id,nom,qtest,piece,forn,nomA,nomf;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comande);
        date = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        setCurrentDateOnView();
        addListenerOnButton();
        setCurrentTimeOnView();
        addListenerOnButton1();
        qte = (EditText) findViewById(R.id.editText11);
        datasource = new CommentsDataSource(this);
        datasource.open();
        piece = datasource.selection_Article();
        Log.i("piece", piece);

        forn = datasource.selection_Forniseur();

        if (piece.equals("") || forn.equals("")) {
            Toast.makeText(getApplicationContext(), "Aucun Articles ou Forniseurs", Toast.LENGTH_LONG).show();


        } else {
            str = piece.split("//");
            num1 = "";
            ArrayList<String> years = new ArrayList<String>();
            for (int i = 0; i < str.length; i++) {
                str1 = str[i].split("/");
                num1 = num1 + str1[0] + "/" + str1[1] + "/" + str1[2] + "/" + str1[3] + "/" + str1[4] + "/" + str1[5] + "/" + str1[6] + "/" + str1[7]+"//";
                years.add(str1[3]);

            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, years);
            final Spinner spinYear = (Spinner) findViewById(R.id.spinnerA);
            spinYear.setAdapter(adapter1);
            spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {

                    i = position;
                    nomA= spinYear.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });

            str2 = forn.split("//");
            num2 = "";
            ArrayList<String> years1 = new ArrayList<String>();
            for (int i = 0; i < str2.length; i++) {
                str3 = str2[i].split("/");
                num2 = num2 + str3[0] + "/" + str3[1] + "/" + str3[2] + "/" + str3[3] + "/" + str3[4] + "/" + str3[5] + "/" + str3[6] + "/" + str3[7] + "/" + str3[8] + "/" + str3[9]+ "/" + str3[10]+ "//";
                years1.add(str3[2]);

            }
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, years1);
            final Spinner spinYear1 = (Spinner) findViewById(R.id.spinnerF);
            spinYear1.setAdapter(adapter2);
            spinYear1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {

                    j = position;
                    nomf= spinYear1.getItemAtPosition(j).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });

            btnChangeDate = (ImageView) findViewById(R.id.button2);
            btnChangeDate.setVisibility(View.VISIBLE);

            ImageView suivant = (ImageView) findViewById(R.id.suivantrapport);

            final AlertDialog.Builder boite2;
            boite2 = new AlertDialog.Builder(this);

            suivant.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    boite2.setTitle("Ajouter");
                    boite2.setIcon(R.drawable.stockicon);
                    boite2.setMessage("Faite votre choix!");
                    boite2.setPositiveButton("Autres produits", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    str4 = num1.split("//");
                                    str5 = str4[i].split("/");
                                    ref = str5[1];
                                    refc = str5[2];
                                    nom = str5[3];
                                    id = str5[4];
                                    tva = str5[5];
                                    prix = str5[6];
                                    str6 = num2.split("//");
                                    str7 = str6[j].split("/");
                                    reff = str7[0];

                                    qte1 = qte.getText().toString();

                                    a = a + date.getText().toString() + "/" + str5[7] + "/" + qte1 + "/" + str7[2] + "/" + str5[0] + "/" + str5[1] + "/" + prix + "/" + tva + "/" + nomA+ "/"+id+"/" +tvDisplayTime.getText().toString()+"/";
                                    Intent intent = new Intent(com.this, autre.class);
                                    intent.putExtra("a", a);
                                    startActivity(intent);

                                }
                            }
                    );
                    boite2.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {

                                    Log.i("num1", num1);
                                    Log.i("num2", num2);
                                    str4 = num1.split("//");
                                    str5 = str4[i].split("/");
                                    if (Integer.parseInt(str5[7]) == 0) {
                                        Toast.makeText(getApplicationContext(), "vérifer votre article", Toast.LENGTH_SHORT).show();

                                    } else {
                                        ref = str5[1];
                                        refc = str5[2];
                                        nom = str5[3];
                                        id = str5[4];
                                        tva = str5[5];
                                        prix = str5[6];
                                        str6 = num2.split("//");
                                        str7 = str6[j].split("/");
                                        reff = str7[0];

                                        y = Integer.parseInt(str5[7]);
                                        qte0 = Integer.parseInt(qte.getText().toString());
                                        if (y > qte0) {
                                            r = 0;
                                            qteS = y - qte0;
                                            Log.i("r", String.valueOf(r));
                                            Log.i("qteS", String.valueOf(qteS));

                                        } else if (y == qte0) {
                                            qteS = 0;
                                            r = 0;
                                            Log.i("r", String.valueOf(r));
                                            Log.i("qteS", String.valueOf(qteS));

                                        } else {
                                            qteS = 0;
                                            r = qte0 - y;
                                            Log.i("r", String.valueOf(r));
                                            Log.i("qteS", String.valueOf(qteS));


                                        }
                                        datasource.open();
                                        int prixttc = ((Integer.parseInt(prix) * Integer.parseInt(tva)) / 100) + Integer.parseInt(prix);
                                        datasource.insert_Commande(date.getText().toString(), str5[7], qte.getText().toString(), str7[2], str5[0], str5[1], prix, tva, String.valueOf(r), String.valueOf(prixttc), String.valueOf(qteS), nomA, Integer.parseInt(id),tvDisplayTime.getText().toString());
                                        datasource.update_byID_piece(i + 1, ref, refc, nom, Integer.parseInt(id), tva, Integer.parseInt(prix), qteS);
                                        Intent i = new Intent(com.this, entree.class);
                                        startActivity(i);
                                        datasource.close();
                                    }
                                }
                            }

                    );
                    boite2.show();


                }

            });


    }
    }


    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        // set current date into textview


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvDisplayDate.setText(new StringBuilder()

                .append(year).append("-").append(month+1).append("-")
                .append(day));


        // set current date into datepicker
        dpResult.updateDate(year, month, day);

    }

    public void addListenerOnButton() {

        btnChangeDate = (ImageView) findViewById(R.id.button2);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


            tvDisplayDate.setText(new StringBuilder()

                    .append(year).append("-").append(month+1).append("-")
                    .append(day));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };
    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(new StringBuilder().append(pad(hour)).append(":")
                .append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);

    }

    public void addListenerOnButton1() {

        btnChangeTime = (ImageView) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }


    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour,
                              int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            // set current time into textview
            tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                    .append(":").append(pad(minute)));

            // set current time into timepicker
            timePicker1.setCurrentHour(hour);
            timePicker1.setCurrentMinute(minute);

        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}

